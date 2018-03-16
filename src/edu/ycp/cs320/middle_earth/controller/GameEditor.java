package edu.ycp.cs320.middle_earth.controller;

public class GameEditor implements Editor{
	private Game model;
	private boolean testing;
	
	public Game getModel(){
		return model;
	}
	
	public void setModel(Game model){
		this.model = model;
	}
	
	public boolean isTesting(){
		return testing;
	}
	
	public void setTesting(boolean testing){
		this.testing = testing;
	}
	
	@Override
	public void launch_test_game(){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void add_object(){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void edit_object(){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void add_mapTile(){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void edit_mapTile(){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
