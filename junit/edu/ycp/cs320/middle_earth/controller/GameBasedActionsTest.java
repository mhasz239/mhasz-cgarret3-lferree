package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * These tests are solely for testing the Game-Based Actions (not specific to Characters) in Game. 
 * Other methods are tested in other classes.
 */
public class GameBasedActionsTest{
	private Game game;
	
	@Before
	public void setup(){
		game = new Game();
	}
	
	/*
	 * Check Character Sheet
	 */
	@Test
	public void testCheckCharacterSheetCommand(){
		game.check_character_sheet();
		
		assertEquals("character", game.get_mode());
	}
	
	/* TODO: JUNIT: Remove? Note: Commented out these in Game.
	@Test
	public void testAlreadyInCharacter(){
		game.set_mode("character");
		
		game.check_character_sheet();
		
		assertEquals("character", game.get_mode());
		assertEquals(1, game.get_dialog().size());
		assertEquals("You're already in it!", game.get_dialog().get(0));
	}
	*/
	
	/*
	 * Check Inventory
	 */
	@Test
	public void testCheckInventoryCommand(){
		game.check_inventory();
		
		assertEquals("inventory", game.get_mode());
	}
	
	/* TODO: JUNIT: Remove? Note: Commented out these in Game.
	@Test
	public void testAlreadyInInventory(){
		game.set_mode("inventory");
		
		game.check_inventory();
		
		assertEquals("inventory", game.get_mode());
		assertEquals(1, game.get_dialog().size());
		assertEquals("You're already in it!", game.get_dialog().get(0));
	}
	*/
	
	/*
	 * Check Map
	 */
	@Test
	public void testMapCommand(){
		game.check_map();
		
		assertEquals("map", game.get_mode());
	}
	
	/* TODO: JUNIT: Remove? Note: Commented out these in Game.
	@Test
	public void testAlreadyInMap(){
		game.set_mode("map");
		
		game.check_map();
		
		assertEquals("map", game.get_mode());
		assertEquals(1, game.get_dialog().size());
		assertEquals("You're already in it!", game.get_dialog().get(0));
	}
	*/
	
	/*
	 * Return to Game
	 */
	@Test
	public void testBackToGameFromCharacterSheet(){
		game.set_mode("character");
		
		game.return_to_game();
		
		assertEquals("game", game.get_mode());
	}
	
	@Test
	public void testBackToGameFromInventory(){
		game.set_mode("inventory");
		
		game.return_to_game();
		
		assertEquals("game", game.get_mode());
	}
	
	@Test
	public void testBackToGameFromMap(){
		game.set_mode("map");
		
		game.return_to_game();
		
		assertEquals("game", game.get_mode());
	}
	
	/* TODO: JUNIT: Remove? Note: Commented out these in Game.
	@Test
	public void testAlreadyInGame(){
		game.set_mode("game");
		
		game.return_to_game();
		
		assertEquals("game", game.get_mode());
		assertEquals(1, game.get_dialog().size());
		assertEquals("You're playing it!", game.get_dialog().get(0));
	}
	*/
	
	/*
	 * Save
	 * TODO: JUNIT: Save Tests
	 */
}
