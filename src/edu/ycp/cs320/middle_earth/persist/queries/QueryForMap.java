package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForMap {
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		Map map = db.getMap();
		
		// check if anything was returned and output the list
		if (map == null) {
			System.out.println("No map found");
		}
		else {
			for (MapTile mapTile : map.getMapTiles()) {
				System.out.println(mapTile.getID() + "\n" 
						+ mapTile.getName() + "\n" 
						+ mapTile.getLongDescription() + "\n" 
						+ mapTile.getShortDescription() + "\n"
						+ mapTile.getConnections() + "\n");		
				if(mapTile.getObjects() == null) {
					System.out.println("No objects");
				} else {	
					for (Object object : mapTile.getObjects()) {
						System.out.println("\tOBJECTS ON MAPTILE:"
							+ "\n\tObject Name = " + object.getName() 
							+ "\n\tLong Desc = " + object.getLongDescription() 
							+ "\n\tShort Desc = " + object.getShortDescription() 
							+ "\n\tCommand Responses: " + object.getCommandResponses());
						if(object.getItems().isEmpty()) {
							System.out.println("\tNo items in object");
						} else {
							for(Item item : object.getItems()) {
								System.out.println("\n\t\tITEMS IN OBJECT:"
									+ "\n\t\tItem ID = " + item.getID() 
									+ "\n\t\tItem Name = " + item.getName() 
									+ "\n\t\tLong Desc = " + item.getLongDescription() 
									+ "\n\t\tShort Desc = " + item.getShortDescription() 
						
									+ "\n\t\tDesc Update = " + item.get_description_update()
									+ "\n\t\tAttack Bonus = " + item.get_attack_bonus()
									+ "\n\t\tDefense Bonus = " + item.get_defense_bonus()
									+ "\n\t\tHP Bonus = " + item.get_hp_bonus() 
						
									+ "\n\t\tItem Weight = " + item.getItemWeight() 
									+ "\n\t\tItem Type = " + item.get_ItemType()
									+ "\n\t\tLevel Requirement = " + item.get_lvl_requirement()
						
									+ "\n\t\tIs a quest item? " + item.getIsQuestItem() + "\n\n");
							}
						}
					}
				}
			}
		}
	}
}
