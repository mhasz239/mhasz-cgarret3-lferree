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
		// Create the Game
		game = new Game();
		
		// Create Player
		Player player = new Player();
		player.setlocation(0);
		
		// Create Characters Array and Put in Player
		ArrayList<Character> chars = new ArrayList<Character>();
		chars.add(player);
		
		// Set Characters Array in Game
		game.setcharacters(chars);
		
		// Create a generic MapTile
		MapTile tile = new MapTile();
		tile.setName("Derp");
		tile.setID(0);
		
		// Create a MapTiles array and add the Tile to it
		ArrayList<MapTile> tiles = new ArrayList<MapTile>();
		tiles.add(tile);
		
		// Create the Map and set the MapTiles Array to it
		Map map = new Map();
		map.setMapTiles(tiles);
		
		// Set the Map to the Game
		game.setmap(map);
		
		// Create a tree
		Object IDontKnowAnymore = new Object();
		IDontKnowAnymore.setName("Tree");
		HashMap<String, String> commandResponses = new HashMap<String, String>();
		commandResponses.put("climb", "Hello now");
		IDontKnowAnymore.setCommandResponses(commandResponses);
		
		// Create an Objects Array with the Tree included
		ArrayList<Object> objs = new ArrayList<Object>();
		objs.add(IDontKnowAnymore);
		
		// Set the Objects Array to the MapTile
		tile.setObjects(objs);
	}
	
	@Test
	public void testClimbTreeCommand(){
		// Run the command
		game.handle_command("climb tree");
		
		// Check that dialog was updated correctly
		assertEquals(1, game.getdialog().size());
		assertEquals("Hello now", game.getdialog().get(0));
	}
	
	@Test
	public void testClimbTreeCommandWeirdCapitals(){
		// Run the command
		game.handle_command("cLImB TReE");
		
		// Check that dialog was updated correctly
		assertEquals(1, game.getdialog().size());
		assertEquals("Hello now", game.getdialog().get(0));
	}
}
