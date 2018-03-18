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
	public void testAddMapTile(){
		MapTile tile1 = new MapTile();
		MapTile tile2 = new MapTile();
		tile2.setName("Derpy");
		
		assertEquals(0, map.getMapTiles().size());
		
		map.addMapTile(tile1);
		assertEquals(1, map.getMapTiles().size());
		assertEquals(tile1, map.getMapTiles().get(0));
		
		map.addMapTile(tile2);
		assertEquals(2, map.getMapTiles().size());
		assertEquals(tile1, map.getMapTiles().get(0));
		assertEquals(tile2, map.getMapTiles().get(1));
	}
	
	@Test
	public void testSetMapTiles(){
		assertEquals(0, map.getMapTiles().size());
		
		ArrayList<MapTile> mapTiles = new ArrayList<MapTile>();
		MapTile tile = new MapTile();
		mapTiles.add(tile);
		
		map.setMapTiles(mapTiles);
		
		assertEquals(1, map.getMapTiles().size());
		assertEquals(tile, map.getMapTiles().get(0));
	}
	
	@Test
	public void testResetMapTiles(){
		assertEquals(0, map.getMapTiles().size());
		
		ArrayList<MapTile> mapTiles = new ArrayList<MapTile>();
		MapTile tile = new MapTile();
		mapTiles.add(tile);
		
		map.setMapTiles(mapTiles);
		
		assertEquals(1, map.getMapTiles().size());
		assertEquals(tile, map.getMapTiles().get(0));
		
		ArrayList<MapTile> mapTiles2 = new ArrayList<MapTile>();
		MapTile tile1 = new MapTile();
		tile1.setName("Basic");
		MapTile tile2 = new MapTile();
		tile2.setName("Other Basic");
		mapTiles2.add(tile1);
		mapTiles2.add(tile2);
		
		map.setMapTiles(mapTiles2);
		
		assertEquals(2, map.getMapTiles().size());
		assertEquals(tile1, map.getMapTiles().get(0));
		assertEquals(tile2, map.getMapTiles().get(1));
	}
}
