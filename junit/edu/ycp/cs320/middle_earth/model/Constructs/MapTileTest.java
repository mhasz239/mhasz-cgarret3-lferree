package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class MapTileTest{
	private MapTile tile;
	
	@Before
	public void setup(){
		tile = new MapTile();
	}
	
	@Test
	public void testSetConnections(){
		HashMap<String, Integer> conns = new HashMap<String, Integer>();
		conns.put("north", 10);
		
		// TODO: Wait for Chris's correction of MapTile (currently connections = <HashMap<String, MapTile>>)
		//tile.setConnections(conns);
		//assertEquals(10, tile.getConnections().get("north"));
		throw new UnsupportedOperationException("Waiting on Chris's fix");
	}
	
	@Test
	public void testResetConnections(){
		HashMap<String, Integer> conns = new HashMap<String, Integer>();
		conns.put("north", 10);
		
		// TODO: Wait for Chris's correction of MapTile (currently connections = <HashMap<String, MapTile>)
		//tile.setConnections(conns);
		//assertEquals(10, tile.getConnections().get("north"));
		
		HashMap<String, Integer> conns2 = new HashMap<String, Integer>();
		conns2.put("south", 25);
		conns2.put("northeast", 50);
		
		// TODO: Wait for Chris's correction of MapTile (currently connections = <HashMap<String, MapTile>)
		//tile.setConnections(conns2);
		//assertEquals(null, tile.getConnections().get("north"));
		//assertEquals(25, tile.getConnections().get("south"));
		//assertEquals(50, tile.getConnections().get("northeast"));
		
		throw new UnsupportedOperationException("Waiting on Chris's fix");
	}
}
