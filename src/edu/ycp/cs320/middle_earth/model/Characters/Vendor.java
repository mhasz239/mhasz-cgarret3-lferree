package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.HashMap;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class Vendor extends NPC{
	private HashMap<Item, Integer> item_prices;
	
	public int get_item_price(Item item){
		return item_prices.get(item);
	}
	
	public void add_item_price(Item item, int price){
		item_prices.put(item, price);
	}
	
	// Player buys item from Vendor
	public void buy_item(Item item){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
	
	// Player sells item to Vendor
	public void sell_item(Item item){
		// TODO Implement
		throw new UnsupportedOperationException("Not implemented yet!");
	}
}
