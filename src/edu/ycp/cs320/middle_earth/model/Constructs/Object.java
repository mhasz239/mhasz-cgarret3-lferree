package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;
import java.util.HashMap;

public class Object extends Construct{
	private HashMap<String, String> commandResponses;
	private ArrayList<Item> items;
	private String name;
	private String description;
	
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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
}
