package edu.ycp.cs320.middle_earth.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

public class InitialData {

	
	public static List<MapTile> getMapTiles() throws IOException {
		List<MapTile> mapTileList = new ArrayList<MapTile>();
		ReadCSV readMapTiles = new ReadCSV("mapTiles.csv");
		try {
			while (true) {
				List<String> tuple = readMapTiles.next();
				if(tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				MapTile mapTile = new MapTile();
				// mapTile.setConnections(
				mapTile.setID(Integer.parseInt(i.next()));
				mapTile.setName(i.next());
				mapTile.setLongDescription(i.next());
				mapTile.setShortDescription(i.next());
				mapTileList.add(mapTile);
			}
			return mapTileList;
		} finally {
			readMapTiles.close();
		}
	}
}
