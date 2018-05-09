package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class Inventory{
	private ArrayList<Item> items;
	private int weight;
	private int inventory_id;
	
	public Inventory(){
		items = new ArrayList<Item>();
	};
	
	public ArrayList<Item> getitems(){
		return items;
	}
	
	public void setitems(ArrayList<Item> items){
		this.items = items;
	}
	
	public int getweight(){
		return weight;
	}
	
	public void setweight(int weight){
		this.weight = weight;
	}
	
	public int getinventory_id() {
		return this.inventory_id;
	}
	
	public void setinventory_id(int inventory_id) {
		this.inventory_id = inventory_id;
	}
}
