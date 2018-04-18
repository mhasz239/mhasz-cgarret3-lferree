package edu.ycp.cs320.middle_earth.model.Characters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class InventoryTest{
	private Inventory inventory;
	
	@Before
	public void setup(){
		inventory = new Inventory();
	}
	
	@Test
	public void testSet_Items(){
		// Create an ArrayList of Items that are totally different
		ArrayList<Item> items = new ArrayList<Item>();
		Item derp = new Item();
		derp.setShortDescription("Just a Derp");
		Item otherDerp = new Item();
		derp.setShortDescription("Just another Derp");
		items.add(derp);
		items.add(otherDerp);
		
		// Set that ArrayList in Inventory
		inventory.set_items(items);
		
		// Ensure that the ArrayList was set correctly
		assertEquals(2, inventory.get_items().size());
		assertEquals(derp, inventory.get_items().get(0));
		assertEquals(otherDerp, inventory.get_items().get(1));
		
		// Make another ArrayList of Items
		ArrayList<Item> items2 = new ArrayList<Item>();
		Item flop = new Item();
		flop.setName("Flop");
		Item qwop = new Item();
		qwop.setName("Qwop");
		Item qwop2 = new Item();
		qwop2.setShortDescription("It's a sequel!");
		items2.add(flop);
		items2.add(qwop);
		items2.add(qwop2);
		
		// Reset Items in Inventory (in case of crazy adding stuff)
		inventory.set_items(items2);
		
		// Ensure set correct
		assertEquals(3, inventory.get_items().size());
		assertEquals(flop, inventory.get_items().get(0));
		assertEquals(qwop, inventory.get_items().get(1));
		assertEquals(qwop2, inventory.get_items().get(2));
	}
	
	@Test
	public void testSet_Weight(){
		// Set the weight
		inventory.set_weight(2938);
		
		// Ensure it was set right
		assertEquals(2938, inventory.get_weight());
		
		// Reset it in case of crazy adding
		inventory.set_weight(9384);
		
		// Ensure it's reset like that
		assertEquals(9384, inventory.get_weight());
	}
	
	// TODO: JUNIT: Remove this (Inventory ID is unnecessary due to Character ID)
	@Test
	public void testSetInventoryID(){
		// Set the ID
		inventory.set_inventory_id(203);
		
		// Ensure it was set right
		assertEquals(203, inventory.get_inventory_id());
		
		// Reset the ID (in case of crazies)
		inventory.set_inventory_id(329);
		
		// Check reset right
		assertEquals(329, inventory.get_inventory_id());
	}
}
