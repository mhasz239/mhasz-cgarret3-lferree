package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.HashMap;
//import edu.ycp.cs320.middle_earth.model.CombatSituation;

public class MapTile extends Construct{
	private HashMap<String, Integer> connections;
	//private ArrayList<CombatSituation> random_encounters;
	
	public MapTile() {
		
	}
	
	public void setConnections(HashMap<String, Integer> connections) {
		this.connections = connections;
		
	}
	
	public HashMap<String, Integer> getConnections() {
		return this.connections;
	}
	
	
	public int getMoveValue(String direction) {
		return connections.get(direction);
	}
}
