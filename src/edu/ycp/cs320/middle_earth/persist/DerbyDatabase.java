package edu.ycp.cs320.middle_earth.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
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
	
	private void loadItem(Item item, ResultSet resultSet, int index) throws SQLException {
		item.setID(resultSet.getInt(index++));
		item.setName(resultSet.getString(index++));
		item.setLongDescription(resultSet.getString(index++));
		item.setShortDescription(resultSet.getString(index++));
		item.setItemWeight(resultSet.getFloat(index++));	
		item.setIsQuestItem(resultSet.getBoolean(index++));
	}
	
	private void loadObject(Object object, ResultSet resultSet, int index) throws SQLException {
		object.setID(resultSet.getInt(index++));
		object.setName(resultSet.getString(index++));
		object.setLongDescription(resultSet.getString(index++));
		object.setShortDescription(resultSet.getString(index++));
		//object.getCommandResponses().put(resultSet.getString(index++), resultSet.getString(index++));
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
	
/*	private void loadInventory() {
		
	}
	
	private void loadQuests() {
		
	}
	
	private void loadPlayer() {
		
	}
*/	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;		// items table
				PreparedStatement stmt2 = null;		// objects table
				PreparedStatement stmt3 = null;		// itemstoobjects table
				PreparedStatement stmt4 = null;		// maptileconnections table
				PreparedStatement stmt5 = null;		// maptiles table
//				PreparedStatement stmt6 = null;		//  
//				PreparedStatement stmt7 = null;
				
				try {
					int tableCounter = 0;
					stmt1 = conn.prepareStatement(
							"create table items (" + 
							"	item_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	itemname varchar(40)," + // constraint author_id references authors, " +
							"	longdescription varchar(200)," +
							"	shortdescription varchar(100)," +
							"   weight float," +
							"   isquestitem boolean " +
							")"
					);
					stmt1.executeUpdate();
					System.out.println("\t" + ++tableCounter + ". <items> table created");
					
					stmt2 = conn.prepareStatement(
							"create table objects (" +
							"	object_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	objectname varchar(40)," + // constraint author_id references authors, " +
							"	longdescription varchar(200)," +
							"	shortdescription varchar(100)" +
							")"
					);
					stmt2.executeUpdate();
					System.out.println("\t" + ++tableCounter + ". <objects> table created");
					
					stmt3 = conn.prepareStatement(
							"create table itemstoobjects (" +
							"   item_id int," +
							"   object_id int" +
							")"
					);
					stmt3.executeUpdate();
					System.out.println("\t" + ++tableCounter + ". <itemstoobjects> table created");

					
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
					System.out.println("\t" + ++tableCounter + ". <maptileconnections> table created");
					
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
					System.out.println("\t" + ++tableCounter + ". <maptiles> table created");
					
/*					stmt6 = conn.prepareStatement(
							"create table map (" +
							"   maptile_id integer primary key " +
							"       generated always as identity (start with 1, increment by 1), " +
							"   mapname varchar(40)," +							
							"	longdescription varchar(200)," +
							"	shortdescription varchar(100)" +
							")"
					);
					stmt6.executeUpdate();
					System.out.println("\t" + ++tableCounter + ". <map> table created");
					*/
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
//					DBUtil.closeQuietly(stmt6);
//					DBUtil.closeQuietly(stmt7);
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
				ArrayList<HashMap<String, Integer>> mapTileConnectionsList = new ArrayList<HashMap<String, Integer>>();
				ArrayList<MapTile> mapTileList;
//				ArrayList<Map> mapList;
				
				try {
					itemList = InitialData.getItems();
					objectList = InitialData.getObjects();
					mapTileConnectionsList = InitialData.getMapTileConnections();
					mapTileList = InitialData.getMapTiles();
//					mapList = InitialData.getMapList();
					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertItem = null;
				PreparedStatement insertObject = null;
				PreparedStatement insertItemToObject = null;
				PreparedStatement insertMapTileConnections = null;
				PreparedStatement insertMapTile = null;
//				PreparedStatement insertMap = null;

				try {		
					int tableLoadCounter = 0;
								
					insertItem = conn.prepareStatement("insert into items (itemname, longdescription, shortdescription, weight, isquestitem) values (?, ?, ?, ?, ?)");
					for (Item item : itemList) {
//						insertItem.setInt(1, item.getItemId());		// auto-generated primary key, don't insert this
						insertItem.setString(1, item.getName());
						insertItem.setString(2, item.getLongDescription());
						insertItem.setString(3, item.getShortDescription());
						insertItem.setFloat(4,  item.getItemWeight());
						insertItem.setBoolean(5, item.getIsQuestItem());
						insertItem.addBatch();
					}
					insertItem.executeBatch();
					System.out.println("\t" + ++tableLoadCounter + ". <items> table loaded");
					
					insertObject = conn.prepareStatement("insert into objects (objectname, longdescription, shortdescription) values (?, ?, ?)");
					for (Object object : objectList) {
//						insertObject.setInt(1, object.getobjectId());		// auto-generated primary key, don't insert this
						//insertObject.setString(1, "climb");
						
						insertObject.setString(1, object.getName());
						insertObject.setString(2, object.getLongDescription());
						insertObject.setString(3, object.getShortDescription());
						//insertObject.setString(5, object.getCommandResponses().get("climb"));
						
					/*	insertItemToObject = conn.prepareStatement("insert into itemstoobjects (item_id, object_id) values (?, ?)");
						for (Item item : object.getItems()) {
							insertItemToObject.setInt(1, item.getID());
							insertItemToObject.setInt(2, object.getID());
							insertItemToObject.addBatch();
						}
						insertItemToObject.executeBatch();
					*/		
						insertObject.addBatch();
					}
					insertObject.executeBatch();
					System.out.println("\t" + ++tableLoadCounter + ". <objects> table loaded");
					//System.out.println("\t" + ++tableLoadCounter + ". <itemstoobjects> table loaded");
					
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
						// auto generated connection ID that correlates to mapTile_ID
						//insertMaptileConnection.setInt(1, getMapTile_ID);
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
					System.out.println("\t" + ++tableLoadCounter + ". <maptileconnections> table loaded");
					
					insertMapTile = conn.prepareStatement("insert into maptiles (maptilename, longdescription, shortdescription) values (?, ?, ?)");
					for (MapTile mapTile : mapTileList) {
//						insertMapTile.setInt(1, mapTile.getmapTileId());		// auto-generated primary key, don't insert this
						insertMapTile.setString(1, mapTile.getName());
						insertMapTile.setString(2, mapTile.getLongDescription());
						insertMapTile.setString(3, mapTile.getShortDescription());
						insertMapTile.addBatch();
					}
					insertMapTile.executeBatch();
					System.out.println("\t" + ++tableLoadCounter + ". <maptiles> table loaded");
					
/*					insertMap = conn.prepareStatement("insert into maps (mapname, longdescription, shortdescription) values (?, ?, ?)");
					for (Map map : mapList) {
//						insertMap.setInt(1, map.getmapId());		// auto-generated primary key, don't insert this
						insertMap.setString(1, map.getName());
						insertMap.setString(2, map.getLongDescription());
						insertMap.setString(3, map.getShortDescription());
						insertMap.addBatch();
					}
					insertMap.executeBatch();	
					System.out.println("\t" + ++tableLoadCounter + ". <map> table loaded");
					*/
					
					return true;
				} finally {
//					DBUtil.closeQuietly(insertMap);
					DBUtil.closeQuietly(insertMapTile);
					DBUtil.closeQuietly(insertMapTileConnections);
					DBUtil.closeQuietly(insertItemToObject);
					DBUtil.closeQuietly(insertObject);
					DBUtil.closeQuietly(insertItem);
				}
			}
		});
	}
	


	@Override
	public Map getMap() {
		return executeTransaction(new Transaction <Map>() {
			@Override
			public Map execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * " +
							"  from maps "
					);
					
					Map result = new Map();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Map map = new Map();
						loadMap(map, resultSet, 1);
						
						result = map;
					}
					
					// check if the maps were found
					if (!found) {
						System.out.println("<maps> table is empty");
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
	public Player getPlayer() {
		// TODO Auto-generated method stub
		return null;
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
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * " +
							"  from objects "
					);
					
					ArrayList<Object> result = new ArrayList<Object>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Object object = new Object();
						loadObject(object, resultSet, 1);
						
					/*	Item item = new Item();
						while(true) {
							loadItem(item, resultSet, 5);
							if(item != null) {
								object.getItems().add(getItemByID(item.getID()));
							} else {
								break;
							}
						}	*/
						
						// object.maptile_id = last element of resultSet
						
						result.add(object);
					}
					
					// check if the title was found
					if (!found) {
						System.out.println("<objects> table is empty");
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
	public ArrayList<MapTile> getAllMapTiles() {
		return executeTransaction(new Transaction <ArrayList<MapTile>>() {
			@Override
			public ArrayList<MapTile> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * "
							+ "from maptiles, maptileconnections "
							+ "where maptiles.maptile_id = maptileconnections.maptile_id"
					);
					
					ArrayList<MapTile> result = new ArrayList<MapTile>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						MapTile mapTile = new MapTile();
						loadMapTile(mapTile, resultSet, 1);
						
						// remember to skip an index for the repeated maptile_id from the connections table
						loadMapTileConnections(mapTile.getConnections(), resultSet, 6);
						
						result.add(mapTile);
					}
					
					// check if the maptiles were found
					if (!found) {
						System.out.println("<maptiles> table is empty");
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
	public ArrayList<Character> getAllCharacters() {
		// TODO Auto-generated method stub
		return null;
	}

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

				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * " +
							"  from objects, items "
							+ "where objects.object_id = ? "
							+ "objects.item_id = items.item_id"
					);
					stmt.setInt(1, objectID);
					
					resultSet = stmt.executeQuery();
					
					Object result = new Object();

					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						loadObject(result, resultSet, 1);
						
						ResultSetMetaData rsmd = resultSet.getMetaData();
						for(int i = 7; i <= rsmd.getColumnCount(); i++) {
							result.getItems().add(getItemByID(resultSet.getInt(i)));
						}
					}
					
					// check if the object was found
					if (!found) {
						System.out.println("no objects with that id");
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
	public MapTile getMapTileByID(int mapTileID) {
		return executeTransaction(new Transaction <MapTile>() {
			@Override
			public MapTile execute(Connection conn) throws SQLException {

				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				try {
					// retrieve all attributes
					stmt = conn.prepareStatement(
							"select * " +
							"  from maptiles, maptileconnections "
							+ "where maptiles.maptile_id = ? "
							+ " and maptiles.maptile_id = maptileconnections.maptile_id"
					);
					stmt.setInt(1, mapTileID);
					
					resultSet = stmt.executeQuery();
							
/*					ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
					resultSetMetaData.getColumnCount();*/
					
					MapTile result = new MapTile();

					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						loadMapTile(result, resultSet, 1);
						loadMapTileConnections(result.getConnections(), resultSet, 6);
					}
					
					// check if the maptile was found
					if (!found) {
						System.out.println("no maptiles with that id");
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
	public Character getCharacterByName(String characterName) {
		// TODO Auto-generated method stub
		return null;
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
}
