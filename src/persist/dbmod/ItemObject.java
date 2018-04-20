package persist.dbmod;

public class ItemObject {

	private int itemID;
	private int objectID;
	
	public ItemObject() {
		
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public int getItemID() {
		return this.itemID;
	}
	
	public void setObjectID(int objectID) {
		this.objectID = objectID;
	}
	
	public int getObjectID() {
		return this.objectID;
	}
}
