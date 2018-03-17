package edu.ycp.cs320.middle_earth.model.Characters;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class VendorTest{
	private Vendor vendor;
	
	@Before
	public void setup(){
		vendor = new Vendor();
	}
	
	@Test
	public void testAdd_Item_Price(){
		Item sword = new Item();
		sword.setName("Sword");
		int price = 50;
		
		vendor.add_item_price(sword, price);
		
		assertEquals(price, vendor.get_item_price(sword));
	}
}
