package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.HashMap;
//import edu.ycp.cs320.middle_earth.model.CombatSituation;

public class MapTile extends Construct{
	private HashMap<String, MapTile> connections;
	//private ArrayList<CombatSituation> random_encounters;
	
	public MapTile() {
		
	}
	
	public void setConnections(HashMap<String, MapTile> connections) {
		this.connections = connections;
	}
	
	public HashMap<String, MapTile> getConnections() {
		return this.connections;
	}
}
