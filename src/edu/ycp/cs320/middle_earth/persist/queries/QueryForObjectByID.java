package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForObjectByID {

	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		Object object = db.getObjectByID(1);
		
		// check if anything was returned and output the list
		if (object == null) {
			System.out.println("No items found with that ID");
		}
		else {
			System.out.println(object.getID() + "\n" 
					+ object.getName() + "\n" 
					+ object.getLongDescription() + "\n" 
					+ object.getShortDescription() + "\n" 
					+ object.getCommandResponses() + "\n");
			if(object.getItems().isEmpty()) {
				System.out.println("No items");
			} else {
				for(Item item : object.getItems()) {
					System.out.println("item_id = " + item.getID() + "\n" 
							+ item.getName() + "\n" + item.getLongDescription() + "\n" 
							+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
							+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
				}
			}
		}
	}
}
