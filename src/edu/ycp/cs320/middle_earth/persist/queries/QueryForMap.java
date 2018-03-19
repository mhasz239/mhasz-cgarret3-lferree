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
						System.out.println("\t" + object.getID() + "\n" 
								+ "\t" + object.getName() + "\n" 
								+ "\t" + object.getLongDescription() + "\n" 
								+ "\t" + object.getShortDescription() + "\n" 
								+ "\t" + object.getCommandResponses() + "\n");
						if(object.getItems().isEmpty()) {
							System.out.println("No items");
						} else {
							for(Item item : object.getItems()) {
								System.out.println("\t\titem_id = " + item.getID() + "\n" 
										+ "\t\t" + item.getName() + "\n\t\t" + item.getLongDescription() + "\n" 
										+ "\t\t" + item.getShortDescription() + "\n\t\titem weight = " + item.getItemWeight() 
										+ "\t\t" + "\n\t\tIs a quest item? " + item.getIsQuestItem() + "\n");
							}
						}
						System.out.println("\n");
					}
				}
			}
		}
	}
}
