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
		Game game = new Game();
		
		ArrayList<String> test_dialog = new ArrayList<String>();
		test_dialog.add("This is a ");
		test_dialog.add("pretty simple ");
		test_dialog.add(" test.");
		
		game.set_dialog(test_dialog);
		
		assertEquals(3, game.get_dialog().size());
		assertEquals("This is a ", game.get_dialog().get(0));
		assertEquals("pretty simple ", game.get_dialog().get(1));
		assertEquals(" test.", game.get_dialog().get(2));
	}
	
	@Test
	public void testAdd_Dialog(){
		Game game = new Game();
		
		assertEquals(0, game.get_dialog().size());
		
		game.add_dialog("Test");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Test", game.get_dialog().get(0));
		
		game.add_dialog("Testy 2");
		
		assertEquals(2, game.get_dialog().size());
		assertEquals("Test", game.get_dialog().get(0));
		assertEquals("Testy 2", game.get_dialog().get(1));
	}
	
	@Test
	public void testAdd_Dialog_Over30(){
		Game game = new Game();
		
		assertEquals(0, game.get_dialog().size());
		
		for(int i = 0; i < 30; i++){
			game.add_dialog("Test: " + i);
			assertEquals(i+1, game.get_dialog().size());
			for(int j = 0; j < i; j++){
				assertEquals("Test: " + j, game.get_dialog().get(j));
			}
		}
		assertEquals(30, game.get_dialog().size());
		
		game.add_dialog("Derp");
		
		// 30 = max dialog length
		assertEquals(30, game.get_dialog().size());
		
		for(int i = 0; i < 29; i++){
			assertEquals("Test: " + (i+1), game.get_dialog().get(i));
		}
		assertEquals("Derp", game.get_dialog().get(29));
	}
	
	@Test
	public void testGet_Display_Text(){
		Game game = new Game();
		
		ArrayList<String> test_dialog = new ArrayList<String>();
		test_dialog.add("This is a ");
		test_dialog.add("pretty simple ");
		test_dialog.add(" test.");
		
		game.set_dialog(test_dialog);
		
		assertEquals("This is a ;pretty simple ; test.", game.get_display_text());
	}
}
