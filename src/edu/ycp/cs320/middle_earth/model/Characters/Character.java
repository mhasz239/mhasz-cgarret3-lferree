package edu.ycp.cs320.middle_earth.model.Characters;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

public abstract class Character{
	private String race;
	private String name;
	private String gender;
	private int level;
	private int hit_points;
	private int magic_points;
	private int attack;
	private int defense;
	private int special_attack;
	private int special_defense;
	private int coins;
	private int map_location;
	private int inventory_id;
	private Inventory inventory;
	private Item helm;
	private Item braces;
	private Item chest;
	private Item legs;
	private Item boots;
	private Item l_hand;
	private Item r_hand;

	public Character(){
		level = 1;
		inventory = new Inventory();
	}
	
	public String getrace(){
		return race;
	}
	
	public void setrace(String race){
		this.race = race;
	}
	
	public String getname(){
		return name;
	}
	
	public void setname(String name){
		this.name = name;
	}
	
	public String getgender(){
		return gender;
	}
	
	public void setgender(String gender){
		this.gender = gender;
	}
	
	public int getlevel(){
		return level;
	}
	
	public void setlevel(int level){
		this.level = level;
	}
	
	public int gethit_points(){
		return hit_points;
	}
	
	public void sethit_points(int hit_points){
		this.hit_points = hit_points;
	}
	
	public int getmagic_points(){
		return magic_points;
	}
	
	public void setmagic_points(int magic_points){
		this.magic_points = magic_points;
	}
	
	public int getattack(){
		return attack;
	}
	
	public void setattack(int attack){
		this.attack = attack;
	}
	
	public int getdefense(){
		return defense;
	}
	
	public void setdefense(int defense){
		this.defense = defense;
	}
	
	public int getspecial_attack(){
		return special_attack;
	}
	
	public void setspecial_attack(int special_attack){
		this.special_attack = special_attack;
	}
	
	public int getspecial_defense(){
		return special_defense;
	}
	
	public void setspecial_defense(int special_defense){
		this.special_defense = special_defense;
	}
	
	public int getcoins(){
		return coins;
	}
	
	public void setcoins(int coins){
		this.coins = coins;
	}
	
	public int getlocation(){
		return map_location;
	}
	
	public void setlocation(int map_location){
		this.map_location = map_location;
	}
	
	public int getinventory_id() {
		return this.inventory_id;
	}
	
	public void setinventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
	
	public Inventory getinventory(){
		return inventory;
	}
	
	public void setinventory(Inventory inventory){
		this.inventory = inventory;
	}
	
	public void sethelm(Item helm) {
		if(helm.getItemType() != ItemType.HELM){
			throw new IllegalArgumentException("This must be a helm!");
		}
		this.helm = helm;
	}
	
	public void remove(String type){
		Item emptyItemSlot = new Item();
		emptyItemSlot.setattack_bonus(0);
		emptyItemSlot.setdefense_bonus(0);
		emptyItemSlot.setdescription_update("You haven't equipped one");
		emptyItemSlot.sethp_bonus(0);
		emptyItemSlot.setlvl_requirement(0);
		emptyItemSlot.setID(0);
		emptyItemSlot.setIsQuestItem(false);
		emptyItemSlot.setItemWeight(0);
		emptyItemSlot.setLongDescription("Empty Slot");
		emptyItemSlot.setShortDescription("Empty Slot");
		emptyItemSlot.setName("Empty Slot");
		
		if (type.equalsIgnoreCase("head")) {
			emptyItemSlot.setItemType(ItemType.HELM);
			this.helm = emptyItemSlot;
		} else if (type.equalsIgnoreCase("chest")) {
			emptyItemSlot.setItemType(ItemType.CHEST);
			this.chest = emptyItemSlot;
		} else if (type.equalsIgnoreCase("arms")) {
			emptyItemSlot.setItemType(ItemType.BRACES);
			this.braces = emptyItemSlot;
		} else if (type.equalsIgnoreCase("lhand")) {
			emptyItemSlot.setItemType(ItemType.L_HAND);
			this.l_hand = emptyItemSlot;
		} else if (type.equalsIgnoreCase("rhand")) {
			emptyItemSlot.setItemType(ItemType.R_HAND);
			this.r_hand = emptyItemSlot;
		} else if (type.equalsIgnoreCase("legs")) {
			emptyItemSlot.setItemType(ItemType.LEGS);
			this.legs = emptyItemSlot;
		} else if (type.equalsIgnoreCase("boots")) {
			emptyItemSlot.setItemType(ItemType.BOOTS);
			this.boots = emptyItemSlot;
		}
	}
	
	public Item gethelm(){
		return helm;
	}
	
	public void setbraces(Item braces) {
		if(braces.getItemType() != ItemType.BRACES){
			throw new IllegalArgumentException("This must be braces!");
		}
		this.braces = braces;
	}
	
	
	public Item getbraces(){
		return braces;
	}
	
	public void setchest(Item chest) {
		if(chest.getItemType() != ItemType.CHEST){
			throw new IllegalArgumentException("This must be a chest!");
		}
		this.chest = chest;
	}
	
	public Item getchest(){
		return chest;
	}
	
	public void setlegs(Item legs) {
		if(legs.getItemType() != ItemType.LEGS){
			throw new IllegalArgumentException("This must be legs!");
		}
		this.legs = legs;
	}
	
	public Item getlegs(){
		return legs;
	}
	
	public void setboots(Item boots) {
		if(boots.getItemType() != ItemType.BOOTS){
			throw new IllegalArgumentException("This must be boots!");
		}
		this.boots = boots;
	}
	
	public Item getboots(){
		return boots;
	}
	
	public void setl_hand(Item l_hand) {
		if(l_hand.getItemType() != ItemType.L_HAND){
			// TODO: Maybe just HAND?
			throw new IllegalArgumentException("This must be a HAND type!");
		}
		this.l_hand = l_hand;
	}
	
	public Item getl_hand(){
		return l_hand;
	}
	
	public void setr_hand(Item r_hand) {
		if(r_hand.getItemType() != ItemType.R_HAND){
			// TODO: Maybe just HAND?
			throw new IllegalArgumentException("This must be a HAND type!");
		}
		this.r_hand = r_hand;
	}
	
	public Item getr_hand(){
		return r_hand;
	}
}
