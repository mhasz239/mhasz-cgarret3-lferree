package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

/**
 * The movement commands are in here separate due to how many there are. It's insane.
 */
public class HandleMovementCommands{
	private Game game;
	private Player player;
	private Map map;
	private MapTile starting;
	private MapTile northOfStarting;
	private MapTile northEastOfStarting;
	private MapTile eastOfStarting;
	private MapTile southEastOfStarting;
	private MapTile southOfStarting;
	private MapTile southWestOfStarting;
	private MapTile westOfStarting;
	private MapTile northWestOfStarting;
	private static String invalidDirection = "You can't go that way";
	private static String invalidMode = "Sorry, I didn't understand that.";
	
	@Before
	public void setup(){
		game = new Game();
		// This is here just in case the Game doesn't initialize the current mode to this.
		game.setmode("game");
		player = new Player();
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.setcharacters(characters);
		
		// MapTiles		8 1 2
		//				7 0 3
		//				6 5 4
		starting = new MapTile();
		starting.setID(0);
		starting.setName("The Starting Area");
		starting.setLongDescription("You materialize in this area. That is all.");
		
		northOfStarting = new MapTile();
		northOfStarting.setID(1);
		northOfStarting.setName("Forest");
		northOfStarting.setLongDescription("You arrive in a lush forest, complete with birds and crickets chirping.");
		
		northEastOfStarting = new MapTile();
		northEastOfStarting.setID(2);
		northEastOfStarting.setName("Wasteland");
		northEastOfStarting.setLongDescription("You arrive in a barren wasteland, complete with radiation poisoning.");
		
		eastOfStarting = new MapTile();
		eastOfStarting.setID(3);
		eastOfStarting.setName("Candyland");
		eastOfStarting.setLongDescription("You arrive in candyland, where I don't know any of the character names.");
		
		southEastOfStarting = new MapTile();
		southEastOfStarting.setID(4);
		southEastOfStarting.setName("L.A.");
		southEastOfStarting.setLongDescription("You arrive in L.A., just to get a flight to leave.");
		
		southOfStarting = new MapTile();
		southOfStarting.setID(5);
		southOfStarting.setName("CS320 2016");
		southOfStarting.setLongDescription("You arrive in CS320 in 2016, where Logan is failing to make a 2D Platformer in Erlang.");
		
		southWestOfStarting = new MapTile();
		southWestOfStarting.setID(6);
		southWestOfStarting.setName("IDK");
		southWestOfStarting.setLongDescription("You arrive in I don't know, just give it up already.");
		
		westOfStarting = new MapTile();
		westOfStarting.setID(7);
		westOfStarting.setName("CS320 3/12/18");
		westOfStarting.setLongDescription("You arrive in CS320 a week early for the milestone to realize no one has worked on it "
				+ "yet.");
		
		northWestOfStarting = new MapTile();
		northWestOfStarting.setID(8);
		northWestOfStarting.setName("Boredom");
		northWestOfStarting.setLongDescription("You arrive in... The narrator died of boredom, so we're waiting on a new one.");
		
		// Starting tile's connections
		starting.setConnection("north", 1);
		starting.setConnection("northeast", 2);
		starting.setConnection("east", 3);
		starting.setConnection("southeast", 4);
		starting.setConnection("south", 5);
		starting.setConnection("southwest", 6);
		starting.setConnection("west", 7);
		starting.setConnection("northwest", 8);
		
		// Add tiles to Map
		map = new Map();
		ArrayList<MapTile> tiles = new ArrayList<MapTile>();
		tiles.add(starting);
		tiles.add(northOfStarting);
		tiles.add(northEastOfStarting);
		tiles.add(eastOfStarting);
		tiles.add(southEastOfStarting);
		tiles.add(southOfStarting);
		tiles.add(southWestOfStarting);
		tiles.add(westOfStarting);
		tiles.add(northWestOfStarting);
		map.setMapTiles(tiles);
		
		game.setmap(map);
	}
	
	/**
	 * This method is to be used by this class. It ensures that a move command functions in valid conditions
	 */
	public void checkValidMoveUpdates(Game game, MapTile original, MapTile destination, String command){
		checkValidMovePreconditions(game, original);
		
		// Run the command.
		game.handle_command(command);
		
		checkValidMovePostConditions(game, destination);
	}
	
	/**
	 * This method is to be used by any class that checks that movement preconditions are setup correctly.
	 */
	public static void checkValidMovePreconditions(Game game, MapTile original){
		// Ensure the player is in the correct starting location.
		assertEquals(original.getID(), game.getplayer().getlocation());
	}
	
	/**
	 * This method is to be used by any class that checks that movement has worked correctly.
	 * It ensures that conditions after the movement are correct.
	 */
	public static void checkValidMovePostConditions(Game game, MapTile destination){
		// Ensure the player moved to the correct destination.
		assertEquals(destination.getID(), game.getplayer().getlocation());
		
		// Ensure 2 lines have been added to the dialog.
		// Note: Cannot check that it's 2 lines exactly because of Combat now
		// Combat can add another line that you encountered an enemy.
		//assertEquals(2, game.getdialog().size());
		// Ensure the first line is the new tile's name.
		assertEquals(destination.getName(), game.getdialog().get(0));
		// Ensure the second line is the new tile's longDescription.
		assertEquals(destination.getLongDescription(), game.getdialog().get(1));
	}
	
	/**
	 * This method is to be used by this class. It ensures that move commands behave properly in invalid conditions.
	 */
	public static void checkInvalidMoveUpdates(Game game, MapTile setup, String command){
		setupInvalidMovePreConditions(game, setup);
		
		// Run the command
		game.handle_command(command);
		
		checkInvalidMovePostConditions(game, setup);
	}
	
	/**
	 * This method is meant to be used by any class that checks invalid movement conditions.
	 * It sets up the location for the player.
	 */
	public static void setupInvalidMovePreConditions(Game game, MapTile setup){
		// Set the player's location to the requested maptile.
		game.getplayer().setlocation(setup.getID());
		// Ensure the player's location is properly set.
		assertEquals(setup.getID(), game.getplayer().getlocation());
	}
	
	/**
	 * This method is meant to be used by any class that checks invalid movement conditions.
	 * It ensures the method has responded properly to not being able to move that direction.
	 */
	public static void checkInvalidMovePostConditions(Game game, MapTile setup){
		// Ensure the player's location hasn't changed.
		assertEquals(setup.getID(), game.getplayer().getlocation());
		// Ensure the game's dialog got one new line.
		assertEquals(1, game.getdialog().size());
		// Ensure that new line is the invalidDirection string.
		assertEquals(invalidDirection, game.getdialog().get(0));
	}
	
	/**
	 * This method is to be used by any class that checks movement commands when not in mode = Game.
	 */
	public static void checkNotInModeGame(Game game, MapTile original, String command){
		// Set mode to anything other than game
		game.setmode("inventory");
		
		// Ensure before the command that the player is in the proper location.
		assertEquals(original.getID(), game.getplayer().getlocation());
		
		// Run the command
		String response = game.handle_command(command);
		
		// Ensure that after the command, the player hasn't moved (since not in mode = game)
		assertEquals(original.getID(), game.getplayer().getlocation());
		// Ensure that 1 line is the invalidMode string
		assertEquals(invalidMode, response);
	}
	
	/*
	 * That one weird issue
	 */
	@Test
	public void testInvalidDirection(){
		// Run command
		game.handle_command("move deprirber0oger");
		
		// Check that correct error stuff is set
		assertEquals(1, game.getdialog().size());
		assertEquals("I don't understand that direction.", game.getdialog().get(0));
	}
	
	/*
	 * North Command Working
	 */
	@Test
	public void testNCommand(){
		checkValidMoveUpdates(game, starting, northOfStarting, "n");
	}
	
	@Test
	public void testNorthCommand(){
		checkValidMoveUpdates(game, starting, northOfStarting, "north");
	}
	
	@Test
	public void testMoveNorthCommand(){
		checkValidMoveUpdates(game, starting, northOfStarting, "move north");
	}
	
	/*
	 * Northeast Command Working
	 */
	@Test
	public void testNECommand(){
		checkValidMoveUpdates(game, starting, northEastOfStarting, "ne");
	}
	
	@Test
	public void testNorthEastCommand(){
		checkValidMoveUpdates(game, starting, northEastOfStarting, "northeast");
	}
	
	@Test
	public void testMoveNorthEastCommand(){
		checkValidMoveUpdates(game, starting, northEastOfStarting, "move northeast");
	}
	
	/*
	 * East Command Working
	 */
	@Test
	public void testECommand(){
		checkValidMoveUpdates(game, starting, eastOfStarting, "e");
	}
	
	@Test
	public void testEastCommand(){
		checkValidMoveUpdates(game, starting, eastOfStarting, "east");
	}
	
	@Test
	public void testMoveEastCommand(){
		checkValidMoveUpdates(game, starting, eastOfStarting, "move east");
	}
	
	/*
	 * Southeast Command Working
	 */
	@Test
	public void testSECommand(){
		checkValidMoveUpdates(game, starting, southEastOfStarting, "se");
	}
	
	@Test
	public void testSouthEastCommand(){
		checkValidMoveUpdates(game, starting, southEastOfStarting, "southeast");
	}
	
	@Test
	public void testMoveSouthEastCommand(){
		checkValidMoveUpdates(game, starting, southEastOfStarting, "move southeast");
	}
	
	/*
	 * South Command Working
	 */
	@Test
	public void testSCommand(){
		checkValidMoveUpdates(game, starting, southOfStarting, "s");
	}
	
	@Test
	public void testSouthCommand(){
		checkValidMoveUpdates(game, starting, southOfStarting, "south");
	}
	
	@Test
	public void testMoveSouthCommand(){
		checkValidMoveUpdates(game, starting, southOfStarting, "move south");
	}
	
	/*
	 * Southwest Command Working
	 */
	@Test
	public void testSWCommand(){
		checkValidMoveUpdates(game, starting, southWestOfStarting, "sw");
	}
	
	@Test
	public void testSouthWestCommand(){
		checkValidMoveUpdates(game, starting, southWestOfStarting, "southwest");
	}
	
	@Test
	public void testMoveSouthWestCommand(){
		checkValidMoveUpdates(game, starting, southWestOfStarting, "move southwest");
	}
	
	/*
	 * West Command Working
	 */
	@Test
	public void testWCommand(){
		checkValidMoveUpdates(game, starting, westOfStarting, "w");
	}
	
	@Test
	public void testWestCommand(){
		checkValidMoveUpdates(game, starting, westOfStarting, "west");
	}
	
	@Test
	public void testMoveWestCommand(){
		checkValidMoveUpdates(game, starting, westOfStarting, "move west");
	}
	
	/*
	 * Northwest Command Working
	 */
	@Test
	public void testNWCommand(){
		checkValidMoveUpdates(game, starting, northWestOfStarting, "nw");
	}
	
	@Test
	public void testNorthWestCommand(){
		checkValidMoveUpdates(game, starting, northWestOfStarting, "northwest");
	}
	
	@Test
	public void testMoveNorthWestCommand(){
		checkValidMoveUpdates(game, starting, northWestOfStarting, "move northwest");
	}
	
	/*
	 * ******************
	 * Invalids
	 * ******************
	 * TODO: JUNIT: Unique messages for invalid directions?
	 */
	
	/*
	 * North Command Invalid
	 */
	@Test
	public void testNCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "n");
	}
	
	@Test
	public void testNorthCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "north");
	}
	
	@Test
	public void testMoveNorthCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "move north");
	}
	
	/*
	 * Northeast Command Invalid
	 */
	@Test
	public void testNECommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "ne");
	}
	
	@Test
	public void testNorthEastCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "northeast");
	}
	
	@Test
	public void testMoveNorthEastCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "move northeast");
	}
	
	/*
	 * East Command Invalid
	 */
	@Test
	public void testECommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "e");
	}
	
	@Test
	public void testEastCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "east");
	}
	
	@Test
	public void testMoveEastCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "move east");
	}
	
	/*
	 * Southeast Command Invalid
	 */
	@Test
	public void testSECommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "se");
	}
	
	@Test
	public void testSouthEastCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "southeast");
	}
	
	@Test
	public void testMoveSouthEastCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "move southeast");
	}
	
	/*
	 * South Command Invalid
	 */
	@Test
	public void testSCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "s");
	}
	
	@Test
	public void testSouthCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "south");
	}
	
	@Test
	public void testMoveSouthCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "move south");
	}
	
	/*
	 * Southwest Command Invalid
	 */
	@Test
	public void testSWCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "sw");
	}
	
	@Test
	public void testSouthWestCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "southwest");
	}
	
	@Test
	public void testMoveSouthWestCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "move southwest");
	}
	
	/*
	 * West Command Invalid
	 */
	@Test
	public void testWCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "w");
	}
	
	@Test
	public void testWestCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "west");
	}
	
	@Test
	public void testMoveWestCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "move west");
	}
	
	/*
	 * Northwest Command Invalid
	 */
	@Test
	public void testNWCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "nw");
	}
	
	@Test
	public void testNorthWestCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "northwest");
	}
	
	@Test
	public void testMoveNorthWestCommandInvalid(){
		checkInvalidMoveUpdates(game, northOfStarting, "move northwest");
	}
	
	/*
	 * ******************
	 * Not in Mode = Game
	 * ******************
	 */
	
	/*
	 * North Command Invalid
	 */
	@Test
	public void testNCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "n");
	}
	
	@Test
	public void testNorthCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "north");
	}
	
	@Test
	public void testMoveNorthCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "move north");
	}
	
	/*
	 * Northeast Command Invalid
	 */
	@Test
	public void testNECommandNotInModeGame(){
		checkNotInModeGame(game, starting, "ne");
	}
	
	@Test
	public void testNorthEastCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "northeast");
	}
	
	@Test
	public void testMoveNorthEastCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "move northeast");
	}
	
	/*
	 * East Command Invalid
	 */
	@Test
	public void testECommandNotInModeGame(){
		checkNotInModeGame(game, starting, "e");
	}
	
	@Test
	public void testEastCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "east");
	}
	
	@Test
	public void testMoveEastCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "move east");
	}
	
	/*
	 * Southeast Command Invalid
	 */
	@Test
	public void testSECommandNotInModeGame(){
		checkNotInModeGame(game, starting, "se");
	}
	
	@Test
	public void testSouthEastCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "southeast");
	}
	
	@Test
	public void testMoveSouthEastCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "move southeast");
	}
	
	/*
	 * South Command Invalid
	 */
	@Test
	public void testSCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "s");
	}
	
	@Test
	public void testSouthCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "south");
	}
	
	@Test
	public void testMoveSouthCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "move south");
	}
	
	/*
	 * Southwest Command Invalid
	 */
	@Test
	public void testSWCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "sw");
	}
	
	@Test
	public void testSouthWestCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "southwest");
	}
	
	@Test
	public void testMoveSouthWestCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "move southwest");
	}
	
	/*
	 * West Command Invalid
	 */
	@Test
	public void testWCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "w");
	}
	
	@Test
	public void testWestCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "west");
	}
	
	@Test
	public void testMoveWestCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "move west");
	}
	
	/*
	 * Northwest Command Invalid
	 */
	@Test
	public void testNWCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "nw");
	}
	
	@Test
	public void testNorthWestCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "northwest");
	}
	
	@Test
	public void testMoveNorthWestCommandNotInModeGame(){
		checkNotInModeGame(game, starting, "move northwest");
	}
}
