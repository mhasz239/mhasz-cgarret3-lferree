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
	
	public void set_description_update( String string) {
		this.description_update = string;
	}
	
	public String get_description_update(){
		return description_update;
	}
	
	public void set_attack_bonus(int bonus) {
		this.attack_bonus = bonus;
	}
	
	public int get_attack_bonus(){
		return attack_bonus;
	}
	
	public void set_defense_bonus(int bonus) {
		this.defense_bonus = bonus;
	}
	
	public int get_defense_bonus(){
		return defense_bonus;
	}
	
	public void set_hp_bonus(int bonus){
		this.hp_bonus = bonus;
	}
	
	public int get_hp_bonus(){
		return hp_bonus;
	}
	
	public void set_lvl_requirement(int level){
		this.lvl_requirement = level;
	}
	
	public int get_lvl_requirement(){
		return lvl_requirement;
	}
	
	public void set_ItemType(ItemType type) {
		this.type = type;
	}
	
	public ItemType get_ItemType(){
		return type;
	}
}
