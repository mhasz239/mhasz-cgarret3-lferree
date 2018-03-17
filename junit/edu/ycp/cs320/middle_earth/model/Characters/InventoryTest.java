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
		ArrayList<Item> items = new ArrayList<Item>();
		Item derp = new Item();
		derp.setShortDescription("Just a Derp");
		Item otherDerp = new Item();
		derp.setShortDescription("Just another Derp");
		items.add(derp);
		items.add(otherDerp);
		
		inventory.set_items(items);
		
		assertEquals(2, inventory.get_items().size());
		assertEquals(derp, inventory.get_items().get(0));
		assertEquals(otherDerp, inventory.get_items().get(1));
	}
	
	@Test
	public void testReset_Items(){
		ArrayList<Item> items = new ArrayList<Item>();
		Item derp = new Item();
		derp.setShortDescription("Just a Derp");
		Item otherDerp = new Item();
		derp.setShortDescription("Just another Derp");
		items.add(derp);
		items.add(otherDerp);
		
		inventory.set_items(items);
		
		assertEquals(2, inventory.get_items().size());
		assertEquals(derp, inventory.get_items().get(0));
		assertEquals(otherDerp, inventory.get_items().get(1));
		
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
		
		inventory.set_items(items2);
		
		assertEquals(3, inventory.get_items().size());
		assertEquals(flop, inventory.get_items().get(0));
		assertEquals(qwop, inventory.get_items().get(1));
		assertEquals(qwop2, inventory.get_items().get(2));
	}
	
	@Test
	public void testSet_Weight(){
		inventory.set_weight(2938);
		
		assertEquals(2938, inventory.get_weight());
	}
	
	@Test
	public void testReset_Weight(){
		inventory.set_weight(2938);
		
		assertEquals(2938, inventory.get_weight());
		
		inventory.set_weight(9384);
		
		assertEquals(9384, inventory.get_weight());
	}
}
