package edu.ycp.cs320.middle_earth.model.Constructs;

public class Armor extends Item{
	// Just a comment
	private int defenseBonus;
	private int specialDefenseBonus;
	public Armor() {
		
	}
	
	public void setDefenseBonus(int defenseBonus) {
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
}
