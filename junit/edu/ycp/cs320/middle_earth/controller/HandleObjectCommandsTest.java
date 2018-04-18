package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

public class HandleObjectCommandsTest{
	private Game game;
	
	@Before
	public void setup(){
		game = new Game();
		
		ArrayList<Character> chars = new ArrayList<Character>();
		Player player = new Player();
		player.set_location(0);
		chars.add(player);
		
		MapTile tile = new MapTile();
		tile.setName("Derp");
		tile.setID(0);
		
		ArrayList<MapTile> tiles = new ArrayList<MapTile>();
		tiles.add(tile);
		Map map = new Map();
		map.setMapTiles(tiles);
		game.set_map(map);
		
		Object IDontKnowAnymore = new Object();
		IDontKnowAnymore.setName("Tree");
		HashMap<String, String> commandResponses = new HashMap<String, String>();
		commandResponses.put("climb", "Hello now");
		IDontKnowAnymore.setCommandResponses(commandResponses);
		
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(IDontKnowAnymore);
		tile.setObjects(objs);
	}
	
	@Test
	public void testClimbTreeCommand(){
		game.handle_command("climb tree");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Hello now", game.get_dialog().get(0));
	}
	
	@Test
	public void testClimbTreeCommandWeirdCapitals(){
		game.handle_command("cLImB TReE");
		
		assertEquals(1, game.get_dialog().size());
		assertEquals("Hello now", game.get_dialog().get(0));
	}
}
