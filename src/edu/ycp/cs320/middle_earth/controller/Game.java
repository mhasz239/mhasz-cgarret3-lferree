package edu.ycp.cs320.middle_earth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.model.CombatSituation;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.NPC;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.FakeDatabase;
import edu.ycp.cs320.middle_earth.persist.DerbyDatabase;
import edu.ycp.cs320.middle_earth.persist.IDatabase;

public class Game implements Engine{
	private Map map;
	private ArrayList<Quest> quests;
	private ArrayList<Character> characters;
	private ArrayList<Object> objects;
	private ArrayList<Item> items;
	private ArrayList<String> dialog;
	private String mode;
	private CombatSituation battle;
	private IDatabase db;
	
	public Game(){
		// dialog and mode are passed back and forth with each servlet/jsp call
		dialog = new ArrayList<String>();
		mode = "game";
		
		//####################################################
		/* This does not work for us, because it resets the database everytime a new page is called
		 * this means that we reset the location of the player, reset his inventory, reset the entire
		 * game everytime that we refresh the page.
		 * I cannot for the life of me figure out how to get the database to only initialize once.
		 * Does it have to be intialized elsewhere? and passed in?
		 * 
		 * I think when we have the real database, it'll be fine, because then we'll be loading the 
		 * current database each time rather than re-initializing it? That it's just bad like this 
		 * for the fake database, since data can't be saved to it persistently.
		 */
		
		//Fake Database is rebuilt each time and populated into the respective fields.
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
		//######################################################
		
		items = db.getAllItems();
		map = db.getMap();
		for (MapTile tile : map.getMapTiles()) {
			tile.setVisited(false);
		}
		//map.setMapTiles(db.getAllMapTiles());
		quests = db.getAllQuests();
		characters = db.getAllCharacters();
		// ######################################################
		// Wouldn't db.getAllCharacters() already grab player?
		// ######################################################
		characters.add(db.getPlayer());
		objects = db.getAllObjects();
		for (Object object : objects) {
			if (!object.getItems().isEmpty()) {
				for (String key : object.getCommandResponses().keySet()) {
					String items_list = new String();
					
					for (Item item : object.getItems()){
						if (object.getItems().size() == 1) {
							items_list = "a " + item.getName();
						} else {
							items_list = items_list + "a " + item.getName() + ", ";
						}
					}
					object.getCommandResponses().put(key, object.getCommandResponses().get(key) + "You see " + items_list);
				}
			}
		}
		
		map.getMapTiles().get(get_player().get_location()).setVisited(true);
	}
	
	public Game get_game() {
		return this;
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
		if(dialog.size() > 25){
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
	
	public String get_mapTile_longDescription(){
		return map.getMapTiles().get(get_player().get_location()).getLongDescription();
	}
	
	public String get_mapTile_name(){
		return map.getMapTiles().get(get_player().get_location()).getName();
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

	public boolean mode_change(String command){
		if(command == null){
			return false;
		}else if(command.equalsIgnoreCase("inventory")){
			check_inventory();
			return true;
		}else if(command.equalsIgnoreCase("character")){
			check_character_sheet();
			return true;
		}else if(command.equalsIgnoreCase("map")){
			check_map();
			return true;
		}else if(command.equalsIgnoreCase("game")){
			return_to_game();
			return true;
		}
		return false;
	}
		
	
	@Override
	public String handle_command(String commandStr){
		// TODO: Implement all the command calls in here
		String returnMessage = null;
		String command = "";
		String arg = null;
		String[] args = commandStr.split(" ");
		// Fixed these if-else if blocks (previously the Too many arguments was not being executed).
		if(args.length > 2){
			return "Too many arguments in your command";
		}else if(args.length == 2){
        	command = args[0];
        	arg = args[1];
		}else if(args.length == 1){
        	command = commandStr;
        }
		
		
			/* #################################
			 * Here down, the mode is checked primarily, and the valid commands for that mode are within the mode.
			 * This fixes a couple potential (future) issues
			 * 1. If a command is valid in 2 modes, it would've been checked via 2 if statements:
			 * Note: Suppose that inventory is the current mode.
			 * - command.equalsIgnoreCase("whatever") && modeCheck("game")
			 * - command.equalsIgnoreCase("whatever") && modeCheck("inventory")
			 * The modeCheck() method would've added dialog of being in the wrong mode for game, then 
			 * successfully executed the command for the inventory mode check, giving extra unnecessary data.
			 * 2. If a valid command is entered for the wrong mode, multiple messages would be returned.
			 * - First, the message about being in the wrong mode
			 * - And Second, the message about it being an invalid command
			 * - The 2nd is due to modeCheck returning false, leading the else statement of invalid command to 
			 * be the block that gets executed.
			 * #################################
			 */
		if (commandStr.equalsIgnoreCase("save")){
			db.saveGame(this);
		} else if(mode.equalsIgnoreCase("game")){
			if(command.equalsIgnoreCase("move")){
				if(args[1].equalsIgnoreCase("north") || arg.equalsIgnoreCase("south") || 
						arg.equalsIgnoreCase("east") || arg.equalsIgnoreCase("west") ||
						arg.equalsIgnoreCase("northwest") || arg.equalsIgnoreCase("northeast") ||
						arg.equalsIgnoreCase("southwest") || arg.equalsIgnoreCase("southeast")){
					move(arg);
				}else{
					add_dialog("I don't understand that direction.");
				}
			}else if((command.equalsIgnoreCase("north") || command.equalsIgnoreCase("N"))){
				move("north");
			}else if((command.equalsIgnoreCase("south") || command.equalsIgnoreCase("S"))){
				move("south");
			}else if((command.equalsIgnoreCase("east") || command.equalsIgnoreCase("E"))){
				move("east");
			}else if((command.equalsIgnoreCase("west") || command.equalsIgnoreCase("W"))){
				move("west");
			}else if((command.equalsIgnoreCase("northeast") || command.equalsIgnoreCase("NE"))){
				move("northeast");
			}else if((command.equalsIgnoreCase("northwest") || command.equalsIgnoreCase("NW"))){
				move("northwest");
			}else if((command.equalsIgnoreCase("southeast") || command.equalsIgnoreCase("SE"))){
				move("southeast");
			}else if((command.equalsIgnoreCase("southwest") || command.equalsIgnoreCase("SW"))){
				move("southwest");
			}else if(command.equalsIgnoreCase("take")) {
				if (arg != null) {
					take(arg);
				} else {
					
				}
			} /*else if (command.equalsIgnoreCase("climb") && mode_check("game") && arg != null) {
				boolean climbable = false;
				ArrayList<Object> Objects = map.getMapTiles().get(get_player().get_location()).getObjects();
				for (Object climbObject : Objects) {
					if (climbObject != null && climbObject.getCommandResponses().containsKey("climb") && climbObject.getName().toLowerCase().contains(arg)) {
						climb(climbObject);
						climbable = true;
					}
				}
				if (!climbable){
					add_dialog("What exactly are you trying to climb?");
				}
			}*/else if(command.equalsIgnoreCase("look")){
				look();
			}else if(command.equalsIgnoreCase("attack")){
					if(get_player().get_location() == 7) {
						add_dialog("You take the pointy stick and throw it at the troll.;It manages to poke him in the eye and knock him off balance.;"
						+"As he falls he drops his sword, you quickly spring into action.;You grab his sword off the ground and lay waste to the foul beast.;"
						+"!!!CONGRATULATIONS!!! You have conqured this small land and laid waste the the evil plauging it.");
					}else{
						if(battle == null){
							add_dialog("You're not in combat!");
						}else{
							battle.doRound(this);
						}
					}
			}else{
				if(!handle_object_commands(commandStr)){
					// Changed this to add_dialog due to our conversation about having mode = game have all text 
					// in dialog instead of having a separate error/response message (while other modes still use 
					// the response message though)
					add_dialog("Sorry, I didn't understand that.");
				}
			}
		}else if(mode.equalsIgnoreCase("inventory")){
			if(command.equalsIgnoreCase("item")){
				if (arg != null) {
					try {
						int Item_num = Integer.parseInt(arg);
						//if (get_player().get_inventory().get_items().size() < Item_num || Item_num < 1 ) {
						if (get_player().get_inventory().get_items().size() < Item_num || Item_num < 1 ) {
							returnMessage = "Sorry you dont have an item at that index";
						} else  {
							returnMessage = item_details(Item_num-1);
						}
					} catch (NumberFormatException nfe) {
						returnMessage = "Invalid item selection. Example: 'item 1' to see the item at position 1";
					}
				} else {
					returnMessage = "Please designate the item # you want to view more details of.";
				}
			} else {
				// Checking if command isn't empty, since it can't be null -> initialized in here to "";
				// Simply changed to else... I may have lost the null command message.
				// Not sure if this message is still okay for a null command error?
				returnMessage = "Sorry, I didn't understand that.";
			}
		} 
		return returnMessage;
	}
	
	private boolean handle_object_commands(String commandStr){
		boolean isObjectCommand = false;
		String[] commands = commandStr.split(" ");
		String command = "";
		String arg = null;
		if (commands.length == 2){
			command = commands[0];
			arg = commands[1];
		} else {
			return false;
		}
		
		Object action_object = null;
		for (Object object : map.getMapTiles().get(get_player().get_location()).getObjects()){
			if (object.getCommandResponses().containsKey(command)) {
				if (object.getName().toLowerCase().contains(arg.toLowerCase())) {
					action_object = object;
					isObjectCommand = true;
				}
			}
		}
		if (action_object != null) {
			dialog.add(action_object.getCommandResponses().get(command));
		}
		return isObjectCommand;
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
		//if(mode.equalsIgnoreCase("inventory")){
		//	add_dialog("You're already in it!");
		//}else{
			mode = "inventory";
		//}
	}
	
	@Override
	public void check_map(){
		//if(mode.equalsIgnoreCase("map")){
		//	add_dialog("You're already in it!");
		//}else{
			mode = "map";
		//}
	}
	
	@Override
	public void return_to_game(){
		//if(mode.equalsIgnoreCase("game")){
		//	add_dialog("You're playing it!");
		//}else{
			mode = "game";
		//}
	}
	
	@Override
	public void save(){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	
	public String item_details(int item_num){
		Item item = get_player().get_inventory().get_items().get(item_num);
		//Item item = items.get(item_num);
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
	public void take(String name){
		int location = get_player().get_location();
		Object holder = new Object();
		if (map.getMapTiles().get(location).getObjects() != null) {
			Item lookFor = null;
			for (Object object : map.getMapTiles().get(location).getObjects()) {
				ArrayList<Item> items = object.getItems();
				for (Item item : items) {
					if (item.getName().toLowerCase().contains(name)) {
						lookFor = item;
						holder = object;
						get_player().get_inventory().get_items().add(item);
					}
				}
			}
			if (lookFor != null) {
				add_dialog("You have taken " + lookFor.getName());
				holder.removeItem(lookFor);
			} else {
				add_dialog("You cannot take " + name + " here.");
			}
		} else {
			add_dialog("There is nothing to take here.");
		}
		
	}

	@Override
	public void take(Object object, Item item){
		
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	@Override
	public void look(){
		add_dialog(get_mapTile_name());
    	add_dialog(get_mapTile_longDescription());
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
		int moveValue = map.getMapTiles().get(player.get_location()).getMoveValue(direction.toLowerCase());
		if (moveValue != 0) {
			if (player.get_location() == 8 && direction.equalsIgnoreCase("west")) {
				boolean key = false;
				for (Item item : player.get_inventory().get_items()) {
					if (item.getName() != null && item.getName().equalsIgnoreCase("Ornate Key")) {
						key = true;
					}
				}
				if (key) {
					add_dialog("You use the Ornate Key and open the gate.");
					player.set_location(player.get_location() + moveValue);
					add_dialog(map.getMapTiles().get(player.get_location()).getLongDescription());
				} else {
					add_dialog("You seem to be missing something to be able to go that direction.");
				}
			} else {
				player.set_location(player.get_location() + moveValue);
				add_dialog(map.getMapTiles().get(player.get_location()).getName());
				add_dialog(map.getMapTiles().get(player.get_location()).getLongDescription());
				Random rand = new Random(System.currentTimeMillis());
				int encounterCheck = rand.nextInt(10);
				if(encounterCheck == 0){
					battle = new CombatSituation(this);
				}
			}
		} else {
			add_dialog("You can't go that way");
		}
		map.getMapTiles().get(player.get_location()).setVisited(true);
		
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
