package edu.ycp.cs320.middle_earth.model.Constructs;

public class Item extends Construct{
	private float itemWeight;
	private boolean isQuestItem;
	private String description_update;
	private int attack_bonus;
	private int defense_bonus;
	private int hp_bonus;
	private int lvl_requirement;
	private ItemType type;
	
	// TODO: Figure out how to put location in here???
	
	public Item() {
		
	}
	
	public Item(float itemWeight, int attack_bonus, int defense_bonus, int hp_bonus, int lvl_requirement, ItemType type, String name, int id, String shortDescription, String longDescription, String name_update) {
		this.itemWeight = itemWeight;
		this.attack_bonus = attack_bonus;
		this.defense_bonus = defense_bonus;
		this.hp_bonus = hp_bonus;
		this.lvl_requirement = lvl_requirement;
		this.description_update = name_update;
		this.type = type;
		this.setName(name);
		this.setID(id);
		this.setShortDescription(shortDescription);
		this.setLongDescription(longDescription);
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
	
	public void setdescription_update( String string) {
		this.description_update = string;
	}
	
	public String getdescription_update(){
		return description_update;
	}
	
	public void setattack_bonus(int bonus) {
		this.attack_bonus = bonus;
	}
	
	public int getattack_bonus(){
		return attack_bonus;
	}
	
	public void setdefense_bonus(int bonus) {
		this.defense_bonus = bonus;
	}
	
	public int getdefense_bonus(){
		return defense_bonus;
	}
	
	public void sethp_bonus(int bonus){
		this.hp_bonus = bonus;
	}
	
	public int gethp_bonus(){
		return hp_bonus;
	}
	
	public void setlvl_requirement(int level){
		this.lvl_requirement = level;
	}
	
	public int getlvl_requirement(){
		return lvl_requirement;
	}
	
	public void setItemType(ItemType type) {
		this.type = type;
	}
	
	public ItemType getItemType(){
		return type;
	}
	
	public ItemType gettype(){
		return type;
	}
}
