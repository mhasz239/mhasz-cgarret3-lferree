package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class Inventory{
	private ArrayList<Item> items;
	private int weight;
	
	public ArrayList<Item> get_items(){
		return items;
	}
	
	public void set_items(ArrayList<Item> items){
		this.items = items;
	}
	
	public int get_weight(){
		return weight;
	}
	
	public void set_weight(int weight){
		this.weight = weight;
	}
}
