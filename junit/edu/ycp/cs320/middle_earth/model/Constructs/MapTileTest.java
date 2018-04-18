package edu.ycp.cs320.middle_earth.model.Constructs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MapTileTest{
	private MapTile tile;
	
	@Before
	public void setup(){
		tile = new MapTile();
	}
	
	@Test
	public void testDefaultConnections(){
		// They all need to be 0 for the given 
		assertEquals(0, (int) tile.getConnections().get("north"));
		assertEquals(0, (int) tile.getConnections().get("south"));
		assertEquals(0, (int) tile.getConnections().get("east"));
		assertEquals(0, (int) tile.getConnections().get("west"));
		assertEquals(0, (int) tile.getConnections().get("northwest"));
		assertEquals(0, (int) tile.getConnections().get("northeast"));
		assertEquals(0, (int) tile.getConnections().get("southwest"));
		assertEquals(0, (int) tile.getConnections().get("southeast"));
		
		// Check that it's only the 8 directions in the HashMap
		assertEquals(8, tile.getConnections().keySet().size());
		
		// Same for these (just a different method for getting them)
		assertEquals(0, (int) tile.getMoveValue("north"));
		assertEquals(0, (int) tile.getMoveValue("south"));
		assertEquals(0, (int) tile.getMoveValue("east"));
		assertEquals(0, (int) tile.getMoveValue("west"));
		assertEquals(0, (int) tile.getMoveValue("northwest"));
		assertEquals(0, (int) tile.getMoveValue("northeast"));
		assertEquals(0, (int) tile.getMoveValue("southwest"));
		assertEquals(0, (int) tile.getMoveValue("southeast"));
	}
	
	@Test
	public void testSetConnection(){
		// Set north to be 10
		tile.setConnection("north", 10);
		// Check north is 10
		assertEquals(10, (int) tile.getConnections().get("north"));
		
		// Set north to be 93
		tile.setConnection("north", 93);
		// Check north to be 93
		assertEquals(93, (int) tile.getConnections().get("north"));
	}
	
	@Test
	public void testSetObjects(){
		// Create an ArrayList of Objects
		ArrayList<Object> objs = new ArrayList<Object>();
		Object derp = new Object();
		derp.setName("Derpykins");
		Object pling = new Object();
		pling.setName("Mine");
		objs.add(derp);
		objs.add(pling);
		
		// Set it to MapTile
		tile.setObjects(objs);
		
		// Ensure it's right
		assertEquals(2, tile.getObjects().size());
		assertEquals(derp, tile.getObjects().get(0));
		assertEquals(pling, tile.getObjects().get(1));
		
		// Create another ArrayList
		ArrayList<Object> objs2 = new ArrayList<Object>();
		Object ploppy = new Object();
		ploppy.setName("IDK");
		Object sloppy = new Object();
		sloppy.setName("Hopefully not too much");
		Object eh = new Object();
		eh.setName("Nah");
		objs2.add(ploppy);
		objs2.add(sloppy);
		objs2.add(eh);
		
		// Reset it to MapTile (in case of crazy adding)
		tile.setObjects(objs2);
		
		// Check that right
		assertEquals(3, tile.getObjects().size());
		assertEquals(ploppy, tile.getObjects().get(0));
		assertEquals(sloppy, tile.getObjects().get(1));
		assertEquals(eh, tile.getObjects().get(2));
	}
	
	@Test
	public void testSetVisited(){
		// Check that by default it's not
		assertEquals(false, tile.getVisited());
		
		// Set it true
		tile.setVisited(true);
		
		// Check it true
		assertEquals(true, tile.getVisited());
		
		// Try setting true again (to ensure not just a flip)
		tile.setVisited(true);
		
		// Check it true (again)
		assertEquals(true, tile.getVisited());
	}
	
	@Test
	public void testSetEnemyString(){
		// Set it
		tile.setEnemyString("2,39,1,980394,120");
		
		// Check it
		assertEquals("2,39,1,980394,120", tile.getEnemyString());
		
		// Reset it (to ensure no crazies)
		tile.setEnemyString("1,2,3,4,65");
		
		// Check it again
		assertEquals("1,2,3,4,65", tile.getEnemyString());
	}
	
	@Test
	public void testGetEnemyIDs(){
		// Set the EnemyString
		tile.setEnemyString("2,39,1,980394,120");
		
		// Get the ArrayList
		ArrayList<Integer> ids = tile.getEnemyIDs();
		
		// Check it's right
		assertEquals(5, ids.size());
		assertEquals(2, (int) ids.get(0));
		assertEquals(39, (int) ids.get(1));
		assertEquals(1, (int) ids.get(2));
		assertEquals(980394, (int) ids.get(3));
		assertEquals(120, (int) ids.get(4));
	}
}
