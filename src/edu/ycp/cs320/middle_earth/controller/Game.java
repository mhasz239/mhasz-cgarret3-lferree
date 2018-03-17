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
	private ArrayList<String> dialog;
	private String mode;
	
	public Game(){
		dialog = new ArrayList<String>();
		mode = "game";
	}
	
	public String get_mode() {
		return this.mode;
	}

	public void set_mode(String mode) {
		this.mode = mode;
	}
	
	public ArrayList<String> get_dialog() {
		return dialog;
	}

	public void set_dialog(ArrayList<String> dialog) {
		this.dialog = dialog;
	}
	
	public void add_dialog(String line){
		dialog.add(line);
		// TODO: Implement removing lines when dialog reaches a certain length.
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
		for (int i = 0; i < this.dialog.size(); i++) {
			display_text = display_text+"\n"+this.dialog.get(i);
		}
		return display_text;
	}

	@Override
	public String handle_command(String commandStr){
		// TODO: Remove errorMessage? We talked about just adding it to the dialog I think?
		String errorMessage = null;
		String command = "";
		String[] args = new String[2];
		if (commandStr.split(" ").length > 1){
        	args = command.split(" ");
        	command = args[0];
        } else {
        	command = commandStr;
        }
		
		if(command.equalsIgnoreCase("inventory")){
			check_inventory();
		}else if(command.equalsIgnoreCase("character")){
			check_character_sheet();
		}else if(command.equalsIgnoreCase("map")){
			check_map();
		}else if(command.equalsIgnoreCase("game")){
			return_to_game();
		}else if(command.equalsIgnoreCase("move") && mode_check("game")){
			if(args[1].equalsIgnoreCase("north") || args[1].equalsIgnoreCase("south") || 
					args[1].equalsIgnoreCase("east") || args[1].equalsIgnoreCase("west") ||
					args[1].equalsIgnoreCase("northwest") || args[1].equalsIgnoreCase("northeast") ||
					args[1].equalsIgnoreCase("southwest") || args[1].equalsIgnoreCase("southeast")){
				move(args[1]);
			}else{
				add_dialog("I don't understand that direction.");
			}
		}else if((command.equalsIgnoreCase("north") || command.equalsIgnoreCase("N")) && mode_check("game")){
			move("north");
		}else if((command.equalsIgnoreCase("south") || command.equalsIgnoreCase("S")) && mode_check("game")){
			move("south");
		}else if((command.equalsIgnoreCase("east") || command.equalsIgnoreCase("E")) && mode_check("game")){
			move("east");
		}else if((command.equalsIgnoreCase("west") || command.equalsIgnoreCase("W")) && mode_check("game")){
			move("west");
		}else if((command.equalsIgnoreCase("northeast") || command.equalsIgnoreCase("NE")) && mode_check("game")){
			move("northeast");
		}else if((command.equalsIgnoreCase("northwest") || command.equalsIgnoreCase("NW")) && mode_check("game")){
			move("northwest");
		}else if((command.equalsIgnoreCase("southeast") || command.equalsIgnoreCase("SE")) && mode_check("game")){
			move("southeast");
		}else if((command.equalsIgnoreCase("southwest") || command.equalsIgnoreCase("SW")) && mode_check("game")){
			move("southwest");
		}else if(!command.equalsIgnoreCase("")){
			// Checking if command isn't empty, since it can't be null -> initialized in here to "";
			errorMessage = "I'm sorry I dont recognize that command";
		}else{
			errorMessage = "No command received";
		}
		return errorMessage;
	}
	
	public boolean mode_check(String required_mode){
		if(!mode.equalsIgnoreCase(required_mode)){
			add_dialog("You can't use that command here.");
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public void check_character_sheet(){
		if(mode.equalsIgnoreCase("character")){
			add_dialog("You're already in it!");
		}else{
			mode = "character";
		}
	}
	
	@Override
	public void check_inventory(){
		if(mode.equalsIgnoreCase("inventory")){
			add_dialog("You're already in it!");
		}else{
			mode = "inventory";
		}
	}
	
	@Override
	public void check_map(){
		if(mode.equalsIgnoreCase("map")){
			add_dialog("You're already in it!");
		}else{
			mode = "map";
		}
	}
	
	@Override
	public void return_to_game(){
		if(mode.equalsIgnoreCase("game")){
			add_dialog("You're playing it!");
		}else{
			mode = "game";
		}
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
		// direction.toLowerCase since they're stored in lowercase in the MapTile.
		int moveValue = map.getMapTiles().get(player.get_location()).getMoveValue(direction.toLowerCase());
		if (moveValue != 0) {
			player.set_location(player.get_location() + moveValue);
			add_dialog(map.getMapTiles().get(player.get_location()).getLongDescription());
		} else {
			add_dialog("You can't go that way");
		}
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
