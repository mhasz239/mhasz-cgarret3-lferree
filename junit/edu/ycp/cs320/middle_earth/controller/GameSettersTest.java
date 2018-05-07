package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.CombatSituation;
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
	public void testgetGame(){
		Game game = new Game();
		
		assertEquals(game, game.getgame());
	}
	
	@Test
	public void testsetMode(){
		Game game = new Game();
		
		game.setmode("game");
		
		assertEquals("game", game.getmode());
	}
	
	@Test
	public void testResetMode(){
		Game game = new Game();
		
		game.setmode("game");
		
		assertEquals("game", game.getmode());
		
		game.setmode("inventory");
		
		assertEquals("inventory", game.getmode());
	}
	
	@Test
	public void testsetCharacters(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		chars.add(player);
		
		game.setcharacters(chars);
		
		assertEquals(1, game.getcharacters().size());
		assertEquals(player, game.getcharacters().get(0));
	}
	
	@Test
	public void testResetCharacters(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		chars.add(player);
		
		game.setcharacters(chars);
		
		assertEquals(1, game.getcharacters().size());
		assertEquals(player, game.getcharacters().get(0));
		
		ArrayList<Character> chars2 = new ArrayList<Character>();
		chars2.add(new Player());
		chars2.add(new Player());
		
		game.setcharacters(chars2);
		
		assertEquals(2, game.getcharacters().size());
	}
	
	@Test
	public void testgetPlayer(){
		Game game = new Game();
		
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(new Player());
		
		game.setcharacters(characters);
		
		assertEquals(game.getcharacters().get(0), game.getplayer());
	}
	
	@Test
	public void testgetPlayer_setCharacters(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		player.sethit_points(100);
		player.setname("Derpkins");
		Player player2 = new Player();
		player2.setattack(20);
		player2.setname("My Face");
		chars.add(player);
		chars.add(player2);
		
		game.setcharacters(chars);
		
		assertEquals(2, game.getcharacters().size());
		assertEquals(player, game.getcharacters().get(0));
		assertEquals(player2, game.getcharacters().get(1));
		
		assertEquals(player, game.getplayer());
	}
	
	@Test
	public void testsetMap(){
		Game game = new Game();
		Map mappy = new Map();
		game.setmap(mappy);
		
		assertEquals(mappy, game.getmap());
	}
	
	@Test
	public void testResetMap(){
		Game game = new Game();
		Map mappy = new Map();
		game.setmap(mappy);
		
		assertEquals(mappy, game.getmap());
		
		Map mappy2 = new Map();
		mappy2.addMapTile(new MapTile());
		game.setmap(mappy2);
		
		assertEquals(mappy2, game.getmap());
	}
	
	@Test
	public void testsetObjects(){
		Game game = new Game();
		ArrayList<Object> objs = new ArrayList<Object>();
		Object bloop = new Object();
		objs.add(bloop);
		game.setobjects(objs);
		
		assertEquals(1, game.getobjects().size());
		assertEquals(bloop, game.getobjects().get(0));
	}
	
	@Test
	public void testResetObjects(){
		Game game = new Game();
		ArrayList<Object> objs = new ArrayList<Object>();
		Object bloop = new Object();
		objs.add(bloop);
		game.setobjects(objs);
		
		assertEquals(1, game.getobjects().size());
		assertEquals(bloop, game.getobjects().get(0));
		
		ArrayList<Object> objs2 = new ArrayList<Object>();
		Object bloop2 = new Object();
		Object derpy = new Object();
		objs2.add(bloop2);
		objs2.add(derpy);
		game.setobjects(objs2);
		
		assertEquals(2, game.getobjects().size());
		assertEquals(bloop2, game.getobjects().get(0));
		assertEquals(derpy, game.getobjects().get(1));
	}
	
	@Test
	public void testsetItems(){
		Game game = new Game();
		ArrayList<Item> itms = new ArrayList<Item>();
		Item plop = new Item();
		itms.add(plop);
		game.setitems(itms);
		
		assertEquals(1, game.getitems().size());
		assertEquals(plop, game.getitems().get(0));
	}
	
	@Test
	public void testResetItems(){
		Game game = new Game();
		ArrayList<Item> itms = new ArrayList<Item>();
		Item plop = new Item();
		itms.add(plop);
		game.setitems(itms);
		
		assertEquals(1, game.getitems().size());
		assertEquals(plop, game.getitems().get(0));
		
		ArrayList<Item> itms2 = new ArrayList<Item>();
		Item plop2 = new Item();
		Item derp = new Item();
		itms2.add(plop2);
		itms2.add(derp);
		game.setitems(itms2);
		
		assertEquals(2, game.getitems().size());
		assertEquals(plop2, game.getitems().get(0));
		assertEquals(derp, game.getitems().get(1));
	}
	
	@Test
	public void testsetDialog(){
		Game game = new Game();
		
		ArrayList<String> test_dialog = new ArrayList<String>();
		test_dialog.add("This is a ");
		test_dialog.add("pretty simple ");
		test_dialog.add(" test.");
		
		game.setdialog(test_dialog);
		
		assertEquals(3, game.getdialog().size());
		assertEquals("This is a ", game.getdialog().get(0));
		assertEquals("pretty simple ", game.getdialog().get(1));
		assertEquals(" test.", game.getdialog().get(2));
	}
	
	@Test
	public void testResetDialog(){
		Game game = new Game();
		
		ArrayList<String> test_dialog = new ArrayList<String>();
		test_dialog.add("This is a ");
		test_dialog.add("pretty simple ");
		test_dialog.add(" test.");
		
		game.setdialog(test_dialog);
		
		assertEquals(3, game.getdialog().size());
		assertEquals("This is a ", game.getdialog().get(0));
		assertEquals("pretty simple ", game.getdialog().get(1));
		assertEquals(" test.", game.getdialog().get(2));
		
		ArrayList<String> test_dialog2 = new ArrayList<String>();
		test_dialog2.add("Another");
		test_dialog2.add("simple");
		
		game.setdialog(test_dialog2);
		
		assertEquals(2, game.getdialog().size());
		assertEquals("Another", game.getdialog().get(0));
		assertEquals("simple", game.getdialog().get(1));
	}
	
	@Test
	public void testAdd_Dialog(){
		Game game = new Game();
		
		assertEquals(0, game.getdialog().size());
		
		game.add_dialog("Test");
		
		assertEquals(1, game.getdialog().size());
		assertEquals("Test", game.getdialog().get(0));
		
		game.add_dialog("Testy 2");
		
		assertEquals(2, game.getdialog().size());
		assertEquals("Test", game.getdialog().get(0));
		assertEquals("Testy 2", game.getdialog().get(1));
	}
	
	@Test
	public void testAdd_Dialog_Over25(){
		Game game = new Game();
		
		assertEquals(0, game.getdialog().size());
		
		for(int i = 0; i < 35; i++){
			game.add_dialog("Test: " + i);
			assertEquals(i+1, game.getdialog().size());
			for(int j = 0; j < i; j++){
				assertEquals("Test: " + j, game.getdialog().get(j));
			}
		}
		assertEquals(35, game.getdialog().size());
		
		game.add_dialog("Derp");
		
		// 35 = max dialog length
		assertEquals(35, game.getdialog().size());
		
		for(int i = 0; i < 34; i++){
			assertEquals("Test: " + (i+1), game.getdialog().get(i));
		}
		assertEquals("Derp", game.getdialog().get(34));
	}
	
	@Test
	public void testgetDisplay_Text(){
		Game game = new Game();
		
		ArrayList<String> test_dialog = new ArrayList<String>();
		test_dialog.add("This is a ");
		test_dialog.add("pretty simple ");
		test_dialog.add(" test.");
		
		game.setdialog(test_dialog);
		
		assertEquals("This is a ;pretty simple ; test.", game.getdisplay_text());
	}
	
	@Test
	public void testsetQuests(){
		Game game = new Game();
		
		ArrayList<Quest> quests = new ArrayList<Quest>();
		game.setquests(quests);
		
		assertEquals(quests, game.getquests());
	}
	
	@Test
	public void testResetQuests(){
		Game game = new Game();
		
		ArrayList<Quest> quests = new ArrayList<Quest>();
		game.setquests(quests);
		
		assertEquals(quests, game.getquests());
		
		ArrayList<Quest> quests2 = new ArrayList<Quest>();
		quests2.add(new Quest());
		game.setquests(quests2);
		
		assertEquals(quests2, game.getquests());
	}
	
	@Test
	public void testgetMapTile_LongDescription(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		player.setlocation(0);
		chars.add(player);
		game.setcharacters(chars);
		
		Map map = new Map();
		MapTile starting = new MapTile();
		starting.setName("Derp");
		starting.setLongDescription("Just a long description here. Nothing more.");
		map.addMapTile(starting);
		game.setmap(map);
		
		assertEquals(starting.getLongDescription(), game.getmapTile_longDescription());
	}
	
	@Test
	public void testgetMapTile_Name(){
		Game game = new Game();
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		player.setlocation(0);
		chars.add(player);
		game.setcharacters(chars);
		
		Map map = new Map();
		MapTile starting = new MapTile();
		starting.setName("Derp");
		starting.setLongDescription("Just a long description here. Nothing more.");
		map.addMapTile(starting);
		game.setmap(map);
		
		assertEquals(starting.getName(), game.getmapTile_name());
	}
	
	@Test
	public void testsetBattle(){
		Game game = new Game();
		
		ArrayList<Character> chars = new ArrayList<Character>();
		chars.add(new Player());
		game.setcharacters(chars);
		
		CombatSituation sitch = new CombatSituation(game, 1, 0);
		game.setBattle(sitch);
		
		assertEquals(sitch, game.getBattle());
		
		// Do it again (for weird adding)
		CombatSituation burp = new CombatSituation(game, 2, 0);
		game.setBattle(burp);
		
		assertEquals(burp, game.getBattle());
	}
}
