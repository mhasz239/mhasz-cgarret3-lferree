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
	private String invalidDirection;
	private String invalidMode;
	
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
		
		invalidDirection = "You can't go that way";
		invalidMode = "You can't use that command here.";
		
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
	
	/*
	 * North Command Working
	 */
	@Test
	public void testNCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("n");
		
		assertEquals(1, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testNorthCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveNorthCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("move north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	/*
	 * Northeast Command Working
	 */
	@Test
	public void testNECommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("ne");
		
		assertEquals(2, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testNorthEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("northeast");
		
		assertEquals(2, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("move northeast");
		
		assertEquals(2, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	/*
	 * East Command Working
	 */
	@Test
	public void testECommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("e");
		
		assertEquals(3, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(eastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("east");
		
		assertEquals(3, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(eastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("move east");
		
		assertEquals(3, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(eastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	/*
	 * Southeast Command Working
	 */
	@Test
	public void testSECommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("se");
		
		assertEquals(4, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("southeast");
		
		assertEquals(4, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("move southeast");
		
		assertEquals(4, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	/*
	 * South Command Working
	 */
	@Test
	public void testSCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("s");
		
		assertEquals(5, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("south");
		
		assertEquals(5, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("move south");
		
		assertEquals(5, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	/*
	 * Southwest Command Working
	 */
	@Test
	public void testSWCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("sw");
		
		assertEquals(6, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("southwest");
		
		assertEquals(6, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("move southwest");
		
		assertEquals(6, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	/*
	 * West Command Working
	 */
	@Test
	public void testWCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("w");
		
		assertEquals(7, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(westOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("west");
		
		assertEquals(7, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(westOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("move west");
		
		assertEquals(7, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(westOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	/*
	 * Northwest Command Working
	 */
	@Test
	public void testNWCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("nw");
		
		assertEquals(8, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testNorthWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("northwest");
		
		assertEquals(8, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("move northwest");
		
		assertEquals(8, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northWestOfStarting.getLongDescription(), game.get_dialog().get(0));
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
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("n");
		
		assertEquals(1, game.get_characters().get(0).get_location());

		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testNorthCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/*
	 * Northeast Command Invalid
	 */
	@Test
	public void testNECommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("ne");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testNorthEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("northeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move northeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/*
	 * East Command Invalid
	 */
	@Test
	public void testECommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("e");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("east");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move east");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/*
	 * Southeast Command Invalid
	 */
	@Test
	public void testSECommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("se");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("southeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move southeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/*
	 * South Command Invalid
	 */
	@Test
	public void testSCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("s");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("south");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move south");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/*
	 * Southwest Command Invalid
	 */
	@Test
	public void testSWCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("sw");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("southwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move southwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/*
	 * West Command Invalid
	 */
	@Test
	public void testWCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("w");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("west");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move west");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/*
	 * Northwest Command Invalid
	 */
	@Test
	public void testNWCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("nw");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testNorthWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("northwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move northwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
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
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("n");
		
		assertEquals(1, game.get_characters().get(0).get_location());

		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testNorthCommandNotInModeGame(){
		game.set_mode("character");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthCommandNotInModeGame(){
		game.set_mode("inventory");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * Northeast Command Invalid
	 */
	@Test
	public void testNECommandNotInModeGame(){
		game.set_mode("map");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("ne");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testNorthEastCommandNotInModeGame(){
		game.set_mode("character");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("northeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthEastCommandNotInModeGame(){
		game.set_mode("map");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move northeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * East Command Invalid
	 */
	@Test
	public void testECommandNotInModeGame(){
		game.set_mode("inventory");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("e");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testEastCommandNotInModeGame(){
		game.set_mode("map");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("east");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveEastCommandNotInModeGame(){
		game.set_mode("character");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move east");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * Southeast Command Invalid
	 */
	@Test
	public void testSECommandNotInModeGame(){
		game.set_mode("character");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("se");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthEastCommandNotInModeGame(){
		game.set_mode("inventory");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("southeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthEastCommandNotInModeGame(){
		game.set_mode("map");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move southeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * South Command Invalid
	 */
	@Test
	public void testSCommandNotInModeGame(){
		game.set_mode("map");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("s");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthCommandNotInModeGame(){
		game.set_mode("inventory");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("south");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthCommandNotInModeGame(){
		game.set_mode("character");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move south");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * Southwest Command Invalid
	 */
	@Test
	public void testSWCommandNotInModeGame(){
		game.set_mode("map");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("sw");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testSouthWestCommandNotInModeGame(){
		game.set_mode("inventory");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("southwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthWestCommandNotInModeGame(){
		game.set_mode("character");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move southwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * West Command Invalid
	 */
	@Test
	public void testWCommandNotInModeGame(){
		game.set_mode("character");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("w");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testWestCommandNotInModeGame(){
		game.set_mode("map");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("west");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveWestCommandNotInModeGame(){
		game.set_mode("inventory");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move west");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	/*
	 * Northwest Command Invalid
	 */
	@Test
	public void testNWCommandNotInModeGame(){
		game.set_mode("character");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("nw");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testNorthWestCommandNotInModeGame(){
		game.set_mode("inventory");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("northwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthWestCommandNotInModeGame(){
		game.set_mode("map");
		
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("move northwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidMode, game.get_dialog().get(0));
	}
}
