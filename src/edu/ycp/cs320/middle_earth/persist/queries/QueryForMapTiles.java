package edu.ycp.cs320.middle_earth.persist.queries;

import java.util.List;

import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForMapTiles {
	
	public static void main(String[] args) throws Exception {
		
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		List<MapTile> mapTileList = db.getAllMapTiles();
		
		// check if anything was returned and output the list
		if (mapTileList.isEmpty()) {
			System.out.println("No mapTiles found");
		}
		else {
			for (MapTile mapTile : mapTileList) {
				System.out.println("MapTile ID = " + mapTile.getID() + "\n" 
						+ mapTile.getName() + "\n" 
						+ mapTile.getLongDescription() + "\n" 
						+ mapTile.getShortDescription() + "\n"
						+ mapTile.getConnections().get("north") + "north\n"
						+ mapTile.getConnections().get("northeast") + "northwest\n"
						+ mapTile.getConnections().get("east") + "east\n"
						+ mapTile.getConnections().get("southeast") + "southeast\n"
						+ mapTile.getConnections().get("south") + "south\n"
						+ mapTile.getConnections().get("southwest") + "southwest\n"
						+ mapTile.getConnections().get("west") + "west\n"
						+ mapTile.getConnections().get("northwest") + "northwest\n");
			}
		}
	}
}
