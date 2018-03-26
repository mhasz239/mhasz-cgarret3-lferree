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
		game.set_characters(characters);
		
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
		inventory.set_items(playerItems);
		player.set_inventory(inventory);
		
		tree = new Object();
		tree.setName("Tree");
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("climb", "It's high up here!");
		tree.setCommandResponses(responses);
		//TODO: JUNIT: Set Tree location to 0 (starting MapTile).
		
		ladder = new Object();
		ladder.setName("Ladder");
		HashMap<String, String> responses2 = new HashMap<String, String>();
		responses2.put("climb", "It's not so high up here...");
		ladder.setCommandResponses(responses2);
		//TODO: JUNIT: Set Ladder location to 1 (northOfStarting MapTile).
		
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
		game.set_map(map);
		
		ArrayList<Item> items = new ArrayList<Item>();
		wood = new Item();
		wood.setName("Wood");
		items.add(wood);
		game.set_items(items);
		// TODO: JUNIT: Set wood location to 0 (starting).
	}
	
	/*
	 * Open(Object)
	 * TODO: JUNIT: Open(Object) Tests
	 */
	/*
	 * Close(Object)
	 * TODO: JUNIT: Close(Object) Tests
	 */
	
	/*
	 * Climb(Object)
	 */
	@Test
	public void testClimbTree(){
		game.climb(tree);
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(tree.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadder(){
		game.get_characters().get(0).set_location(1);
		game.climb(ladder);
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(ladder.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	/*
	 * Take(Item)
	 */
	@Test
	public void testTakeCommand(){
		assertEquals(3, player.get_inventory().get_items().size());
		assertEquals(1, game.get_items().size());
		assertEquals(wood, game.get_items().get(0));
		
		game.take(wood.getName());
		
		assertEquals(0, game.get_items().size());
		assertEquals(4, player.get_inventory().get_items().size());
		assertEquals(wood, player.get_inventory().get_items().get(0));
		// TODO: JUNIT: Check dialog
	}
	
	/* 
	 * Take(Object, Item)
	 * TODO: JUNIT: Take(Object, Item) Tests
	 */
	
	/*
	 * Look
	 */
	@Test
	public void testLookAtStarting(){
		assertEquals(0, player.get_location());
		
		game.look();
		
		assertEquals(2, game.get_dialog().size());
		assertEquals(starting.getName(), game.get_dialog().get(0));
		assertEquals(starting.getLongDescription(), game.get_dialog().get(1));
	}
	
	@Test
	public void testLookAtNorthOfStarting(){
		game.get_characters().get(0).set_location(1);
		
		assertEquals(1, player.get_location());
		
		game.look();
		
		assertEquals(2, game.get_dialog().size());
		assertEquals(northOfStarting.getName(), game.get_dialog().get(0));
		assertEquals(northOfStarting.getLongDescription(), game.get_dialog().get(1));
	}
	
	/*
	 * Fast Travel
	 * TODO: JUNIT: Fast Travel Tests
	 */
	/*
	 * Buy(Item)
	 * TODO: JUNIT: Buy(Item) Tests
	 */
	/*
	 * Sell(Item)
	 * TODO: JUNIT: Sell(Item) Tests
	 */
	/*
	 * Talk(NPC)
	 * TODO: JUNIT: Talk(NPC) Tests
	 */
}
