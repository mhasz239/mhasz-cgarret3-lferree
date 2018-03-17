package edu.ycp.cs320.middle_earth.model.Constructs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class MapTest{
	private Map map;
	
	@Before
	public void setup(){
		map = new Map();
	}
	
	@Test
	public void testSetMapTiles(){
		assertEquals(0, map.getMapTiles().size());
		
		ArrayList<MapTile> mapTiles = new ArrayList<MapTile>();
		MapTile tile = new MapTile();
		
		map.setMapTiles(mapTiles);
		
		assertEquals(1, map.getMapTiles().size());
		assertEquals(tile, map.getMapTiles().get(0));
	}
}
