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
 * These tests are solely for the Character-Specific (excluding Player) action methods in Game.
 * Other methods in Game are tested elsewhere.
 */
public class GameCharacterActionsTest{
	private Game game;
	private Map map;
	private Player player;
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
	
	@Before
	public void setup(){
		game = new Game();
		// This is here in case Game doesn't set mode to game by default.
		game.set_mode("game");
		player = new Player();
		player.set_location(0);
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.set_characters(characters);
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
		
		// Set Connections
		starting.setConnection("north", 1);
		starting.setConnection("northeast", 2);
		starting.setConnection("east", 3);
		starting.setConnection("southeast", 4);
		starting.setConnection("south", 5);
		starting.setConnection("southwest", 6);
		starting.setConnection("west", 7);
		starting.setConnection("northwest", 8);
		
		// Create Map
		map = new Map();
		
		// Populate Map
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
		
		invalidDirection = "You can't go that way";
	}
	
	
	/*
	 * Move Player
	 */
	
	@Test
	public void testMoveNorth(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.move("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthEast(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.move("northeast");
		
		assertEquals(2, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveEast(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.move("east");
		
		assertEquals(3, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(eastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthEast(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.move("southeast");
		
		assertEquals(4, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southEastOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouth(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.move("south");
		
		assertEquals(5, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthWest(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.move("southwest");
		
		assertEquals(6, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(southWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveWest(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.move("west");
		
		assertEquals(7, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(westOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthWest(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.move("northwest");
		
		assertEquals(8, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(northWestOfStarting.getLongDescription(), game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.move("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthEastInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.move("northeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveEastInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.move("east");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthEastInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.move("southeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.move("south");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveSouthWestInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.move("southwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveWestInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.move("west");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	@Test
	public void testMoveNorthWestInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.move("northwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		assertEquals(1, game.get_dialog().size());
		assertEquals(invalidDirection, game.get_dialog().get(0));
	}
	
	/*
	 * Attack
	 * TODO: JUNIT: Attack tests
	 */
	
	/*
	 * Loot
	 * TODO: JUNIT: Loot tests
	 */
}
