package edu.ycp.cs320.middle_earth.model.Constructs;

public class Armor extends Item{
	// Just a comment
	private int defenseBonus;
	private int specialDefenseBonus;
	private String name;
	private String description;
	
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
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
