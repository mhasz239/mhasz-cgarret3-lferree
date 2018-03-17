package edu.ycp.cs320.middle_earth.persist;

import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Weapon;
import edu.ycp.cs320.middle_earth.model.Constructs.Armor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.middle_earth.persist.InitialData;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
//import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;


public class FakeDatabase {
	// map // 9 tiles, 0 - 8
	// maptiles // long desc, short desc
	
	// objects	// tree, stone door, (maybe) table
	
	// items // key, one weap, one def (pointed stick)
	// character // 
	// inventory
	// User Stats
	
	private Map map;
	
	
	private List<MapTile> mapTileList;
	
	
	private List<Object> objectList;
	
	
	private List<Item> itemList;
	
	
	private List<Character> characterList;
	
	
	private Inventory inventory;
	
	
	private Player player;	
	
	public FakeDatabase() {
		map = new Map();
		mapTileList = new ArrayList<MapTile>();
		objectList = new ArrayList<Object>();
		itemList = new ArrayList<Item>();
		characterList = new ArrayList<Character>();
		inventory = new Inventory();
		player = new Player();
		
		
		
	}
	
	public void readInitialData() {
		try {
			map = InitialData.getMap();
			mapTileList.addAll(InitialData.getMapTiles());
			objectList.addAll(InitialData.getObjects());
			itemList.addAll(InitialData.getItems());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
}
