package edu.ycp.cs320.middle_earth.model.Constructs;

//import java.util.ArrayList;
//import java.util.HashMap;

//import edu.ycp.cs320.middle_earth.model.CombatSituation;

public class MapTile extends Construct{
	//private HashMap<String, MapTile> connections;
	//private ArrayList<CombatSituation> random_encounters;
	private String name;
	private int id;
	private String shortDescription;
	private String londDescription;
	
	public MapTile() {
		
	}
	//this is just a comment
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
