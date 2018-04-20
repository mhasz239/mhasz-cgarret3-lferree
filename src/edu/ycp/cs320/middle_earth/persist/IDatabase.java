package edu.ycp.cs320.middle_earth.persist;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
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
	public ArrayList<Inventory> getAllInventories();
	public ArrayList<Quest> getAllQuests();
	
	public Item getItemByID(int itemID);
	public Object getObjectByID(int objectID);
	public MapTile getMapTileByID(int mapTileID);
	public Inventory getInventoryByID(int inventoryID);
	
	public Character getCharacterByName(String characterName);
	
	public Item removeItemFromInventory(int itemID, int inventoryID);
	public Item removeItemFromObject(int itemID, int objectID);
	
	public Item addItemToInventory(int itemID, int inventoryID);
	public Item addItemToObject(int itemID, int objectID);
	
	public Game loadGame();
	public void saveGame(Game game);
	
	public String getUserPasswordByUserName(String username);
	public ArrayList<String> getAllUserNames();
}
