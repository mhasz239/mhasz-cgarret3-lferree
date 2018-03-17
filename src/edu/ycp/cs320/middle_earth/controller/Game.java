package edu.ycp.cs320.middle_earth.controller;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.NPC;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;

public class Game implements Engine{
	private Map map;
	private ArrayList<Quest> quests;
	private ArrayList<Character> characters;
	private ArrayList<Object> objects;
	private ArrayList<Item> items;
	private ArrayList<String> dialog = new ArrayList<String>();
	private Item item;
	private String mode;
	
	public String get_mode() {
		return this.mode;
	}

	public void set_mode(String mode) {
		this.mode = mode;
	}
	
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
	
	public Character get_player(){
		//Player is assigned to index 0 of Characters List. ###Will have to update for multiplayer###
		return characters.get(0);
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
		this.set_dialog("Blue Bunny Balls");
		for (int i = 0; i < this.dialog.size(); i++) {
			display_text = display_text+"\n"+this.dialog.get(i);
		}
		return display_text;
	}

	@Override
	public String handle_command(String commandStr){
		// TODO: Implement "valid"
		String errorMessage = null;
		String command = "";
		String[] args = new String[2];
		if (commandStr.split(" ").length > 1){
        	args = command.split(" ");
        	command = args[0];
        } else {
        	command = commandStr;
        }
		
		if (mode == "game"){
			if (command == "inventory") {
				mode = "inventory";
			}
			else if (command == "map") {
				mode = "map";
			} 
			else if (command == "character") {
				mode = "inventory";
			}
			else if (command == "move" && (args[1] == "north" || args[1] == "south" || args[1] == "east" || args[1] == "west")){
				this.move(args[1]);
			}
			else if (command == "north" || command == "N" ){
				this.move("north");
			}
			else if (command == "south" || command == "S" ){
				this.move("south");
			}
			else if (command == "east" || command == "E" ){
				this.move("east");
			}
			else if (command == "west" || command == "W" ){
				this.move("west");
			}
		}
		
		else if (mode == "inventory"){
			if (command == "game") {
				mode = "game";
			}
			else if (command == "map") {
				mode = "map";
			} 
			else if (command == "character") {
				mode = "inventory";
			}
		} 
		
		else if (mode == "map"){
			if (command == "inventory") {
				mode = "inventory";
			}
			else if (command == "game") {
				mode = "game";
			} 
			else if (command == "character") {
				mode = "inventory";
			}
		}
		
		else if (mode == "character"){
			if (command == "inventory") {
				mode = "inventory";
			}
			else if (command == "map") {
				mode = "map";
			} 
			else if (command == "game") {
				mode = "game";
			}
		}
		
		
		if (command == "inventory") {
			
		}
		else if (command != null) {
			errorMessage = "I'm sorry I dont recognize that command";
		}
		//throw new UnsupportedOperationException("Not implemented yet!");
		return errorMessage;
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
	public void move(String direction){
		Character player = characters.get(0);
		int moveValue = map.getMapTiles().get(player.get_location()).getMoveValue(direction);
		if (moveValue != 0) {
			player.set_location(player.get_location() + moveValue);
		} else {
			dialog.add("You can't go that way");
		}
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
