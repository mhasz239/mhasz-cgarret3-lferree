package persist.dbmod;

public class MapTileMap {
	private int mapTileID;
	private int mapID;
	
	public MapTileMap() {
		
	}
	
	public void setMapTileID(int mapTileID) {
		this.mapTileID = mapTileID;
	}
	
	public int getMapTileID() {
		return mapTileID;
	}
	
	public void setMapID(int mapID) {
		this.mapID = mapID;
	}
	
	public int getMapID() {
		return this.mapID;
	}
}
