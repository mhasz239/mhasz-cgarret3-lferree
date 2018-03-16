package edu.ycp.cs320.middle_earth.controller;

import java.util.ArrayList;


import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.NPC;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;

public class Game implements Engine{
	private Map map;
	private ArrayList<Quest> quests;
	private ArrayList<Character> characters;
	private ArrayList<Object> objects;
	private ArrayList<Item> items;
	private ArrayList<String> dialog;

	public ArrayList<String> get_dialog() {
		return dialog;
	}

	public void set_dialog(String dialog) {
		this.dialog.add(dialog);
	}

	public Map get_map(){
		return map;
	}
	
	public void set_map(Map map){
		this.map = map;
	}

	public ArrayList<Quest> get_quests(){
		return quests;
	}
	
	public void set_quests(ArrayList<Quest> quests){
		this.quests = quests;
	}
	
	public ArrayList<Character> get_characters(){
		return characters;
	}
	
	public void set_characters(ArrayList<Character> characters){
		this.characters = characters;
	}
	
	public ArrayList<Object> get_objects(){
		return objects;
	}
	
	public void set_objects(ArrayList<Object> objects){
		this.objects = objects;
	}
	
	public ArrayList<Item> get_items(){
		return items;
	}
	
	public void set_items(ArrayList<Item> items){
		this.items = items;
	}

	public String get_display_text(){
		String display_text = "";
		for (int i = 0; i < dialog.size(); i++){
			display_text = display_text + "\n" + dialog.get(i);
		}
		return display_text;
	}

	@Override
	public String handle_command(String command){
		// TODO: Implement "valid"
		if (command != valid) {
			String errorMessage = "Invalid Command";
		}

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
	public void save(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	/*
	 * Player-Specific Actions
	 */
	
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
	
	/*
	 * Character-Specific Actions (outside of Player)
	 */
	
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
}
