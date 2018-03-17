package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.HashMap;
//import edu.ycp.cs320.middle_earth.model.CombatSituation;

public class MapTile extends Construct{
	private HashMap<String, Integer> connections;
	//private ArrayList<CombatSituation> random_encounters;
	
	public MapTile() {
		connections = new HashMap<String, Integer>();
		connections.put("north", 0);
		connections.put("northeast", 0);
		connections.put("east", 0);
		connections.put("southeast", 0);
		connections.put("south", 0);
		connections.put("southwest", 0);
		connections.put("west", 0);
		connections.put("northwest", 0);
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
