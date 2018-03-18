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
	private Inventory inventory;
	private Player player;	
	
	public FakeDatabase() {
		map = new Map();
		mapTileList = new ArrayList<MapTile>();
		objectList = new ArrayList<Object>();
		itemList = new ArrayList<Item>();
		questList = new ArrayList<Quest>();
		characterList = new ArrayList<Character>();
		inventory = new Inventory();
		player = new Player();	
		
		readInitialData();
	}
	
	public void readInitialData() {
		try {
//			map = InitialData.getMap();
//			mapTileList.addAll(InitialData.getMapTiles());
			objectList.addAll(InitialData.getObjects());
			itemList.addAll(InitialData.getItems());
//			questList.addAll(InitialData.getQuests());
//			characterList.addAll(InitialData.getCharacters());
			inventory = InitialData.getInventory();
//			player = InitialData.getPlayer();
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	
	public ArrayList<Item> getAllItems() {
		return itemList;
	}
	
	public ArrayList<Object> getAllObjects() {
		return objectList;
	}
	
	public ArrayList<MapTile> getAllMapTiles() {
		return mapTileList;
	}
	
	public Map getMap() {
		return map;
	}
	
	public ArrayList<Character> getAllCharacters() {
		return characterList;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public ArrayList<Quest> getAllQuests() {
		return questList;
	}
} 
