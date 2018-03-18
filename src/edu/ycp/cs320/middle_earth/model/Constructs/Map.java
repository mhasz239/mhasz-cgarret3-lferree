package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;

public class Map extends Construct{
	private ArrayList<MapTile> mapTiles;
	
	public Map() {
		mapTiles = new ArrayList<MapTile>();
	}
	
	public ArrayList<MapTile> getMapTiles(){
		return mapTiles;
	}
	
	public void addMapTile(MapTile tile){
		mapTiles.add(tile);
	}
	
	public void setMapTiles(ArrayList<MapTile> mapTiles){
		this.mapTiles = mapTiles;
	}
}
