package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

public class GamePlayerActionsTest{
	private Game game;
	private Player player;
	private Object tree;
	private Object ladder;
	private MapTile starting;
	private MapTile northOfStarting;
	private MapTile northEastOfStarting;
	private Item sword;
	private Item helmet;
	private Item key;
	private Item wood;
	
	@Before
	public void setup(){
		game = new Game();
		player = new Player();
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.setcharacters(characters);
		
		// Populate Player's inventory
		ArrayList<Item> playerItems = new ArrayList<Item>();
		sword = new Item();
		sword.setName("Sword");
		sword.setLongDescription("A Long sword. Probably stolen from a giant golem or something.");
		sword.setItemWeight((float) 9.6);
		sword.setIsQuestItem(false);
		helmet = new Item();
		helmet.setName("Helmet");
		helmet.setLongDescription("A helmet forged in the hot, hot fires of Mordor.");
		helmet.setItemWeight((float) 29.3);
		helmet.setIsQuestItem(false);
		key = new Item();
		key.setName("Key");
		key.setLongDescription("A key to treasure too expensive to buy with Bill Gates' salary. (Believe it)");
		key.setItemWeight((float) 93.1);
		key.setIsQuestItem(true);
		playerItems.add(sword);
		playerItems.add(helmet);
		playerItems.add(key);
		Inventory inventory = new Inventory();
		inventory.setitems(playerItems);
		player.setinventory(inventory);
		
		tree = new Object();
		tree.setName("Tree");
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("climb", "It's high up here!");
		tree.setCommandResponses(responses);
		
		// MapTiles		8 1 2
		//				7 0 3
		//				6 5 4
		starting = new MapTile();
		starting.setID(0);
		northOfStarting = new MapTile();
		northOfStarting.setID(1);
		northOfStarting.setLongDescription("You arrive in a lush forest, complete with birds and crickets chirping.");
		northEastOfStarting = new MapTile();
		northEastOfStarting.setID(2);
		northEastOfStarting.setLongDescription("You arrive in a barren wasteland, complete with radiation poisoning.");
		
		Map map = new Map();
		map.addMapTile(starting);
		map.addMapTile(northOfStarting);
		map.addMapTile(northEastOfStarting);
		game.setmap(map);
		
		ArrayList<Item> items = new ArrayList<Item>();
		wood = new Item();
		wood.setName("Wood");
		items.add(wood);
		game.setitems(items);
		
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(tree);
		Object derp = new Object();
		ArrayList<Item> its = new ArrayList<Item>();
		its.add(wood);
		derp.setItems(its);
		objs.add(derp);
		starting.setObjects(objs);
	}
	
	/*
	 * Take(Item)
	 */
	@Test
	public void testTakeCommand(){
		assertEquals(3, player.getinventory().getitems().size());
		assertEquals(1, starting.getObjects().get(1).getItems().size());
		assertEquals(wood, starting.getObjects().get(1).getItems().get(0));
		
		game.take(wood.getName());
		
		assertEquals(0, starting.getObjects().get(1).getItems().size());
		assertEquals(4, player.getinventory().getitems().size());
		assertEquals(wood, player.getinventory().getitems().get(3));
		
		// Check dialog
		assertEquals(1, game.getdialog().size());
		assertEquals("You have taken " + wood.getName(), game.getdialog().get(0));
	}
	
	/*
	 * Look
	 */
	@Test
	public void testLookAtStarting(){
		assertEquals(0, player.getlocation());
		
		game.look();
		
		assertEquals(2, game.getdialog().size());
		assertEquals(starting.getName(), game.getdialog().get(0));
		assertEquals(starting.getLongDescription(), game.getdialog().get(1));
	}
	
	@Test
	public void testLookAtNorthOfStarting(){
		game.getcharacters().get(0).setlocation(1);
		
		assertEquals(1, player.getlocation());
		
		game.look();
		
		assertEquals(2, game.getdialog().size());
		assertEquals(northOfStarting.getName(), game.getdialog().get(0));
		assertEquals(northOfStarting.getLongDescription(), game.getdialog().get(1));
	}
}
