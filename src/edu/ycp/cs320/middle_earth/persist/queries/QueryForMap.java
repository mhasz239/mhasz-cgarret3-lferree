package edu.ycp.cs320.middle_earth.persist.queries;

import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
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
			}
		}
	}
}
