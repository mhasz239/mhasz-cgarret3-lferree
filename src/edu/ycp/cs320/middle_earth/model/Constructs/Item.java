package edu.ycp.cs320.middle_earth.model.Constructs;

public class Item extends Construct{
	private float itemWeight;
	private boolean isQuestItem;
	private String name;
	private int id;
	private String shortDescription;
	private String longDescription;
	// TODO: Figure out how to put location in here???
	
	public Item() {
		
	}
	
	public void setItemWeight(int itemWeight) {
		this.itemWeight = itemWeight;
	}
	
	public float getItemWeight() {
		return this.itemWeight;
	}
	
	public void setIsQuestItem(boolean isQuestItem) {
		this.isQuestItem = isQuestItem;
	}
	
	public boolean getIsQuestItem() {
		return this.isQuestItem;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public String getshortDescription() {
		return this.shortDescription;
	}
	
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	
	public String getLongDescription() {
		return this.longDescription;
	}
}
