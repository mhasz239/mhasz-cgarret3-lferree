package edu.ycp.cs320.middle_earth.controller;

import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.DerbyDatabase;
import edu.ycp.cs320.middle_earth.persist.IDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account{
	private String username;
	private ArrayList<Integer> game_ids;
	private int current_game;
	
	
	
	public Account(String username){
		this.username = username;
	}
	
	public Account() {
	}

	public String getusername(){
		return username;
	}
	
	public void setusername(String username){
		this.username = username;
	}
	
	public ArrayList<Integer> getgame_ids(){
		return game_ids;
	}
	
	public void setgame_ids(ArrayList<Integer> game_ids){
		this.game_ids = game_ids;
	}
	
	public int getcurrent_game(){
		return current_game;
	}
	
	public void setcurrent_game(int id) {
		this.current_game = id;
	}
	
	public String usernameCheck(String username){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		if (db.doesUserNameExist(username)) {
			return "Sorry that Username is already taken.";
		} else {
			return "Passes";
		}
		// TODO: Implement (Checks database to see if username already exists, if not returns "Passes"  as result;
		//throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	public String emailCheck(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return "Please enter and email address.";
        if (pat.matcher(email).matches()) {
        	return "Passes";
        } else {
        	return "Please enter a vail email address.";
        }
    }
	
	public String passwordCheck(String password){
		boolean numCheck = false;
		for (int i = 0; i < password.length(); i++) {
			try{
				Integer.parseInt(password.substring(i, i+1));
				numCheck = true;
			} catch (NumberFormatException e) {
				// Do nothing needs to only pass once. 
			}
		}
		
		if (password.length() < 6 || !numCheck) {
			return "Password needs to be atleast 6 characters long and atleast 1 alpha-numeric value";
		} else {
			return "Passes";
		}
	}
	
	public String create_account(String username, String password, String email){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		if (db.createNewUser(username, password, email) ){
				return "Successful";
		} else {
			return "Error";
		}
	}
	
	public String login(String username, String password){
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		
		if (password.equals(db.getUserPasswordByUserName(username))) {
			return "Success!";
		} else {
			return "Invalid Username or Password";
		}
	}
}
