package edu.ycp.cs320.middle_earth.controller;

import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.DerbyDatabase;
import edu.ycp.cs320.middle_earth.persist.IDatabase;

public class Account{
	private int user_id;
	private int[] game_ids;
	
	
	public int get_user_token(String username){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		//Need to add getUserID to database calls.
		//return db.getUserID(username);
		return user_id;
	}
	
	public void set_user_token(int user_id){
		this.user_id = user_id;
	}
	
	public int[] get_game_ids(String username){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		//Need to add getGameIDs to database calls.
		//return db.getGameIDs(username);
		return game_ids;
	}
	
	public void set_game_id(int game_id, int game_slot){
		this.game_ids[game_slot] = game_id;
	}
	
	public void create_account(String username, String password, String email){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public String login(String username, String password){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		if (password.equals(db.getUserPasswordByUserName(username))) {
			//update user_token and game_id
			
			return "Success!";
		} else {
			return "Invalid Username or Password";
		}
	}
}
