package edu.ycp.cs320.middle_earth.persist;

import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.middle_earth.persist.InitialData;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;


public class FakeDatabase implements IDatabase {
	
	private Map map;
	private ArrayList<MapTile> mapTileList;
	private ArrayList<Object> objectList;
	private ArrayList<Item> itemList;
	private ArrayList<Quest> questList;	
	private ArrayList<Character> characterList;
	private ArrayList<Inventory> inventoryList;
	private Player player;	
	
	public FakeDatabase() {
//		map = new Map();
//		mapTileList = new ArrayList<MapTile>();
		objectList = new ArrayList<Object>();
		itemList = new ArrayList<Item>();
		questList = new ArrayList<Quest>();
		characterList = new ArrayList<Character>();
		inventoryList = new ArrayList<Inventory>();
		player = new Player();	
		
		readInitialData();
	}
	
	public void readInitialData() {
		try {
			map = InitialData.getMap();
			mapTileList.addAll(InitialData.getMapTiles());
			objectList.addAll(InitialData.getObjects());
			itemList.addAll(InitialData.getItems());
			questList.addAll(InitialData.getQuests());
			inventoryList.addAll(InitialData.getAllInventories());
			player = InitialData.getPlayer();
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	
	public ArrayList<Item> getAllItems() {
		return itemList;
	}
	
	public ArrayList<Object> getAllObjects() {
		//////////////////////////////
		getAllItems();
		//////////////////////////////
		for(Object object : objectList) {
			if(object.getItems() != null) {
				for(Item item : object.getItems()) {
					for(Item listedItem : itemList) {
						if(item.getID() == listedItem.getID()) {
							item.setName(listedItem.getName());
							item.setLongDescription(listedItem.getLongDescription());
							item.setShortDescription(listedItem.getShortDescription());
							item.setItemWeight(listedItem.getItemWeight());
							item.setIsQuestItem(listedItem.getIsQuestItem());
							break;
						}
					}
				}
			}
		}
			
		return objectList;
	}
	
	public ArrayList<MapTile> getAllMapTiles() {
		return mapTileList;
	}
	
	public Map getMap() {
		for(MapTile mapTile : mapTileList) {
			map.addMapTile(mapTile);
		}
		return map;
	}
	
	public ArrayList<Character> getAllCharacters() {
		return characterList;
	}
	
	public Player getPlayer() {
		//////////////////////////////
		getAllInventories();
		/////////////////////////////
		
		for(Inventory inventory : inventoryList) {
			if(inventory.get_inventory_id() == player.get_inventory_id()) {
				player.set_inventory(inventory);
			}
		}
		return player;
	}
	
	public ArrayList<Inventory> getAllInventories() {
		for(Inventory inventory : inventoryList) {
			for(Item item : inventory.get_items()) {
				for(Item listedItem : itemList) {
					if(item.getID() == listedItem.getID())
					{
						item.setName(listedItem.getName());
						item.setLongDescription(listedItem.getLongDescription());
						item.setShortDescription(listedItem.getShortDescription());
						item.setItemWeight(listedItem.getItemWeight());
						item.setIsQuestItem(listedItem.getIsQuestItem());
						break;
					}
				}
			}
		}
		return inventoryList;
	}
	
	public ArrayList<Quest> getAllQuests() {
		for(Quest quest : questList) {
			if(quest.getRewardItems() != null) {
				for(Item item : quest.getRewardItems()) {
					for(Item listedItem : itemList) {
						if(item.getID() == listedItem.getID())
						{
							item.setName(listedItem.getName());
							item.setLongDescription(listedItem.getLongDescription());
							item.setShortDescription(listedItem.getShortDescription());
							item.setItemWeight(listedItem.getItemWeight());
							item.setIsQuestItem(listedItem.getIsQuestItem());
							break;
						}
					}
				}
			}
		}
		return questList;
	}
	
	
	
	public Item getItemByID(int itemID) {
		for(Item item : itemList) {
			if(item.getID() == itemID) {
				return item;
			}
		}
		
		System.out.println("No items match that ID number");
		return null;
	}
	
	public Object getObjectByID(int objectID) {
		for(Object object : objectList) {
			if(object.getID() == objectID) {
				return object;
			}
		}
		
		System.out.println("no objects match that ID number");
		return null;
	}
	
	public MapTile getMapTileByID(int mapTileID) {
		for(MapTile mapTile : mapTileList) {
			if(mapTile.getID() == mapTileID) {
				return mapTile;
			}
		}
		
		System.out.println("No mapTiles match that ID number");
		return null;
	}
	
	public Character getCharacterByName(String characterName) {
		for(Character character : characterList) {
			if(character.get_name() == characterName) {
				return character;
			}
		}
		System.out.println("No character exists by that name");
		return null;
	}
} 
