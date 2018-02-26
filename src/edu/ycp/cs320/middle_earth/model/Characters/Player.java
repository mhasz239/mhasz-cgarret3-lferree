package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Game;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class Player extends Character implements PlayerAction{
	private int experience;
	private int carry_weight;
	private ArrayList<Quest> quests;
	
	@Override
	public void move(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void attack(Character character){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void loot(Character character){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void open(Object object){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void close(Object object){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void climb(Object object){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void take(Item item){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void take(Object object, Item item){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void look(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void fast_travel(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void check_character_sheet(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void check_inventory(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void check_map(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void save(Game game){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void buy(Item item){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void sell(Item item){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void talk(NPC npc){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
