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

	public Character() {
		
	}
	
	public String get_race(){
		return race;
	}
	
	public void set_race(String race){
		this.race = race;
	}
	
	public String get_name(){
		return name;
	}
	
	public void set_name(String name){
		this.name = name;
	}
	
	public String get_gender(){
		return gender;
	}
	
	public void set_gender(String gender){
		this.gender = gender;
	}
	
	public int get_level(){
		return level;
	}
	
	public void set_level(int level){
		this.level = level;
	}
	
	public int get_hit_points(){
		return hit_points;
	}
	
	public void set_hit_points(int hit_points){
		this.hit_points = hit_points;
	}
	
	public int get_magic_points(){
		return magic_points;
	}
	
	public void set_magic_points(int magic_points){
		this.magic_points = magic_points;
	}
	
	public int get_attack(){
		return attack;
	}
	
	public void set_attack(int attack){
		this.attack = attack;
	}
	
	public int get_defense(){
		return defense;
	}
	
	public void set_defense(int defense){
		this.defense = defense;
	}
	
	public int get_special_attack(){
		return special_attack;
	}
	
	public void set_special_attack(int special_attack){
		this.special_attack = special_attack;
	}
	
	public int get_special_defense(){
		return special_defense;
	}
	
	public void set_special_defense(int special_defense){
		this.special_defense = special_defense;
	}
	
	public int get_coins(){
		return coins;
	}
	
	public void set_coins(int coins){
		this.coins = coins;
	}
	
	public int get_location(){
		return map_location;
	}
	
	public void set_location(int map_location){
		this.map_location = map_location;
	}
	
	public int get_inventory_id() {
		return this.inventory_id;
	}
	
	public void set_inventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
	
	public Inventory get_inventory(){
		return inventory;
	}
	
	public void set_inventory(Inventory inventory){
		this.inventory = inventory;
	}
	
	public void set_helm(Item helm) {
		if(helm.get_ItemType() != ItemType.HELM){
			throw new IllegalArgumentException("This must be a helm!");
		}
		this.helm = helm;
	}
	
	public Item get_helm(){
		return helm;
	}
	
	public void set_braces(Item braces) {
		if(braces.get_ItemType() != ItemType.BRACES){
			throw new IllegalArgumentException("This must be braces!");
		}
		this.braces = braces;
	}
	
	public Item get_braces(){
		return braces;
	}
	
	public void set_chest(Item chest) {
		if(chest.get_ItemType() != ItemType.CHEST){
			throw new IllegalArgumentException("This must be a chest!");
		}
		this.chest = chest;
	}
	
	public Item get_chest(){
		return chest;
	}
	
	public void set_legs(Item legs) {
		if(legs.get_ItemType() != ItemType.LEGS){
			throw new IllegalArgumentException("This must be legs!");
		}
		this.legs = legs;
	}
	
	public Item get_legs(){
		return legs;
	}
	
	public void set_boots(Item boots) {
		if(boots.get_ItemType() != ItemType.BOOTS){
			throw new IllegalArgumentException("This must be boots!");
		}
		this.boots = boots;
	}
	
	public Item get_boots(){
		return boots;
	}
	
	public void set_l_hand(Item l_hand) {
		if(l_hand.get_ItemType() != ItemType.L_HAND){
			// TODO: Maybe just HAND?
			throw new IllegalArgumentException("This must be a HAND type!");
		}
		this.l_hand = l_hand;
	}
	
	public Item get_l_hand(){
		return l_hand;
	}
	
	public void set_r_hand(Item r_hand) {
		if(r_hand.get_ItemType() != ItemType.R_HAND){
			// TODO: Maybe just HAND?
			throw new IllegalArgumentException("This must be a HAND type!");
		}
		this.r_hand = r_hand;
	}
	
	public Item get_r_hand(){
		return r_hand;
	}
}
