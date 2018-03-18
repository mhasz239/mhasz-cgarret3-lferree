package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForInventory {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		Inventory inventory = db.getInventory();
		
		// check if anything was returned and output the list
		if (inventory == null) {
			System.out.println("No inventory found");
		}
		else {
			System.out.println("Inventory weight = " + inventory.get_weight() + "\n");
			for (Item item : inventory.get_items()) {
				System.out.println("item_id = " + item.getID() + "\n" 
						+ item.getName() + "\n" + item.getLongDescription() + "\n" 
						+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
						+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
			}
		}
	}
}
