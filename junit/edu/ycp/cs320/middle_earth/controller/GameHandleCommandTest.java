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

/**
 * These Tests are meant to solely test handle_command(String command). The methods called by handle_command are 
 * tested in other Test classes.
 * 
 * Movement Commands are in a separate class due to there being a lot of different ones.
 * 
 * TODO: Get Matt's commit of Game, then test get_dialog() for every command tested.
 */
public class GameHandleCommandTest{
	private Game game;
	private Player player;
	private Object tree;
	private Object ladder;
	private MapTile starting;
	private MapTile northOfStarting;
	private MapTile northEastOfStarting;
	private String invalidMode;
	
	@Before
	public void setup(){
		game = new Game();
		player = new Player();
		player.set_location(0);
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
		
		invalidMode = "You can't use that command here.";
	}
	
	@Test
	public void testInvalidCommand(){
		game.handle_command("blofjerf");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Sorry, I didn't understand that.", game.get_dialog().get(0));
	}
	
	/*
	 * Game-Based Commands (Not Specific to Characters)
	 * Check Character Sheet Command
	 */
	@Test
	public void testCheckCharacterSheetCommand(){
		game.handle_command("character");
		
		assertEquals("character", game.get_mode());
	}
	
	@Test
	public void testBackToGameFromCharacterSheet(){
		game.set_mode("character");
		
		game.handle_command("game");
		
		assertEquals("game", game.get_mode());
	}
	
	/*
	 * Game-Based Commands (Not Specific to Characters)
	 * Check Inventory Command
	 */
	@Test
	public void testCheckInventoryCommand(){
		game.handle_command("inventory");
		
		assertEquals("inventory", game.get_mode());
	}
	
	@Test
	public void testBackToGameFromInventory(){
		game.set_mode("inventory");
		
		game.handle_command("game");
		
		assertEquals("game", game.get_mode());
	}
	
	/*
	 * Game-Based Commands (Not Specific to Characters)
	 * Check Map Command
	 */
	@Test
	public void testMapCommand(){
		game.handle_command("map");
		
		assertEquals("map", game.get_mode());
	}
	
	@Test
	public void testBackToGameFromMap(){
		game.set_mode("map");
		
		game.handle_command("game");
		
		assertEquals("game", game.get_mode());
	}
	
	/*
	 * Game-Based Commands (Not Specific to Characters)
	 * TODO: Save Command
	 */
	
	/*
	 * Player-Specific Commands
	 * TODO: Open(Object) Tests
	 * TODO: Close(Object) Tests
	 */
	
	/* 
	 * Player-Specific Commands
	 * Climb(Object) Commands
	 */
	@Test
	public void testClimbTreeCommand(){
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(tree.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbTreeCommandNoClimbablePresent(){
		game.get_characters().get(0).set_location(2);
		
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("There is no tree", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbTreeCommandOtherClimbablePresent(){
		game.get_characters().get(0).set_location(1);
		
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("There is no tree", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommand(){
		game.get_characters().get(0).set_location(1);
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(tree.getCommandResponses().get("climb"), game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommandNoClimbable(){
		game.get_characters().get(0).set_location(2);
		
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("There is no ladder", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommandOtherClimbablePresent(){
		game.handle_command("climb ladder");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("There is no ladder", game.get_dialog().get(0));
	}
	
	/*
	 * Player-Specific Commands
	 * TODO: Take(Item) Tests
	 */
	
	
	/*
	 * Player-Specific Commands
	 * TODO: Take(Object, Item) Tests
	 */
	
	/*
	 * Player-Specific Commands
	 * Look Command
	 */
	@Test
	public void testLookCommandAtStarting(){
		game.handle_command("look");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(starting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testLookCommandAtNorthOfStarting(){
		game.get_characters().get(0).set_location(1);
		
		game.handle_command("look");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(northOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testLookCommandNotInModeGame(){
		game.set_mode("inventory");
		
		game.handle_command("look");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * Player-Specific Commands
	 * TODO: Fast Travel Tests
	 * TODO: Buy(Item) Tests
	 * TODO: Sell(Item) Tests
	 * TODO: Talk(NPC) Tests
	 */
	
	/*
	 * Character-Specific Actions
	 * Attack Command
	 * TODO: Attack command tests
	 */
	
	/*
	 * Character-Specific Actions
	 * Loot Command
	 * TODO: Loot command tests
	 */
}
