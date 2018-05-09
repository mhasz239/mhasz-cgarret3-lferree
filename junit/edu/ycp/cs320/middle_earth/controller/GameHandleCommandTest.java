package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.CombatSituation;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

/**
 * These Tests are meant to solely test handle_command(String command). The methods called by handle_command are 
 * tested in other Test classes.
 * 
 * Movement Commands are in a separate class due to there being a lot of different ones.
 */
public class GameHandleCommandTest{
	private Game game;
	private Player player;
	private Item sword;
	private Item helmet;
	private Item key;
	private Item wood;
	private Object tree;
	private Object ladder;
	private MapTile starting;
	private MapTile northOfStarting;
	private MapTile northEastOfStarting;
	private String noComprend;
	
	@Before
	public void setup(){
		game = new Game();
		player = new Player();
		player.setlocation(0);
		
		// In case not already in game mode
		game.setmode("game");
		
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
		
		// Add Player to Game
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.setcharacters(characters);
		
		// MapTiles		8 1 2
		//				7 0 3
		//				6 5 4
		starting = new MapTile();
		starting.setID(0);
		starting.setName("The Starting Tile");
		starting.setLongDescription("It's not that exciting...");
		northOfStarting = new MapTile();
		northOfStarting.setID(1);
		northOfStarting.setName("Forest");
		northOfStarting.setLongDescription("You arrive in a lush forest, complete with birds and crickets chirping.");
		northEastOfStarting = new MapTile();
		northEastOfStarting.setID(2);
		northEastOfStarting.setLongDescription("You arrive in a barren wasteland, complete with radiation poisoning.");
		
		Map map = new Map();
		map.addMapTile(starting);
		map.addMapTile(northOfStarting);
		map.addMapTile(northEastOfStarting);
		game.setmap(map);
		
		ladder = new Object();
		ladder.setName("Ladder");
		HashMap<String, String> responses2 = new HashMap<String, String>();
		responses2.put("climb", "It's not so high up here...");
		ladder.setCommandResponses(responses2);
		ArrayList<Object> objs2 = new ArrayList<Object>();
		objs2.add(ladder);
		northOfStarting.setObjects(objs2);
		
		wood = new Item();
		wood.setName("wood");
		
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(wood);
		
		// Object burple holds wood
		Object burple = new Object();
		burple.setItems(items);
		
		tree = new Object();
		tree.setName("Tree");
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("climb", "It's high up here!");
		tree.setCommandResponses(responses);
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(tree);
		objs.add(burple);
		starting.setObjects(objs);
		
		noComprend = "Sorry, I didn't understand that.";
	}
	
	@Test
	public void testInvalidCommand(){
		game.handle_command("blofjerf");
		
		assertEquals(1, game.getdialog().size());
		assertEquals(noComprend, game.getdialog().get(0));
	}
	
	@Test
	public void testTooManyArgumentsInCommand(){
		String response = game.handle_command("test command long");
		
		assertEquals("Too many arguments in your command", response);
	}
	
	@Test
	public void testNullCommandInModeGame(){
		String response = game.handle_command("");
		
		assertEquals(1, game.getdialog().size());
		assertEquals(noComprend, game.getdialog().get(0));
		assertEquals(null, response);
	}
	
	@Test
	public void testNullCommandInModeInventory(){
		game.setmode("inventory");
		
		String response = game.handle_command("");
		
		assertEquals(noComprend, response);
	}
	
	@Test
	public void testSpaceCommandInModeGame(){
		String response = game.handle_command(" ");
		
		assertEquals(1, game.getdialog().size());
		assertEquals(noComprend, game.getdialog().get(0));
		assertEquals(null, response);
	}
	
	@Test
	public void testSpaceCommandInModeInventory(){
		game.setmode("inventory");
		
		String response = game.handle_command(" ");
		
		assertEquals(noComprend, response);
	}
	
	/*
	 * Game-Based Commands (Not Specific to Characters)
	 * TODO: JUNIT: Save Command
	 */
	
	/*
	 * Inventory Mode Commands
	 * Item Command
	 */
	@Test
	public void testItemCommandNoNumber(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item");
		
		// Check that response is correct
		assertEquals("Please designate the item # you want to view more details of.", response);
	}
	
	@Test
	public void testItemCommandNotInInventoryMode(){
		game.handle_command("item");
		
		assertEquals(1, game.getdialog().size());
		assertEquals("Sorry, I didn't understand that.", game.getdialog().get(0));
	}
	
	@Test
	public void testItemCommandNotANumber(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item derpykinsmcgee");
		
		// Check that response is correct
		assertEquals("Invalid item selection. Example: 'item 1' to see the item at position 1", response);
	}
	
	@Test
	public void testItemCommandInvalidNumber0(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 0");
		
		// Check that response is correct
		assertEquals("Sorry you dont have an item at that index", response);
	}
	
	@Test
	public void testItemCommandInvalidNumberAboveRange(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 4");
		
		// Check that response is correct
		assertEquals("Sorry you dont have an item at that index", response);
	}
	
	@Test
	public void testItemCommandLowEndOf1(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 1");
		
		// Set item to sword (for copy-pasting)
		Item item = sword;
		
		// Check that response is correct
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), response);
	}
	
	@Test
	public void testItemCommandMidRangeOf2(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 2");
		
		// Set item to helmet (for copy-pasting)
		Item item = helmet;
		
		// Check that response is correct
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), response);
	}
	
	@Test
	public void testItemCommandHighEndOf3(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Run command and get response
		String response = game.handle_command("item 3");
		
		// Set item to key (for copy-pasting)
		Item item = key;
		
		// Check that response is correct
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), response);
	}
	
	/* 
	 * Player-Specific Commands
	 * Climb(Object) Commands
	 */
	@Test
	public void testClimbTreeCommand(){
		game.handle_command("climb tree");
		
		assertEquals(1, game.getdialog().size());
		assertEquals(tree.getCommandResponses().get("climb"), game.getdialog().get(0));
	}
	
	@Test
	public void testClimbTreeCommandNoClimbablePresent(){
		game.getcharacters().get(0).setlocation(2);
		
		game.handle_command("climb tree");
		
		assertEquals(1, game.getdialog().size());
		assertEquals("Sorry, I didn't understand that.", game.getdialog().get(0));
	}
	
	@Test
	public void testClimbTreeCommandOtherClimbablePresent(){
		game.getcharacters().get(0).setlocation(1);
		
		game.handle_command("climb tree");
		
		assertEquals(1, game.getdialog().size());
		assertEquals("Sorry, I didn't understand that.", game.getdialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommand(){
		game.getcharacters().get(0).setlocation(1);
		game.handle_command("climb ladder");
		
		assertEquals(1, game.getdialog().size());
		assertEquals(ladder.getCommandResponses().get("climb"), game.getdialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommandNoClimbable(){
		game.getcharacters().get(0).setlocation(2);
		
		game.handle_command("climb ladder");
		
		assertEquals(1, game.getdialog().size());
		assertEquals("Sorry, I didn't understand that.", game.getdialog().get(0));
	}
	
	@Test
	public void testClimbLadderCommandOtherClimbablePresent(){
		game.handle_command("climb ladder");
		
		assertEquals(1, game.getdialog().size());
		assertEquals("Sorry, I didn't understand that.", game.getdialog().get(0));
	}
	
	/*
	 * Player-Specific Commands
	 * Take(Item) Command
	 */
	@Test
	public void testTakeCommand(){
		assertEquals(3, player.getinventory().getitems().size());
		assertEquals(1, starting.getObjects().get(1).getItems().size());
		assertEquals(wood, starting.getObjects().get(1).getItems().get(0));
		
		game.handle_command("take wood");
		
		assertEquals(0, starting.getObjects().get(1).getItems().size());
		assertEquals(4, player.getinventory().getitems().size());
		assertEquals(wood, player.getinventory().getitems().get(3));
		
		// Check dialog
		assertEquals(1, game.getdialog().size());
		assertEquals("You have taken " + wood.getName(), game.getdialog().get(0));
	}
	
	@Test
	public void testTakeCommandItemNotOnTile(){
		// Set location of wood to a different tile than starting.
		starting.setObjects(null);
		ArrayList<Object> objs = new ArrayList<Object>();
		Object derp = new Object();
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(wood);
		derp.setItems(items);
		objs.add(derp);
		northOfStarting.setObjects(objs);
		
		assertEquals(3, player.getinventory().getitems().size());
		assertEquals(wood, northOfStarting.getObjects().get(0).getItems().get(0));
		
		game.handle_command("take wood");
		
		assertEquals(1, northOfStarting.getObjects().get(0).getItems().size());
		assertEquals(wood, northOfStarting.getObjects().get(0).getItems().get(0));
		assertEquals(3, player.getinventory().getitems().size());
		
		// Check dialog
		assertEquals(1, game.getdialog().size());
		assertEquals("There is nothing to take here.", game.getdialog().get(0));
	}
	
	@Test
	public void testTakeCommandItemDoesntExist(){
		assertEquals(3, player.getinventory().getitems().size());
		
		game.handle_command("take cheese");
		
		assertEquals(3, player.getinventory().getitems().size());
		assertEquals(1, game.getdialog().size());
		assertEquals("You cannot take cheese here.", game.getdialog().get(0));
	}
	
	@Test
	public void testTakeCommandNotInModeGame(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Check that stuff is like this before command run
		assertEquals(3, player.getinventory().getitems().size());
		
		// Run command and get response
		String response = game.handle_command("take wood");
		
		// Make sure stuff wasn't changed
		assertEquals(3, player.getinventory().getitems().size());
		
		// Check that response is correct
		assertEquals("Sorry, I didn't understand that.", response);
	}
	
	/*
	 * Player-Specific Commands
	 * Look Command
	 */
	@Test
	public void testLookCommandAtStarting(){
		game.handle_command("look");
		
		assertEquals(2, game.getdialog().size());
		assertEquals(starting.getName(), game.getdialog().get(0));
		assertEquals(starting.getLongDescription(), game.getdialog().get(1));
	}
	
	@Test
	public void testLookCommandAtNorthOfStarting(){
		game.getcharacters().get(0).setlocation(1);
		
		game.handle_command("look");
		
		assertEquals(2, game.getdialog().size());
		assertEquals(northOfStarting.getName(), game.getdialog().get(0));
		assertEquals(northOfStarting.getLongDescription(), game.getdialog().get(1));
	}
	
	@Test
	public void testLookCommandNotInModeGame(){
		// Set mode to inventory
		game.setmode("inventory");
		
		// Run command and get response
		String response = game.handle_command("look");
		
		// Check that response is correct
		assertEquals("Sorry, I didn't understand that.", response);
	}
	
	/*
	 * Player-Specific Commands
	 * Attack Tests
	 */
	@Test
	public void testAttackNotInCombat(){
		// Run command
		game.handle_command("attack");
		
		// Check proper error stuff
		assertEquals(1, game.getdialog().size());
		assertEquals("You're not in combat!", game.getdialog().get(0));
	}
	
	/*@Test			No Longer a Valid Test
	public void testAttackOnBossTile(){
		// Set up pre-conditions
		game.getplayer().setlocation(7);
		
		// Run command
		game.handle_command("attack");
		
		// Check proper messages
		assertEquals(5, game.getdialog().size());
		assertEquals("You take the pointy stick and throw it at the troll.", game.getdialog().get(0));
		assertEquals("It manages to poke him in the eye and knock him off balance.", game.getdialog().get(1));
		assertEquals("As he falls, he drops his sword and you quickly spring into action.", game.getdialog().get(2));
		assertEquals("You grab his sword off the ground and lay waste to the foul beast.", game.getdialog().get(3));
		assertEquals("!!!CONGRATULATIONS!!! You have conquered this small land and laid waste to the evil plaguing it!", 
				game.getdialog().get(4));
	}*/
	
	@Test
	public void testAttackNoTarget(){
		// Set up pre-conditions
		game.setBattle(new CombatSituation(game, 1, 0));
		game.setdialog(new ArrayList<String>());
		
		// Run command
		game.handle_command("attack");
		
		// Check error
		assertEquals(1, game.getdialog().size());
		assertEquals("What do you want to attack? (use name or race)", game.getdialog().get(0));
	}
	
	@Test
	public void testNonAttackCommandInCombat(){
		// Set up pre-conditions
		game.setBattle(new CombatSituation(game, 1, 0));
		game.setdialog(new ArrayList<String>());
		
		// Run command
		game.handle_command("boogledee boo");
		
		// Check error
		assertEquals(1, game.getdialog().size());
		assertEquals("You're in combat!", game.getdialog().get(0));
	}
}
