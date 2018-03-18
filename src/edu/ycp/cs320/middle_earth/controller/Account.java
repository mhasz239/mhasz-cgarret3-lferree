package edu.ycp.cs320.middle_earth.controller;

public class Account{
	private String user_token;
	
	public String get_user_token(){
		return user_token;
	}
	
	public void set_user_token(String user_token){
		this.user_token = user_token;
	}
	
	public void create_account(String username, String password, String email){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public void login(String username, String password){
		// TODO: Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
