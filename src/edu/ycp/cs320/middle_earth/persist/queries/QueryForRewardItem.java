package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForRewardItem {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		Item item = db.getHandHeldItem();
		
		// check if anything was returned and output the list
		if (item == null) {
			System.out.println("No items found with that ID");
		}
		else {
			System.out.println("Item ID = " + item.getID() 
				+ "\nItem Name = " + item.getName() 
				+ "\nLong Desc = " + item.getLongDescription() 
				+ "\nShort Desc = " + item.getShortDescription() 
			
				+ "\nDesc Update = " + item.get_description_update()
				+ "\nAttack Bonus = " + item.get_attack_bonus()
				+ "\nDefense Bonus = " + item.get_defense_bonus()
				+ "\nHP Bonus = " + item.get_hp_bonus() 
			
				+ "\nItem Weight = " + item.getItemWeight() 
				+ "\nItem Type = " + item.get_ItemType()
				+ "\nLevel Requirement = " + item.get_lvl_requirement()
			
				+ "\nIs a quest item? " + item.getIsQuestItem() + "\n\n");
		}
	}
}
