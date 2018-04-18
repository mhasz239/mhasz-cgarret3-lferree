package persist.dbmod;

public class ItemInventory {
	private int itemID;
	private int inventoryID;
	
	public ItemInventory() {
		
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	
	public int getItemID() {
		return this.itemID;
	}
	
	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}
	
	public int getInventoryID() {
		return this.inventoryID;
	}
}
