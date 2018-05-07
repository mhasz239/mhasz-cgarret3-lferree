package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameModeChangeTest{
	private Game game;
	
	@Before
	public void setup(){
		game = new Game();
		game.setmode("game");
	}
	
	@Test
	public void testModeChangeNullCommand(){
		assertFalse(game.mode_change(null));
	}
	
	@Test
	public void testModeChangeEmptyCommand(){
		assertFalse(game.mode_change(""));
	}
	
	@Test
	public void testModeChangeGameCommand(){
		game.setmode("inventory");
		assertTrue(game.mode_change("game"));
		
		assertEquals("game", game.getmode());
	}
	
	@Test
	public void testModeChangeInventoryCommand(){
		assertTrue(game.mode_change("inventory"));
		
		assertEquals("inventory", game.getmode());
	}
	
	@Test
	public void testModeChangeMapCommand(){
		assertTrue(game.mode_change("map"));
		
		assertEquals("map", game.getmode());
	}
	
	@Test
	public void testModeChangeCharacterCommand(){
		assertTrue(game.mode_change("character"));
		
		assertEquals("character", game.getmode());
	}
}
