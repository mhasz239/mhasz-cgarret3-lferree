package edu.ycp.cs320.middle_earth.persist.queries;

import java.util.List;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForObjects {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		List<Object> objectList = db.getAllObjects();
		
		// check if anything was returned and output the list
		if (objectList.isEmpty()) {
			System.out.println("No objects found");
		}
		else {
			for (Object object : objectList) {
				System.out.println("Object ID = " + object.getID() 
					+ "\nObject Name = " + object.getName() 
					+ "\nLong Desc = " + object.getLongDescription() 
					+ "\nShort Desc = " + object.getShortDescription() 
					+ "\nCommand Responses: " + object.getCommandResponses());
				if(object.getItems().isEmpty()) {
					System.out.println("No items in object\n\n");
				} else {
					for(Item item : object.getItems()) {
						System.out.println("\tItem ID = " + item.getID() 
							+ "\n\tItem Name = " + item.getName() 
							+ "\n\tLong Desc = " + item.getLongDescription() 
							+ "\n\tShort Desc = " + item.getShortDescription() 
					
							+ "\n\tDesc Update = " + item.getdescription_update()
							+ "\n\tAttack Bonus = " + item.getattack_bonus()
							+ "\n\tDefense Bonus = " + item.getdefense_bonus()
							+ "\n\tHP Bonus = " + item.gethp_bonus() 
					
							+ "\n\tItem Weight = " + item.getItemWeight() 
							+ "\n\tItem Type = " + item.getItemType()
							+ "\n\tLevel Requirement = " + item.getlvl_requirement()
					
							+ "\n\tIs a quest item? " + item.getIsQuestItem() + "\n\n");
					}
				}
			}
		}
	}
}
