package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForItemByID {

	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		Item item = db.getItemByID(13);
		
		// check if anything was returned and output the list
		if (item == null) {
			System.out.println("No items found with that ID");
		}
		else {
			System.out.println("Item ID = " + item.getID() 
				+ "\nItem Name = " + item.getName() 
				+ "\nLong Desc = " + item.getLongDescription() 
				+ "\nShort Desc = " + item.getShortDescription() 
			
				+ "\nDesc Update = " + item.getdescription_update()
				+ "\nAttack Bonus = " + item.getattack_bonus()
				+ "\nDefense Bonus = " + item.getdefense_bonus()
				+ "\nHP Bonus = " + item.gethp_bonus() 
			
				+ "\nItem Weight = " + item.getItemWeight() 
				+ "\nItem Type = " + item.getItemType()
				+ "\nLevel Requirement = " + item.getlvl_requirement()
			
				+ "\nIs a quest item? " + item.getIsQuestItem() + "\n\n");
		}
	}
}
