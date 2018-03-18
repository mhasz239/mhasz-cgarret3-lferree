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
			System.out.println(player.get_race() + "\n"
					+ player.get_name() + "\n"
					+ player.get_gender() + "\n"
					+ player.get_level() + "\n"
					+ player.get_hit_points() + "\n"
					+ player.get_magic_points() + "\n"
					+ player.get_attack() + "\n"
					+ player.get_defense() + "\n"
					+ player.get_special_attack() + "\n"
					+ player.get_special_defense() + "\n"
					+ player.get_coins() + "\n"
					+ player.get_location() + "\n"
					+ player.get_experience() + "\n"
					+ player.get_carry_weight() + "\n");		
				
			System.out.println(player.get_inventory().get_weight());
			for (Item item : player.get_inventory().get_items()) {
				System.out.println("item_id = " + item.getID() + "\n" 
						+ item.getName() + "\n" + item.getLongDescription() + "\n" 
						+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
						+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
			}
			for (Quest quest : player.get_quests()) {
				for(Item item : quest.getRewardItems()) {
					System.out.println("item_id = " + item.getID() + "\n" 
							+ item.getName() + "\n" + item.getLongDescription() + "\n" 
							+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
							+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
				}
				System.out.println(quest.getRewardCoins());
				System.out.println(quest.getDialogue());					
			}
		}
	}
}
