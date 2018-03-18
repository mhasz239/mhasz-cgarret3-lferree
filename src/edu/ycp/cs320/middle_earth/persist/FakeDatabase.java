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
	private List<MapTile> mapTileList;
	private List<Object> objectList;
	private List<Item> itemList;
	private List<Quest> questList;	
	private List<Character> characterList;
	private Inventory inventory;
	private Player player;	
	
	public FakeDatabase() {
		map = new Map();
		mapTileList = new ArrayList<MapTile>();
		objectList = new ArrayList<Object>();
		itemList = new ArrayList<Item>();
		characterList = new ArrayList<Character>();
		player = new Player();
		
		readInitialData();
	}
	
	public void readInitialData() {
		try {
//			map = InitialData.getMap();
//			mapTileList.addAll(InitialData.getMapTiles());
/*			objectList.addAll(InitialData.getObjects());*/
			itemList.addAll(InitialData.getItems());
			//characterList.addAll(InitialData.getCharacters());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	
	public List<Item> getAllItems() {
		return itemList;
	}
	
	public List<Object> getAllObjects() {
		return objectList;
	}
	
	public List<MapTile> getAllMapTiles() {
		return mapTileList;
	}
	
	public Map getMap() {
		return map;
	}
	
	public List<Character> getAllCharacters() {
		return characterList;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public List<Quest> getAllQuests() {
		return questList;
	}
} 
