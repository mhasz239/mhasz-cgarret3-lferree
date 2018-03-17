package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

/**
 * These tests are solely meant to test the setters in the Game class. Other methods are tested in other Test classes.
 */
public class GameSettersTest{
	
	@Test
	public void testSet_Characters(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		chars.add(player);
		
		game.set_characters(chars);
		
		assertEquals(player, game.get_characters().get(0));
		assertEquals(1, game.get_characters().size());
	}
	
	@Test
	public void testSet_Map(){
		Game game = new Game();
		Map mappy = new Map();
		game.set_map(mappy);
		
		assertEquals(mappy, game.get_map());
	}
	
	@Test
	public void testSet_Objects(){
		Game game = new Game();
		ArrayList<Object> objs = new ArrayList<Object>();
		Object bloop = new Object();
		objs.add(bloop);
		game.set_objects(objs);
		
		assertEquals(1, game.get_objects().size());
		assertEquals(bloop, game.get_objects().get(0));
	}
	
	@Test
	public void testSet_Items(){
		Game game = new Game();
		ArrayList<Item> itms = new ArrayList<Item>();
		Item plop = new Item();
		itms.add(plop);
		game.set_items(itms);
		
		assertEquals(1, game.get_items().size());
		assertEquals(plop, game.get_items().get(0));
	}
	
	@Test
	public void testSet_Dialog(){
		//TODO: Get Matt's commit of Game to do this.
		// Wouldn't Set Dialog be to set the full thing, not just add one line?
		// Dialog would need to be set in some way with each jsp load, since model and controller
		// get recreated each time.
		// It can be done line by line I guess, but it should probably be add_dialog() then.
		throw new UnsupportedOperationException("Waiting on game.set_dialog() method");
	}
	
	@Test
	public void testGet_Display_Text(){
		// TODO: Get Matt's commit of Game for this.
		throw new UnsupportedOperationException("Waiting on game.get_display_text() method");
	}
}
