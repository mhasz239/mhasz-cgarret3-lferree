package edu.ycp.cs320.middle_earth.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import persist.dbmod.IntPair;
import persist.dbmod.ObjectIDCommandResponse;
import persist.dbmod.StringPair;
import persist.dbmod.User;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	/************************************************************************************************
	 * 										Loading Methods
	 * **********************************************************************************************/
	
	private void loadItem(Item item, ResultSet resultSet, int index) throws SQLException {
		item.setID(resultSet.getInt(index++));
		
		item.setName(resultSet.getString(index++));
		item.setLongDescription(resultSet.getString(index++));
		item.setShortDescription(resultSet.getString(index++));
		
		item.setdescription_update(resultSet.getString(index++));
		item.setattack_bonus(resultSet.getInt(index++));
  		item.setdefense_bonus(resultSet.getInt(index++));
  		item.sethp_bonus(resultSet.getInt(index++));	

		item.setItemWeight(resultSet.getFloat(index++));	
		item.setItemType(ItemType.valueOf(resultSet.getString(index++))); 
		item.setlvl_requirement(resultSet.getInt(index++));
	}
	
	private void loadObject(Object object, ResultSet resultSet, int index) throws SQLException {
		object.setID(resultSet.getInt(index++));
		object.setName(resultSet.getString(index++));
		object.setLongDescription(resultSet.getString(index++));
		object.setShortDescription(resultSet.getString(index++));
	}
	
	private void loadObjectCommandResponse(HashMap<String, String> objectCommandResponse, ResultSet resultSet, int index) {
		try {
			objectCommandResponse.put(resultSet.getString(index++), resultSet.getString(index++));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void loadMapTileConnections(HashMap<String, Integer> mapTileConnections, ResultSet resultSet, int index) throws SQLException {
		mapTileConnections.put("north", resultSet.getInt(index++));
		mapTileConnections.put("northeast", resultSet.getInt(index++));
		mapTileConnections.put("east", resultSet.getInt(index++));
		mapTileConnections.put("southeast", resultSet.getInt(index++));
		mapTileConnections.put("south", resultSet.getInt(index++));
		mapTileConnections.put("southwest", resultSet.getInt(index++));
		mapTileConnections.put("west", resultSet.getInt(index++));
		mapTileConnections.put("northwest", resultSet.getInt(index++));
	}
	
	private void loadMapTile(MapTile mapTile, ResultSet resultSet, int index) throws SQLException {
		mapTile.setID(resultSet.getInt(index++));
		mapTile.setName(resultSet.getString(index++));
		mapTile.setLongDescription(resultSet.getString(index++));
		mapTile.setShortDescription(resultSet.getString(index++));		
		mapTile.setAreaDifficulty(resultSet.getInt(index++));
	}
	 
	private void loadMap(Map map, ResultSet resultSet, int index) throws SQLException {
		map.setID(resultSet.getInt(index++));
		map.setName(resultSet.getString(index++));
		map.setLongDescription(resultSet.getString(index++));
		map.setShortDescription(resultSet.getString(index++));	
	}
	
	private void loadEnemy(Enemy enemy, ResultSet resultSet, int index) throws SQLException {
		enemy.setrace(resultSet.getString(index++));
		enemy.sethit_points(resultSet.getInt(index++));
		enemy.setmagic_points(resultSet.getInt(index++));
		
		StringPair randNameGender = getRandomName();
		
		enemy.setname(randNameGender.getString1());
		enemy.setgender(randNameGender.getString2());
		enemy.setattack(resultSet.getInt(index++));
		enemy.setdefense(resultSet.getInt(index++));
		enemy.setspecial_attack(resultSet.getInt(index++));
		enemy.setspecial_defense(resultSet.getInt(index++));		
	}
	
	private void loadNameGender(StringPair nameGender, ResultSet resultSet, int index) throws SQLException {
		nameGender.setString1(resultSet.getString(index++));
		nameGender.setString2(resultSet.getString(index++));
	}
	
	private void loadPlayer(Player player, ResultSet resultSet, int index) {
		try {
		player.setrace(resultSet.getString(index++));
		player.setname(resultSet.getString(index++));
		player.setgender(resultSet.getString(index++));
		player.setlevel(resultSet.getInt(index++));
		player.sethit_points(resultSet.getInt(index++));
		
		player.setmagic_points(resultSet.getInt(index++));
		player.setattack(resultSet.getInt(index++));
		player.setdefense(resultSet.getInt(index++));
		player.setspecial_attack(resultSet.getInt(index++));
		player.setspecial_defense(resultSet.getInt(index++));
		
		player.setcoins(resultSet.getInt(index++));
		player.setlocation(resultSet.getInt(index++));
		player.setinventory_id(resultSet.getInt(index++));

		int carryWeight = 0;
		player.setinventory(getInventoryByID(player.getinventory_id()));
		
		// Sum up inventory weight
		for(Item item : player.getinventory().getitems()) {
			carryWeight += item.getItemWeight();
		}

		Item emptyItemSlot = new Item();
		emptyItemSlot.setattack_bonus(0);
		emptyItemSlot.setdefense_bonus(0);
		emptyItemSlot.setdescription_update("You haven't equipped one");
		emptyItemSlot.sethp_bonus(0);
		emptyItemSlot.setlvl_requirement(0);
		emptyItemSlot.setID(0);
		emptyItemSlot.setIsQuestItem(false);
		emptyItemSlot.setItemWeight(0);
		emptyItemSlot.setLongDescription("Empty Slot");
		emptyItemSlot.setShortDescription("Empty Slot");
		emptyItemSlot.setName("Empty Slot");

		int itemID;
		
		// helm
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.setItemType(ItemType.HELM);
			player.sethelm(emptyItemSlot);
		} else {
			player.sethelm(getItemByID(itemID));
			carryWeight += player.gethelm().getItemWeight();
		}
		
		// braces
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.setItemType(ItemType.BRACES);
			player.setbraces(emptyItemSlot);
		} else {
			player.setbraces(getItemByID(itemID));
			carryWeight += player.getbraces().getItemWeight();
		}		
		
		// chest
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.setItemType(ItemType.CHEST);
			player.setchest(emptyItemSlot);
		} else {
			player.setchest(getItemByID(itemID));
			carryWeight += player.getchest().getItemWeight();
		}
		
		// legs
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.setItemType(ItemType.LEGS);
			player.setlegs(emptyItemSlot);
		} else {
			player.setlegs(getItemByID(itemID));
			carryWeight += player.getlegs().getItemWeight();
		}
		
		// boots
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.setItemType(ItemType.BOOTS);
			player.setboots(emptyItemSlot);
		} else {
			player.setboots(getItemByID(itemID));
			carryWeight += player.getboots().getItemWeight();
		}
		
		// l_hand
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.setItemType(ItemType.L_HAND);
			player.setl_hand(emptyItemSlot);
		} else {
			player.setl_hand(getItemByID(itemID));
			carryWeight += player.getl_hand().getItemWeight();
		}
		
		// r_hand
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.setItemType(ItemType.R_HAND);
			player.setr_hand(emptyItemSlot);
		} else {
			player.setr_hand(getItemByID(itemID));
			carryWeight += player.getr_hand().getItemWeight();
		}
		
		player.setcarry_weight(index++);
		player.setexperience(resultSet.getInt(index++));
		
		// Add the sum total of weight to the inventory
		player.getinventory().setweight(carryWeight);
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**************************************************************************************************
	 * 										Building the Database
	 **************************************************************************************************/
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt0 = null;		// users table
				PreparedStatement stmt1 = null;		// items table
				PreparedStatement stmt2 = null;		// objects table
				PreparedStatement stmt3 = null;		// itemstoobjects table
				PreparedStatement stmt4 = null;		// maptileconnections table
				PreparedStatement stmt5 = null;		// maptiles table
				PreparedStatement stmt6 = null;		// objectstomaptiles table
				PreparedStatement stmt7 = null;		// itemstoinventories table 
				PreparedStatement stmt8 = null;		// players table
				PreparedStatement stmt9 = null; 	// objectcommandresponses table
				PreparedStatement stmt10 = null;	// maps table
				PreparedStatement stmt11 = null;	// maptilestomaps table
				PreparedStatement stmt12 = null;	// enemies table
				PreparedStatement stmt13 = null;	// names table
				PreparedStatement stmt14 = null;	// gamestousers table
				PreparedStatement stmt15 = null;	// mapstogames table
				PreparedStatement stmt16 = null;	// playerstogames table
				PreparedStatement stmt17 = null;	// inventoriestoplayers table
													//	quests table				
				try {
					stmt0 = conn.prepareStatement(
							"create table users (" +
							" user_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							" username varchar(100), " +
							" password varchar(100), " +
							" email varchar(100))"
					);
					stmt0.executeUpdate();
					
					stmt1 = conn.prepareStatement(
							"create table items (" + 
							"	item_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	itemname varchar(40)," + 
							"	longdescription varchar(200)," +
							"	shortdescription varchar(100)," +
							"	descriptionupdate varchar(100)," +
							"	attackbonus int," +
							"	defensebonus int," +
							"	hpbonus int," +
							"   weight float," +
							"   itemtype varchar(20)," +
							"	levelreq int" +
							")"
					);
					stmt1.executeUpdate();
					
					stmt2 = conn.prepareStatement(
							"create table objects (" +
							"	object_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	objectname varchar(40)," + 
							"	longdescription varchar(200)," +
							"	shortdescription varchar(100)" +
							")"
					);
					stmt2.executeUpdate();
					
					stmt3 = conn.prepareStatement(
							"create table itemstoobjects (" +
							"   item_id int," +
							"   object_id int" +
							")"
					);
					stmt3.executeUpdate();
					
					stmt4 = conn.prepareStatement(
							"create table maptileconnections (" +
							"   maptile_id integer primary key " +
							"       generated always as identity (start with 1, increment by 1), " +

							"   north int," +
							"	northeast int," +
							"   east int," +							
							"   southeast int," +							
							"   south int," +							
							"   southwest int," +							
							"   west int," +							
							"   northwest int" +
							")"
					);
					stmt4.executeUpdate();
					
					stmt5 = conn.prepareStatement(
							"create table maptiles (" +
							"   maptile_id integer primary key " +
							"       generated always as identity (start with 1, increment by 1), " +
							"   maptilename varchar(40)," +							
							"	longdescription varchar(200)," +
							"	shortdescription varchar(100)," +
							"	difficulty int" +
							")"
					);
					stmt5.executeUpdate();
					
					stmt6 = conn.prepareStatement(
							"create table objectstomaptiles (" +
							"	object_id int, " +
							"   maptile_id int" +
							")"
					);
					stmt6.executeUpdate();
					
					stmt7 = conn.prepareStatement(
							"create table itemstoinventories ("
							+ "item_id int, "
							+ "inventory_id int"
							+ ")"
					);
					stmt7.executeUpdate();	
					
					stmt8 = conn.prepareStatement(
							"create table players ("
							+ "race varchar(40),"
							+ "name varchar(40),"
							+ "gender varchar(40),"
							+ "level int,"
							+ "hit_points int,"
							
							+ "magic_points int,"
							+ "attack int,"
							+ "defense int,"
							+ "sp_attack int,"
							+ "sp_defense int,"
							
							+ "coins int,"
							+ "map_location int,"
							+ "inventory_id int,"
							+ "helm_item_id int,"
							+ "braces_item_id int,"
							
							+ "chest_item_id int,"
							+ "legs_item_id int,"
							+ "boots_item_id int,"
							+ "l_hand_item_id int,"
							+ "r_hand_item_id int,"
							
							+ "experience int,"
							+ "carry_weight int"
							+ ")"
					);
					stmt8.executeUpdate();
					
					stmt9 = conn.prepareStatement(
							"create table objectcommandresponses ("
							+ "object_id int, "
							+ "command varchar(10), "
							+ "response varchar (100)"
							+ ")"
					);					
					stmt9.executeUpdate();
					
					stmt10 = conn.prepareStatement(
							"create table maps (" +
							"   map_id integer primary key " +
							"       generated always as identity (start with 1, increment by 1), " +
							"   mapname varchar(40)," +							
							"	longdescription varchar(200)," +
							"	shortdescription varchar(100)" +
							")"
					);
					stmt10.executeUpdate();
					
					stmt11 = conn.prepareStatement(
							"create table maptilestomaps ("
							+ "maptile_id int, "
							+ "map_id int "
							+ ")"
					);
					stmt11.executeUpdate();
					
					stmt12 = conn.prepareStatement(
							"create table enemies ("
							+ "race varchar(40), "
							+ "hp int, "
							+ "mp int, "
							+ "attack int, "
							+ "defense int, "
							+ "sp_atk int, "
							+ "sp_def int "
							+ ")"
					);
					stmt12.executeUpdate();
					
					stmt13 = conn.prepareStatement(
							"create table names ("
							+ "number int primary key "
							+ "		generated always as identity (start with 1, increment by 1), "
							+ "name varchar(40), "
							+ "gender varchar(10)"
							+ ")"
					);
					stmt13.executeUpdate();
					
					stmt14 = conn.prepareStatement(
							"create table gamestousers ("
							+ "game_id int primary key "
							+ "		generated always as identity (start with 1, increment by 1), "
							+ "username varchar(40)"
							+ ")"
					);
					stmt14.executeUpdate();
					
					stmt15 = conn.prepareStatement(
							"create table mapstogames ("
							+ "map_id int, "
							+ "game_id int"
							+ ")"
					);
					stmt15.executeUpdate();
					
					stmt16 = conn.prepareStatement(
							"create table playerstogames ("
							+ "playername varchar(100), "
							+ "game_id int"
							+ ")"
					);
					stmt16.executeUpdate();
					
/*					stmt17 = conn.prepareStatement(
							"create table inventoriestoplayers ("
							+ "playername varchar(100)"
*/							
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt0);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
					DBUtil.closeQuietly(stmt7);
					DBUtil.closeQuietly(stmt8);
					DBUtil.closeQuietly(stmt9);
					DBUtil.closeQuietly(stmt10);
					DBUtil.closeQuietly(stmt11);
					DBUtil.closeQuietly(stmt12);
					DBUtil.closeQuietly(stmt13);		
					DBUtil.closeQuietly(stmt14);
					DBUtil.closeQuietly(stmt15);
					DBUtil.closeQuietly(stmt16);
					DBUtil.closeQuietly(stmt17);
				}
			}
		});
	}
		
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				ArrayList<Item> itemList;
				ArrayList<Object> objectList;
				ArrayList<IntPair> itemsToObjectsList;
				ArrayList<ObjectIDCommandResponse> objectCommandResponseList;
				ArrayList<HashMap<String, Integer>> mapTileConnectionsList;
				ArrayList<MapTile> mapTileList;
				ArrayList<IntPair> objectsToMapTilesList;
				ArrayList<IntPair> itemsToInventoriesList;
				ArrayList<Player> playerList;
				ArrayList<Map> mapList;
				ArrayList<IntPair> mapTilesToMapsList;
				ArrayList<User> userList;
				ArrayList<Enemy> enemyList;
				ArrayList<StringPair> nameGenderList;
				
				try {
					itemList = InitialData.getItems();
					objectList = InitialData.getObjects();
					itemsToObjectsList = InitialData.getItemsToObjects();
					objectCommandResponseList = InitialData.getObjectCommandResponses();
					mapTileConnectionsList = InitialData.getMapTileConnections();
					mapTileList = InitialData.getMapTiles();
					objectsToMapTilesList = InitialData.getObjectsToMapTiles();
					itemsToInventoriesList = InitialData.getItemsToInventories();
					playerList = InitialData.getPlayers();
					mapList = InitialData.getMaps();
					mapTilesToMapsList = InitialData.getMapTilesToMaps();
					userList = InitialData.getUsers();
					enemyList = InitialData.getEnemies();
					nameGenderList = InitialData.getNameGenderList();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertItem = null;
				PreparedStatement insertObject = null;
				PreparedStatement insertItemsToObjects = null;
				PreparedStatement insertMapTileConnections = null;
				PreparedStatement insertMapTile = null;
				PreparedStatement insertObjectsToMapTiles = null;
				PreparedStatement insertItemsToInventories = null;
				PreparedStatement insertPlayers = null;
				PreparedStatement insertMaps = null;			
				PreparedStatement insertMapTilesToMaps = null;
				PreparedStatement insertObjectCommandResponses = null;
				PreparedStatement insertUsers = null;
				PreparedStatement insertEnemies = null;
				PreparedStatement insertNames = null;				

				try {					
					insertItem = conn.prepareStatement("insert into items (itemname, longdescription, shortdescription, "
							+ "descriptionupdate, attackbonus, defensebonus, hpbonus, "
							+ "weight, itemtype, levelreq) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Item item : itemList) {
						insertItem.setString(1, item.getName());
						insertItem.setString(2, item.getLongDescription());
						insertItem.setString(3, item.getShortDescription());
						insertItem.setString(4, item.getdescription_update());
						insertItem.setInt(5, item.getattack_bonus());
						insertItem.setInt(6, item.getdefense_bonus());
						insertItem.setInt(7, item.gethp_bonus());						
						insertItem.setFloat(8,  item.getItemWeight());
						insertItem.setString(9, String.valueOf(item.getItemType())); 
						insertItem.setInt(10, item.getlvl_requirement());
						
						insertItem.addBatch();
					}
					insertItem.executeBatch();
					
					insertObject = conn.prepareStatement("insert into objects (objectname, longdescription, shortdescription) values (?, ?, ?)");
					for (Object object : objectList) {						
						insertObject.setString(1, object.getName());
						insertObject.setString(2, object.getLongDescription());
						insertObject.setString(3, object.getShortDescription());
		
						insertObject.addBatch();
					}
					insertObject.executeBatch();
					
					insertItemsToObjects = conn.prepareStatement("insert into itemstoobjects (item_id, object_id) values (?, ?)");
					for(IntPair intPair : itemsToObjectsList) {
						insertItemsToObjects.setInt(1, intPair.getInt1());
						insertItemsToObjects.setInt(2, intPair.getInt2());
						insertItemsToObjects.addBatch();
					}
					insertItemsToObjects.executeBatch();
					
					insertMapTileConnections = conn.prepareStatement("insert into maptileconnections "
							+ "("
							+ "north,"
							+ "northeast,"
							+ "east,"
							+ "southeast,"
							+ "south,"
							+ "southwest,"
							+ "west,"
							+ "northwest)"
							+ " values (?,?,?,?,?,?,?,?)");
					
					for(HashMap<String, Integer> mapTileConnections : mapTileConnectionsList) {
						int i = 1; 
						
						insertMapTileConnections.setInt(i++, mapTileConnections.get("north"));
						insertMapTileConnections.setInt(i++, mapTileConnections.get("northeast"));
						insertMapTileConnections.setInt(i++, mapTileConnections.get("east"));
						insertMapTileConnections.setInt(i++, mapTileConnections.get("southeast"));
						insertMapTileConnections.setInt(i++, mapTileConnections.get("south"));
						insertMapTileConnections.setInt(i++, mapTileConnections.get("southwest"));
						insertMapTileConnections.setInt(i++, mapTileConnections.get("west"));
						insertMapTileConnections.setInt(i++, mapTileConnections.get("northwest"));
						
						insertMapTileConnections.addBatch();
					}
					insertMapTileConnections.executeBatch();
					
					insertMapTile = conn.prepareStatement("insert into maptiles (maptilename, longdescription, shortdescription, difficulty) values (?, ?, ?, ?)");
					for (MapTile mapTile : mapTileList) {
						insertMapTile.setString(1, mapTile.getName());
						insertMapTile.setString(2, mapTile.getLongDescription());
						insertMapTile.setString(3, mapTile.getShortDescription());
						insertMapTile.setInt(4, mapTile.getAreaDifficulty());
						
						insertMapTile.addBatch();
					}
					insertMapTile.executeBatch();
					
					insertObjectsToMapTiles = conn.prepareStatement("insert into objectstomaptiles (object_id, maptile_id) values (?, ?)");
					for(IntPair intPair : objectsToMapTilesList) {
						insertObjectsToMapTiles.setInt(1, intPair.getInt1());
						insertObjectsToMapTiles.setInt(2, intPair.getInt2());
						
						insertObjectsToMapTiles.addBatch();
					}
					insertObjectsToMapTiles.executeBatch();
					
					insertItemsToInventories = conn.prepareStatement("insert into itemstoinventories (item_id, inventory_id) values (?, ?)");
					for(IntPair intPair : itemsToInventoriesList) {
						insertItemsToInventories.setInt(1, intPair.getInt1());
						insertItemsToInventories.setInt(2, intPair.getInt2());
						
						insertItemsToInventories.addBatch();
					}
					insertItemsToInventories.executeBatch();
					
					insertPlayers = conn.prepareStatement("insert into players ("
							+ "race, name, gender, level, hit_points, "
							+ "magic_points, attack, defense, sp_attack, sp_defense, "
							+ "coins, map_location, inventory_id, helm_item_id, braces_item_id, "
							+ "chest_item_id, legs_item_id, boots_item_id, l_hand_item_id, r_hand_item_id,"
							+ "experience, carry_weight) "
							+ "values("
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?)" );
					for(Player player : playerList) {
						int i = 1;
						insertPlayers.setString(i++, player.getrace());
						insertPlayers.setString(i++, player.getname());
						insertPlayers.setString(i++, player.getgender());
						insertPlayers.setInt(i++, player.getlevel());
						insertPlayers.setInt(i++, player.gethit_points());
						
						insertPlayers.setInt(i++, player.getmagic_points());
						insertPlayers.setInt(i++, player.getattack());
						insertPlayers.setInt(i++, player.getdefense());
						insertPlayers.setInt(i++, player.getspecial_attack());
						insertPlayers.setInt(i++, player.getspecial_defense());
						
						insertPlayers.setInt(i++, player.getcoins());
						insertPlayers.setInt(i++, player.getlocation());
						insertPlayers.setInt(i++, player.getinventory_id());
						insertPlayers.setInt(i++, player.gethelm().getID());
						insertPlayers.setInt(i++, player.getbraces().getID());
						
						insertPlayers.setInt(i++, player.getchest().getID());
						insertPlayers.setInt(i++, player.getlegs().getID());
						insertPlayers.setInt(i++, player.getboots().getID());
						insertPlayers.setInt(i++, player.getl_hand().getID());
						insertPlayers.setInt(i++, player.getr_hand().getID());
						
						insertPlayers.setInt(i++, player.getexperience());
						insertPlayers.setInt(i++, player.getcarry_weight());
							
						insertPlayers.addBatch();
					}
					insertPlayers.executeBatch();							
						
					insertMaps = conn.prepareStatement("insert into maps (mapname, longdescription, shortdescription) values (?, ?, ?)");
					for (Map map : mapList) {
						insertMaps.setString(1, map.getName());
						insertMaps.setString(2, map.getLongDescription());
						insertMaps.setString(3, map.getShortDescription());
						
						insertMaps.addBatch();
					}
					insertMaps.executeBatch();	
					
					insertMapTilesToMaps = conn.prepareStatement(" insert into maptilestomaps (maptile_id, map_id) values (?, ?)");
					for(IntPair intPair : mapTilesToMapsList) {
						insertMapTilesToMaps.setInt(1, intPair.getInt1());
						insertMapTilesToMaps.setInt(2, intPair.getInt2());
						
						insertMapTilesToMaps.addBatch();
					}
					insertMapTilesToMaps.executeBatch();
					
					insertObjectCommandResponses = conn.prepareStatement(" insert into objectCommandResponses (object_id, command, response) values (?, ?, ?)");
					for(ObjectIDCommandResponse objectCommandResponse : objectCommandResponseList) {
						insertObjectCommandResponses.setInt(1, objectCommandResponse.getObjectID());
						insertObjectCommandResponses.setString(2, objectCommandResponse.getCommand());
						insertObjectCommandResponses.setString(3, objectCommandResponse.getResponse());
						
						insertObjectCommandResponses.addBatch();
					}
					insertObjectCommandResponses.executeBatch();					
					
					insertUsers = conn.prepareStatement(" insert into users (username, password, email) values (?, ?, ?)");
					for(User user : userList) {
						insertUsers.setString(1, user.getUserName());
						insertUsers.setString(2, user.getPassword());
						insertUsers.setString(3, user.getEmail());
						
						insertUsers.addBatch();
					}
					insertUsers.executeBatch();
					
					insertEnemies = conn.prepareStatement(" insert into enemies (race, hp, mp, attack, defense, sp_atk, sp_def) values (?, ?, ?, ?, ?, ?, ?)");
					for(Enemy enemy : enemyList) {
						insertEnemies.setString(1, enemy.getrace());
						insertEnemies.setInt(2, enemy.gethit_points());
						insertEnemies.setInt(3, enemy.getmagic_points());
						insertEnemies.setInt(4, enemy.getattack());
						insertEnemies.setInt(5, enemy.getdefense());
						insertEnemies.setInt(6, enemy.getspecial_attack());
						insertEnemies.setInt(7, enemy.getspecial_defense());
						
						insertEnemies.addBatch();
					}
					insertEnemies.executeBatch();
					
					insertNames = conn.prepareStatement(" insert into names (name, gender) values(?, ?)");
					for(StringPair nameGender : nameGenderList) {
						insertNames.setString(1, nameGender.getString1());
						insertNames.setString(2, nameGender.getString2());
						
						insertNames.addBatch();
					}
					insertNames.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertMaps);
					DBUtil.closeQuietly(insertPlayers);
					DBUtil.closeQuietly(insertItemsToInventories);
					DBUtil.closeQuietly(insertObjectsToMapTiles);
					DBUtil.closeQuietly(insertMapTile);
					DBUtil.closeQuietly(insertMapTileConnections);
					DBUtil.closeQuietly(insertItemsToObjects);
					DBUtil.closeQuietly(insertObject);
					DBUtil.closeQuietly(insertItem);
					DBUtil.closeQuietly(insertUsers);
					DBUtil.closeQuietly(insertEnemies);
					DBUtil.closeQuietly(insertNames);
				}
			}
		});
	}

	/**************************************************************************************************
	 * 										Get Methods
	 **************************************************************************************************/
	
	///////////////////////////////////////////////////////////////////////////////////////
	//  								Maps
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Map getMap() {
		return executeTransaction(new Transaction <Map>() {
			@Override
			public Map execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSetMap = null;
				ResultSet resultSetMapTileIDs = null;
				
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * " +
							"  from maps "
					);
					
					Map map = new Map();
					MapTile emptyMapTile = new MapTile();
					map.addMapTile(emptyMapTile);
					
					resultSetMap = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSetMap.next()) {
						found = true;
						loadMap(map, resultSetMap, 1);
						
						stmt = conn.prepareStatement(
								" select maptiles.* "
								+ "from maptilestomaps, maptiles "
								+ "where maptilestomaps.map_id = ?"
								+ "AND maptiles.maptile_id = maptilestomaps.maptile_id "
								);
						stmt.setInt(1, map.getID());
						resultSetMapTileIDs = stmt.executeQuery();
						
						while(resultSetMapTileIDs.next()) {
							MapTile mapTile = new MapTile();
							mapTile.setID(resultSetMapTileIDs.getInt(1));
							mapTile = getMapTileByID(mapTile.getID());
							map.addMapTile(mapTile);
						}
					}
					
					// check if the maps were found
					if (!found) {
						System.out.println("<maps> table is empty");
					}
					
					return map;
				} finally {
					DBUtil.closeQuietly(resultSetMapTileIDs);
					DBUtil.closeQuietly(resultSetMap);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	@SuppressWarnings("unused")
	private ArrayList<String> getAllMapNames() {
		return executeTransaction(new Transaction <ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				ArrayList<String> mapNameList = new ArrayList<String>();
				try {
					
					stmt = conn.prepareStatement(
							"select maps.name from maps"
					);
					resultSet = stmt.executeQuery();
					
					int i = 1;
					while(resultSet.next()) {
						mapNameList.add(resultSet.getString(i++));
					}			
					return mapNameList;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//  								MapTiles 
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ArrayList<MapTile> getAllMapTiles() {
		return executeTransaction(new Transaction <ArrayList<MapTile>>() {
			@Override
			public ArrayList<MapTile> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSetMapTiles = null;
				ResultSet resultSetObjects = null;
				ResultSet resultSetObjectCommandResponses = null;
				ResultSet resultSetItems = null;
				
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * "
							+ "from maptiles, maptileconnections "
							+ "where maptiles.maptile_id = maptileconnections.maptile_id"
					);
					
					ArrayList<MapTile> resultMapTiles = new ArrayList<MapTile>();
					
					resultSetMapTiles = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSetMapTiles.next()) {
						found = true;
						
						MapTile mapTile = new MapTile();
						loadMapTile(mapTile, resultSetMapTiles, 1);
						
						// remember to skip an index for the repeated maptile_id from the connections table
						loadMapTileConnections(mapTile.getConnections(), resultSetMapTiles, 7);
						
						// Now get all objects associated with the mapTile
						stmt = conn.prepareStatement(
								"select * "
								+ "from objects, objectstomaptiles "
								+ "where objectstomaptiles.maptile_id = ? "
								+ "AND objectstomaptiles.object_id = objects.object_id "
						);
						
						stmt.setInt(1, mapTile.getID());
						ArrayList<Object> resultObjects = new ArrayList<Object>();
						
						resultSetObjects = stmt.executeQuery();
						
						while(resultSetObjects.next()) {
							Object object = new Object();
							loadObject(object, resultSetObjects, 1);
							
							// Now get the commandResponses
							stmt2 = conn.prepareStatement( 
									"select objectcommandresponses.command, objectcommandresponses.response "
									+ "from objectcommandresponses "
									+ "where objectcommandresponses.object_id = ?"
							);
							stmt2.setInt(1, object.getID());		
							ArrayList<HashMap<String, String>> resultObjectCommandResponses = new ArrayList<HashMap<String, String>>();
							
							resultSetObjectCommandResponses = stmt2.executeQuery();
							
							while (resultSetObjectCommandResponses.next()) {
								HashMap<String, String> objectCommandResponse = new HashMap<String, String>();
								loadObjectCommandResponse(objectCommandResponse, resultSetObjectCommandResponses, 1);
								
								resultObjectCommandResponses.add(objectCommandResponse);
							}
							if(!resultObjectCommandResponses.isEmpty()) {
								for(HashMap<String, String> objectCommandResponse : resultObjectCommandResponses) {
									object.setCommandResponses(objectCommandResponse);
								}
							}		
						
							// Now get the items in the object
							stmt = conn.prepareStatement(
									"select * " +
									"	from items, itemstoobjects" +
									"   where itemstoobjects.object_id = ?"
									+ "AND itemstoobjects.item_id = items.item_id "
							);
							
							stmt.setInt(1, object.getID());
							ArrayList<Item> resultItems = new ArrayList<Item>();
							
							resultSetItems = stmt.executeQuery();
							
							while (resultSetItems.next()) {
								Item item = new Item();
								loadItem(item, resultSetItems, 1);
								
								resultItems.add(item);
							}
							if(!resultItems.isEmpty()) {
								for(Item item : resultItems) {
									object.addItem(item);
								}
							}
							
							resultObjects.add(object);
						}
						if(!resultObjects.isEmpty()) {
							mapTile.setObjects(resultObjects);
						}
							
						resultMapTiles.add(mapTile);
					}
					
					// check if the maptiles were found
					if (!found) {
						System.out.println("<maptiles> table is empty");
					}
					
					return resultMapTiles;
				} finally {
					DBUtil.closeQuietly(resultSetItems);
					DBUtil.closeQuietly(resultSetObjectCommandResponses);
					DBUtil.closeQuietly(resultSetObjects);
					DBUtil.closeQuietly(resultSetMapTiles);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}

	@Override
	public MapTile getMapTileByID(int mapTileID) {
		return executeTransaction(new Transaction <MapTile>() {
			@Override
			public MapTile execute(Connection conn) throws SQLException {

				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSetMapTiles = null;
				ResultSet resultSetObjects = null;
				ResultSet resultSetObjectCommandResponses = null;
				ResultSet resultSetItems = null;
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * " +
							"  from maptiles, maptileconnections "
							+ "where maptiles.maptile_id = ? "
							+ " and maptiles.maptile_id = maptileconnections.maptile_id"
					);
					stmt.setInt(1, mapTileID);
					
					resultSetMapTiles = stmt.executeQuery();
					
					MapTile mapTile = new MapTile();

					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSetMapTiles.next()) {
						found = true;
						
						loadMapTile(mapTile, resultSetMapTiles, 1);
						loadMapTileConnections(mapTile.getConnections(), resultSetMapTiles, 7);
						
						// Now get all objects associated with the mapTile
						stmt = conn.prepareStatement(
								"select * "
								+ "from objects, objectstomaptiles "
								+ "where objectstomaptiles.maptile_id = ? "
								+ "AND objectstomaptiles.object_id = objects.object_id "
						);
						
						stmt.setInt(1, mapTile.getID());
						ArrayList<Object> resultObjects = new ArrayList<Object>();
						
						resultSetObjects = stmt.executeQuery();
						
						while(resultSetObjects.next()) {
							Object object = new Object();
							loadObject(object, resultSetObjects, 1);
							
							// Now get the commandResponses
							stmt2 = conn.prepareStatement( 
									"select objectcommandresponses.command, objectcommandresponses.response "
									+ "from objectcommandresponses "
									+ "where objectcommandresponses.object_id = ?"
							);
							stmt2.setInt(1, object.getID());		
							ArrayList<HashMap<String, String>> resultObjectCommandResponses = new ArrayList<HashMap<String, String>>();
							
							resultSetObjectCommandResponses = stmt2.executeQuery();
							
							while (resultSetObjectCommandResponses.next()) {
								HashMap<String, String> objectCommandResponse = new HashMap<String, String>();
								loadObjectCommandResponse(objectCommandResponse, resultSetObjectCommandResponses, 1);
								
								resultObjectCommandResponses.add(objectCommandResponse);
							}
							if(!resultObjectCommandResponses.isEmpty()) {
								for(HashMap<String, String> objectCommandResponse : resultObjectCommandResponses) {
									object.setCommandResponses(objectCommandResponse);
								}
							}		
							
							// Now get the items in the object
							stmt = conn.prepareStatement(
									"select * " +
									"	from items, itemstoobjects" +
									"   where itemstoobjects.object_id = ?"
									+ "AND itemstoobjects.item_id = items.item_id "
							);
							
							stmt.setInt(1, object.getID());
							ArrayList<Item> resultItems = new ArrayList<Item>();
							
							resultSetItems = stmt.executeQuery();
							
							while (resultSetItems.next()) {
								Item item = new Item();
								loadItem(item, resultSetItems, 1);
								
								resultItems.add(item);
							}
							if(!resultItems.isEmpty()) {
								for(Item item : resultItems) {
									object.addItem(item);
								}
							}
							
							resultObjects.add(object);
						}
						if(!resultObjects.isEmpty()) {
							mapTile.setObjects(resultObjects);
						}
					}
					
					// check if the maptile was found
					if (!found) {
						System.out.println("no maptiles with that id");
					}
					
					return mapTile;
				} finally {
					DBUtil.closeQuietly(resultSetItems);
					DBUtil.closeQuietly(resultSetObjectCommandResponses);
					DBUtil.closeQuietly(resultSetObjects);
					DBUtil.closeQuietly(resultSetMapTiles);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	//  								Objects 
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ArrayList<Object> getAllObjects() {
		return executeTransaction(new Transaction <ArrayList<Object>>() {
			@Override
			public ArrayList<Object> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSetObjects = null;
				ResultSet resultSetObjectCommandResponses = null;
				ResultSet resultSetItems = null;
				
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * " +
							"  from objects "
					);
					
					ArrayList<Object> resultObjects = new ArrayList<Object>();
					resultSetObjects = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					while (resultSetObjects.next()) {
						found = true;
						
						Object object = new Object();
						loadObject(object, resultSetObjects, 1);
						
						// Now get the commandResponses
						stmt2 = conn.prepareStatement( 
								"select objectcommandresponses.command, objectcommandresponses.response "
								+ "from objectcommandresponses "
								+ "where objectcommandresponses.object_id = ?"
						);
						stmt2.setInt(1, object.getID());		
						ArrayList<HashMap<String, String>> resultObjectCommandResponses = new ArrayList<HashMap<String, String>>();
						
						resultSetObjectCommandResponses = stmt2.executeQuery();
						
						while (resultSetObjectCommandResponses.next()) {
							HashMap<String, String> objectCommandResponse = new HashMap<String, String>();
							loadObjectCommandResponse(objectCommandResponse, resultSetObjectCommandResponses, 1);
							
							resultObjectCommandResponses.add(objectCommandResponse);
						}
						if(!resultObjectCommandResponses.isEmpty()) {
							for(HashMap<String, String> objectCommandResponse : resultObjectCommandResponses) {
								object.setCommandResponses(objectCommandResponse);
							}
						}		
						
						// Now get the items in the object
						stmt = conn.prepareStatement(
								"select * " +
								"	from items, itemstoobjects" +
								"   where itemstoobjects.object_id = ?"
								+ "AND itemstoobjects.item_id = items.item_id "
						);
						
						stmt.setInt(1, object.getID());
						ArrayList<Item> resultItems = new ArrayList<Item>();
						
						resultSetItems = stmt.executeQuery();
						
						while (resultSetItems.next()) {
							Item item = new Item();
							loadItem(item, resultSetItems, 1);
							
							resultItems.add(item);
						}
						if(!resultItems.isEmpty()) {
							for(Item item : resultItems) {
								object.addItem(item);
							}
						}
						
						resultObjects.add(object);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<objects> table is empty");
					}
					
					return resultObjects;
				} finally {
					DBUtil.closeQuietly(resultSetItems);
					DBUtil.closeQuietly(resultSetObjectCommandResponses);
					DBUtil.closeQuietly(resultSetObjects);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}

	@Override
	public Object getObjectByID(int objectID) {
		return executeTransaction(new Transaction <Object>() {
			@Override
			public Object execute(Connection conn) throws SQLException {

				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				ResultSet resultSetObjectCommandResponses = null;
				ResultSet resultSetItems = null;
				
				try {
					// retrieve all attributes
					stmt1 = conn.prepareStatement(
							"select * "
							+ "from objects "
							+ "where objects.object_id = ? "
					);
					stmt1.setInt(1, objectID);
					
					resultSet = stmt1.executeQuery();
					
					Object resultObject = new Object();

					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						loadObject(resultObject, resultSet, 1);
						
						// Now get the commandResponses
						stmt2 = conn.prepareStatement( 
								"select objectcommandresponses.command, objectcommandresponses.response "
								+ "from objectcommandresponses "
								+ "where objectcommandresponses.object_id = ?"
						);
						stmt2.setInt(1, resultObject.getID());		
						ArrayList<HashMap<String, String>> resultObjectCommandResponses = new ArrayList<HashMap<String, String>>();
						
						resultSetObjectCommandResponses = stmt2.executeQuery();
						
						while (resultSetObjectCommandResponses.next()) {
							HashMap<String, String> objectCommandResponse = new HashMap<String, String>();
							loadObjectCommandResponse(objectCommandResponse, resultSetObjectCommandResponses, 1);
							
							resultObjectCommandResponses.add(objectCommandResponse);
						}
						if(!resultObjectCommandResponses.isEmpty()) {
							for(HashMap<String, String> objectCommandResponse : resultObjectCommandResponses) {
								resultObject.setCommandResponses(objectCommandResponse);
							}
						}						
						
						// Now get the items in the object
						stmt3 = conn.prepareStatement(
								"select * " +
								"	from items, itemstoobjects" +
								"   where itemstoobjects.object_id = ?"
								+ "AND itemstoobjects.item_id = items.item_id "
						);
						
						stmt3.setInt(1, resultObject.getID());
						ArrayList<Item> resultItems = new ArrayList<Item>();
						
						resultSetItems = stmt3.executeQuery();
						
						while (resultSetItems.next()) {
							Item item = new Item();
							loadItem(item, resultSetItems, 1);
							
							resultItems.add(item);
						}
						if(!resultItems.isEmpty()) {
							for(Item item : resultItems) {
								resultObject.addItem(item);
							}
						}
					}
					
					// check if the object was found
					if (!found) {
						System.out.println("no objects with that id");
					}
					
					return resultObject;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(resultSetObjectCommandResponses);
					DBUtil.closeQuietly(resultSetItems);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	//  								Items 
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ArrayList<Item> getAllItems() {
		return executeTransaction(new Transaction <ArrayList<Item>>() {
			@Override
			public ArrayList<Item> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * " +
							"  from items "
					);
					
					ArrayList<Item> result = new ArrayList<Item>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Item item = new Item();
						loadItem(item, resultSet, 1);
						
						result.add(item);
					}
					
					// check if the items were found
					if (!found) {
						System.out.println("<items> table is empty");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	@Override
	public Item getItemByID(int itemID) {
		return executeTransaction(new Transaction <Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes 
					stmt = conn.prepareStatement(
							"select * " +
							"  from items "
							+ "where items.item_id = ? "
					);
					stmt.setInt(1, itemID);
					resultSet = stmt.executeQuery();
					
					Item result = new Item();

					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;						
						loadItem(result, resultSet, 1);
					}
					
					// check if the item was found
					if (!found) {
						System.out.println("no items with that id");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}

	@Override
	public Item getLegendaryItem() {
		return executeTransaction(new Transaction<Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select items.item_id "
							+ "from items "
							+ "where items.longdescription = 'LEGENDARY'"
					);
					resultSet = stmt.executeQuery();

					Random rand = new Random();
					
					ArrayList<Integer> itemIDList = new ArrayList<Integer>();
					
					while(resultSet.next()) {
						itemIDList.add(resultSet.getInt(1));
					}
					
					return getItemByID(itemIDList.get(rand.nextInt(itemIDList.size())));
					
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}				
			}
		});
	}

	@Override
	public Item getLegendaryItem(String itemType) {
		return executeTransaction(new Transaction<Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select items.item_id "
							+ "from items "
							+ "where items.shortdescription = 'LEGENDARY'"
							+ "AND items.itemtype = ?"
					);
					stmt.setString(1, itemType);
					resultSet = stmt.executeQuery();

					Random rand = new Random();
					
					ArrayList<Integer> itemIDList = new ArrayList<Integer>();
					
					while(resultSet.next()) {
						itemIDList.add(resultSet.getInt(1));
					}
					
					return getItemByID(itemIDList.get(rand.nextInt(itemIDList.size())));
					
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}				
			}
		});
	}
	
	@Override
	public Item getHandHeldItem() {
		return executeTransaction(new Transaction<Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select items.item_id "
							+ "from items "
							+ "where (items.itemtype = 'L_HAND' "
							+ "OR items.itemtype = 'R_HAND') "
							+ "AND NOT items.longdescription = 'LEGENDARY'"
					);
					resultSet = stmt.executeQuery();

					Random rand = new Random();
					
					ArrayList<Integer> itemIDList = new ArrayList<Integer>();
					
					while(resultSet.next()) {
						itemIDList.add(resultSet.getInt(1));
					}
					
					return getItemByID(itemIDList.get(rand.nextInt(itemIDList.size())));
					
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}				
			}
		});
	}	
	
	@Override
	public Item getHandHeldItem(String whichHand) {
		return executeTransaction(new Transaction<Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select items.item_id "
							+ "from items "
							+ "where items.itemtype = ? "
							+ "AND NOT items.longdescription = 'LEGENDARY'"
					);
					stmt.setString(1, whichHand);
					resultSet = stmt.executeQuery();
					Random rand = new Random();
					
					ArrayList<Integer> itemIDList = new ArrayList<Integer>();
					
					while(resultSet.next()) {
						itemIDList.add(resultSet.getInt(1));
					}
					
					return getItemByID(itemIDList.get(rand.nextInt(itemIDList.size())));
					
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}				
			}
		});
	}
	
	@Override
	public Item getArmorItem() {
		return executeTransaction(new Transaction<Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select items.item_id "
							+ "from items "
							+ "where (items.itemtype = 'CHEST' "
							+ "OR items.itemtype = 'BRACES' "
							+ "OR items.itemtype = 'LEGS' "
							+ "OR items.itemtype = 'BOOTS') "
							+ "AND NOT items.longdescription = 'LEGENDARY'"
					);
					resultSet = stmt.executeQuery();
					Random rand = new Random();
					
					ArrayList<Integer> itemIDList = new ArrayList<Integer>();
					
					while(resultSet.next()) {
						itemIDList.add(resultSet.getInt(1));
					}
					
					return getItemByID(itemIDList.get(rand.nextInt(itemIDList.size())));
					
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}				
			}
		});
	}
	
	@Override
	public Item getArmorItem(String armorType) {
		return executeTransaction(new Transaction<Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select items.item_id "
							+ "from items "
							+ "where items.itemtype = ? "
							+ "AND NOT items.longdescription = 'LEGENDARY'"
					);
					stmt.setString(1, armorType);
					resultSet = stmt.executeQuery();
					Random rand = new Random();
					
					ArrayList<Integer> itemIDList = new ArrayList<Integer>();
					
					while(resultSet.next()) {
						itemIDList.add(resultSet.getInt(1));
					}
					
					return getItemByID(itemIDList.get(rand.nextInt(itemIDList.size())));
					
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}				
			}
		});
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	//  								Players
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public Player getPlayer() {
		return executeTransaction(new Transaction <Player>() {
			@Override
			public Player execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement("select * from players");
					
					Player result = new Player();
					resultSet = stmt.executeQuery();
					
					Boolean found = false;
					while(resultSet.next()) {
						found = true;
						
						loadPlayer(result, resultSet, 1);
					}
					
					if(!found) {
						System.out.println("<players> table is empty");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					edu.ycp.cs320.sqldemo.DBUtil.closeQuietly(conn);
				}
			}
		});
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//  								Characters 
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ArrayList<Character> getAllCharacters() {
		ArrayList<Character> characterList = new ArrayList<Character>();
		characterList.add(getPlayer());
		return characterList;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	//  								Enemies 
	///////////////////////////////////////////////////////////////////////////////////////
	@Override
	public ArrayList<String> getAllEnemyRaces() {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				ArrayList<String> enemyRaceList = new ArrayList<String>();
				try {
					stmt = conn.prepareStatement(
							"select enemies.race "
							+ "from enemies"
					);
					resultSet = stmt.executeQuery();
					while (resultSet.next()) {
						enemyRaceList.add(resultSet.getString(1));
					}
					return enemyRaceList;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}			
		});
	}

	public ArrayList<Enemy> getAllEnemies() {
		return executeTransaction(new Transaction<ArrayList<Enemy>>() {
			public ArrayList<Enemy> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
						"select * "
						+ "from enemies");
					resultSet = stmt.executeQuery();
					
					ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
					
					Boolean found = false;
					while (resultSet.next()) {
						found = true;
						Enemy enemy = new Enemy();
						loadEnemy(enemy, resultSet, 1);
						enemyList.add(enemy);
					}
					
					if(!found) {
						System.out.println("EnemyList is empty");
					}
					
					return enemyList;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
			
		});
	}
	
	@Override
	public Enemy getEnemyByRace(String race) {
		return executeTransaction(new Transaction<Enemy>() {
			@Override
			public Enemy execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * "
							+ "from enemies "
							+ "where enemies.race = ?"
					);
					stmt.setString(1, race);
					resultSet = stmt.executeQuery();
					
					Enemy enemy = new Enemy();

					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						loadEnemy(enemy, resultSet, 1);
					}
					
					// check if the item was found
					if (!found) {
						System.out.println("no enemies with that race");
					}
					
					return enemy;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}			
		});
	}
	
	private StringPair getRandomName() {
		return executeTransaction(new Transaction<StringPair>() {
			public StringPair execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select names.name, names.gender "
							+ "from names "
							+ "where names.number = ?"
					);
					Random rand = new Random();					
					stmt.setInt(1, rand.nextInt(8) + 1);
					
					resultSet = stmt.executeQuery();
					StringPair nameGender = new StringPair();
					
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						loadNameGender(nameGender, resultSet, 1);
					}
					
					if (!found) {
						System.out.println("no names under that number");
					}
					
					return nameGender;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//									Inventories
	////////////////////////////////////////////////////////////////////////////////////////
	public Inventory getInventoryByID (int inventoryID) {
		return executeTransaction(new Transaction <Inventory>() {
			@Override
			public Inventory execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select items.item_id "
							+ "from items, itemstoinventories "
							+ "where items.item_id = itemstoinventories.item_id "
							+ "AND itemstoinventories.inventory_id = ? "
						);
					stmt.setInt(1, inventoryID);
					
					resultSet = stmt.executeQuery();
					
					Inventory inventory = new Inventory();
					ArrayList<Item> itemList = new ArrayList<Item>();
					Item item = new Item();
					
					Boolean found = false;
					while(resultSet.next()) {
						found = true;
						
						item = getItemByID(resultSet.getInt(1));
						itemList.add(item);
					}
					if(!found) {
						System.out.println("This inventory is empty");
					}
					inventory.setitems(itemList);
					
					return inventory;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	private Inventory getInventory (String playerName, int inventoryID) {
		return executeTransaction(new Transaction <Inventory>() {
			@Override
			public Inventory execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select items.item_id "
							+ "from items, itemstoinventories" + playerName
							+ " where items.item_id = itemstoinventories" + playerName+ ".item_id "
							+ " AND itemstoinventories" + playerName + ".inventory_id = ? "
						);
					stmt.setInt(1, inventoryID);
					
					resultSet = stmt.executeQuery();
					
					Inventory inventory = new Inventory();
					ArrayList<Item> itemList = new ArrayList<Item>();
					Item item = new Item();
					
					Boolean found = false;
					while(resultSet.next()) {
						found = true;
						
						item = getItemByID(resultSet.getInt(1));
						itemList.add(item);
					}
					if(!found) {
						System.out.println("This inventory is empty");
					}
					inventory.setitems(itemList);
					
					return inventory;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	//										Users
	////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<String> getAllUserNames() {
		return executeTransaction(new Transaction<ArrayList<String>>() {
			@Override
			public ArrayList<String> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					
					stmt = conn.prepareStatement(
							"select users.username "
							+ "from users");
					
					resultSet = stmt.executeQuery();
					
					String username = new String();
					ArrayList<String> userList = new ArrayList<String>();
					
					Boolean found = false;
					while(resultSet.next()) {
						found = true;
						username = resultSet.getString(1);
						userList.add(username);
					}
					
					if(!found) {
						System.out.println("UserList is empty");
					}
					
					return userList;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}

	@Override
	public Boolean doesUserNameExist(String username) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select users.username "
							+ "from users "
							+ "where users.username = ?"
					);
					stmt.setString(1, username);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {
						return true;
					}
					return false;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Boolean isEmailInUse(String email) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				ResultSet resultSet = null;
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"select users.email "
							+ "from users "
							+ "where users.email = ?"
					);
					stmt.setString(1, email);
					resultSet = stmt.executeQuery();
					
					if(resultSet.next()) {
						return true;
					}
					return false;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public String getUserPasswordByUserName(final String userName) {
		return executeTransaction(new Transaction<String>() {
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultPassword = null;
				try {
					stmt = conn.prepareStatement(
							"select users.password "
							+ "from users "
							+ "where users.username = ? ");
					stmt.setString(1, userName);
					resultPassword = stmt.executeQuery();
					
					String password = new String();
					
					Boolean found = false;
					while(resultPassword.next()) {
						found = true;
						password = resultPassword.getString(1);
					}
					
					if(!found) {
						System.out.println("That username was not found");
					}
					
					return password;
				} finally {
					DBUtil.closeQuietly(resultPassword);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//  									Games
	///////////////////////////////////////////////////////////////////////////////////////	
	
	public ArrayList<Integer> getGameIDs(final String username) {
		return executeTransaction(new Transaction<ArrayList<Integer>>() {
			public ArrayList<Integer> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					stmt = conn.prepareStatement(
							"select gamestousers.game_id "
							+ "from gamestousers "
							+ "where gamestousers.username = ? ");
					stmt.setString(1, username);
					resultSet = stmt.executeQuery();
					
					ArrayList<Integer> gameIDList = new ArrayList<Integer>();
					
					Boolean found = false;
					while(resultSet.next()) {
						found = true;
						gameIDList.add(resultSet.getInt(1));
					}
					
					if(!found) {
						System.out.println("That username was not found");
					}
					
					return gameIDList;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	/******************************************************************************************************
	 * 										*Get Specific* Methods
	 ******************************************************************************************************/
	
	@Override
	public Character getCharacterByName(String characterName) {
		// TODO Auto-generated method stub
		return null;
	}	
	

	/*******************************************************************************************************
	*											load/save game
	********************************************************************************************************/
	// Will need to take gameID in future
	public Game loadGame(int gameID) {
		
		Game game = new Game();

		game.setmap(getMap());
		
		game.setobjects(getAllObjects());
		game.setitems(getAllItems());
		ArrayList<Character> characterList = new ArrayList<Character>();
		characterList.add(getPlayer());
		characterList.get(0).setinventory(getInventory(characterList.get(0).getname(), characterList.get(0).getinventory_id()));
		game.setcharacters(characterList);
		
		return game;
	}
	
	public void saveGame(Game game) {
		updateMap(game.getmap());
		updateCharacters(game.getcharacters());
	} 
	
	/**************************************************************************************************
	 * 										Init new game
	***************************************************************************************************/
	
	@Override
	public Boolean createNewUser(String username, String password, String email) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement insertUser = null;
				try {
					insertUser = conn.prepareStatement(
							"insert into users (username, password, email) "
							+ "values (?, ?, ?)"
					);
					insertUser.setString(1, username);
					insertUser.setString(2, password);
					insertUser.setString(3, email);
					insertUser.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(insertUser);
				}
			}
		});
	}
	
	public Integer createNewGame(String username) { 
		return executeTransaction (new Transaction<Integer>() {
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into gamestousers (username) values (?) "
					);
					stmt.setString(1, username);
					stmt.executeUpdate();
					
					Player player = createPlayer();
					
					// create userObjects -- stmt 1
					createWorldForNewGame(player.getname());
					
					// get gameID from gamestousers -- resultSet
					stmt2 = conn.prepareStatement(
							"select game_id from gamestousers where gamestousers.username = ?"
					);
					stmt2.setString(1, username);
					resultSet = stmt2.executeQuery();
					
					int gameID = 0;
					while(resultSet.next()) {
						gameID = resultSet.getInt(1);
					}
				
					stmt3 = conn.prepareStatement(
							"insert into playerstogames (playername, game_id) values (?, ?) "
					);
					stmt3.setString(1, player.getname());
					stmt3.setInt(2, gameID);
					
					insertPlayer(player);
							
					return gameID;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	private Boolean createWorldForNewGame(String playerName) { 
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute (Connection conn) throws SQLException {
				PreparedStatement insertMapTile = null;
				PreparedStatement insertMapTileConnections = null;
				PreparedStatement insertObject = null;
				PreparedStatement insertItemsToObjects = null;
				PreparedStatement insertObjectCommandResponses = null;
				
				ArrayList<MapTile> mapTileList = getAllMapTiles();
				try {
					createWildcardMapTileTable(playerName);
					createWildcardMapTileConnectionsTable(playerName);
					createWildcardObjectsTable(playerName);
					createWildcardObjectCommandResponseTable(playerName);
					createWildcardItemsToObjectsTable(playerName);
					createWildcardItemsToInventoriesTable(playerName);
					
					insertMapTile = conn.prepareStatement("insert into maptiles" + playerName + " (maptilename, longdescription, shortdescription, difficulty) values (?, ?, ?, ?)");
					insertMapTileConnections = conn.prepareStatement("insert into maptileconnections" + playerName + " "
							+ "("
							+ "north,"
							+ "northeast,"
							+ "east,"
							+ "southeast,"
							+ "south,"
							+ "southwest,"
							+ "west,"
							+ "northwest)"
							+ " values (?,?,?,?,?,?,?,?)");
					
					insertObject = conn.prepareStatement("insert into objects" + playerName + " (objectname, longdescription, shortdescription) values (?, ?, ?)");
					insertItemsToObjects = conn.prepareStatement("insert into itemstoobjects" + playerName + " (item_id, object_id) values (?, ?)");
					insertObjectCommandResponses = conn.prepareStatement(" insert into objectCommandResponses" + playerName + " (object_id, command, response) values (?, ?, ?)");
					
					ArrayList<ObjectIDCommandResponse> objectCommandResponseList = InitialData.getObjectCommandResponses();
					for(ObjectIDCommandResponse objectCommandResponse : objectCommandResponseList) {
						insertObjectCommandResponses.setInt(1, objectCommandResponse.getObjectID());
						insertObjectCommandResponses.setString(2, objectCommandResponse.getCommand());
						insertObjectCommandResponses.setString(3, objectCommandResponse.getResponse());
						
						insertObjectCommandResponses.addBatch();
					}					
				
					for (MapTile mapTile : mapTileList) {
						insertMapTile.setString(1, mapTile.getName());
						insertMapTile.setString(2, mapTile.getLongDescription());
						insertMapTile.setString(3, mapTile.getShortDescription());
						insertMapTile.setInt(4, mapTile.getAreaDifficulty());
						
							// Connections for each mapTile
							int i = 1;
							insertMapTileConnections.setInt(i++, mapTile.getConnections().get("north"));
							insertMapTileConnections.setInt(i++, mapTile.getConnections().get("northeast"));
							insertMapTileConnections.setInt(i++, mapTile.getConnections().get("east"));
							insertMapTileConnections.setInt(i++, mapTile.getConnections().get("southeast"));
							insertMapTileConnections.setInt(i++, mapTile.getConnections().get("south"));
							insertMapTileConnections.setInt(i++, mapTile.getConnections().get("southwest"));
							insertMapTileConnections.setInt(i++, mapTile.getConnections().get("west"));
							insertMapTileConnections.setInt(i++, mapTile.getConnections().get("northwest"));
							insertMapTileConnections.addBatch();
							
							// If there are objects on the maptile, add them
							if(mapTile.getObjects() != null) {
								for(Object object : mapTile.getObjects()) {
									insertObject.setString(1, object.getName());
									insertObject.setString(2, object.getLongDescription());
									insertObject.setString(3, object.getShortDescription());
									
									// If there are items in the objects, add them
									if(!object.getItems().isEmpty()) {
										for(Item item : object.getItems()) {
											insertItemsToObjects.setInt(1, item.getID());
											insertItemsToObjects.setInt(2, object.getID());
											
											insertItemsToObjects.addBatch();
										}
									}
									insertObject.addBatch();
								}
							}	
						insertMapTile.addBatch();
					}
					insertObject.executeBatch();
					insertItemsToObjects.executeBatch();
					insertMapTile.executeBatch();						
					insertMapTileConnections.executeBatch();
					insertObjectCommandResponses.executeBatch();
					
					return true;
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(insertObject);
					DBUtil.closeQuietly(insertItemsToObjects);
					DBUtil.closeQuietly(insertObjectCommandResponses);
					DBUtil.closeQuietly(insertMapTileConnections);
					DBUtil.closeQuietly(insertMapTile);
				}
			}
		});
	}
	
	private Boolean insertPlayer(Player player) {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement insertPlayer = null;
				try {
					insertPlayer = conn.prepareStatement("insert into players ("
							+ "race, name, gender, level, hit_points, "
							+ "magic_points, attack, defense, sp_attack, sp_defense, "
							+ "coins, map_location, inventory_id, helm_item_id, braces_item_id, "
							+ "chest_item_id, legs_item_id, boots_item_id, l_hand_item_id, r_hand_item_id,"
							+ "experience, carry_weight) "
							+ "values("
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?)" );
					
						int i = 1;
						insertPlayer.setString(i++, player.getrace());
						insertPlayer.setString(i++, player.getname());
						insertPlayer.setString(i++, player.getgender());
						insertPlayer.setInt(i++, player.getlevel());
						insertPlayer.setInt(i++, player.gethit_points());
						
						insertPlayer.setInt(i++, player.getmagic_points());
						insertPlayer.setInt(i++, player.getattack());
						insertPlayer.setInt(i++, player.getdefense());
						insertPlayer.setInt(i++, player.getspecial_attack());
						insertPlayer.setInt(i++, player.getspecial_defense());
						
						insertPlayer.setInt(i++, player.getcoins());
						insertPlayer.setInt(i++, player.getlocation());
						insertPlayer.setInt(i++, player.getinventory_id());
						insertPlayer.setInt(i++, player.gethelm().getID());
						insertPlayer.setInt(i++, player.getbraces().getID());
						
						insertPlayer.setInt(i++, player.getchest().getID());
						insertPlayer.setInt(i++, player.getlegs().getID());
						insertPlayer.setInt(i++, player.getboots().getID());
						insertPlayer.setInt(i++, player.getl_hand().getID());
						insertPlayer.setInt(i++, player.getr_hand().getID());
						
						insertPlayer.setInt(i++, player.getexperience());
						insertPlayer.setInt(i++, player.getcarry_weight());
							
						insertPlayer.addBatch();
					
					insertPlayer.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(insertPlayer);
				}
			}
		});
	}
	
	private Boolean createWildcardMapTileTable(String playerName) {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"create table maptiles" + playerName + " (" +
							"   maptile_id integer primary key " +
							"       generated always as identity (start with 1, increment by 1), " +
							"   maptilename varchar(40)," +							
							"	longdescription varchar(200)," +
							"	shortdescription varchar(100)," +
							"	difficulty int" +
							")"
					);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	private Boolean createWildcardMapTileConnectionsTable(String playerName) {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"create table maptileconnections" + playerName + " (" +
							"   maptile_id integer primary key " +
							"       generated always as identity (start with 1, increment by 1), " +

							"   north int," +
							"	northeast int," +
							"   east int," +							
							"   southeast int," +							
							"   south int," +							
							"   southwest int," +							
							"   west int," +							
							"   northwest int" +
							")"
					);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	private Boolean createWildcardObjectsTable(String playerName) {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
						"create table objects" + playerName + " (" +
						"	object_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +
						"	objectname varchar(40)," + 
						"	longdescription varchar(200)," +
						"	shortdescription varchar(100)" +
						")"
					);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	private Boolean createWildcardItemsToObjectsTable(String playerName) {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"create table itemstoobjects" + playerName + " (" +
							"   item_id int," +
							"   object_id int" +
							")"
					);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	private Boolean createWildcardObjectCommandResponseTable(String playerName) {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"create table objectcommandresponses" + playerName + " ("
							+ "object_id int, "
							+ "command varchar(10), "
							+ "response varchar (100)"
							+ ")"
					);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	private Boolean createWildcardItemsToInventoriesTable(String playerName) {
		return executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"create table itemstoinventories" + playerName + " ("
							+ "item_id int, "
							+ "inventory_id int"
							+ ")"
					);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	private Player createPlayer() {
		Player player = new Player();		
		
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Choose your name: ");
		
		player.setname(keyboard.next());
		
		String race = "Human";
		player.setrace(race);
		
		System.out.println("Choose your gender");
		player.setgender(keyboard.next());
		
		player.setlevel(1);
		player.sethit_points(100);
		
		player.setmagic_points(25);
		player.setattack(10);
		player.setdefense(10);
		player.setspecial_attack(15);
		player.setspecial_defense(10);
		
		player.setcoins(0);
		player.setlocation(1);

		int carryWeight = 0;

		Item emptyItemSlot = new Item();
		emptyItemSlot.setattack_bonus(0);
		emptyItemSlot.setdefense_bonus(0);
		emptyItemSlot.setdescription_update("You haven't equipped one");
		emptyItemSlot.sethp_bonus(0);
		emptyItemSlot.setlvl_requirement(0);
		emptyItemSlot.setID(0);
		emptyItemSlot.setIsQuestItem(false);
		emptyItemSlot.setItemWeight(0);
		emptyItemSlot.setLongDescription("Empty Slot");
		emptyItemSlot.setShortDescription("Empty Slot");
		emptyItemSlot.setName("Empty Slot");
		
		// helm
		emptyItemSlot.setItemType(ItemType.HELM);
		player.sethelm(emptyItemSlot);
		
		// braces
		emptyItemSlot.setItemType(ItemType.BRACES);
		player.setbraces(emptyItemSlot);
		
		// chest
		emptyItemSlot.setItemType(ItemType.CHEST);
		player.setchest(emptyItemSlot);
		
		emptyItemSlot.setItemType(ItemType.LEGS);
		player.setlegs(emptyItemSlot);
		
		// boots
		emptyItemSlot.setItemType(ItemType.BOOTS);
		player.setboots(emptyItemSlot);
		
		// l_hand
		emptyItemSlot.setItemType(ItemType.L_HAND);
		player.setl_hand(emptyItemSlot);
		
		// r_hand
		emptyItemSlot.setItemType(ItemType.R_HAND);
		player.setr_hand(emptyItemSlot);
		
		player.setcarry_weight(20);
		player.setexperience(0);
		
		player.setinventory(new Inventory());
		
		// Add the sum total of weight to the inventory
		player.getinventory().setweight(carryWeight);
		
		//keyboard.close();
		
		
		
		return player;
	}
	
	/*******************************************************************************************************
	 * 										Update Database Methods
	********************************************************************************************************/
	
	private void updateMap(final Map map) {
		// In the future will need to update map for Editor
		// update all maptiles
		updateMapTiles(map.getMapTiles());
	}
	
	private void updateMapTiles(final ArrayList<MapTile> mapTileList) {
		
		for(MapTile mapTile : mapTileList) {
			updateMapTile(mapTile);
		}
	}
	
	private void updateMapTile(final MapTile mapTile) {
		// removes all objects from the objectstomaptile table
		removeAllObjectsFromMapTile(mapTile.getID());
		
		// adds every object in the maptile.getObjects() objectList
		// to the objectstomaptiles table
		if (mapTile.getObjects() != null) {
			for (Object object : mapTile.getObjects()) {
				addObjectToMapTile(object.getID(), mapTile.getID());
			}
		}
	}
	
	private void updateObject(final Object object) {
		
		// removes all items from itemstoobjects table
		removeAllItemsFromObject(object.getID());		
		
		// adds every item in the current object.getItems() itemList
		// to the itemstoobjects table
		if (!object.getItems().isEmpty()) {
			for(Item item : object.getItems()) {
				addItemToObject(item, object);
			}
		}
	}
	
	private void updateCharacters(ArrayList<Character> characterList) {
		//Inventory inventory = characterList.get(0).getinventory();
		updatePlayer(characterList.get(0));
		updateInventory(characterList.get(0));
		//characterList.get(0).setinventory(getInventoryByID(characterList.get(0).getinventory_id()));
	}
	
	private void updatePlayer(Character playerC) {
		executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmtRemovePlayer = null;
				PreparedStatement stmtUpdatePlayer = null;
				
				Player player = (Player) playerC;
				
				try {
					stmtRemovePlayer = conn.prepareStatement(
							"delete from players "
							+ "where players.name = ? "
					);
					stmtRemovePlayer.setString(1, player.getname());
					stmtRemovePlayer.executeUpdate();
					
					stmtUpdatePlayer = conn.prepareStatement(
							"insert into players ("
							+ "race, name, gender, level, hit_points, "
							+ "magic_points, attack, defense, sp_attack, sp_defense, "
							+ "coins, map_location, inventory_id, helm_item_id, braces_item_id, "
							+ "chest_item_id, legs_item_id, boots_item_id, l_hand_item_id, r_hand_item_id, "
							+ "experience, carry_weight) "
							+ "values("
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?)" 
					);
					
						int i = 1;
						stmtUpdatePlayer.setString(i++, player.getrace());
						stmtUpdatePlayer.setString(i++, player.getname());
						stmtUpdatePlayer.setString(i++, player.getgender());
						stmtUpdatePlayer.setInt(i++, player.getlevel());
						stmtUpdatePlayer.setInt(i++, player.gethit_points());
						
						stmtUpdatePlayer.setInt(i++, player.getmagic_points());
						stmtUpdatePlayer.setInt(i++, player.getattack());
						stmtUpdatePlayer.setInt(i++, player.getdefense());
						stmtUpdatePlayer.setInt(i++, player.getspecial_attack());
						stmtUpdatePlayer.setInt(i++, player.getspecial_defense());
						
						stmtUpdatePlayer.setInt(i++, player.getcoins());
						stmtUpdatePlayer.setInt(i++, player.getlocation());
						stmtUpdatePlayer.setInt(i++, player.getinventory_id());
						stmtUpdatePlayer.setInt(i++, player.gethelm().getID());
						stmtUpdatePlayer.setInt(i++, player.getbraces().getID());
						
						stmtUpdatePlayer.setInt(i++, player.getchest().getID());
						stmtUpdatePlayer.setInt(i++, player.getlegs().getID());
						stmtUpdatePlayer.setInt(i++, player.getboots().getID());
						stmtUpdatePlayer.setInt(i++, player.getl_hand().getID());
						stmtUpdatePlayer.setInt(i++, player.getr_hand().getID());
						stmtUpdatePlayer.setInt(i++, player.getexperience());
						stmtUpdatePlayer.setInt(i++, player.getcarry_weight());
							
						stmtUpdatePlayer.addBatch();	
						stmtUpdatePlayer.executeBatch();	
						
					return true;
				
				} finally {
					DBUtil.closeQuietly(stmtRemovePlayer);
					DBUtil.closeQuietly(stmtUpdatePlayer);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	private void updateInventory(Character player) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt1 = null;
				
				ArrayList<Item> itemList = player.getinventory().getitems();
				ArrayList<Integer> itemIDList = new ArrayList<Integer>();
				for(Item item : itemList) {
					itemIDList.add(item.getID());
				}
				
				try {	
					stmt = conn.prepareStatement(
							"delete from itemstoinventories" + player.getname() +
							" where inventory_id = ? "
					);
					stmt.setInt(1, player.getinventory_id());
					stmt.executeUpdate();
					
					stmt1 = conn.prepareStatement(
							"insert into itemstoinventories" + player.getname() +
							" (item_id, inventory_id) "
							+ "values (?, ?)");
					for(Integer itemID : itemIDList) {
						stmt1.setInt(1, itemID);
						stmt1.setInt(2, player.getinventory_id());
						stmt1.addBatch();
					}
					stmt1.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	/**	This should only be called after checking if item exists in items table already **/
	private void insertItem(Item item) {		
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement insertItem = null;
				try {					
					insertItem = conn.prepareStatement("insert into items (itemname, longdescription, shortdescription, "
							+ "descriptionupdate, attackbonus, defensebonus, hpbonus, "
							+ "weight, itemtype, levelreq) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						insertItem.setString(1, item.getName());
						insertItem.setString(2, item.getLongDescription());
						insertItem.setString(3, item.getShortDescription());
						insertItem.setString(4, item.getdescription_update());
						insertItem.setInt(5, item.getattack_bonus());
						insertItem.setInt(6, item.getdefense_bonus());
						insertItem.setInt(7, item.gethp_bonus());						
						insertItem.setFloat(8,  item.getItemWeight());
						insertItem.setString(9, String.valueOf(item.getItemType())); 
						insertItem.setInt(10, item.getlvl_requirement());
						insertItem.addBatch();
					insertItem.executeBatch();
					return true;
				} finally {
					DBUtil.closeQuietly(conn);
					DBUtil.closeQuietly(insertItem);
				}
			}
		});
	}
	/*******************************************************************************************************
	 * 											addToConstruct Methods
	 *******************************************************************************************************/
	
	// Adds the association in the itemstoinventories table, adds the item to
	// the inventory's item list.
	public void addItemToInventory(final Item item, Inventory inventory) {
		executeTransaction(new Transaction <Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"insert into itemstoinventories (item_id, inventory_id) "
							+ "values (?, ?)");
					stmt.setInt(1, item.getID());
					stmt.setInt(2, inventory.getinventory_id());
					stmt.executeUpdate();
					
					inventory.getitems().add(item);
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}	
	
	// Adds an association in the itemstoobjects table and adds the item to the 
	// object's itemList.
	public void addItemToObject(final Item item, Object object) {
		executeTransaction(new Transaction <Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				try {
					stmt = conn.prepareStatement(
							"insert into itemstoobjects (item_id, object_id) "
							+ "values (?, ?)");
					stmt.setInt(1, item.getID());
					stmt.setInt(2, object.getID());
					stmt.executeUpdate();
					object.addItem(item);
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}	
	
	private void addCommandResponsesToObject(HashMap<String, String> commandResponses, int objectID) {
		executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(" insert into objectCommandResponses (object_id, command, response) values (?, ?, ?)");
							for(String key : commandResponses.keySet()) {
								stmt.setInt(1, objectID);
								stmt.setString(2, key);
								stmt.setString(3, commandResponses.get(key));
								stmt.addBatch();
							}
							stmt.executeBatch();	
					
					return true;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	public String addUser(final String userName, final String password, final String email) {
		return executeTransaction(new Transaction<String>() {
			@Override
			public String execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert int users (userName, password, email) "
							+ "values (?, ?, ?)");
					stmt.setString(1, userName);
					stmt.setString(2, password);
					stmt.setString(3, email);
					stmt.executeUpdate();		
					
					return userName;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	private void addObjectToMapTile(final int objectID, final int mapTileID) {
		executeTransaction(new Transaction <Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into objectstomaptiles (object_id, maptile_id) "
							+ "values (?, ?)");
							
					stmt.setInt(1, objectID);
					stmt.setInt(2, mapTileID);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	/*******************************************************************************************************
	 * 											removeFromConstruct Methods
	 *******************************************************************************************************/
	
	// Removes the item from the inventory.  Deletes the association in the 
	// itemstoinventories table in Derby.  Returns the same item given to it
	public Item removeItemFromInventory(final Item item, Inventory inventory) {
		return executeTransaction(new Transaction <Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from itemstoinventories "
							+ "where itemstoinventories.item_id = ? "
							+ "AND itemstoinventories.inventory_id = ? ");
					stmt.setInt(1, item.getID());
					stmt.setInt(2, inventory.getinventory_id());
					stmt.executeUpdate();
					
					inventory.getitems().remove(item);
					
					return item;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	// Removes the item from the object.  Deletes the association in the
	// itemstoobjects table in Derby.  Returns the same item given to it
 	public Item removeItemFromObject(final Item item, Object object) {
		return executeTransaction(new Transaction <Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from itemstoobjects "
							+ "where itemstoobjects.item_id = ? "
							+ "AND itemstoobjects.object_id = ? ");
					stmt.setInt(1, item.getID());
					stmt.setInt(2, object.getID());
					stmt.executeUpdate();
					
					object.getItems().remove(item);
					
					return item;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
 	
	private void removeAllItemsFromObject(final int objectID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement removeItem = null;
				try {
					removeItem = conn.prepareStatement(
							"delete from itemstoobjects "
							+ "where itemstoobjects.objectID = ? "
					);
					removeItem.setInt(1, objectID);
					
					return true;
				} finally {
					DBUtil.closeQuietly(removeItem);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	private void removeAllObjectCommandResponses(int objectID) {
		executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from objectcommandresponses "
							+ "where objectcommandresponses.object_id = ? "
					);
					stmt.setInt(1, objectID);
					stmt.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(conn);
				}
			}			
		});
	} 
	
	private void removeObjectCommandResponseByCommand(final String command, final int objectID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement removeCommandResponse = null;
				try {
					removeCommandResponse = conn.prepareStatement(
							"delete from objectcommandresponses "
							+ "where objectcommandresponses.command = ? "
							+ "AND objectcommandresponses.object_id = ? "
					);
					removeCommandResponse.setString(1, command);
					removeCommandResponse.setInt(2, objectID);
					
					return true;
				} finally {
					DBUtil.closeQuietly(removeCommandResponse);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	private void removeAllObjectsFromMapTile(final int mapTileID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement removeObject = null;
				try {
					removeObject = conn.prepareStatement(
							"delete from objectstomaptiles "
							+ "where objectstomaptiles.maptile_id = ? "
					);
					removeObject.setInt(1, mapTileID);
					removeObject.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(removeObject);
					DBUtil.closeQuietly(conn);
				}
			}
		});
	}
	
	
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}



	@Override
	public ArrayList<Quest> getAllQuests() {
		// TODO Auto-generated method stub
		return null;
	}
}
