package edu.ycp.cs320.middle_earth.model.Constructs;

import static org.junit.Assert.assertEquals;

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
		assertEquals(0, (int) tile.getConnections().get("north"));
		assertEquals(0, (int) tile.getConnections().get("south"));
		assertEquals(0, (int) tile.getConnections().get("east"));
		assertEquals(0, (int) tile.getConnections().get("west"));
		assertEquals(0, (int) tile.getConnections().get("northwest"));
		assertEquals(0, (int) tile.getConnections().get("northeast"));
		assertEquals(0, (int) tile.getConnections().get("southwest"));
		assertEquals(0, (int) tile.getConnections().get("southeast"));
	}
	
	@Test
	public void testSetConnection(){
		tile.setConnection("north", 10);
		
		assertEquals(10, (int) tile.getConnections().get("north"));
	}
	
	@Test
	public void testResetConnection(){
		tile.setConnection("north", 10);
		assertEquals(10, (int) tile.getConnections().get("north"));
		
		tile.setConnection("north", 93);
		assertEquals(93, (int) tile.getConnections().get("north"));
	}
}
