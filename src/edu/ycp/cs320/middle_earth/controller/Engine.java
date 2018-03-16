package edu.ycp.cs320.middle_earth.controller;

import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.NPC;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

public interface Engine{
	public abstract String handle_command(String command);
	
	public abstract void check_character_sheet();
	
	public abstract void check_inventory();
	
	public abstract void check_map();
	
	public abstract void save();
	
	// Player-Specific Actions
	
	public abstract void open(Object object);
	
	public abstract void close(Object object);
	
	public abstract void climb(Object object);
	
	public abstract void take(Item item);
	
	public abstract void take(Object object, Item item);
	
	public abstract void look();
	
	public abstract void fast_travel();
	
	public abstract void buy(Item item);
	
	public abstract void sell(Item item);
	
	public abstract void talk(NPC npc);
	
	// Character-Specific Actions (Not just Player)
	
	public abstract void move();
	
	public abstract void attack(Character character);
	
	public abstract void loot(Character character);
}
