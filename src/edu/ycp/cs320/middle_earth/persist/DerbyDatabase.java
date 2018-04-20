package edu.ycp.cs320.middle_earth.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import persist.dbmod.ItemInventory;
import persist.dbmod.ItemObject;
import persist.dbmod.MapTileMap;
import persist.dbmod.ObjectIDCommandResponse;
import persist.dbmod.ObjectMapTile;
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
		
		item.set_description_update(resultSet.getString(index++));
		item.set_attack_bonus(resultSet.getInt(index++));
  		item.set_defense_bonus(resultSet.getInt(index++));
  		item.set_hp_bonus(resultSet.getInt(index++));	

		item.setItemWeight(resultSet.getFloat(index++));	
		item.set_ItemType(ItemType.valueOf(resultSet.getString(index++))); 
		item.set_lvl_requirement(resultSet.getInt(index++));
	}
	
	private void loadObject(Object object, ResultSet resultSet, int index) throws SQLException {
		object.setID(resultSet.getInt(index++));
		object.setName(resultSet.getString(index++));
		object.setLongDescription(resultSet.getString(index++));
		object.setShortDescription(resultSet.getString(index++));
		//object.getCommandResponses().put(resultSet.getString(index++), resultSet.getString(index++));
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
	}
	 
	private void loadMap(Map map, ResultSet resultSet, int index) throws SQLException {
		map.setID(resultSet.getInt(index++));
		map.setName(resultSet.getString(index++));
		map.setLongDescription(resultSet.getString(index++));
		map.setShortDescription(resultSet.getString(index++));	
	}
	
	private void loadPlayer(Player player, ResultSet resultSet, int index) {
		try {
		player.set_race(resultSet.getString(index++));
		player.set_name(resultSet.getString(index++));
		player.set_gender(resultSet.getString(index++));
		player.set_level(resultSet.getInt(index++));
		player.set_hit_points(resultSet.getInt(index++));
		
		player.set_magic_points(resultSet.getInt(index++));
		player.set_attack(resultSet.getInt(index++));
		player.set_defense(resultSet.getInt(index++));
		player.set_special_attack(resultSet.getInt(index++));
		player.set_special_defense(resultSet.getInt(index++));
		
		player.set_coins(resultSet.getInt(index++));
		player.set_location(resultSet.getInt(index++));
		player.set_inventory_id(resultSet.getInt(index++));

		int carryWeight = 0;
		player.set_inventory(getInventoryByID(player.get_inventory_id()));
		
		// Sum up inventory weight
		for(Item item : player.get_inventory().get_items()) {
			carryWeight += item.getItemWeight();
		}

		Item emptyItemSlot = new Item();
		emptyItemSlot.set_attack_bonus(0);
		emptyItemSlot.set_defense_bonus(0);
		emptyItemSlot.set_description_update("You haven't equipped one");
		emptyItemSlot.set_hp_bonus(0);
		emptyItemSlot.set_lvl_requirement(0);
		emptyItemSlot.setID(0);
		emptyItemSlot.setIsQuestItem(false);
		emptyItemSlot.setItemWeight(0);
		emptyItemSlot.setLongDescription("Empty Slot");
		emptyItemSlot.setShortDescription("Empty Slot");
		emptyItemSlot.setName("Empty Slot");

		int itemID;
		
		// helm
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.set_ItemType(ItemType.HELM);
			player.set_helm(emptyItemSlot);
		} else {
			player.set_helm(getItemByID(itemID));
			carryWeight += player.get_helm().getItemWeight();
		}
		
		// braces
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.set_ItemType(ItemType.BRACES);
			player.set_braces(emptyItemSlot);
		} else {
			player.set_braces(getItemByID(itemID));
			carryWeight += player.get_braces().getItemWeight();
		}		
		
		// chest
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.set_ItemType(ItemType.CHEST);
			player.set_chest(emptyItemSlot);
		} else {
			player.set_chest(getItemByID(itemID));
			carryWeight += player.get_chest().getItemWeight();
		}
		
		// legs
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.set_ItemType(ItemType.LEGS);
			player.set_legs(emptyItemSlot);
		} else {
			player.set_legs(getItemByID(itemID));
			carryWeight += player.get_legs().getItemWeight();
		}
		
		// boots
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.set_ItemType(ItemType.BOOTS);
			player.set_boots(emptyItemSlot);
		} else {
			player.set_boots(getItemByID(itemID));
			carryWeight += player.get_boots().getItemWeight();
		}
		
		// l_hand
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.set_ItemType(ItemType.L_HAND);
			player.set_l_hand(emptyItemSlot);
		} else {
			player.set_l_hand(getItemByID(itemID));
			carryWeight += player.get_l_hand().getItemWeight();
		}
		
		// r_hand
		if((itemID = resultSet.getInt(index++)) == 0) {
			emptyItemSlot.set_ItemType(ItemType.R_HAND);
			player.set_r_hand(emptyItemSlot);
		} else {
			player.set_r_hand(getItemByID(itemID));
			carryWeight += player.get_r_hand().getItemWeight();
		}
		
		player.set_carry_weight(index++);
		player.set_experience(resultSet.getInt(index++));
		
		// Add the sum total of weight to the inventory
		player.get_inventory().set_weight(carryWeight);
		
		} catch (SQLException e) {
			System.out.println("Not implemented\n" + e );
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
				PreparedStatement stmt10 = null;	//	maps table
				PreparedStatement stmt11 = null;	// maptilestomaps table
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
							"	shortdescription varchar(100)" +
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
				ArrayList<ItemObject> itemsToObjectsList;
				ArrayList<ObjectIDCommandResponse> objectCommandResponseList;
				ArrayList<HashMap<String, Integer>> mapTileConnectionsList;
				ArrayList<MapTile> mapTileList;
				ArrayList<ObjectMapTile> objectsToMapTilesList;
				ArrayList<ItemInventory> itemsToInventoriesList;
				Player player;
				Map map; 			//	ArrayList<Map> mapList;
				ArrayList<MapTileMap> mapTilesToMapsList;
				ArrayList<User> userList;
				
				try {
					itemList = InitialData.getItems();
					objectList = InitialData.getObjects();
					itemsToObjectsList = InitialData.getItemsToObjects();
					objectCommandResponseList = InitialData.getObjectCommandResponses();
					mapTileConnectionsList = InitialData.getMapTileConnections();
					mapTileList = InitialData.getMapTiles();
					objectsToMapTilesList = InitialData.getObjectsToMapTiles();
					itemsToInventoriesList = InitialData.getItemsToInventories();
					player = InitialData.getPlayer();
					map = InitialData.getMap();		//	mapList = InitialData.getMapList();
					mapTilesToMapsList = InitialData.getMapTilesToMaps();
					userList = InitialData.getUsers();
					
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
				PreparedStatement insertMap = null;			//insertMaps
				PreparedStatement insertMapTilesToMaps = null;
				PreparedStatement insertObjectCommandResponses = null;
				PreparedStatement insertUsers = null;
				

				try {					
					insertItem = conn.prepareStatement("insert into items (itemname, longdescription, shortdescription, "
							+ "descriptionupdate, attackbonus, defensebonus, hpbonus, "
							+ "weight, itemtype, levelreq) "
							+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Item item : itemList) {
						insertItem.setString(1, item.getName());
						insertItem.setString(2, item.getLongDescription());
						insertItem.setString(3, item.getShortDescription());
						insertItem.setString(4, item.get_description_update());
						insertItem.setInt(5, item.get_attack_bonus());
						insertItem.setInt(6, item.get_defense_bonus());
						insertItem.setInt(7, item.get_hp_bonus());						
						insertItem.setFloat(8,  item.getItemWeight());
						insertItem.setString(9, String.valueOf(item.get_ItemType())); 
						insertItem.setInt(10, item.get_lvl_requirement());
						
						insertItem.addBatch();
					}
					insertItem.executeBatch();
					
					insertObject = conn.prepareStatement("insert into objects (objectname, longdescription, shortdescription) values (?, ?, ?)");
					for (Object object : objectList) {
						//insertObject.setString(1, "climb");
						
						insertObject.setString(1, object.getName());
						insertObject.setString(2, object.getLongDescription());
						insertObject.setString(3, object.getShortDescription());
						//insertObject.setString(5, object.getCommandResponses().get("climb"));
							
						insertObject.addBatch();
					}
					insertObject.executeBatch();
					
					insertItemsToObjects = conn.prepareStatement("insert into itemstoobjects (item_id, object_id) values (?, ?)");
					for(ItemObject itemObject : itemsToObjectsList) {
						insertItemsToObjects.setInt(1, itemObject.getItemID());
						insertItemsToObjects.setInt(2, itemObject.getObjectID());
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
					
					insertMapTile = conn.prepareStatement("insert into maptiles (maptilename, longdescription, shortdescription) values (?, ?, ?)");
					for (MapTile mapTile : mapTileList) {
						insertMapTile.setString(1, mapTile.getName());
						insertMapTile.setString(2, mapTile.getLongDescription());
						insertMapTile.setString(3, mapTile.getShortDescription());
						insertMapTile.addBatch();
					}
					insertMapTile.executeBatch();
					
					insertObjectsToMapTiles = conn.prepareStatement("insert into objectstomaptiles (object_id, maptile_id) values (?, ?)");
					for(ObjectMapTile objectMapTile : objectsToMapTilesList) {
						insertObjectsToMapTiles.setInt(1, objectMapTile.getObjectID());
						insertObjectsToMapTiles.setInt(2, objectMapTile.getMapTileID());
						insertObjectsToMapTiles.addBatch();
					}
					insertObjectsToMapTiles.executeBatch();
					
					insertItemsToInventories = conn.prepareStatement("insert into itemstoinventories (item_id, inventory_id) values (?, ?)");
					for(ItemInventory itemInventory : itemsToInventoriesList) {
						insertItemsToInventories.setInt(1, itemInventory.getItemID());
						insertItemsToInventories.setInt(2, itemInventory.getInventoryID());
						insertItemsToInventories.addBatch();
					}
					insertItemsToInventories.executeBatch();
					
					
					// Can easily be changed to Players with a for each
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
						int i = 1;
						insertPlayers.setString(i++, player.get_race());
						insertPlayers.setString(i++, player.get_name());
						insertPlayers.setString(i++, player.get_gender());
						insertPlayers.setInt(i++, player.get_level());
						insertPlayers.setInt(i++, player.get_hit_points());
						
						insertPlayers.setInt(i++, player.get_magic_points());
						insertPlayers.setInt(i++, player.get_attack());
						insertPlayers.setInt(i++, player.get_defense());
						insertPlayers.setInt(i++, player.get_special_attack());
						insertPlayers.setInt(i++, player.get_special_defense());
						
						insertPlayers.setInt(i++, player.get_coins());
						insertPlayers.setInt(i++, player.get_location());
						insertPlayers.setInt(i++, player.get_inventory_id());
						insertPlayers.setInt(i++, player.get_helm().getID());
						insertPlayers.setInt(i++, player.get_braces().getID());
						
						insertPlayers.setInt(i++, player.get_chest().getID());
						insertPlayers.setInt(i++, player.get_legs().getID());
						insertPlayers.setInt(i++, player.get_boots().getID());
						insertPlayers.setInt(i++, player.get_l_hand().getID());
						insertPlayers.setInt(i++, player.get_r_hand().getID());
						
						insertPlayers.setInt(i++, player.get_experience());
						insertPlayers.setInt(i++, player.get_carry_weight());
							
						insertPlayers.addBatch();	
					insertPlayers.executeBatch();							
						
					insertMap = conn.prepareStatement("insert into maps (mapname, longdescription, shortdescription) values (?, ?, ?)");
					//for (Map map : mapList) {
						insertMap.setString(1, map.getName());
						insertMap.setString(2, map.getLongDescription());
						insertMap.setString(3, map.getShortDescription());
						insertMap.addBatch();
					//}
					insertMap.executeBatch();	
					
					insertMapTilesToMaps = conn.prepareStatement(" insert into maptilestomaps (maptile_id, map_id) values (?, ?)");
					for(MapTileMap mapTileMap : mapTilesToMapsList) {
						insertMapTilesToMaps.setInt(1, mapTileMap.getMapTileID());
						insertMapTilesToMaps.setInt(2, mapTileMap.getMapID());
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
					
					return true;
				} finally {
//					DBUtil.closeQuietly(insertMap);
					DBUtil.closeQuietly(insertPlayers);
					DBUtil.closeQuietly(insertItemsToInventories);
					DBUtil.closeQuietly(insertObjectsToMapTiles);
					DBUtil.closeQuietly(insertMapTile);
					DBUtil.closeQuietly(insertMapTileConnections);
					DBUtil.closeQuietly(insertItemsToObjects);
					DBUtil.closeQuietly(insertObject);
					DBUtil.closeQuietly(insertItem);
					DBUtil.closeQuietly(insertUsers);
				}
			}
		});
	}
	
	

	
	/**************************************************************************************************
	 * 										*Get All* Methods
	 **************************************************************************************************/

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
				}
			}
		});
	}

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
				}
			}
		});
	}
	
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
				}
			}
		});
	}

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
				}
			}
		});
	}

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
						loadMapTileConnections(mapTile.getConnections(), resultSetMapTiles, 6);
						
						
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
				}
			}
		});
	}

	@Override
	public ArrayList<Character> getAllCharacters() {
		ArrayList<Character> characterList = new ArrayList<Character>();
		characterList.add(getPlayer());
		return characterList;
	}

	/*
		This method is unnecessary now that getPlayers populates the inventory
	*/
	@Override
	public ArrayList<Inventory> getAllInventories() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Quest> getAllQuests() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<String> getAllUserNames() {
		return executeTransaction(new Transaction<ArrayList<String>>() {
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
				}
			}
		});
	}
	
	/******************************************************************************************************
	 * 										*Get Specific* Methods
	 ******************************************************************************************************/
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
						loadMapTileConnections(mapTile.getConnections(), resultSetMapTiles, 6);
						
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
				}
			}
		});
	}

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
					inventory.set_items(itemList);
					
					return inventory;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public Character getCharacterByName(String characterName) {
		// TODO Auto-generated method stub
		return null;
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
				}
			}
		});
	}
	/*******************************************************************************************************
	*											load/save game
	********************************************************************************************************/
	// Will need to take gameID in future
	public Game loadGame() {
		
		Game game = new Game();
		game.set_map(getMap());
		game.set_objects(getAllObjects());
		game.set_items(getAllItems());
		ArrayList<Character> characterList = new ArrayList<Character>();
		characterList.add(getPlayer());
		game.set_characters(characterList);
		
		return game;
	}
	
	public void saveGame(Game game) {
		updateMap(game.get_map());
		updateCharacters(game.get_characters());
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
		// update all objects on maptile
			// remove them all
			// add the ones passed in maptile.getObjects()
		
		// update mapTile Connections										// For the editor
		// Update Enemy String
		// update maptile visited
		
		// Will need to update random_encounters
		
		for(MapTile mapTile : mapTileList) {
			updateMapTile(mapTile);
		}
	}
	
	private void updateMapTile(final MapTile mapTile) {
		// removes all objects from the objectstomaptile table
		removeAllObjectsFromMapTile(mapTile.getID());
		
		// adds every object in the maptile.getObjects() objectList
		// to the objectstomaptiles table
		int count = 0;
		if (mapTile.getObjects() != null) {
			for (Object object : mapTile.getObjects()) {
				count++;
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
				addItemToObject(item.getID(), object.getID());
			}
		}
	}
	
	private void updateCharacters(ArrayList<Character> characterList) {
		updatePlayer(characterList.get(0));
		characterList.get(0).set_inventory(getInventoryByID(characterList.get(0).get_inventory_id()));
	}
	
	private void updatePlayer(Character player) {
		executeTransaction(new Transaction<Boolean>() {
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmtRemovePlayer = null;
				PreparedStatement stmtUpdatePlayer = null;
				
				try {
					stmtRemovePlayer = conn.prepareStatement(
							"delete from players "
							+ "where players.name = ? "
					);
					stmtRemovePlayer.setString(1, player.get_name());
					stmtRemovePlayer.executeUpdate();
					
					stmtUpdatePlayer = conn.prepareStatement(
							"insert into players ("
							+ "race, name, gender, level, hit_points, "
							+ "magic_points, attack, defense, sp_attack, sp_defense, "
							+ "coins, map_location, inventory_id, helm_item_id, braces_item_id, "
							+ "chest_item_id, legs_item_id, boots_item_id, l_hand_item_id, r_hand_item_id) "
							+ "values("
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?,"
							+ "?, ?, ?, ?, ?)" 
					);
					
						int i = 1;
						stmtUpdatePlayer.setString(i++, player.get_race());
						stmtUpdatePlayer.setString(i++, player.get_name());
						stmtUpdatePlayer.setString(i++, player.get_gender());
						stmtUpdatePlayer.setInt(i++, player.get_level());
						stmtUpdatePlayer.setInt(i++, player.get_hit_points());
						
						stmtUpdatePlayer.setInt(i++, player.get_magic_points());
						stmtUpdatePlayer.setInt(i++, player.get_attack());
						stmtUpdatePlayer.setInt(i++, player.get_defense());
						stmtUpdatePlayer.setInt(i++, player.get_special_attack());
						stmtUpdatePlayer.setInt(i++, player.get_special_defense());
						
						stmtUpdatePlayer.setInt(i++, player.get_coins());
						stmtUpdatePlayer.setInt(i++, player.get_location());
						stmtUpdatePlayer.setInt(i++, player.get_inventory_id());
						stmtUpdatePlayer.setInt(i++, player.get_helm().getID());
						stmtUpdatePlayer.setInt(i++, player.get_braces().getID());
						
						stmtUpdatePlayer.setInt(i++, player.get_chest().getID());
						stmtUpdatePlayer.setInt(i++, player.get_legs().getID());
						stmtUpdatePlayer.setInt(i++, player.get_boots().getID());
						stmtUpdatePlayer.setInt(i++, player.get_l_hand().getID());
						stmtUpdatePlayer.setInt(i++, player.get_r_hand().getID());
							
						stmtUpdatePlayer.addBatch();	
						stmtUpdatePlayer.executeBatch();	
						
					return true;
				
				} finally {
					DBUtil.closeQuietly(stmtRemovePlayer);
					DBUtil.closeQuietly(stmtUpdatePlayer);
				}
			}
		});
	}
	
	private void updateInventory(Inventory inventory) {
		throw new UnsupportedOperationException("Not yet implemented");
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
						insertItem.setString(4, item.get_description_update());
						insertItem.setInt(5, item.get_attack_bonus());
						insertItem.setInt(6, item.get_defense_bonus());
						insertItem.setInt(7, item.get_hp_bonus());						
						insertItem.setFloat(8,  item.getItemWeight());
						insertItem.setString(9, String.valueOf(item.get_ItemType())); 
						insertItem.setInt(10, item.get_lvl_requirement());
						insertItem.addBatch();
					insertItem.executeBatch();
					return true;
				} finally {
				insertItem.close();
				}
			}
		});
	}
	/*******************************************************************************************************
	 * 											addToConstruct Methods
	 *******************************************************************************************************/
	
	// addItemToInventory
	
	public Item addItemToInventory(final int itemID, final int inventoryID) {
		return executeTransaction(new Transaction <Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into itemstoinventories (item_id, inventory_id) "
							+ "values (?, ?)");
					stmt.setInt(1, itemID);
					stmt.setInt(2, inventoryID);
					stmt.executeUpdate();
					
					Item item = new Item();
					item = getItemByID(itemID);
					
					return item;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}	
	
	public Item addItemToObject(final int itemID, final int objectID) {
		return executeTransaction(new Transaction <Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into itemstoobjects (item_id, object_id) "
							+ "values (?, ?)");
							
					stmt.setInt(1, itemID);
					stmt.setInt(2, objectID);
					stmt.executeUpdate();
					
					Item item = new Item();
					item = getItemByID(itemID);
					
					return item;
				} finally {
					DBUtil.closeQuietly(stmt);
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
				}
			}
			
		});
	}
	
	
	/*******************************************************************************************************
	 * 											removeFromConstruct Methods
	 *******************************************************************************************************/
	
	public Item removeItemFromInventory(final int itemID, final int inventoryID) {
		return executeTransaction(new Transaction <Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from itemstoinventories "
							+ "where itemstoinventories.item_id = ? "
							+ "AND itemstoinventories.inventory_id = ? ");
					stmt.setInt(1, itemID);
					stmt.setInt(2, inventoryID);
					stmt.executeUpdate();
					
					Item item = new Item();
					item = getItemByID(itemID);
					
					return item;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
 	public Item removeItemFromObject(final int itemID, final int objectID) {
		return executeTransaction(new Transaction <Item>() {
			@Override
			public Item execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"delete from itemstoobjects "
							+ "where itemstoobjects.item_id = ? "
							+ "AND itemstoobjects.object_id = ? ");
					stmt.setInt(1, itemID);
					stmt.setInt(2, objectID);
					stmt.executeUpdate();
					
					Item item = new Item();
					item = getItemByID(itemID);
					
					return item;
				} finally {
					DBUtil.closeQuietly(stmt);
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
							+ "where objectstomaptiles.maptile_ID = ? "
					);
					removeObject.setInt(1, mapTileID);
					removeObject.executeUpdate();
					
					return true;
				} finally {
					DBUtil.closeQuietly(removeObject);
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
				}
			}
		});
	}
	/*******************************************************************************************************
	 * 									Combinations of Multi-Methods
	 *******************************************************************************************************/
	
	// Take from Object - Remove from itemstoobjects table, add to itemstoinventories table
	
	
	/*******************************************************************************************************
	 * 							Query for Conflict (e.g. maptiles in the same location)
	 *******************************************************************************************************/
	
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}
}
