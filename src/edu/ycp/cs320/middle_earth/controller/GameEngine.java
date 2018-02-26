package edu.ycp.cs320.middle_earth.controller;

import edu.ycp.cs320.middle_earth.model.Game;

public class GameEngine implements GameAction{
	private Game model;
	
	public void setModel(Game model){
		this.model = model;
	}
	
	public String handleCommand(String command){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void check_character_sheet(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void check_inventory(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void check_map(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void save(Game game){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
