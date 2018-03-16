package edu.ycp.cs320.middle_earth.model.Constructs;

public class Weapon extends Item{
	private int attackBonus;
	private int specialAttackBonus;
	private String name;
	private String description;
	
	public Weapon() {
		
	}
	
	public void setAttackBonus(int attackBonus) {
		this.attackBonus = attackBonus;
	}
	
	public int getAttackBonus() {
		return this.attackBonus;
	}
	
	public void setSpecialAttackBonus(int specialAttackBonus) {
		this.specialAttackBonus = specialAttackBonus;
	}
	
	public int getSpecialAttackBonus() {
		return this.specialAttackBonus;
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
