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
	
	// Note: Having setConnections(HashMap) causes us to lose the default values of 0.
	public void setConnection(String direction, int weight){
		connections.put(direction, weight);
	}
	
	public HashMap<String, Integer> getConnections() {
		return this.connections;
	}
	
	
	public int getMoveValue(String direction) {
		return connections.get(direction);
	}
}
