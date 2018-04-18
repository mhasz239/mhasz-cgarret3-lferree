package edu.ycp.cs320.middle_earth.model.Characters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

public class CharacterTest{
	private Character character;
	private class Derp extends Character{};
	
	@Before
	public void setup(){
		character = new Derp();
	}
	
	@Test
	public void testSet_Race(){
		// Set that race
		character.set_race("Manticorn");
		
		// Check that race (Manticorn privilege, anyone?)
		assertEquals("Manticorn", character.get_race());
		
		// Reset race in case of crazy adding
		character.set_race("Monkey");
		
		// Check it again (Trans-Ethnic?)
		assertEquals("Monkey", character.get_race());
	}
	
	@Test
	public void testSet_Name(){
		// Set my name
		character.set_name("Tadukoo");
		
		// Check my name
		assertEquals("Tadukoo", character.get_name());
		
		// Then Matt
		character.set_name("mhasz");
		
		// Is Matt
		assertEquals("mhasz", character.get_name());
	}
	
	@Test
	public void testSet_Gender(){
		// The fairer gender
		character.set_gender("Female");
		
		// Yep, she's got the right parts
		assertEquals("Female", character.get_gender());
		
		// The other gender
		character.set_gender("Male");
		
		// I don't wanna check that
		assertEquals("Male", character.get_gender());
	}
	
	@Test
	public void testSet_Level(){
		// Set my level super high!
		character.set_level(10293);
		
		// It's over 9000!
		assertEquals(10293, character.get_level());
		
		// Check again!
		character.set_level(293);
		
		// Guess it's not off the scales after all...
		assertEquals(293, character.get_level());
	}
	
	@Test
	public void testSet_Hit_Points(){
		// Set those hps
		character.set_hit_points(10293);
		
		// Check them hps
		assertEquals(10293, character.get_hit_points());
		
		/// Set the health
		character.set_hit_points(293);
		
		// Check the health
		assertEquals(293, character.get_hit_points());
	}
	
	@Test
	public void testSet_Magic_Points(){
		// Set them mps
		character.set_magic_points(10293);
		
		// Check those mps
		assertEquals(10293, character.get_magic_points());
		
		// Set that magic
		character.set_magic_points(293);
		
		// Check that magic
		assertEquals(293, character.get_magic_points());
	}
	
	@Test
	public void testSet_Attack(){
		// Set that att
		character.set_attack(10293);
		
		// Check that att
		assertEquals(10293, character.get_attack());
		
		// I'm sensing a pattern
		character.set_attack(293);
		
		// Is it a pattern?
		assertEquals(293, character.get_attack());
	}
	
	@Test
	public void testSet_Defense(){
		// Set that def
		character.set_defense(10293);
		
		// Check that def
		assertEquals(10293, character.get_defense());
		
		// These numbers...
		character.set_defense(293);
		
		// They seem familiar...
		assertEquals(293, character.get_defense());
	}
	
	@Test
	public void testSet_Special_Attack(){
		// Set that spatt
		character.set_special_attack(10293);
		
		// Check that spatt
		assertEquals(10293, character.get_special_attack());
		
		// No really
		character.set_special_attack(293);
		
		// They're the same numbers
		assertEquals(293, character.get_special_attack());
	}
	
	@Test
	public void testSet_Special_Defense(){
		// Set that spdef
		character.set_special_defense(10293);
		
		// Check that spdef
		assertEquals(10293, character.get_special_defense());
		
		// Why would anyone be
		character.set_special_defense(293);
		
		// That lazy?
		assertEquals(293, character.get_special_defense());
	}
	
	@Test
	public void testSet_Coins(){
		// Set that coinage
		character.set_coins(10293);
		
		// Cheque that coinage
		assertEquals(10293, character.get_coins());
		
		// It's much simpler
		character.set_coins(293);
		
		// to do it
		assertEquals(293, character.get_coins());
	}
	
	@Test
	public void testSet_Location(){
		// Set that locashe
		character.set_location(10293);
		
		// Check that locashe
		assertEquals(10293, character.get_location());
		
		// this
		character.set_location(293);
		
		// way
		assertEquals(293, character.get_location());
	}
	
	@Test
	public void testSet_Inventory(){
		// Setup an Inventory
		Inventory inventory = new Inventory();
		Item derp = new Item();
		derp.setName("Derpy Derp");
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(derp);
		inventory.set_items(items);
		
		// Set it to the Character
		character.set_inventory(inventory);
		
		// Ensure it's right
		assertEquals(inventory, character.get_inventory());
		
		// Setup another Inventory
		Inventory inventory2 = new Inventory();
		Item derp2 = new Item();
		derp2.setName("Not the Same Derp");
		ArrayList<Item> items2 = new ArrayList<Item>();
		items2.add(derp2);
		inventory.set_items(items2);
		
		// reset the character's inventory to it
		character.set_inventory(inventory2);
		
		// Ensure it's again right
		assertEquals(inventory2, character.get_inventory());
	}
	
	@Test
	public void testSet_Helm(){
		// Create Helmet
		Item helmet = new Item();
		helmet.set_ItemType(ItemType.HELM);
		helmet.setName("Iron Helm");
		
		// Set the Helmet
		character.set_helm(helmet);
		
		// Ensure it's right
		assertEquals(helmet, character.get_helm());
		
		// Create another Helmet
		Item helmet2 = new Item();
		helmet2.set_ItemType(ItemType.HELM);
		helmet2.setName("Golden Helmet");
		
		// Set the new helmet
		character.set_helm(helmet2);
		
		// Check it again
		assertEquals(helmet2, character.get_helm());
	}
	
	@Test
	public void testSet_HelmNotHelm(){
		// Create fake helmet
		Item notHelmet = new Item();
		notHelmet.set_ItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.set_helm(notHelmet);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testSet_Braces(){
		// Create Braces
		Item braces = new Item();
		braces.set_ItemType(ItemType.BRACES);
		braces.setName("Iron Braces");
		
		// Set the Braces
		character.set_braces(braces);
		
		// Ensure it's right
		assertEquals(braces, character.get_braces());
		
		// Create another Braces
		Item braces2 = new Item();
		braces2.set_ItemType(ItemType.BRACES);
		braces2.setName("Golden Braces");
		
		// Set the new braces
		character.set_braces(braces2);
		
		// Check it again
		assertEquals(braces2, character.get_braces());
	}
	
	@Test
	public void testSet_BracesNotBraces(){
		// Create fake braces
		Item notBraces = new Item();
		notBraces.set_ItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.set_braces(notBraces);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
}
