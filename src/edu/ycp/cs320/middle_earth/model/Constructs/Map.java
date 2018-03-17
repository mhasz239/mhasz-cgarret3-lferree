package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;

public class Map extends Construct{
	private ArrayList<MapTile> mapTiles;
	
	public Map() {
		mapTiles = new ArrayList<MapTile>();
	}
	
	public void setMapTiles(ArrayList<MapTile> mapTiles) {
		for(MapTile mapTile : mapTiles) {
			this.mapTiles.add(mapTile);
		}
	}
}
