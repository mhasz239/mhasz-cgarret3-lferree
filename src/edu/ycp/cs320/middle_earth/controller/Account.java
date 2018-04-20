package edu.ycp.cs320.middle_earth.controller;

public class Account{
	private String user_token;
	private int game_id;
	
	public String get_user_token(){
		return user_token;
	}
	
	public void set_user_token(String user_token){
		this.user_token = user_token;
	}
	
	public int get_game_id(){
		return game_id;
	}
	
	public void set_game_id(int game_id){
		this.game_id = game_id;
	}
	
	public void create_account(String username, String password, String email){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public String login(String username, String password){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
		if () {
			//update user_token and game_id
			return "Success!";
		} else {
			return "Invalid Username or Password";
		}
	}
}
