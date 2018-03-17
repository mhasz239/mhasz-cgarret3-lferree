package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
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
	
	@Before
	public void setup(){
		game = new Game();
		player = new Player();
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.set_characters(characters);
		
		tree = new Object();
		tree.setName("Tree");
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("climb", "It's high up here!");
		tree.setCommandResponses(responses);
		//TODO: Set Tree location to 0 (starting MapTile).
		
		ladder = new Object();
		ladder.setName("Ladder");
		HashMap<String, String> responses2 = new HashMap<String, String>();
		responses.put("climb", "It's not so high up here...");
		ladder.setCommandResponses(responses2);
		//TODO: Set Ladder location to 1 (northOfStarting MapTile).
		
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
	}
	
	/*
	 * Open(Object)
	 * TODO: Open(Object) Tests
	 */
	/*
	 * Close(Object)
	 * TODO: Close(Object) Tests
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
		assertEquals(tree.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	/*
	 * Take(Item)
	 * TODO: Take(Item) Tests
	 */
	/* 
	 * Take(Object, Item)
	 * TODO: Take(Object, Item) Tests
	 */
	/*
	 * Look
	 * TODO: Look Tests
	 */
	/*
	 * Fast Travel
	 * TODO: Fast Travel Tests
	 */
	/*
	 * Buy(Item)
	 * TODO: Buy(Item) Tests
	 */
	/*
	 * Sell(Item)
	 * TODO: Sell(Item) Tests
	 */
	/*
	 * Talk(NPC)
	 * TODO: Talk(NPC) Tests
	 */
}
