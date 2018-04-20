package edu.ycp.cs320.middle_earth.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import persist.dbmod.ItemInventory;
import persist.dbmod.ItemObject;
import persist.dbmod.MapTileMap;
import persist.dbmod.ObjectIDCommandResponse;
import persist.dbmod.ObjectMapTile;
import persist.dbmod.User;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;

public class InitialData {
	
//	getCharacters
	
//	getGames

/*	public static ArrayList<Integer> getInventoriesToPlayers() throws IOException {
		ArrayList<Integer> inventoriesToCharactersList = new ArrayList<Integer>();
		ReadCSV readInventoriesToCharacters = new ReadCSV("inventoriestoplayers.csv");
		try {
			while (true) {
				List<String> tuple = readInventoriesToCharacters.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				while (i.hasNext()) {
					inventoriesToCharactersList.add(Integer.parseInt(i.next()));
				}
			}
			return inventoriesToCharactersList;
			
		} finally {
			readInventoriesToCharacters.close();
		}
	}
	
	public static ArrayList<Integer> getInventoriesToVendors() throws IOException {
		ArrayList<Integer> inventoriesToCharactersList = new ArrayList<Integer>();
		ReadCSV readInventoriesToCharacters = new ReadCSV("inventoriestovendors.csv");
		try {
			while (true) {
				List<String> tuple = readInventoriesToCharacters.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				while (i.hasNext()) {
					inventoriesToCharactersList.add(Integer.parseInt(i.next()));
				}
			}
			return inventoriesToCharactersList;
			
		} finally {
			readInventoriesToCharacters.close();
		}
	} */
	
	public static ArrayList<Item> getItems() throws IOException {
		ArrayList<Item> itemList = new ArrayList<Item>();
		ReadCSV readItems = new ReadCSV("items.csv");
		try {
			while (true) {
				List<String> tuple = readItems.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();		
				Item item = new Item();
					
				item.setName(i.next());
 				item.setLongDescription(i.next());
 				item.setShortDescription(i.next());
 				
 				item.set_description_update(i.next());
				item.set_attack_bonus(Integer.parseInt(i.next()));
  				item.set_defense_bonus(Integer.parseInt(i.next()));
  				item.set_hp_bonus(Integer.parseInt(i.next())); 				
 				item.setItemWeight(Integer.parseInt(i.next()));
 				item.set_ItemType(ItemType.valueOf(i.next()));
 				item.set_lvl_requirement(Integer.parseInt(i.next()));
				
				itemList.add(item);
			}
			return itemList;
		} finally {
			readItems.close();
		}
	}
		
	public static ArrayList<ItemInventory> getItemsToInventories() throws IOException {
		ArrayList<ItemInventory> itemInventoryList = new ArrayList<ItemInventory>();
		ReadCSV readItemsToInventories = new ReadCSV("itemstoinventories.csv");
		try {						
			while (true) {
				List<String> tuple = readItemsToInventories.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				ItemInventory itemInventory = new ItemInventory();
				itemInventory.setItemID(Integer.parseInt(i.next()));
				itemInventory.setInventoryID(Integer.parseInt(i.next()));
				
				itemInventoryList.add(itemInventory);
			}
			return itemInventoryList;	
			
		} finally {
			readItemsToInventories.close();
		}
	}
	
	public static ArrayList<ItemObject> getItemsToObjects() throws IOException {
		ArrayList<ItemObject> itemsToObjectsList = new ArrayList<ItemObject>();
		ReadCSV readItemsToObjects = new ReadCSV("itemstoobjects.csv");
		
		try {
			while (true) {
				List<String> tuple = readItemsToObjects.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				ItemObject itemObject = new ItemObject();
				itemObject.setItemID(Integer.parseInt(i.next()));
				itemObject.setObjectID(Integer.parseInt(i.next()));
				
				itemsToObjectsList.add(itemObject);
			}
			return itemsToObjectsList;
			
		} finally {
			readItemsToObjects.close();
		}
	}
	
	public static Map getMap() throws IOException {
		Map map = new Map();
		ReadCSV readMap = new ReadCSV("map.csv");
		try {
			while (true) {
				List<String> tuple = readMap.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				map.setName(i.next());
				map.setShortDescription(i.next());
				map.setLongDescription(i.next());
			}
			return map;
		} finally {
			readMap.close();
		}
	}
	
	public static ArrayList<MapTileMap> getMapTilesToMaps() throws IOException {
		ArrayList<MapTileMap> mapTileMapList = new ArrayList<MapTileMap>();
		ReadCSV readMapTilesToMaps = new ReadCSV("maptilestomaps.csv");
		try {
			while (true) {
				List<String> tuple = readMapTilesToMaps.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				MapTileMap mapTileMap = new MapTileMap();
				mapTileMap.setMapTileID(Integer.parseInt(i.next()));
				mapTileMap.setMapID(Integer.parseInt(i.next()));
				mapTileMapList.add(mapTileMap);
			}
			return mapTileMapList;
		} finally {
			readMapTilesToMaps.close();
		}	
	}
	
	public static ArrayList<HashMap<String, Integer>> getMapTileConnections() throws IOException {
		ArrayList<HashMap<String, Integer>> mapTileConnectionsList = new ArrayList<HashMap<String, Integer>>();
		ReadCSV readMapTileConnections = new ReadCSV("maptileconnections.csv");
		try {
			while (true) {
				List<String> tuple = readMapTileConnections.next();
				HashMap<String, Integer> mapTileConnections = new HashMap<String, Integer>();
				
				if(tuple == null) {
					break;
				}
				
				Iterator<String> i = tuple.iterator();
				
				mapTileConnections.put("north", Integer.parseInt(i.next()));
				mapTileConnections.put("northeast", Integer.parseInt(i.next()));
				mapTileConnections.put("east", Integer.parseInt(i.next()));
				mapTileConnections.put("southeast", Integer.parseInt(i.next()));
				mapTileConnections.put("south", Integer.parseInt(i.next()));
				mapTileConnections.put("southwest", Integer.parseInt(i.next()));
				mapTileConnections.put("west", Integer.parseInt(i.next()));
				mapTileConnections.put("northwest", Integer.parseInt(i.next()));
					
				mapTileConnectionsList.add(mapTileConnections);
			}
			return mapTileConnectionsList;
		} finally {
			readMapTileConnections.close();
		}
	}
	
	public static ArrayList<MapTile> getMapTiles() throws IOException {
		ArrayList<MapTile> mapTileList = new ArrayList<MapTile>();
		
		ReadCSV readMapTiles = new ReadCSV("maptiles.csv");
		try {
			int mapTileID = 1;
			while (true) {
				List<String> tuple = readMapTiles.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				MapTile mapTile = new MapTile();
				
				mapTile.setID(mapTileID++);
				mapTile.setName(i.next());
				mapTile.setLongDescription(i.next());
				mapTile.setShortDescription(i.next());
				
				mapTileList.add(mapTile);
			}
			
			return mapTileList;
		} finally {
			readMapTiles.close();
		}
	}
	
	public static ArrayList<ObjectIDCommandResponse> getObjectCommandResponses() throws IOException {
		ArrayList<ObjectIDCommandResponse> objectCommandResponseList = new ArrayList<ObjectIDCommandResponse>();
		
		ReadCSV readObjectCommandResponses = new ReadCSV("objectcommandresponses.csv");
		
		try {
			while (true) {
				List<String> tuple = readObjectCommandResponses.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				ObjectIDCommandResponse objectCommandResponse = new ObjectIDCommandResponse();
				
				while (i.hasNext()) {
					objectCommandResponse.setObjectID(Integer.parseInt(i.next()));
					objectCommandResponse.setCommand(i.next());
					objectCommandResponse.setResponse(i.next());
					objectCommandResponseList.add(objectCommandResponse);
				}
			}
			return objectCommandResponseList;
		} finally {
			readObjectCommandResponses.close();
		}
	}
	
	public static ArrayList<Object> getObjects() throws IOException {
		ArrayList<Object> objectList = new ArrayList<Object>();

		ReadCSV readObjects = new ReadCSV("objects.csv");

		try {
			while (true) {
				List<String> tuple = readObjects.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
		
				Object object = new Object();
				
				object.setName(i.next());
				object.setLongDescription(i.next());
				object.setShortDescription(i.next());
				
				objectList.add(object);
			}
			return objectList;
		} finally {
			readObjects.close();
		}
	} 
	
	public static ArrayList<ObjectMapTile> getObjectsToMapTiles() throws IOException {
		ArrayList<ObjectMapTile> objectsToMapTilesList = new ArrayList<ObjectMapTile>();
		ReadCSV readObjectsToMapTiles = new ReadCSV("objectstomaptiles.csv");
		try {
			while (true) {
				List<String> tuple = readObjectsToMapTiles.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				ObjectMapTile objectMapTile = new ObjectMapTile();
				objectMapTile.setObjectID(Integer.parseInt(i.next()));
				objectMapTile.setMapTileID(Integer.parseInt(i.next()));
				
				objectsToMapTilesList.add(objectMapTile);
			}			
			return objectsToMapTilesList;
			
		} finally {
			readObjectsToMapTiles.close();
		}
	}

	public static Player getPlayer() throws IOException {
		Player player = new Player();
		ReadCSV readPlayer = new ReadCSV("players.csv");
		try {
			List<String> tuple = readPlayer.next();

			Iterator<String> i = tuple.iterator();
			
			player.set_race(i.next());
			player.set_name(i.next());
			player.set_gender(i.next());
			player.set_level(Integer.parseInt(i.next()));
			player.set_hit_points(Integer.parseInt(i.next()));
			
			player.set_magic_points(Integer.parseInt(i.next()));
			player.set_attack(Integer.parseInt(i.next()));
			player.set_defense(Integer.parseInt(i.next()));
			player.set_special_attack(Integer.parseInt(i.next()));
			player.set_special_defense(Integer.parseInt(i.next()));
			
			player.set_coins(Integer.parseInt(i.next()));
			player.set_location(Integer.parseInt(i.next()));
			player.set_inventory_id(Integer.parseInt(i.next()));
			
			/**
			 * The next few lines are equipping armor pieces to pass player
			 * back up to the database.  If there is no item associated with
			 * a slot, it puts an empty item of the correct ItemType in the
			 * slot. There is a check in DerbyDatabase to make sure the any 
			 * item in the slot is of the correct type in actuality, but they
			 * must be passed assuming so until that check.  It would mean 
			 * that the database is broken.  Unfortunately you cannot check
			 * at this stage, you must check after the item is pulled from 
			 * the masteritems table
			 */
			Item item = new Item();
			
			// helm
			item.set_ItemType(ItemType.HELM);
			item.setID(Integer.parseInt(i.next()));
			player.set_helm(item);
			
			// braces
			item.set_ItemType(ItemType.BRACES);
			item.setID(Integer.parseInt(i.next()));
			player.set_braces(item);
			
			// chest
			item.set_ItemType(ItemType.CHEST);
			item.setID(Integer.parseInt(i.next()));
			player.set_chest(item);
			
			// legs
			item.set_ItemType(ItemType.LEGS);
			item.setID(Integer.parseInt(i.next()));
			player.set_legs(item);
			
			// boots
			item.set_ItemType(ItemType.BOOTS);
			item.setID(Integer.parseInt(i.next()));
			player.set_boots(item);
			
			// l_hand
			item.set_ItemType(ItemType.L_HAND);
			item.setID(Integer.parseInt(i.next()));
			player.set_l_hand(item);
			
			// r_hand
			item.set_ItemType(ItemType.R_HAND);
			item.setID(Integer.parseInt(i.next()));
			player.set_r_hand(item);
			
			player.set_experience(Integer.parseInt(i.next()));
			player.set_carry_weight(Integer.parseInt(i.next()));
				
			return player;
		} finally {
			readPlayer.close();
		}
	}
	
	public static ArrayList<Quest> getQuests() throws IOException {
		ArrayList<Quest> questList = new ArrayList<Quest>();
		ReadCSV readQuests = new ReadCSV("quests.csv");
		try {
			while (true) {
				List<String> tuple = readQuests.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				Quest quest = new Quest();
				if(i.hasNext()) {
					String questAtt = i.next();
					if(questAtt.equals("rewardItems")) {
						questAtt = i.next();
						ArrayList<Item> itemList = new ArrayList<Item>();

						while(!questAtt.equals("rewardCoins") && i.hasNext()) {
							Item item = new Item();
							item.setID(Integer.parseInt(questAtt));
							itemList.add(item);
							questAtt = i.next();
						}
						quest.setRewardItems(itemList);
					}
					
					if(i.hasNext() && questAtt.equals("rewardCoins"))
					{
						quest.setRewardCoins(Integer.parseInt(i.next()));
					}
				}
				
				questList.add(quest);
			}
				
			return questList;
		} finally {
			readQuests.close();
		}
	} 
	
	public static ArrayList<User> getUsers() throws IOException {
		ArrayList<User> userList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		try {
			while (true) {
				List<String> tuple = readUsers.next();
				if(tuple == null) {
					break;
				}
				
				Iterator<String> i = tuple.iterator();
				
				User user = new User();
				
				//user.setUserID(Integer.parseInt(i.next()));
				user.setPassword(i.next());
				user.setEmail(i.next());
				userList.add(user);			
			} 
			return userList; 
		} finally {
			readUsers.close();
		}
	}
	
}
