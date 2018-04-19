package edu.ycp.cs320.middle_earth.persist.queries;


import edu.ycp.cs320.middle_earth.model.Quest;
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
					"Race = "+  player.get_race() + "\n"
					+ "Name = " + player.get_name() + "\n"
					+ "Gender = " + player.get_gender() + "\n"
					+ "Level = " + player.get_level() + "\n"
					+ "HP = " + player.get_hit_points() + "\n"
					
					+ "MP = " + player.get_magic_points() + "\n"
					+ "ATK = " + player.get_attack() + "\n"
					+ "DEF = " + player.get_defense() + "\n"
					+ "SPATK = " + player.get_special_attack() + "\n"
					+ "SPDEF = " + player.get_special_defense() + "\n"
					
					+ "Coins = " + player.get_coins() + "\n"
					+ "MapTile Location = " + player.get_location() + "\n"
					+ "Inventory ID = " + player.get_inventory_id() + "\n"
					+ "Total EXP = " + player.get_experience() + "\n"
					+ "Max Weight = " + player.get_carry_weight());		
				
			System.out.println("Current Weight = " + player.get_inventory().get_weight() + "\n");
			for (Item item : player.get_inventory().get_items()) {
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
/*			for (Quest quest : player.get_quests()) {
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
