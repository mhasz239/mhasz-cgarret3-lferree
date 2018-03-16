package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;

public class Map extends Construct{
	private ArrayList<MapTile> mapTiles;
	private String name;
	private String description;
	
	public Map() {
		mapTiles = new ArrayList<MapTile>();
	}
	
	public void setMapTiles(ArrayList<MapTile> mapTiles) {
		for(MapTile mapTile : mapTiles) {
			this.mapTiles.add(mapTile);
		}
	}
	
	public ArrayList<MapTile> getMapTiles() {
		return this.mapTiles;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
