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
	
	/*
	 * Check Inventory
	 */
	@Test
	public void testCheckInventoryCommand(){
		game.check_inventory();
		
		assertEquals("inventory", game.get_mode());
	}
	
	/*
	 * Check Map
	 */
	@Test
	public void testMapCommand(){
		game.check_map();
		
		assertEquals("map", game.get_mode());
	}
	
	/*
	 * Return to Game
	 */
	@Test
	public void testBackToGameFromCharacterSheet(){
		game.set_mode("character");
		
		// TODO: Assuming there will be a return to game method
		//game.returnToGame();
		
		assertEquals("game", game.get_mode());
	}
	
	@Test
	public void testBackToGameFromInventory(){
		game.set_mode("inventory");
		
		// TODO: Assuming there will be a return to game method
		//game.returnToGame();
		
		assertEquals("game", game.get_mode());
	}
	
	@Test
	public void testBackToGameFromMap(){
		game.set_mode("map");
		
		// TODO: Assuming there will be a return to game method
		//game.returnToGame();
		
		assertEquals("game", game.get_mode());
	}
	
	/*
	 * Save
	 * TODO: Save Tests
	 */
}
