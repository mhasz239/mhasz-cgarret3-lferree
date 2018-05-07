package edu.ycp.cs320.middle_earth.controller;

import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.DerbyDatabase;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public String usernameCheck(String username){
		return "Passes";
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
		// TODO: Implement 
		// Needs to call database and return "Successful" if the account was successfully created
		// return Error if there was an error in creating the account.
		
		return "Error";
		//throw new UnsupportedOperationException("Not implemented yet!");
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
