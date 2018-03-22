package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

/**
 * These tests are solely meant to test the setters in the Game class. Other methods are tested in other Test classes.
 */
public class GameSettersTest{
	
	// TODO: JUNIT: Test that the constructor properly gets stuff from the database?
	@Test
	public void testGet_Game(){
		Game game = new Game();
		
		assertEquals(game, game.get_game());
	}
	
	@Test
	public void testSet_Mode(){
		Game game = new Game();
		
		game.set_mode("game");
		
		assertEquals("game", game.get_mode());
	}
	
	@Test
	public void testReset_Mode(){
		Game game = new Game();
		
		game.set_mode("game");
		
		assertEquals("game", game.get_mode());
		
		game.set_mode("inventory");
		
		assertEquals("inventory", game.get_mode());
	}
	
	@Test
	public void testSet_Characters(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		chars.add(player);
		
		game.set_characters(chars);
		
		assertEquals(1, game.get_characters().size());
		assertEquals(player, game.get_characters().get(0));
	}
	
	@Test
	public void testReset_Characters(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		chars.add(player);
		
		game.set_characters(chars);
		
		assertEquals(1, game.get_characters().size());
		assertEquals(player, game.get_characters().get(0));
		
		ArrayList<Character> chars2 = new ArrayList<Character>();
		chars2.add(new Player());
		chars2.add(new Player());
		
		game.set_characters(chars2);
		
		assertEquals(2, game.get_characters().size());
	}
	
	@Test
	public void testGet_Player(){
		Game game = new Game();
		
		assertEquals(game.get_characters().get(0), game.get_player());
	}
	
	@Test
	public void testGet_Player_Set_Characters(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		Player player2 = new Player();
		chars.add(player);
		chars.add(player2);
		
		game.set_characters(chars);
		
		assertEquals(2, game.get_characters().size());
		assertEquals(player, game.get_characters().get(0));
		assertEquals(player2, game.get_characters().get(1));
		
		assertEquals(player, game.get_player());
	}
	
	@Test
	public void testSet_Map(){
		Game game = new Game();
		Map mappy = new Map();
		game.set_map(mappy);
		
		assertEquals(mappy, game.get_map());
	}
	
	@Test
	public void testReset_Map(){
		Game game = new Game();
		Map mappy = new Map();
		game.set_map(mappy);
		
		assertEquals(mappy, game.get_map());
		
		Map mappy2 = new Map();
		mappy2.addMapTile(new MapTile());
		game.set_map(mappy2);
		
		assertEquals(mappy2, game.get_map());
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
	public void testReset_Objects(){
		Game game = new Game();
		ArrayList<Object> objs = new ArrayList<Object>();
		Object bloop = new Object();
		objs.add(bloop);
		game.set_objects(objs);
		
		assertEquals(1, game.get_objects().size());
		assertEquals(bloop, game.get_objects().get(0));
		
		ArrayList<Object> objs2 = new ArrayList<Object>();
		Object bloop2 = new Object();
		Object derpy = new Object();
		objs2.add(bloop2);
		objs2.add(derpy);
		game.set_objects(objs2);
		
		assertEquals(2, game.get_objects().size());
		assertEquals(bloop2, game.get_objects().get(0));
		assertEquals(derpy, game.get_objects().get(1));
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
	public void testReset_Items(){
		Game game = new Game();
		ArrayList<Item> itms = new ArrayList<Item>();
		Item plop = new Item();
		itms.add(plop);
		game.set_items(itms);
		
		assertEquals(1, game.get_items().size());
		assertEquals(plop, game.get_items().get(0));
		
		ArrayList<Item> itms2 = new ArrayList<Item>();
		Item plop2 = new Item();
		Item derp = new Item();
		itms2.add(plop2);
		itms2.add(derp);
		game.set_items(itms2);
		
		assertEquals(2, game.get_items().size());
		assertEquals(plop2, game.get_items().get(0));
		assertEquals(derp, game.get_items().get(1));
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
	
	@Test
	public void testSet_Quests(){
		Game game = new Game();
		
		ArrayList<Quest> quests = new ArrayList<Quest>();
		game.set_quests(quests);
		
		assertEquals(quests, game.get_quests());
	}
	
	@Test
	public void testReset_Quests(){
		Game game = new Game();
		
		ArrayList<Quest> quests = new ArrayList<Quest>();
		game.set_quests(quests);
		
		assertEquals(quests, game.get_quests());
		
		ArrayList<Quest> quests2 = new ArrayList<Quest>();
		quests2.add(new Quest());
		game.set_quests(quests2);
		
		assertEquals(quests2, game.get_quests());
	}
	
	@Test
	public void testGet_MapTile_LongDescription(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		player.set_location(0);
		chars.add(player);
		game.set_characters(chars);
		
		Map map = new Map();
		MapTile starting = new MapTile();
		starting.setName("Derp");
		starting.setLongDescription("Just a long description here. Nothing more.");
		map.addMapTile(starting);
		game.set_map(map);
		
		assertEquals(starting.getLongDescription(), game.get_mapTile_longDescription());
	}
	
	@Test
	public void testGet_MapTile_Name(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		player.set_location(0);
		chars.add(player);
		game.set_characters(chars);
		
		Map map = new Map();
		MapTile starting = new MapTile();
		starting.setName("Derp");
		starting.setLongDescription("Just a long description here. Nothing more.");
		map.addMapTile(starting);
		game.set_map(map);
		
		assertEquals(starting.getName(), game.get_mapTile_name());
	}
}
