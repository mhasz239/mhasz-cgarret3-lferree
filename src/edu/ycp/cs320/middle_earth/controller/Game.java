package edu.ycp.cs320.middle_earth.controller;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.NPC;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.FakeDatabase;
import edu.ycp.cs320.middle_earth.persist.IDatabase;

public class Game implements Engine{
	private Map map;
	private ArrayList<Quest> quests;
	private ArrayList<Character> characters;
	private ArrayList<Object> objects;
	private ArrayList<Item> items;
	private ArrayList<String> dialog;
	private String mode;
	
	public Game(){
		//Is this how we initiate dialog? I know we call 
		//Game game = new Game();
		//in the servlets, so wouldnt that erase the current dialog and write a new one every time?
		// The model and controller get recreated every time due to how the program is setup (Hake's way, not ours)
		// So we would have to put the dialog back in here every time as well. (with set_dialog)
		// The dialog would be passed between every jsp through get and post stuff in the request and response.
		dialog = new ArrayList<String>();
		//Was messing with inventoryServlet so set game_mode to always be inventory when it was called
		mode = "game";
		DatabaseProvider.setInstance(new FakeDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		items = (ArrayList<Item>) db.getAllItems();
		
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
		if(dialog.size() > 30){
			dialog.remove(0);
		}
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
			// Is it supposed to have a \n before the first line? (Not sure)
			if (i == 0) {
				display_text = this.dialog.get(i);
			} else {
				display_text = display_text+";"+this.dialog.get(i);
			}
		}
		return display_text;
	}

	@Override
	public String handle_command(String commandStr){
		// TODO: Remove errorMessage? We talked about just adding it to the dialog I think?
		//Yes, error message is just what I was using as a string holder to return cause it was already there, so we can name it whatever.
		// Well I thought that we were going to entirely get rid of a return mesage in handle_command, and just have it 
		// place any messages into the dialog?
		String returnMessage = null;
		String command = "";
		String arg = null;
		String[] args = commandStr.split(" ");
		if (args.length > 1){
        	command = args[0];
        	arg = args[1];
		} else if (args.length >  2) {
			return "Too many arguments in your command";
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
			if(args[1].equalsIgnoreCase("north") || arg.equalsIgnoreCase("south") || 
					arg.equalsIgnoreCase("east") || arg.equalsIgnoreCase("west") ||
					arg.equalsIgnoreCase("northwest") || arg.equalsIgnoreCase("northeast") ||
					arg.equalsIgnoreCase("southwest") || arg.equalsIgnoreCase("southeast")){
				move(arg);
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
		}else if(command.equalsIgnoreCase("item") && mode_check("inventory")){
			if (arg != null) {
				try {
					int Item_num = Integer.parseInt(arg);
					//if (get_player().get_inventory().get_items().size() < Item_num || Item_num < 1 ) {
					if (items.size() < Item_num || Item_num < 1 ) {
						returnMessage = "Sorry you dont have an item at that index";
					} else  {
						returnMessage = item_details(Item_num-1);
					}
				} catch (NumberFormatException nfe) {
					returnMessage = "Invalid number selection. Example: 'item 1' to see the item at position 1";
				}
			} else {
				returnMessage = "Please designate the item # you want to view more details of.";
			}
		}else if(!command.equalsIgnoreCase("")){
			// Checking if command isn't empty, since it can't be null -> initialized in here to "";
			returnMessage = "Sorry, I didn't understand that.";
		}else{
			returnMessage = "No command received";
		}
		return returnMessage;
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

	
	public String item_details(int item_num){
		//Item item = get_player().get_inventory().get_items().get(item_num);
		Item item = items.get(item_num);
		return item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + ";Quest item: " + String.valueOf(item.getIsQuestItem());
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
