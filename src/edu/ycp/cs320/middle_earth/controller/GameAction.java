package edu.ycp.cs320.middle_earth.controller;

import edu.ycp.cs320.middle_earth.model.Game;

public interface GameAction{
	public abstract void check_character_sheet();
	
	public abstract void check_inventory();
	
	public abstract void check_map();
	
	public abstract void save(Game game);
}
