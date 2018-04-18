package edu.ycp.cs320.middle_earth.persist.queries;

import java.util.List;

import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForQuests {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		List<Quest> questList = db.getAllQuests();
		
		// check if anything was returned and output the list
		if (questList.isEmpty()) {
			System.out.println("No quests found");
		}
		else {
			for (Quest quest : questList) {
				if(quest.getRewardItems() != null) {
					for(Item item : quest.getRewardItems()) {
						System.out.println("item_id = " + item.getID() + "\n" 
								+ item.getName() + "\n" + item.getLongDescription() + "\n" 
								+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
								+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
					}
				}
				else {
					System.out.println("No reward tems for this quest");
				}
				System.out.println(quest.getRewardCoins());
				System.out.println(quest.getDialogue());	
			}
		}
	}
}
