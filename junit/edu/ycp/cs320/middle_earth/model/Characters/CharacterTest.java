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
	public void testsetRace(){
		// Set that race
		character.setrace("Manticorn");
		
		// Check that race (Manticorn privilege, anyone?)
		assertEquals("Manticorn", character.getrace());
		
		// Reset race in case of crazy adding
		character.setrace("Monkey");
		
		// Check it again (Trans-Ethnic?)
		assertEquals("Monkey", character.getrace());
	}
	
	@Test
	public void testsetName(){
		// Set my name
		character.setname("Tadukoo");
		
		// Check my name
		assertEquals("Tadukoo", character.getname());
		
		// Then Matt
		character.setname("mhasz");
		
		// Is Matt
		assertEquals("mhasz", character.getname());
	}
	
	@Test
	public void testsetGender(){
		// The fairer gender
		character.setgender("Female");
		
		// Yep, she's got the right parts
		assertEquals("Female", character.getgender());
		
		// The other gender
		character.setgender("Male");
		
		// I don't wanna check that
		assertEquals("Male", character.getgender());
	}
	
	@Test
	public void testsetLevel(){
		// Set my level super high!
		character.setlevel(10293);
		
		// It's over 9000!
		assertEquals(10293, character.getlevel());
		
		// Check again!
		character.setlevel(293);
		
		// Guess it's not off the scales after all...
		assertEquals(293, character.getlevel());
	}
	
	@Test
	public void testsetHit_Points(){
		// Set those hps
		character.sethit_points(10293);
		
		// Check them hps
		assertEquals(10293, character.gethit_points());
		
		/// Set the health
		character.sethit_points(293);
		
		// Check the health
		assertEquals(293, character.gethit_points());
	}
	
	@Test
	public void testsetMagic_Points(){
		// Set them mps
		character.setmagic_points(10293);
		
		// Check those mps
		assertEquals(10293, character.getmagic_points());
		
		// Set that magic
		character.setmagic_points(293);
		
		// Check that magic
		assertEquals(293, character.getmagic_points());
	}
	
	@Test
	public void testsetAttack(){
		// Set that att
		character.setattack(10293);
		
		// Check that att
		assertEquals(10293, character.getattack());
		
		// I'm sensing a pattern
		character.setattack(293);
		
		// Is it a pattern?
		assertEquals(293, character.getattack());
	}
	
	@Test
	public void testsetDefense(){
		// Set that def
		character.setdefense(10293);
		
		// Check that def
		assertEquals(10293, character.getdefense());
		
		// These numbers...
		character.setdefense(293);
		
		// They seem familiar...
		assertEquals(293, character.getdefense());
	}
	
	@Test
	public void testsetSpecial_Attack(){
		// Set that spatt
		character.setspecial_attack(10293);
		
		// Check that spatt
		assertEquals(10293, character.getspecial_attack());
		
		// No really
		character.setspecial_attack(293);
		
		// They're the same numbers
		assertEquals(293, character.getspecial_attack());
	}
	
	@Test
	public void testsetSpecial_Defense(){
		// Set that spdef
		character.setspecial_defense(10293);
		
		// Check that spdef
		assertEquals(10293, character.getspecial_defense());
		
		// Why would anyone be
		character.setspecial_defense(293);
		
		// That lazy?
		assertEquals(293, character.getspecial_defense());
	}
	
	@Test
	public void testsetCoins(){
		// Set that coinage
		character.setcoins(10293);
		
		// Cheque that coinage
		assertEquals(10293, character.getcoins());
		
		// It's much simpler
		character.setcoins(293);
		
		// to do it
		assertEquals(293, character.getcoins());
	}
	
	@Test
	public void testsetLocation(){
		// Set that locashe
		character.setlocation(10293);
		
		// Check that locashe
		assertEquals(10293, character.getlocation());
		
		// this
		character.setlocation(293);
		
		// way
		assertEquals(293, character.getlocation());
	}
	
	@Test
	public void testsetInventory(){
		// Setup an Inventory
		Inventory inventory = new Inventory();
		Item derp = new Item();
		derp.setName("Derpy Derp");
		ArrayList<Item> items = new ArrayList<Item>();
		items.add(derp);
		inventory.setitems(items);
		
		// Set it to the Character
		character.setinventory(inventory);
		
		// Ensure it's right
		assertEquals(inventory, character.getinventory());
		
		// Setup another Inventory
		Inventory inventory2 = new Inventory();
		Item derp2 = new Item();
		derp2.setName("Not the Same Derp");
		ArrayList<Item> items2 = new ArrayList<Item>();
		items2.add(derp2);
		inventory.setitems(items2);
		
		// reset the character's inventory to it
		character.setinventory(inventory2);
		
		// Ensure it's again right
		assertEquals(inventory2, character.getinventory());
	}
	
	@Test
	public void testsetHelm(){
		// Create Helmet
		Item helmet = new Item();
		helmet.setItemType(ItemType.HELM);
		helmet.setName("Iron Helm");
		
		// Set the Helmet
		character.sethelm(helmet);
		
		// Ensure it's right
		assertEquals(helmet, character.gethelm());
		
		// Create another Helmet
		Item helmet2 = new Item();
		helmet2.setItemType(ItemType.HELM);
		helmet2.setName("Golden Helmet");
		
		// Set the new helmet
		character.sethelm(helmet2);
		
		// Check it again
		assertEquals(helmet2, character.gethelm());
	}
	
	@Test
	public void testsetHelmNotHelm(){
		// Create fake helmet
		Item notHelmet = new Item();
		notHelmet.setItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.sethelm(notHelmet);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testsetBraces(){
		// Create Braces
		Item braces = new Item();
		braces.setItemType(ItemType.BRACES);
		braces.setName("Iron Braces");
		
		// Set the Braces
		character.setbraces(braces);
		
		// Ensure it's right
		assertEquals(braces, character.getbraces());
		
		// Create another Braces
		Item braces2 = new Item();
		braces2.setItemType(ItemType.BRACES);
		braces2.setName("Golden Braces");
		
		// Set the new braces
		character.setbraces(braces2);
		
		// Check it again
		assertEquals(braces2, character.getbraces());
	}
	
	@Test
	public void testsetBracesNotBraces(){
		// Create fake braces
		Item notBraces = new Item();
		notBraces.setItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.setbraces(notBraces);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testsetChest(){
		// Create Chest
		Item chest = new Item();
		chest.setItemType(ItemType.CHEST);
		chest.setName("Iron Chest");
		
		// Set the Chest
		character.setchest(chest);
		
		// Ensure it's right
		assertEquals(chest, character.getchest());
		
		// Create another Chest
		Item chest2 = new Item();
		chest2.setItemType(ItemType.CHEST);
		chest2.setName("Golden Chest");
		
		// Set the new chest
		character.setchest(chest2);
		
		// Check it again
		assertEquals(chest2, character.getchest());
	}
	
	@Test
	public void testsetChestNotChest(){
		// Create fake chest
		Item notChest = new Item();
		notChest.setItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.setchest(notChest);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testsetLegs(){
		// Create Legs
		Item legs = new Item();
		legs.setItemType(ItemType.LEGS);
		legs.setName("Iron Legs");
		
		// Set the Legs
		character.setlegs(legs);
		
		// Ensure it's right
		assertEquals(legs, character.getlegs());
		
		// Create another Legs
		Item legs2 = new Item();
		legs2.setItemType(ItemType.LEGS);
		legs2.setName("Golden Legs");
		
		// Set the new legs
		character.setlegs(legs2);
		
		// Check it again
		assertEquals(legs2, character.getlegs());
	}
	
	@Test
	public void testsetLegsNotLegs(){
		// Create fake legs
		Item notLegs = new Item();
		notLegs.setItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.setlegs(notLegs);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testsetBoots(){
		// Create Boots
		Item boots = new Item();
		boots.setItemType(ItemType.BOOTS);
		boots.setName("Iron Boots");
		
		// Set the Boots
		character.setboots(boots);
		
		// Ensure it's right
		assertEquals(boots, character.getboots());
		
		// Create another Boots
		Item boots2 = new Item();
		boots2.setItemType(ItemType.BOOTS);
		boots2.setName("Golden Boots");
		
		// Set the new boots
		character.setboots(boots2);
		
		// Check it again
		assertEquals(boots2, character.getboots());
	}
	
	@Test
	public void testsetBootsNotBoots(){
		// Create fake boots
		Item notBoots = new Item();
		notBoots.setItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.setboots(notBoots);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testsetL_Hand(){
		// Create hand
		Item hand = new Item();
		hand.setItemType(ItemType.L_HAND);
		hand.setName("Iron Shield");
		
		// Set the Hand
		character.setl_hand(hand);
		
		// Ensure it's right
		assertEquals(hand, character.getl_hand());
		
		// Create another hand
		Item hand2 = new Item();
		hand2.setItemType(ItemType.L_HAND);
		hand2.setName("Golden Shield");
		
		// Set the new hand
		character.setl_hand(hand2);
		
		// Check it again
		assertEquals(hand2, character.getl_hand());
	}
	
	@Test
	public void testsetL_HandNotHand(){
		// Create fake hand
		Item notHand = new Item();
		notHand.setItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.setl_hand(notHand);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
	
	@Test
	public void testsetR_Hand(){
		// Create hand
		Item hand = new Item();
		hand.setItemType(ItemType.R_HAND);
		hand.setName("Iron Sword");
		
		// Set the Hand
		character.setr_hand(hand);
		
		// Ensure it's right
		assertEquals(hand, character.getr_hand());
		
		// Create another hand
		Item hand2 = new Item();
		hand2.setItemType(ItemType.R_HAND);
		hand2.setName("Golden Sword");
		
		// Set the new hand
		character.setr_hand(hand2);
		
		// Check it again
		assertEquals(hand2, character.getr_hand());
	}
	
	@Test
	public void testsetR_HandNotHand(){
		// Create fake hand
		Item notHand = new Item();
		notHand.setItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			character.setr_hand(notHand);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
}
