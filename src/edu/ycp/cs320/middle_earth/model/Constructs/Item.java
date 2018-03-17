package edu.ycp.cs320.middle_earth.model.Constructs;

public class Item extends Construct{
	private float itemWeight;
	private boolean isQuestItem;
	// TODO: Figure out how to put location in here???
	
	public Item() {
		
	}
	
	public void setItemWeight(float itemWeight) {
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
}
