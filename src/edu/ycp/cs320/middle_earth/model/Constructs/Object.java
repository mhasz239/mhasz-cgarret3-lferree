package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;
import java.util.HashMap;

public class Object extends Construct{
	private HashMap<String, String> commandResponses;
	private ArrayList<Item> items;
	
	// TODO: Figure out how to put location in here???
	public Object() {
		items = new ArrayList<Item>();
		commandResponses = new HashMap<String, String>();
	}
	
	public void setCommandResponses(HashMap<String, String> commandResponses) {
		this.commandResponses = commandResponses;
	}
	
	public HashMap<String, String> getCommandResponses() {
		return this.commandResponses;
	}
	
	public void addItem(Item item){
		items.add(item);
	}
	
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
}
