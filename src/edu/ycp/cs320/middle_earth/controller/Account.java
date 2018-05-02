package edu.ycp.cs320.middle_earth.controller;

import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.DerbyDatabase;
import edu.ycp.cs320.middle_earth.persist.IDatabase;

public class Account{
	private int user_id;
	private int[] game_ids;
	
	
	public int get_user_token(){
		return user_id;
	}
	
	public void set_user_token(int user_id){
		this.user_id = user_id;
	}
	
	public int[] get_game_ids(){
		return game_ids;
	}
	
	public void set_game_ids(int[] game_ids){
		this.game_ids = game_ids;
	}
	
	public void create_account(String username, String password, String email){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public String login(String username, String password){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		if (password.equals(db.getUserPasswordByUserName(username))) {
			//TODO: update user_token and game_id (Need to add to database calls)
			//user_id = db.getUserID(username);
			//game_ids = db.getGameIDs(username);
			
			return "Success!";
		} else {
			return "Invalid Username or Password";
		}
	}
}
