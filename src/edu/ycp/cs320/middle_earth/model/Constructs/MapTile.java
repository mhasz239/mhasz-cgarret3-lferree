package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;
import java.util.HashMap;
//import edu.ycp.cs320.middle_earth.model.CombatSituation;

public class MapTile extends Construct{
	private HashMap<String, Integer> connections;
	//private ArrayList<CombatSituation> random_encounters;
	private ArrayList<Object> objects;
	private boolean visited;
	private String enemyString;
	private int areaDifficulty;
	
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
	
	public void setObjects(ArrayList<Object> objects) {
		this.objects = objects;
	}
	
	public ArrayList<Object> getObjects() {
		return objects;
	}
	
	public HashMap<String, Integer> getConnections() {
		return this.connections;
	}
	
	public int getMoveValue(String direction) {
		return connections.get(direction);
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}
	
	public boolean getVisited() {
		return this.visited;
	}
	
	public String getEnemyString(){
		return enemyString;
	}
	
	public void setEnemyString(String enemyString){
		this.enemyString = enemyString;
	}
	
	public ArrayList<Integer> getEnemyIDs(){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for(String s: enemyString.split(",")){
			ids.add(Integer.parseInt(s));
		}
		return ids;
	}
	
	public void setAreaDifficulty(int areaDifficulty) {
		this.areaDifficulty = areaDifficulty;
	}
	
	public int getAreaDifficulty() {
		return this.areaDifficulty;
	}
}
