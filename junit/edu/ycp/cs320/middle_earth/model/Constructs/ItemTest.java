package edu.ycp.cs320.middle_earth.model.Constructs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ItemTest{
	private Item item;
	
	@Before
	public void setup(){
		item = new Item();
	}
	
	@Test
	public void testSetWeight(){
		float weight = (float) 5.6;
		item.setItemWeight(weight);
		
		assertEquals(weight, item.getItemWeight(), 0.001);
	}
	
	@Test
	public void testResetWeight(){
		float weight = (float) 5.6;
		item.setItemWeight(weight);
		
		assertEquals(weight, item.getItemWeight(), 0.001);
		
		float newWeight = (float) 39.2;
		item.setItemWeight(newWeight);
		
		assertEquals(newWeight, item.getItemWeight(), 0.001);
	}
	
	@Test
	public void testSetIsQuestItem(){
		item.setIsQuestItem(true);
		
		assertEquals(true, item.getIsQuestItem());
		
		item.setIsQuestItem(true);
		
		assertEquals(true, item.getIsQuestItem());
		
		item.setIsQuestItem(false);
		
		assertEquals(false, item.getIsQuestItem());
		
		item.setIsQuestItem(false);
		
		assertEquals(false, item.getIsQuestItem());
	}
}
