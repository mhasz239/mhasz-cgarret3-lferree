package edu.ycp.cs320.middle_earth.model.Constructs;

public class Armor extends Item{
	// Just a comment
	private int defenseBonus;
	private int specialDefenseBonus;
	private String name;
	private	int id;
	private String shortDescription;
	private String longDescription;
	
	public Armor() {
		
	}
	
	public void setDefenceBonus(int defenseBonus) {
		this.defenseBonus = defenseBonus;
	}
	
	public int getDefenseBonus() {
		return this.defenseBonus;
	}
	
	public void setSpecialDefenseBonus(int specialDefenseBonus) {
		this.specialDefenseBonus = specialDefenseBonus;
	}
	
	public int getSpecialDefenseBonus() {
		return this.specialDefenseBonus;
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
