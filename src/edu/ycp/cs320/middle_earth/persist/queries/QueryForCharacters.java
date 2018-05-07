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
				System.out.println(character.getrace() + "\n"
						+ character.getname() + "\n"
						+ character.getgender() + "\n"
						+ character.getlevel() + "\n"
						+ character.gethit_points() + "\n"
						+ character.getmagic_points() + "\n"
						+ character.getattack() + "\n"
						+ character.getdefense() + "\n"
						+ character.getspecial_attack() + "\n"
						+ character.getspecial_defense() + "\n"
						+ character.getcoins() + "\n"
						+ character.getlocation() + "\n");		
				
				System.out.println(character.getinventory().getweight());
				for (Item item : character.getinventory().getitems()) {
					System.out.println("item_id = " + item.getID() + "\n" 
							+ item.getName() + "\n" + item.getLongDescription() + "\n" 
							+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
							+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
				}
			}
		}
	}
}
