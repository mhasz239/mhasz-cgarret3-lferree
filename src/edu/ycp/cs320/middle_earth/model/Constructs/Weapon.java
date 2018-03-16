package edu.ycp.cs320.middle_earth.model.Constructs;

public class Weapon extends Item{
	private int attackBonus;
	private int specialAttackBonus;
	private String name;
	private int id;
	private String shortDescription;
	private String longDescription;
	
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
