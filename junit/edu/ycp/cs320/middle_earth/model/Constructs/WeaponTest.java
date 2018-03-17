package edu.ycp.cs320.middle_earth.model.Constructs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class WeaponTest{
	private Weapon weapon;
	
	@Before
	public void setup(){
		weapon = new Weapon();
	}
	
	@Test
	public void testSetAttackBonus(){
		weapon.setAttackBonus(2938);
		
		assertEquals(2938, weapon.getAttackBonus());
	}
	
	@Test
	public void testResetAttackBonus(){
		weapon.setAttackBonus(2938);
		
		assertEquals(2938, weapon.getAttackBonus());
		
		weapon.setAttackBonus(12039);
		
		assertEquals(12039, weapon.getAttackBonus());
	}
	
	@Test
	public void testSetSpecialAttackBonus(){
		weapon.setSpecialAttackBonus(9384);
		
		assertEquals(9384, weapon.getSpecialAttackBonus());
	}
	
	@Test
	public void testResetSpecialAttackBonus(){
		weapon.setSpecialAttackBonus(9384);
		
		assertEquals(9384, weapon.getSpecialAttackBonus());
		
		weapon.setSpecialAttackBonus(19283);
		
		assertEquals(19283, weapon.getSpecialAttackBonus());
	}
}
