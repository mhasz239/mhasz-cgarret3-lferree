package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

/**
 * These Tests are meant to solely test handle_command(String command). The methods called by handle_command are 
 * tested in other Test classes.
 * 
 * TODO: Get Matt's commit of Game, then test get_dialog() for every command tested.
 */
public class GameHandleCommandTest{
	private Game game;
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
	
	@Before
	public void setup(){
		game = new Game();
		player = new Player();
		player.set_location(0);
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.set_characters(characters);
		starting = new MapTile();
		starting.setID(0);
		northOfStarting = new MapTile();
		northOfStarting.setID(1);
		northEastOfStarting = new MapTile();
		northEastOfStarting.setID(2);
		eastOfStarting = new MapTile();
		eastOfStarting.setID(3);
		southEastOfStarting = new MapTile();
		southEastOfStarting.setID(4);
		southOfStarting = new MapTile();
		southOfStarting.setID(5);
		southWestOfStarting = new MapTile();
		southWestOfStarting.setID(6);
		westOfStarting = new MapTile();
		westOfStarting.setID(7);
		northWestOfStarting = new MapTile();
		northWestOfStarting.setID(8);
		// TODO: When MapTile has connections enabled, do this.
		//starting.addConnection("north", 1);
		//starting.addConnection("northeast", 2);
		//starting.addConnection("east", 3);
		//starting.addConnection("southeast", 4);
		//starting.addConnection("south", 5);
		//starting.addConnection("southwest", 6);
		//starting.addConnection("west", 7);
		//starting.addConnection("northwest", 8);
	}
	
	@Test
	public void testInvalidCommand(){
		game.handle_command("blofjerf");
		
		// TODO: Get Matt's commit of Game to do these tests.
		//assertEquals(1, game.getDialog().size());
		//assertEquals("Sorry, I didn't understand that.", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	/*
	 * TODO: Game-Based Commands (Not Specific to Characters)
	 */
	
	/*
	 * TODO: Player-Specific Commands
	 */
	
	
	/*
	 * Character-Specific Commands
	 * Move Commands
	 */
	
	@Test
	public void testMoveNorthCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveNorthEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("northeast");
		
		assertEquals(2, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("east");
		
		assertEquals(3, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveSouthEastCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("southeast");
		
		assertEquals(4, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveSouthCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("south");
		
		assertEquals(5, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveSouthWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("southwest");
		
		assertEquals(6, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("west");
		
		assertEquals(7, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveNorthWestCommand(){
		assertEquals(0, game.get_characters().get(0).get_location());
		
		game.handle_command("northwest");
		
		assertEquals(8, game.get_characters().get(0).get_location());
	}
	
	@Test
	public void testMoveNorthCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("north");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveNorthEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("northeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("east");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthEastCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("southeast");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("south");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveSouthWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("southwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("west");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
	@Test
	public void testMoveNorthWestCommandInvalid(){
		game.get_characters().get(0).set_location(1);
		assertEquals(1, game.get_characters().get(0).get_location());
		
		game.handle_command("northwest");
		
		assertEquals(1, game.get_characters().get(0).get_location());
		// TODO: Get Matt's commit of Game to do this.
		//assertEquals(1, game.getDialog().size());
		// Not sure where message will be stored, since unique messages can be given.
		// e.g. There's a fence in the way, vs. ocean border vs. cliff too high to climb, etc.
		//assertEquals("", game.getDialog().get(0));
		throw new UnsupportedOperationException("Waiting on game.getDialog() method");
	}
	
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
