package edu.ycp.cs320.middle_earth.controller;

public interface Engine{
	public abstract String handle_command(String command);
	
	public abstract void check_character_sheet();
	
	public abstract void check_inventory();
	
	public abstract void check_map();
	
	public abstract void return_to_game();
	
	public abstract void save();
	
	// Player-Specific Actions
	
	public abstract void take(String name);
	
	public abstract void look();
	
	// Character-Specific Actions (Not just Player)
	
	public abstract void move(String direction);
}
