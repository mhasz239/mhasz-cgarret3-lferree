package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForInventoryByID {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		Inventory inventory = db.getInventoryByID(2);
		
		// check if anything was returned and output the list
		if (inventory == null) {
			System.out.println("No inventory found");
		} else {
			System.out.println(
					"Items:");
			for(Item item : inventory.getitems()) {
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
