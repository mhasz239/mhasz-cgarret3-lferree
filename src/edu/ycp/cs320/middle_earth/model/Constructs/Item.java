package edu.ycp.cs320.middle_earth.model.Constructs;

public class Item extends Construct{
	private float itemWeight;
	private boolean isQuestItem;
	private String name;
	private String description;
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
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
