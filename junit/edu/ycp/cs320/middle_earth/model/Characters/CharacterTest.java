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
	
	@Test
	public void testSet_Chest(){
		// Create Chest
		Item chest = new Item();
		chest.set_ItemType(ItemType.CHEST);
		chest.setName("Iron Chest");
		
		// Set the Chest
		character.set_chest(chest);
		
		// Ensure it's right
		assertEquals(chest, character.get_chest());
		
		// Create another Chest
		Item chest2 = new Item();
		chest2.set_ItemType(ItemType.CHEST);
		chest2.setName("Golden Chest");
		
		// Set the new chest
		character.set_chest(chest2);
		
		// Check it again
		assertEquals(chest2, character.get_chest());
	}
	
	@Test
	public void testSet_ChestNotChest(){
		// Create fake chest
		Item notChest = new Item();
		notChest.set_ItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.set_chest(notChest);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testSet_Legs(){
		// Create Legs
		Item legs = new Item();
		legs.set_ItemType(ItemType.LEGS);
		legs.setName("Iron Legs");
		
		// Set the Legs
		character.set_legs(legs);
		
		// Ensure it's right
		assertEquals(legs, character.get_legs());
		
		// Create another Legs
		Item legs2 = new Item();
		legs2.set_ItemType(ItemType.LEGS);
		legs2.setName("Golden Legs");
		
		// Set the new legs
		character.set_legs(legs2);
		
		// Check it again
		assertEquals(legs2, character.get_legs());
	}
	
	@Test
	public void testSet_LegsNotLegs(){
		// Create fake legs
		Item notLegs = new Item();
		notLegs.set_ItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.set_legs(notLegs);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testSet_Boots(){
		// Create Boots
		Item boots = new Item();
		boots.set_ItemType(ItemType.BOOTS);
		boots.setName("Iron Boots");
		
		// Set the Boots
		character.set_boots(boots);
		
		// Ensure it's right
		assertEquals(boots, character.get_boots());
		
		// Create another Boots
		Item boots2 = new Item();
		boots2.set_ItemType(ItemType.BOOTS);
		boots2.setName("Golden Boots");
		
		// Set the new boots
		character.set_boots(boots2);
		
		// Check it again
		assertEquals(boots2, character.get_boots());
	}
	
	@Test
	public void testSet_BootsNotBoots(){
		// Create fake boots
		Item notBoots = new Item();
		notBoots.set_ItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.set_boots(notBoots);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testSet_L_Hand(){
		// Create hand
		Item hand = new Item();
		hand.set_ItemType(ItemType.L_HAND);
		hand.setName("Iron Shield");
		
		// Set the Hand
		character.set_l_hand(hand);
		
		// Ensure it's right
		assertEquals(hand, character.get_l_hand());
		
		// Create another hand
		Item hand2 = new Item();
		hand2.set_ItemType(ItemType.L_HAND);
		hand2.setName("Golden Shield");
		
		// Set the new hand
		character.set_l_hand(hand2);
		
		// Check it again
		assertEquals(hand2, character.get_l_hand());
	}
	
	@Test
	public void testSet_L_HandNotHand(){
		// Create fake hand
		Item notHand = new Item();
		notHand.set_ItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.set_l_hand(notHand);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testSet_R_Hand(){
		// Create hand
		Item hand = new Item();
		hand.set_ItemType(ItemType.R_HAND);
		hand.setName("Iron Sword");
		
		// Set the Hand
		character.set_r_hand(hand);
		
		// Ensure it's right
		assertEquals(hand, character.get_r_hand());
		
		// Create another hand
		Item hand2 = new Item();
		hand2.set_ItemType(ItemType.R_HAND);
		hand2.setName("Golden Sword");
		
		// Set the new hand
		character.set_r_hand(hand2);
		
		// Check it again
		assertEquals(hand2, character.get_r_hand());
	}
	
	@Test
	public void testSet_R_HandNotHand(){
		// Create fake hand
		Item notHand = new Item();
		notHand.set_ItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.set_r_hand(notHand);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
}
