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
	private static String invalidMode = "You can't use that command here.";
	
	@Before
	public void setup(){
		game = new Game();
		// This is here just in case the Game doesn't initialize the current mode to this.
		game.set_mode("game");
		player = new Player();
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.set_characters(characters);
		
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
		eastOfStarting = new MapTile();
		eastOfStarting.setID(3);
		eastOfStarting.setLongDescription("You arrive in candyland, where I don't know any of the character names.");
		southEastOfStarting = new MapTile();
		southEastOfStarting.setID(4);
		southEastOfStarting.setLongDescription("You arrive in L.A., just to get a flight to leave.");
		southOfStarting = new MapTile();
		southOfStarting.setID(5);
		southOfStarting.setLongDescription("You arrive in CS320 in 2016, where Logan is failing to make a 2D Platformer in Erlang.");
		southWestOfStarting = new MapTile();
		southWestOfStarting.setID(6);
		southWestOfStarting.setLongDescription("You arrive in I don't know, just give it up already.");
		westOfStarting = new MapTile();
		westOfStarting.setID(7);
		westOfStarting.setLongDescription("You arrive in CS320 a week early for the milestone to realize no one has worked on it "
				+ "yet.");
		northWestOfStarting = new MapTile();
		northWestOfStarting.setID(8);
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
		
		game.set_map(map);
	}
	
	/**
	 * This method is to be used by any class that checks movement commands that they have worked correctly.
	 */
	public static void checkValidMoveUpdates(Game game, MapTile destination){
		assertEquals(destination.getID(), game.get_player().get_location());
		
		assertEquals(2, game.get_dialog().size());
		assertEquals(destination.getName(), game.get_dialog().get(0));
		assertEquals(destination.getLongDescription(), game.get_dialog().get(1));
	}
	
	/**
	 * This method is to be used by any class that checks movement commands when an invalid direction.
	 */
	public static void checkInvalidMoveUpdates(Game game, MapTile original){
		assertEquals(original.getID(), game.get_player().get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/**
	 * This method is to be used by any class that checks movement commands when not in mode = Game.
	 */
	public static void checkNotInModeGame(Game game, MapTile original){
		assertEquals(original.getID(), game.get_player().get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * North Command Working
	 */
	@Test
	public void testNCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("n");
		
		checkValidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testNorthCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("north");
		
		checkValidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveNorthCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("move north");
		
		checkValidMoveUpdates(game, northOfStarting);
	}
	
	/*
	 * Northeast Command Working
	 */
	@Test
	public void testNECommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("ne");
		
		checkValidMoveUpdates(game, northEastOfStarting);
	}
	
	@Test
	public void testNorthEastCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("northeast");
		
		checkValidMoveUpdates(game, northEastOfStarting);
	}
	
	@Test
	public void testMoveNorthEastCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("move northeast");
		
		checkValidMoveUpdates(game, northEastOfStarting);
	}
	
	/*
	 * East Command Working
	 */
	@Test
	public void testECommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("e");
		
		checkValidMoveUpdates(game, eastOfStarting);
	}
	
	@Test
	public void testEastCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("east");
		
		checkValidMoveUpdates(game, eastOfStarting);
	}
	
	@Test
	public void testMoveEastCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("move east");
		
		checkValidMoveUpdates(game, eastOfStarting);
	}
	
	/*
	 * Southeast Command Working
	 */
	@Test
	public void testSECommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("se");
		
		checkValidMoveUpdates(game, southEastOfStarting);
	}
	
	@Test
	public void testSouthEastCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("southeast");
		
		checkValidMoveUpdates(game, southEastOfStarting);
	}
	
	@Test
	public void testMoveSouthEastCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("move southeast");
		
		checkValidMoveUpdates(game, southEastOfStarting);
	}
	
	/*
	 * South Command Working
	 */
	@Test
	public void testSCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("s");
		
		checkValidMoveUpdates(game, southOfStarting);
	}
	
	@Test
	public void testSouthCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("south");
		
		checkValidMoveUpdates(game, southOfStarting);
	}
	
	@Test
	public void testMoveSouthCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("move south");
		
		checkValidMoveUpdates(game, southOfStarting);
	}
	
	/*
	 * Southwest Command Working
	 */
	@Test
	public void testSWCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("sw");
		
		checkValidMoveUpdates(game, southWestOfStarting);
	}
	
	@Test
	public void testSouthWestCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("southwest");
		
		checkValidMoveUpdates(game, southWestOfStarting);
	}
	
	@Test
	public void testMoveSouthWestCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("move southwest");
		
		checkValidMoveUpdates(game, southWestOfStarting);
	}
	
	/*
	 * West Command Working
	 */
	@Test
	public void testWCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("w");
		
		checkValidMoveUpdates(game, westOfStarting);
	}
	
	@Test
	public void testWestCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("west");
		
		checkValidMoveUpdates(game, westOfStarting);
	}
	
	@Test
	public void testMoveWestCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("move west");
		
		checkValidMoveUpdates(game, westOfStarting);
	}
	
	/*
	 * Northwest Command Working
	 */
	@Test
	public void testNWCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("nw");
		
		checkValidMoveUpdates(game, northWestOfStarting);
	}
	
	@Test
	public void testNorthWestCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("northwest");
		
		checkValidMoveUpdates(game, northWestOfStarting);
	}
	
	@Test
	public void testMoveNorthWestCommand(){
		assertEquals(0, player.get_location());
		
		game.handle_command("move northwest");
		
		checkValidMoveUpdates(game, northWestOfStarting);
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
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("n");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testNorthCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("north");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveNorthCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("move north");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	/*
	 * Northeast Command Invalid
	 */
	@Test
	public void testNECommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("ne");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testNorthEastCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("northeast");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveNorthEastCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("move northeast");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	/*
	 * East Command Invalid
	 */
	@Test
	public void testECommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("e");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testEastCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("east");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveEastCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("move east");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	/*
	 * Southeast Command Invalid
	 */
	@Test
	public void testSECommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("se");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testSouthEastCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("southeast");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveSouthEastCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("move southeast");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	/*
	 * South Command Invalid
	 */
	@Test
	public void testSCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("s");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testSouthCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("south");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveSouthCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("move south");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	/*
	 * Southwest Command Invalid
	 */
	@Test
	public void testSWCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("sw");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testSouthWestCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("southwest");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveSouthWestCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("move southwest");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	/*
	 * West Command Invalid
	 */
	@Test
	public void testWCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("w");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testWestCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("west");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveWestCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("move west");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	/*
	 * Northwest Command Invalid
	 */
	@Test
	public void testNWCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("nw");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testNorthWestCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("northwest");
		
		checkInvalidMoveUpdates(game, northOfStarting);
	}
	
	@Test
	public void testMoveNorthWestCommandInvalid(){
		player.set_location(1);
		assertEquals(1, player.get_location());
		
		game.handle_command("move northwest");
		
		checkInvalidMoveUpdates(game, northOfStarting);
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
		game.set_mode("inventory");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("n");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testNorthCommandNotInModeGame(){
		game.set_mode("character");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("north");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testMoveNorthCommandNotInModeGame(){
		game.set_mode("inventory");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("move north");
		
		checkNotInModeGame(game, starting);
	}
	
	/*
	 * Northeast Command Invalid
	 */
	@Test
	public void testNECommandNotInModeGame(){
		game.set_mode("map");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("ne");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testNorthEastCommandNotInModeGame(){
		game.set_mode("character");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("northeast");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testMoveNorthEastCommandNotInModeGame(){
		game.set_mode("map");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("move northeast");
		
		checkNotInModeGame(game, starting);
	}
	
	/*
	 * East Command Invalid
	 */
	@Test
	public void testECommandNotInModeGame(){
		game.set_mode("inventory");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("e");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testEastCommandNotInModeGame(){
		game.set_mode("map");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("east");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testMoveEastCommandNotInModeGame(){
		game.set_mode("character");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("move east");
		
		checkNotInModeGame(game, starting);
	}
	
	/*
	 * Southeast Command Invalid
	 */
	@Test
	public void testSECommandNotInModeGame(){
		game.set_mode("character");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("se");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testSouthEastCommandNotInModeGame(){
		game.set_mode("inventory");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("southeast");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testMoveSouthEastCommandNotInModeGame(){
		game.set_mode("map");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("move southeast");
		
		checkNotInModeGame(game, starting);
	}
	
	/*
	 * South Command Invalid
	 */
	@Test
	public void testSCommandNotInModeGame(){
		game.set_mode("map");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("s");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testSouthCommandNotInModeGame(){
		game.set_mode("inventory");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("south");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testMoveSouthCommandNotInModeGame(){
		game.set_mode("character");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("move south");
		
		checkNotInModeGame(game, starting);
	}
	
	/*
	 * Southwest Command Invalid
	 */
	@Test
	public void testSWCommandNotInModeGame(){
		game.set_mode("map");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("sw");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testSouthWestCommandNotInModeGame(){
		game.set_mode("inventory");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("southwest");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testMoveSouthWestCommandNotInModeGame(){
		game.set_mode("character");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("move southwest");
		
		checkNotInModeGame(game, starting);
	}
	
	/*
	 * West Command Invalid
	 */
	@Test
	public void testWCommandNotInModeGame(){
		game.set_mode("character");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("w");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testWestCommandNotInModeGame(){
		game.set_mode("map");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("west");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testMoveWestCommandNotInModeGame(){
		game.set_mode("inventory");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("move west");
		
		checkNotInModeGame(game, starting);
	}
	
	/*
	 * Northwest Command Invalid
	 */
	@Test
	public void testNWCommandNotInModeGame(){
		game.set_mode("character");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("nw");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testNorthWestCommandNotInModeGame(){
		game.set_mode("inventory");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("northwest");
		
		checkNotInModeGame(game, starting);
	}
	
	@Test
	public void testMoveNorthWestCommandNotInModeGame(){
		game.set_mode("map");
		
		assertEquals(0, player.get_location());
		
		game.handle_command("move northwest");
		
		checkNotInModeGame(game, starting);
	}
}
