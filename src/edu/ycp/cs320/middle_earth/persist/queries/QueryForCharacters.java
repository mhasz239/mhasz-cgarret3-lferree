package edu.ycp.cs320.middle_earth.persist.queries;

import java.util.List;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForCharacters {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		List<Character> characterList = db.getAllCharacters();
		
		// check if anything was returned and output the list
		if (characterList.isEmpty()) {
			System.out.println("No characters found");
		}
		else {
			for(Character character : characterList) {
				System.out.println(character.get_race() + "\n"
						+ character.get_name() + "\n"
						+ character.get_gender() + "\n"
						+ character.get_level() + "\n"
						+ character.get_hit_points() + "\n"
						+ character.get_magic_points() + "\n"
						+ character.get_attack() + "\n"
						+ character.get_defense() + "\n"
						+ character.get_special_attack() + "\n"
						+ character.get_special_defense() + "\n"
						+ character.get_coins() + "\n"
						+ character.get_location() + "\n");		
				
				System.out.println(character.get_inventory().get_weight());
				for (Item item : character.get_inventory().get_items()) {
					System.out.println("item_id = " + item.getID() + "\n" 
							+ item.getName() + "\n" + item.getLongDescription() + "\n" 
							+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
							+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
				}
			}
		}
	}
}
