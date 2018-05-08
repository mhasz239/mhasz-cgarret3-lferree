package edu.ycp.cs320.middle_earth.persist;

import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

import java.io.IOException;
import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.persist.InitialData;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
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
	private ArrayList<Player> playerList;	
	
	public FakeDatabase() {
		map = new Map();
		mapTileList = new ArrayList<MapTile>();
		objectList = new ArrayList<Object>();
		itemList = new ArrayList<Item>();
		questList = new ArrayList<Quest>();
		characterList = new ArrayList<Character>();
		inventoryList = new ArrayList<Inventory>();
		playerList = new ArrayList<Player>();	
		
		readInitialData();
	}
	
	public void readInitialData() {
		try {
			itemList.addAll(InitialData.getItems());
//			inventoryList.addAll(InitialData.getItemsToInventories());
			objectList.addAll(InitialData.getObjects());
			mapTileList.addAll(InitialData.getMapTiles());
			//map = InitialData.getMap();
			questList.addAll(InitialData.getQuests());
			playerList = InitialData.getPlayers();
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	
	@Override
	public ArrayList<Item> getAllItems() {
		return itemList;
	}
	
	@Override
	public ArrayList<Object> getAllObjects() {
		
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
	
	@Override
	public ArrayList<MapTile> getAllMapTiles() {
		
		for (MapTile mapTile :mapTileList) {
			if (mapTile.getObjects() != null) {
				for (Object object : mapTile.getObjects()) {
					for (Object listedObject : getAllObjects()) {
						if (object.getID() == listedObject.getID()) {
							object.setCommandResponses(listedObject.getCommandResponses());
							object.setItems(listedObject.getItems());
							object.setLongDescription(listedObject.getLongDescription());
							object.setName(listedObject.getName());
							object.setShortDescription(listedObject.getShortDescription());
							break;
						}
					}
				}
			}
		}
		return mapTileList;
	}
	
	@Override
	public Map getMap() {
		getAllItems();
		getAllObjects();
		getAllMapTiles();
		for(MapTile mapTile : mapTileList) {
			map.addMapTile(mapTile);
		}
		return map;
	}
	
	@Override
	public ArrayList<Character> getAllCharacters() {
		return characterList;
	}
	
	@Override
	public Player getPlayer() {
		
		for(Inventory inventory : inventoryList) {
			if(inventory.getinventory_id() == playerList.get(0).getinventory_id()) {
				playerList.get(0).setinventory(inventory);
			}
		}
		return playerList.get(0);
	}
	
/*	public ArrayList<Inventory> getAllInventories() {
		for(Inventory inventory : inventoryList) {
			for(Item item : inventory.getitems()) {
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
*/	
	@Override
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
	
	
	
	@Override
	public Item getItemByID(int itemID) {
		for(Item item : itemList) {
			if(item.getID() == itemID) {
				return item;
			}
		}
		
		System.out.println("No items match that ID number");
		return null;
	}
	
	@Override
	public Object getObjectByID(int objectID) {
		for(Object object : objectList) {
			if(object.getID() == objectID) {
				return object;
			}
		}
		
		System.out.println("no objects match that ID number");
		return null;
	}
	
	@Override
	public MapTile getMapTileByID(int mapTileID) {
		for(MapTile mapTile : mapTileList) {
			if(mapTile.getID() == mapTileID) {
				return mapTile;
			}
		}
		
		System.out.println("No mapTiles match that ID number");
		return null;
	}
	
	@Override
	public Character getCharacterByName(String characterName) {
		for(Character character : characterList) {
			if(character.getname() == characterName) {
				return character;
			}
		}
		System.out.println("No character exists by that name");
		return null;
	}

	@Override
	public Inventory getInventoryByID(int inventoryID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeItemFromInventory(Item item, Inventory inventory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeItemFromObject(Item item, Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addItemToInventory(Item item, Inventory inventory) {
		// TODO Auto-generated method stub
	}

	@Override
	public void addItemToObject(Item item, Object object) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveGame(Game game) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String getUserPasswordByUserName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllUserNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enemy getEnemyByRace(String race) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Enemy> getAllEnemies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getAllEnemyRaces() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getLegendaryItem(String itemType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getHandHeldItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getHandHeldItem(String whichHand) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getArmorItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getArmorItem(String armorType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item getLegendaryItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean doesUserNameExist(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean createNewUser(String username, String password, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer createNewGame(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game loadGame(int gameID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Integer> getGameIDs(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isEmailInUse(String email) {
		// TODO Auto-generated method stub
		return null;
	}
} 
