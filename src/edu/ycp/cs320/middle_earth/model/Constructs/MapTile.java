package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.HashMap;
import java.util.ArrayList;
//import edu.ycp.cs320.middle_earth.model.CombatSituation;

public class MapTile extends Construct{
	private ArrayList<HashMap<String, MapTile>> connections;
	//private ArrayList<CombatSituation> random_encounters;
	
	public MapTile() {
		
	}
	
	public void setConnections(ArrayList<HashMap<String, MapTile>> connections) {
		this.connections = connections;
	}
	
	public ArrayList<HashMap<String, MapTile>> getConnections() {
		return this.connections;
	}
}
