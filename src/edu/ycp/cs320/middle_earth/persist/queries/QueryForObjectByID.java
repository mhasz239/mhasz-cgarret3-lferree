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
			System.out.println("Object ID = " + object.getID() 
				+ "\nObject Name = " + object.getName() 
				+ "\nLong Desc = " + object.getLongDescription() 
				+ "\nShort Desc = " + object.getShortDescription() 
				+ "\nCommand Responses: " + object.getCommandResponses());
			if(object.getItems().isEmpty()) {
				System.out.println("No items");
			} else {
				for(Item item : object.getItems()) {
					System.out.println("\tItem ID = " + item.getID() 
						+ "\n\tItem Name = " + item.getName() 
						+ "\n\tLong Desc = " + item.getLongDescription() 
						+ "\n\tShort Desc = " + item.getShortDescription() 
			
						+ "\n\tDesc Update = " + item.get_description_update()
						+ "\n\tAttack Bonus = " + item.get_attack_bonus()
						+ "\n\tDefense Bonus = " + item.get_defense_bonus()
						+ "\n\tHP Bonus = " + item.get_hp_bonus() 
			
						+ "\n\tItem Weight = " + item.getItemWeight() 
						+ "\n\tItem Type = " + item.get_ItemType()
						+ "\n\tLevel Requirement = " + item.get_lvl_requirement()
			
						+ "\n\tIs a quest item? " + item.getIsQuestItem() + "\n\n");
				}
			}
		}
	}
}
