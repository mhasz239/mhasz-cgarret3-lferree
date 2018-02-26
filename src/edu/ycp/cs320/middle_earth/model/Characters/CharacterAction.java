package edu.ycp.cs320.middle_earth.model.Characters;

public interface CharacterAction{
	public abstract void move();
	
	public abstract void attack(Character character);
	
	public abstract void loot(Character character);
}
