package edu.ycp.cs320.middle_earth.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;

public class InitialData {

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
				
				//map.setID(Integer.parseInt(i.next()));
				map.setName(i.next());
				map.setShortDescription(i.next());
				map.setLongDescription(i.next());
			}
			return map;
		} finally {
			readMap.close();
		}
	}
	
	public static ArrayList<MapTile> getMapTiles() throws IOException {
		ArrayList<MapTile> mapTileList = new ArrayList<MapTile>();
		// Null element for indexing of the array
		MapTile nullMapTile = new MapTile();
		mapTileList.add(nullMapTile);
		
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
				
				for (int j = 0; j < 8; j++) {
					mapTile.setConnection(i.next(), Integer.parseInt(i.next()));
				}
				ArrayList<Object> objects = new ArrayList<Object>();
				
				if (i.hasNext()) {
					String checker = i.next();
					if (checker.equals("objects")) {
						while (i.hasNext()) {
							Object object = new Object();
							object.setID(Integer.parseInt(i.next()));
							objects.add(object);
					}
				}
				
					
					
				}
				mapTile.setObjects(objects);
				mapTileList.add(mapTile);
			}
			
			return mapTileList;
		} finally {
			readMapTiles.close();
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
				
				object.setID(Integer.parseInt(i.next()));
				
				object.setName(i.next());
				object.setLongDescription(i.next());
				object.setShortDescription(i.next());
				HashMap<String, String> commandResponses = new HashMap<String, String>();
				ArrayList<Item> itemList = new ArrayList<Item>();
				while(i.hasNext()) {
					String typeIDString = i.next();
					if(typeIDString.equals("command")) {
						String command = i.next();
						String commandResponse = i.next();
						commandResponses.put(command, commandResponse);
					}
					else if(typeIDString.equals("items")) {
						String itemIDString;
						
						while(i.hasNext()) {
							itemIDString = i.next();
							Item item = new Item();
							
							item.setID(Integer.parseInt(itemIDString));
							itemList.add(item);	
						}
						
					}
					object.setCommandResponses(commandResponses);
					object.setItems(itemList);;
				}
			
				objectList.add(object);
			}
			return objectList;
		} finally {
			readObjects.close();
		}
	} 
	
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
				itemList.add(extractItem(i));
			}
			return itemList;
		} finally {
			readItems.close();
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
				
				/*
				 * Need to insert dialogue HashMap extraction
				 */
				
				questList.add(quest);
			}
				
			return questList;
		} finally {
			readQuests.close();
		}
	} 
	
	public static ArrayList<Inventory> getAllInventories() throws IOException {
		ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
		ReadCSV readInventory = new ReadCSV("inventory.csv");
		try {						
			Inventory inventory = new Inventory();
			ArrayList<Item> itemList = new ArrayList<Item>();

			while (true) {
				List<String> tuple = readInventory.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				inventory.set_inventory_id(Integer.parseInt(i.next()));
				while(i.hasNext()) {
					Item item = new Item();
					item.setID(Integer.parseInt(i.next()));
					itemList.add(item);
				}
				inventory.set_items(itemList);
				inventoryList.add(inventory);
			}
			return inventoryList;	
			
		} finally {
			readInventory.close();
		}
	}
	
	public static Player getPlayer() throws IOException {
		Player player = new Player();
		ReadCSV readPlayer = new ReadCSV("player.csv");
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
			player.set_experience(Integer.parseInt(i.next()));
			player.set_carry_weight(Integer.parseInt(i.next()));
			player.set_inventory_id(Integer.parseInt(i.next()));
				
			return player;
		} finally {
			readPlayer.close();
		}
	}
	
	private static Item extractItem(Iterator<String> i) {		
		Item item = new Item();
			item.setID(Integer.parseInt(i.next()));
			
			item.setName(i.next());
			item.setLongDescription(i.next());
			item.setShortDescription(i.next());
			
			item.setItemWeight(Integer.parseInt(i.next()));
			
			String checkIfTrue = i.next();
			if(checkIfTrue.equals("false")) {
				item.setIsQuestItem(false);
			} else {
				item.setIsQuestItem(true);
			}
		return item;
	}
}
