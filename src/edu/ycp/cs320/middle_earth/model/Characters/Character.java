package edu.ycp.cs320.middle_earth.model.Characters;

import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

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
	private MapTile location;
	private Inventory inventory;
	
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
	
	public MapTile get_location(){
		return location;
	}
	
	public void set_location(MapTile location){
		this.location = location;
	}
	
	public Inventory get_inventory(){
		return inventory;
	}
	
	public void set_inventory(Inventory inventory){
		this.inventory = inventory;
	}
}
