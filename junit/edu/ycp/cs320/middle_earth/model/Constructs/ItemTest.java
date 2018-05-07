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
		// Set the item weight
		float weight = (float) 5.6;
		item.setItemWeight(weight);
		
		// Ensure it was set correctly
		assertEquals(weight, item.getItemWeight(), 0.001);
		
		// Reset it to ensure it doesn't get added or anything crazy
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
	
	@Test
	public void testSetDescriptionUpdate(){
		// Set the Description Update (Please tell me you knew that by reading the below line)
		item.setdescription_update("I'm a derp");
		
		// Ensure it was set correctly
		assertEquals("I'm a derp", item.getdescription_update());
		
		// Set it again (to ensure not adding to the string)
		item.setdescription_update("I'm also a derp of some sort.");
		
		// Ensure again it was set correctly
		assertEquals("I'm also a derp of some sort.", item.getdescription_update());
	}
	
	@Test
	public void testSetAttackBonus(){
		// Set the Attack Bonus
		item.setattack_bonus(5093);
		
		// Ensure it was set correctly
		assertEquals(5093, item.getattack_bonus());
		
		// Reset it (to ensure not being crazy and adding or anything)
		item.setattack_bonus(19284);
		
		// Ensure again correct
		assertEquals(19284, item.getattack_bonus());
	}
	
	@Test
	public void testSetDefenseBonus(){
		// Set the Defense Bonus
		item.setdefense_bonus(53);
		
		// Ensure it was set correctly
		assertEquals(53, item.getdefense_bonus());
		
		// Reset it (to ensure not being crazy and adding or anything)
		item.setdefense_bonus(283749);
		
		// Ensure again correct
		assertEquals(283749, item.getdefense_bonus());
	}
	
	@Test
	public void testSetHPBonus(){
		// Set the HP Bonus
		item.sethp_bonus(3928);
		
		// Ensure it was set correctly
		assertEquals(3928, item.gethp_bonus());
		
		// Reset it (to ensure not being crazy and adding or anything)
		item.sethp_bonus(209);
		
		// Ensure again correct
		assertEquals(209, item.gethp_bonus());
	}
	
	@Test
	public void testSetLvlRequirement(){
		// Set the Level Requirement
		item.setlvl_requirement(1000000);
		
		// Ensure it was set correctly
		assertEquals(1000000, item.getlvl_requirement());
		
		// Reset it (to ensure not being crazy and adding or anything)
		item.setlvl_requirement(1);
		
		// Ensure again correct
		assertEquals(1, item.getlvl_requirement());
	}
	
	@Test
	public void testSetType(){
		// Set the Type
		item.setItemType(ItemType.QUEST);
		
		// Check it's right
		assertEquals(ItemType.QUEST, item.getItemType());
	}
}
