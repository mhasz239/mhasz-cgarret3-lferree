package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;
import java.util.HashMap;

public class Object extends Construct{
	private HashMap<String, String> commandResponses;
	private ArrayList<Item> items;
	
	// TODO: Figure out how to put location in here???
	public Object() {
		
	}
	
	public void setCommandResponses(HashMap<String, String> commandResponses) {
		this.commandResponses = commandResponses;
	}
	
	public HashMap<String, String> getCommandResponses() {
		return this.commandResponses;
	}
	
	public void setItems(ArrayList<Item> items) {
		for(Item item : items) {
			this.items.add(item);
		}
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
}
