package edu.ycp.cs320.middle_earth.persist;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

public interface IDatabase {
	public Map getMap();
	public Player getPlayer();
	
	public ArrayList<Item> getAllItems();
	public ArrayList<Object> getAllObjects();
	public ArrayList<MapTile> getAllMapTiles();
	public ArrayList<Character> getAllCharacters();
	public ArrayList<Quest> getAllQuests();
	
	public Item getItemByID(int itemID);
	public Object getObjectByID(int objectID);
	public MapTile getMapTileByID(int mapTileID);
	public Inventory getInventoryByID(int inventoryID);
	
	public Character getCharacterByName(String characterName);
	
	public Item removeItemFromInventory(Item item, Inventory inventory);
	public Item removeItemFromObject(Item item, Object object);
	
	public void addItemToInventory(Item item, Inventory inventory);
	public void addItemToObject(Item item, Object object);
	
	public Game loadGame(int gameID);
	public void saveGame(Game game);
	
	public String getUserPasswordByUserName(String username);
	public ArrayList<String> getAllUserNames();
	public Boolean doesUserNameExist(String username);
	public Boolean isEmailInUse(String email);
	public Boolean createNewUser(String username, String password, String email);
	public Integer createNewGame(String username);
	public ArrayList<Integer> getGameIDs(String username);
	
	public Enemy getEnemyByRace(String race);
	public ArrayList<Enemy> getAllEnemies();
	public ArrayList<String> getAllEnemyRaces();
	
	public Item getLegendaryItem();
	public Item getLegendaryItem(String itemType);
	public Item getHandHeldItem();
	public Item getHandHeldItem(String whichHand);
	public Item getArmorItem();
	public Item getArmorItem(String armorType);
}
