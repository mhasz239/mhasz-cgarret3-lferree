package edu.ycp.cs320.middle_earth.model.Characters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class CharacterTest{
	private Character character;
	private class Derp extends Character{};
	
	@Before
	public void setup(){
		character = new Derp();
	}
	
	@Test
	public void testSet_Race(){
		character.set_race("Manticorn");
		
		assertEquals("Manticorn", character.get_race());
	}
	
	@Test
	public void testReset_Race(){
		character.set_race("Manticorn");
		
		assertEquals("Manticorn", character.get_race());
		
		character.set_race("Monkey");
		
		assertEquals("Monkey", character.get_race());
	}
	
	@Test
	public void testSet_Name(){
		character.set_name("Tadukoo");
		
		assertEquals("Tadukoo", character.get_name());
	}
	
	@Test
	public void testReset_Name(){
		character.set_name("Tadukoo");
		
		assertEquals("Tadukoo", character.get_name());
		
		character.set_name("mhasz");
		
		assertEquals("mhasz", character.get_name());
	}
	
	@Test
	public void testSet_Gender(){
		character.set_gender("Female");
		
		assertEquals("Female", character.get_gender());
	}
	
	@Test
	public void testReset_Gender(){
		character.set_gender("Female");
		
		assertEquals("Female", character.get_gender());
		
		character.set_gender("Male");
		
		assertEquals("Male", character.get_gender());
	}
	
	@Test
	public void testSet_Level(){
		character.set_level(10293);
		
		assertEquals(10293, character.get_level());
	}
	
	@Test
	public void testReset_Level(){
		character.set_level(10293);
		
		assertEquals(10293, character.get_level());
		
		character.set_level(293);
		
		assertEquals(293, character.get_level());
	}
	
	@Test
	public void testSet_Hit_Points(){
		character.set_hit_points(10293);
		
		assertEquals(10293, character.get_hit_points());
	}
	
	@Test
	public void testReset_Hit_Points(){
		character.set_hit_points(10293);
		
		assertEquals(10293, character.get_hit_points());
		
		character.set_hit_points(293);
		
		assertEquals(293, character.get_hit_points());
	}
	
	@Test
	public void testSet_Magic_Points(){
		character.set_magic_points(10293);
		
		assertEquals(10293, character.get_magic_points());
	}
	
	@Test
	public void testReset_Magic_Points(){
		character.set_magic_points(10293);
		
		assertEquals(10293, character.get_magic_points());
		
		character.set_magic_points(293);
		
		assertEquals(293, character.get_magic_points());
	}
	
	@Test
	public void testSet_Attack(){
		character.set_attack(10293);
		
		assertEquals(10293, character.get_attack());
	}
	
	@Test
	public void testReset_Attack(){
		character.set_attack(10293);
		
		assertEquals(10293, character.get_attack());
		
		character.set_attack(293);
		
		assertEquals(293, character.get_attack());
	}
	
	@Test
	public void testSet_Defense(){
		character.set_defense(10293);
		
		assertEquals(10293, character.get_defense());
	}
	
	@Test
	public void testReset_Defense(){
		character.set_defense(10293);
		
		assertEquals(10293, character.get_defense());
		
		character.set_defense(293);
		
		assertEquals(293, character.get_defense());
	}
	
	@Test
	public void testSet_Special_Attack(){
		character.set_special_attack(10293);
		
		assertEquals(10293, character.get_special_attack());
	}
	
	@Test
	public void testReset_Special_Attack(){
		character.set_special_attack(10293);
		
		assertEquals(10293, character.get_special_attack());
		
		character.set_special_attack(293);
		
		assertEquals(293, character.get_special_attack());
	}
	
	@Test
	public void testSet_Special_Defense(){
		character.set_special_defense(10293);
		
		assertEquals(10293, character.get_special_defense());
	}
	
	@Test
	public void testReset_Special_Defense(){
		character.set_special_defense(10293);
		
		assertEquals(10293, character.get_special_defense());
		
		character.set_special_defense(293);
		
		assertEquals(293, character.get_special_defense());
	}
	
	@Test
	public void testSet_Coins(){
		character.set_coins(10293);
		
		assertEquals(10293, character.get_coins());
	}
	
	@Test
	public void testReset_Coins(){
		character.set_coins(10293);
		
		assertEquals(10293, character.get_coins());
		
		character.set_coins(293);
		
		assertEquals(293, character.get_coins());
	}
	
	@Test
	public void testSet_Location(){
		character.set_location(10293);
		
		assertEquals(10293, character.get_location());
	}
	
	@Test
	public void testReset_Location(){
		character.set_location(10293);
		
		assertEquals(10293, character.get_location());
		
		character.set_location(293);
		
		assertEquals(293, character.get_location());
	}
	
	@Test
	public void testSet_Inventory(){
		Inventory inventory = new Inventory();
		Item derp = new Item();
		derp.setName("Derpy Derp");
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(derp);
		inventory.set_items(items);
		
		character.set_inventory(inventory);
		
		assertEquals(inventory, character.get_inventory());
	}
	
	@Test
	public void testReset_Inventory(){
		Inventory inventory = new Inventory();
		Item derp = new Item();
		derp.setName("Derpy Derp");
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(derp);
		inventory.set_items(items);
		
		character.set_inventory(inventory);
		
		assertEquals(inventory, character.get_inventory());
		
		Inventory inventory2 = new Inventory();
		Item derp2 = new Item();
		derp2.setName("Not the Same Derp");
		ArrayList<Item> items2 = new ArrayList<Item>();
		items2.add(derp2);
		inventory.set_items(items2);
		
		character.set_inventory(inventory2);
		
		assertEquals(inventory2, character.get_inventory());
	}
}
