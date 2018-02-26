package edu.ycp.cs320.middle_earth.model.Characters;

import edu.ycp.cs320.middle_earth.model.Game;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public interface PlayerAction{
	public abstract void open(Object object);
	
	public abstract void close(Object object);
	
	public abstract void climb(Object object);
	
	public abstract void take(Item item);
	
	public abstract void take(Object object, Item item);
	
	public abstract void look();
	
	public abstract void fast_travel();
	
	public abstract void check_character_sheet();
	
	public abstract void check_inventory();
	
	public abstract void check_map();
	
	public abstract void save(Game game);
	
	public abstract void buy(Item item);
	
	public abstract void sell(Item item);
	
	public abstract void talk(NPC npc);
}
