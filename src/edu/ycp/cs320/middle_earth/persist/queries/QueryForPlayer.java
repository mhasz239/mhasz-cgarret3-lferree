package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForPlayer {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		Player player = db.getPlayer();
		
		// check if anything was returned and output the list
		if (player == null) {
			System.out.println("No player found");
		}
		else {
			System.out.println(
					"Race = "+  player.getrace() + "\n"
					+ "Name = " + player.getname() + "\n"
					+ "Gender = " + player.getgender() + "\n"
					+ "Level = " + player.getlevel() + "\n"
					+ "HP = " + player.gethit_points() + "\n"
					
					+ "MP = " + player.getmagic_points() + "\n"
					+ "ATK = " + player.getattack() + "\n"
					+ "DEF = " + player.getdefense() + "\n"
					+ "SPATK = " + player.getspecial_attack() + "\n"
					+ "SPDEF = " + player.getspecial_defense() + "\n"
					
					+ "Coins = " + player.getcoins() + "\n"
					+ "MapTile Location = " + player.getlocation() + "\n"
					+ "Inventory ID = " + player.getinventory_id() + "\n"
					+ "Total EXP = " + player.getexperience() + "\n"
					+ "Max Weight = " + player.getcarry_weight());		
				
			System.out.println("Current Weight = " + player.getinventory().getweight() + "\n");
			for (Item item : player.getinventory().getitems()) {
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
/*			for (Quest quest : player.getquests()) {
				for(Item item : quest.getRewardItems()) {
					System.out.println("item_id = " + item.getID() + "\n" 
							+ item.getName() + "\n" + item.getLongDescription() + "\n" 
							+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
							+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
				}
				System.out.println(quest.getRewardCoins());
				System.out.println(quest.getDialogue());					
			} */
		}
	}
}
