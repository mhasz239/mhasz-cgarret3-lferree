package edu.ycp.cs320.middle_earth.model.Constructs;

public class Weapon extends Item{
	private int attackBonus;
	private int specialAttackBonus;
	
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
}
