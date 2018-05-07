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
			System.out.println("Map ID = " + map.getID() + "\n"
						+ "Map Name = " + map.getName() + "\n "
						+ "Map Long Desc = " + map.getLongDescription() + "\n "
						+ "Map Short Desc = " + map.getShortDescription() + "\n");
			for (MapTile mapTile : map.getMapTiles()) {
				System.out.println("MapTile ID = " + mapTile.getID() 
						+ "\nMapTile Name = " + mapTile.getName() 
						+ "\nLong Desc = " + mapTile.getLongDescription() 
						+ "\nShort Desc = "	+ mapTile.getShortDescription() 
						+ "\nArea Difficulty = " + mapTile.getAreaDifficulty()
						+ "\n"
						+ mapTile.getConnections().get("north") + " north\n"
						+ mapTile.getConnections().get("northeast") + " northeast\n"
						+ mapTile.getConnections().get("east") + " east\n"
						+ mapTile.getConnections().get("southeast") + " southeast\n"
						+ mapTile.getConnections().get("south") + " south\n"
						+ mapTile.getConnections().get("southwest") + " southwest\n"
						+ mapTile.getConnections().get("west") + " west\n"
						+ mapTile.getConnections().get("northwest") + " northwest");
				if (mapTile.getObjects() == null) {
					System.out.println("No Objects on MapTile\n");
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
						
									+ "\n\t\tDesc Update = " + item.getdescription_update()
									+ "\n\t\tAttack Bonus = " + item.getattack_bonus()
									+ "\n\t\tDefense Bonus = " + item.getdefense_bonus()
									+ "\n\t\tHP Bonus = " + item.gethp_bonus() 
						
									+ "\n\t\tItem Weight = " + item.getItemWeight() 
									+ "\n\t\tItem Type = " + item.getItemType()
									+ "\n\t\tLevel Requirement = " + item.getlvl_requirement()
						
									+ "\n\t\tIs a quest item? " + item.getIsQuestItem() + "\n");
							}
						}
					}
				}
			}
		}
	}
}
