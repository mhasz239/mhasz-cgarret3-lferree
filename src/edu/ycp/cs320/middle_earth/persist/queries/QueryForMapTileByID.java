package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForMapTileByID {
	
	public static void main(String[] args) throws Exception {
		
		InitDatabase.init();
		// get the DB instance and execute transaction
		
		int mapTileID = 6;

		IDatabase db = DatabaseProvider.getInstance();
		MapTile mapTile = db.getMapTileByID(mapTileID);
		
		// check if anything was returned and output the list
		if (mapTile == null) {
			System.out.println("No mapTile found matching that ID");
		}
		else {
			System.out.println("MapTile ID = " + mapTile.getID() + "\n" 
					+ mapTile.getName() + "\n" 
					+ mapTile.getLongDescription() + "\n" 
					+ mapTile.getShortDescription() + "\n"
					+ mapTile.getConnections().get("north") + " north\n"
					+ mapTile.getConnections().get("northeast") + " northeast\n"
					+ mapTile.getConnections().get("east") + " east\n"
					+ mapTile.getConnections().get("southeast") + " southeast\n"
					+ mapTile.getConnections().get("south") + " south\n"
					+ mapTile.getConnections().get("southwest") + " southwest\n"
					+ mapTile.getConnections().get("west") + " west\n"
					+ mapTile.getConnections().get("northwest") + " northwest\n");
			if (mapTile.getObjects() == null) {
				System.out.println("No Objects on mapTile");
			} else {
				for (Object object : mapTile.getObjects()) {
					System.out.println("OBJECT ON MAPTILE:\nobject_id = " + object.getID() + "\nobject_name = " 
							+ object.getName() + "\nobject_long = " + object.getLongDescription() + "\nobject_short = " 
							+ object.getShortDescription() + "\nobject commands = " + object.getCommandResponses());
					if(object.getItems().isEmpty()) {
						System.out.println("No items in object");
					} else {
						for(Item item : object.getItems()) {
							System.out.println("\nITEM IN OBJECT:\nitem_id = " + item.getID() + "\n" 
									+ item.getName() + "\n" + item.getLongDescription() + "\n" 
									+ item.getShortDescription() + "\nitem weight = " + item.getItemWeight() 
									+ "\nIs a quest item? " + item.getIsQuestItem() + "\n");
						}
					}
				}
			}
		}
	}
}
