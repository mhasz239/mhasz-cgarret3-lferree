package edu.ycp.cs320.middle_earth.model.Constructs;

public class Item extends Construct{
	private float itemWeight;
	private String isQuestItem;
	// TODO: Figure out how to put location in here???
	
	public Item() {
		
	}
	
	public void setItemWeight(int itemWeight) {
		this.itemWeight = itemWeight;
	}
	
	public float getItemWeight() {
		return this.itemWeight;
	}
	
	public void setIsQuestItem(String isQuestItem) {
		this.isQuestItem = isQuestItem;
	}
	
	public String getIsQuestItem() {
		return this.isQuestItem;
	}
}
