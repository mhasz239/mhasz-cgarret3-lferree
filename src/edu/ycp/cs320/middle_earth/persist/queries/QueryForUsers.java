package edu.ycp.cs320.middle_earth.persist.queries;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForUsers {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<String> userList = db.getAllUserNames();
		
		// check if anything was returned and output the list
		if (userList.isEmpty()) {
			System.out.println("No map found");
		} else {
			for(String username : userList) {
				System.out.println(username);
			}
		}
		
		System.out.println("Does " + userList.get(0) + " exist? = " + db.doesUserNameExist(userList.get(0)));
		
		if(!db.doesUserNameExist("Blart")) {
			System.out.println(db.createNewUser("Blart", "whocares", "Blart@email.email"));
		}
	}
		
}
