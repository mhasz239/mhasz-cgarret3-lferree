package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;

public class Map extends Construct{
	private ArrayList<MapTile> mapTiles;
	private String name;
	private int id;
	private String shortDescription;
	private String longDescription;
	
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
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getshortDescription() {
		return this.shortDescription;
	}
	
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	public String getLongDescription() {
		return this.longDescription;
	}
}
