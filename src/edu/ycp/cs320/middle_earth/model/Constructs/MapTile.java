package edu.ycp.cs320.middle_earth.model.Constructs;

//import java.util.ArrayList;
//import java.util.HashMap;

//import edu.ycp.cs320.middle_earth.model.CombatSituation;

public class MapTile extends Construct{
	//private HashMap<String, MapTile> connections;
	//private ArrayList<CombatSituation> random_encounters;
	private String name;
	private String shortDescription;
	
	public MapTile() {
		
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
