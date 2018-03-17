package edu.ycp.cs320.middle_earth.model.Constructs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ArmorTest{
	private Armor armor;
	
	@Before
	public void setup(){
		armor = new Armor();
	}
	
	@Test
	public void testSetDefenseBonus(){
		armor.setDefenseBonus(293);
		
		assertEquals(293, armor.getDefenseBonus());
	}
	
	@Test
	public void testResetDefenseBonus(){
		armor.setDefenseBonus(293);
		
		assertEquals(293, armor.getDefenseBonus());
		
		armor.setDefenseBonus(2010);
		
		assertEquals(2010, armor.getDefenseBonus());
	}
	
	@Test
	public void testSetSpecialDefenseBonus(){
		armor.setSpecialDefenseBonus(293);
		
		assertEquals(293, armor.getSpecialDefenseBonus());
	}
	
	@Test
	public void testResetSpecialDefenseBonus(){
		armor.setSpecialDefenseBonus(293);
		
		assertEquals(293, armor.getSpecialDefenseBonus());
		
		armor.setSpecialDefenseBonus(2010);
		
		assertEquals(2010, armor.getSpecialDefenseBonus());
	}
}
