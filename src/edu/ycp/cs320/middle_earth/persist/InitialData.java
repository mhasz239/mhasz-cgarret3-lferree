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
				
				map.setID(Integer.parseInt(i.next()));
				map.setName(i.next());
				map.setShortDescription(i.next());
				map.setLongDescription(i.next());
			}
			return map;
		} finally {
			readMap.close();
		}
	}
	
	public static List<MapTile> getMapTiles() throws IOException {
		List<MapTile> mapTileList = new ArrayList<MapTile>();
		ReadCSV readMapTiles = new ReadCSV("mapTiles.csv");
		try {
			while (true) {
				List<String> tuple = readMapTiles.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				MapTile mapTile = new MapTile();
				// mapTile.setConnections(
				mapTile.setID(Integer.parseInt(i.next()));
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
	
	/*			Unsure how to bind the connections
	
	public static List<HashMap<String, MapTile>> getConnections() throws IOException {
		List<HashMap<String, MapTile>> connectionList = new ArrayList<HashMap<String, MapTile>>();
		ReadCSV readConnections = new ReadCSV("mapTileConnections.csv");
		try {
			while (true) {
				List<String> tuple = readConnections.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				HashMap<String, MapTile> connections = new HashMap<String, MapTile>();
				connections = i.next();
			}
		return connectionList;
		} finally {
			readConnections.close();
		}
	} */
	
	public static List<Object> getObjects() throws IOException {
		List<Object> objectList = new ArrayList<Object>();
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
				objectList.add(object);
			}
			return objectList;
		} finally {
			readObjects.close();
		}
	}
	
	public static List<Item> getItems() throws IOException {
		List<Item> itemList = new ArrayList<Item>();
		ReadCSV readItems = new ReadCSV("items.csv");
		try {
			while (true) {
				List<String> tuple = readItems.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				
				Item item = new Item();
				item.setID(Integer.parseInt(i.next()));
				item.setName(i.next());
				item.setLongDescription(i.next());
				item.setShortDescription(i.next());
				item.setItemWeight(Integer.parseInt(i.next()));
				item.setIsQuestItem(i.next());
			}
		}
	}
	
	public static List<Character> getCharacters() throws IOException {
		List<Character> characterList = new ArrayList<Character>();
		ReadCSV readCharacters = new ReadCSV("characters.csv");
		try {
			List<String> tuple = readCharacters.next();
			if(tuple == null) {
				break;
			}
			while (true) {
				Iterator<String> i = tuple.iterator();
				
				Character character = new Character();
				
			}
		}
	}
	
	
	
}
