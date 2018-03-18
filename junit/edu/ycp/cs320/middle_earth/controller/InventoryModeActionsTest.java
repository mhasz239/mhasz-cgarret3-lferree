package edu.ycp.cs320.middle_earth.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class InventoryModeActionsTest{
	private Game game;
	private Player player;
	private Item sword;
	private Item helmet;
	private Item key;
	
	@Before
	public void setup(){
		game = new Game();
		player = new Player();
		
		// Populate Player's inventory
		ArrayList<Item> playerItems = new ArrayList<Item>();
		sword = new Item();
		sword.setName("Sword");
		sword.setLongDescription("A Long sword. Probably stolen from a giant golem or something.");
		sword.setItemWeight((float) 9.6);
		sword.setIsQuestItem(false);
		helmet = new Item();
		helmet.setName("Helmet");
		helmet.setLongDescription("A helmet forged in the hot, hot fires of Mordor.");
		helmet.setItemWeight((float) 29.3);
		helmet.setIsQuestItem(false);
		key = new Item();
		key.setName("Key");
		key.setLongDescription("A key to treasure too expensive to buy with Bill Gates' salary. (Believe it)");
		key.setItemWeight((float) 93.1);
		key.setIsQuestItem(true);
		playerItems.add(sword);
		playerItems.add(helmet);
		playerItems.add(key);
		Inventory inventory = new Inventory();
		inventory.set_items(playerItems);
		player.set_inventory(inventory);
	}
	
	/*
	 * Item Details
	 * Note: handle_command checks that the number is valid and such
	 */
	@Test
	public void testItemDetailsLowEndOf0(){
		game.item_details(0);
		
		Item item = sword;
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), game.get_dialog().get(0));
	}
	
	@Test
	public void testItemDetailsMidIndexOf1(){
		game.item_details(1);
		
		Item item = helmet;
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), game.get_dialog().get(0));
	}
	
	@Test
	public void testItemDetailsHighEndOf2(){
		game.item_details(2);
		
		Item item = key;
		
		assertEquals(1, game.get_dialog().size());
		assertEquals(item.getName() + ": " + item.getLongDescription() + ";Weight: " + item.getItemWeight() + 
				";Quest item: " + String.valueOf(item.getIsQuestItem()), game.get_dialog().get(0));
	}
}
