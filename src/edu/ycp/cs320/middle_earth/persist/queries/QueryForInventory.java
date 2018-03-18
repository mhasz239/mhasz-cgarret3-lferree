package edu.ycp.cs320.middle_earth.persist.queries;

import java.util.ArrayList;

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
		ArrayList<Inventory> inventoryList = db.getAllInventories();
		
		// check if anything was returned and output the list
		if (inventoryList.isEmpty()) {
			System.out.println("No inventory found");
		}
		else {
			for(Inventory inventory : inventoryList) {
				System.out.println("Inventory_id = " + inventory.get_inventory_id() + "\n"
						+ "Inventory weight = " + inventory.get_weight() + "\n");
				for (Item item : inventory.get_items()) {
					System.out.println("item_id = " + item.getID() + "\n" 
							+ item.getName() + "\n" + item.getLongDescription() + "\n" 
							+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
							+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
				}
			}
		}
	}
}
