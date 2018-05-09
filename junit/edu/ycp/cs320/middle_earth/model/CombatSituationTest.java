package edu.ycp.cs320.middle_earth.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

public class CombatSituationTest{
	private Game game;
	private Player player;
	private CombatSituation battle;
	
	@Before
	public void setup(){
		// Create Game
		game = new Game();
		
		// Create Player
		player = new Player();
		player.setname("Me");
		player.sethit_points(100);
		player.setattack(40);
		player.setdefense(5);
		
		// Add Player to Game
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.setcharacters(characters);
		
		// Create CombatSituation
		battle = new CombatSituation(game, 1, 0);
	}
	
	@Test
	public void testConstructor(){
		// Check that 2 Characters are involved in the CombatSituation
		assertEquals(2, battle.getCharacterIDs().size());
		
		// Check that Player is present
		assertEquals(player, game.getcharacters().get(battle.getCharacterIDs().get(0)));
		
		// Check that dialog was updated appropriately
		assertEquals(1, game.getdialog().size());
		assertEquals("Me is staring into the eyes of a " + game.getcharacters().get(battle.getCharacterIDs().get(1)).getrace(), 
				game.getdialog().get(0));
    
		// Check that done is false
		assertEquals(false, battle.isDone());
	}
	
	@Test
	public void testCreateEnemy(){
		// Create races list
		ArrayList<String> races = new ArrayList<String>();
		races.add("Goblin");
		
		// Create the Enemy on mapTile (playerLocation) 4
		Enemy enemy = battle.createEnemy(races, 4);
		System.out.println(enemy.getattack() + "  " + enemy.getdefense() +  " " + enemy.gethit_points() + " " + enemy.getlevel() + " " + enemy.getname() + " " + enemy.getrace());
		// Check that Stats and Stuff are correct (based on current setup)
		assertEquals(16, enemy.getattack());
		assertEquals(0, enemy.getdefense());
		assertEquals(50, enemy.gethit_points());
		assertEquals(1, enemy.getlevel());
		assertTrue(enemy.getname() != null);
		assertEquals("Goblin", enemy.getrace());
	}
	
	@Test
	public void testCalculateAttackEnemy(){
		int mins = 0;
		int mids = 0;
		int maxs = 0;
		
		for(int i = 0; i < 300; i++){
			int result = battle.calculateAttack(game, 1);
			if(result == 14 || result == 15){
				mins++;
			}else if(result == 16){
				mids++;
			}else if(result == 18 || result == 17){
				maxs++;
			}else{
				// Should never get here!
				assertEquals(1, 0);
			}
		}
		
		System.out.println("CalculateAttack for Enemy Distribution");
		System.out.println("Min's(14/15):  " + mins);
		System.out.println("Mid(16):  " + mids);
		System.out.println("Max's(17/18):  " + maxs);
	}
	
	@Test
	public void testCalculateAttackPlayerNoArmor(){
		// Min and Max based off of 40 as base player attack
		int min = 36;
		int max = 44;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerHelmet(){
		// Create Helmet
		Item helmet = new Item();
		helmet.setItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.setattack_bonus(10);
		
		// Give the Player the Helmet
		player.sethelm(helmet);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerBraces(){
		// Create Braces
		Item braces = new Item();
		braces.setItemType(ItemType.BRACES);
		braces.setName("Generic Braces");
		braces.setattack_bonus(10);
		
		// Give the Player the Braces
		player.setbraces(braces);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerChest(){
		// Create Chest
		Item chest = new Item();
		chest.setItemType(ItemType.CHEST);
		chest.setName("Generic Chest");
		chest.setattack_bonus(10);
		
		// Give the Player the Chest
		player.setchest(chest);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerLegs(){
		// Create Legs
		Item legs = new Item();
		legs.setItemType(ItemType.LEGS);
		legs.setName("Generic Legs");
		legs.setattack_bonus(10);
		
		// Give the Player the Legs
		player.setlegs(legs);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerBoots(){
		// Create Boots
		Item boots = new Item();
		boots.setItemType(ItemType.BOOTS);
		boots.setName("Generic Boots");
		boots.setattack_bonus(10);
		
		// Give the Player the Boots
		player.setboots(boots);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerLHand(){
		// Create Shield
		Item shield = new Item();
		shield.setItemType(ItemType.L_HAND);
		shield.setName("Generic Shield");
		shield.setattack_bonus(10);
		
		// Give the Player the Shield
		player.setl_hand(shield);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerRHand(){
		// Create Sword
		Item sword = new Item();
		sword.setItemType(ItemType.R_HAND);
		sword.setName("Generic Sword");
		sword.setattack_bonus(10);
		
		// Give the Player the Sword
		player.setr_hand(sword);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerFullArmorAndHands(){
		// Create Helmet
		Item helmet = new Item();
		helmet.setItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.setattack_bonus(10);
		
		// Give the Player the Helmet
		player.sethelm(helmet);
		
		// Create Braces
		Item braces = new Item();
		braces.setItemType(ItemType.BRACES);
		braces.setName("Generic Braces");
		braces.setattack_bonus(10);
		
		// Give the Player the Braces
		player.setbraces(braces);
		
		// Create Chest
		Item chest = new Item();
		chest.setItemType(ItemType.CHEST);
		chest.setName("Generic Chest");
		chest.setattack_bonus(10);
		
		// Give the Player the Chest
		player.setchest(chest);
		
		// Create Legs
		Item legs = new Item();
		legs.setItemType(ItemType.LEGS);
		legs.setName("Generic Legs");
		legs.setattack_bonus(10);
		
		// Give the Player the Legs
		player.setlegs(legs);
		
		// Create Boots
		Item boots = new Item();
		boots.setItemType(ItemType.BOOTS);
		boots.setName("Generic Boots");
		boots.setattack_bonus(10);
		
		// Give the Player the Boots
		player.setboots(boots);
		
		// Create Shield
		Item shield = new Item();
		shield.setItemType(ItemType.L_HAND);
		shield.setName("Generic Shield");
		shield.setattack_bonus(10);
		
		// Give the Player the Shield
		player.setl_hand(shield);

		// Create Sword
		Item sword = new Item();
		sword.setItemType(ItemType.R_HAND);
		sword.setName("Generic Sword");
		sword.setattack_bonus(10);
		
		// Give the Player the Sword
		player.setr_hand(sword);
		
		// These based off of 110 as base attack (40 + 10*7 pieces)
		int min = 99;
		int max = 121;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(game, 0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateDefenseEnemy(){
		// Default Enemy's defense is 0
		assertEquals(0, battle.calculateDefense(game, 1));
	}
	
	@Test
	public void testCalculateDefensePlayerNoArmor(){
		// Default Player's defense is 5
		assertEquals(5, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithHelmet(){
		// Create Helmet
		Item helmet = new Item();
		helmet.setItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.setdefense_bonus(10);
		
		// Give Helmet to Player
		player.sethelm(helmet);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithBraces(){
		// Create Braces
		Item braces = new Item();
		braces.setItemType(ItemType.BRACES);
		braces.setName("Generic Braces");
		braces.setdefense_bonus(10);
		
		// Give Braces to Player
		player.setbraces(braces);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithChest(){
		// Create Chest
		Item chest = new Item();
		chest.setItemType(ItemType.CHEST);
		chest.setName("Generic Chest");
		chest.setdefense_bonus(10);
		
		// Give Chest to Player
		player.setchest(chest);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithLegs(){
		// Create Legs
		Item legs = new Item();
		legs.setItemType(ItemType.LEGS);
		legs.setName("Generic Legs");
		legs.setdefense_bonus(10);
		
		// Give Legs to Player
		player.setlegs(legs);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithBoots(){
		// Create Boots
		Item boots = new Item();
		boots.setItemType(ItemType.BOOTS);
		boots.setName("Generic Boots");
		boots.setdefense_bonus(10);
		
		// Give Boots to Player
		player.setboots(boots);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithShield(){
		// Create Shield
		Item shield = new Item();
		shield.setItemType(ItemType.L_HAND);
		shield.setName("Generic Shield");
		shield.setdefense_bonus(10);
		
		// Give Shield to Player
		player.setl_hand(shield);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithSword(){
		// Create Sword
		Item sword = new Item();
		sword.setItemType(ItemType.R_HAND);
		sword.setName("Generic Sword");
		sword.setdefense_bonus(10);
		
		// Give Sword to Player
		player.setr_hand(sword);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testCalculateDefensePlayerFullArmor(){
		// Create Helmet
		Item helmet = new Item();
		helmet.setItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.setdefense_bonus(10);
		
		// Give Helmet to Player
		player.sethelm(helmet);
		
		// Create Braces
		Item braces = new Item();
		braces.setItemType(ItemType.BRACES);
		braces.setName("Generic Braces");
		braces.setdefense_bonus(10);
		
		// Give Braces to Player
		player.setbraces(braces);
		
		// Create Chest
		Item chest = new Item();
		chest.setItemType(ItemType.CHEST);
		chest.setName("Generic Chest");
		chest.setdefense_bonus(10);
		
		// Give Chest to Player
		player.setchest(chest);
		
		// Create Legs
		Item legs = new Item();
		legs.setItemType(ItemType.LEGS);
		legs.setName("Generic Legs");
		legs.setdefense_bonus(10);
		
		// Give Legs to Player
		player.setlegs(legs);
		
		// Create Boots
		Item boots = new Item();
		boots.setItemType(ItemType.BOOTS);
		boots.setName("Generic Boots");
		boots.setdefense_bonus(10);
		
		// Give Boots to Player
		player.setboots(boots);
		
		// Create Shield
		Item shield = new Item();
		shield.setItemType(ItemType.L_HAND);
		shield.setName("Generic Shield");
		shield.setdefense_bonus(10);
		
		// Give Shield to Player
		player.setl_hand(shield);

		// Create Sword
		Item sword = new Item();
		sword.setItemType(ItemType.R_HAND);
		sword.setName("Generic Sword");
		sword.setdefense_bonus(10);
		
		// Give Helmet to Player
		player.setr_hand(sword);
		
		// Check that defense is 75
		assertEquals(75, battle.calculateDefense(game, 0));
	}
	
	@Test
	public void testDoPlayerWon(){
		// Set Bob's HP to 0 (to make sure combat will be done at the end)
		game.getcharacters().get(1).sethit_points(0);
		
		// Check that Player has 0 experience (to confirm 10 was added later)
		assertEquals(0, player.getexperience());
		
		// Run doPlayerWon
		battle.doPlayerWon(game, 0, 1);
		
		// Check that dialog was added to appropriately
		assertEquals(5, game.getdialog().size());
		assertEquals("You killed " + game.getcharacters().get(battle.getCharacterIDs().get(1)).getname() + "!", 
				game.getdialog().get(1));
		assertEquals("You have been awarded 300 experience!", game.getdialog().get(2));
		// Skip getting items line
		assertEquals("You have killed everyone! (in this combat situation here)", game.getdialog().get(4));
		
		// Check that Player got 300 experience (levels work it out to 150)
		assertEquals(150, player.getexperience());
		
		// Check that Battle is Done
		assertEquals(true, battle.isDone());
	}
	
	@Test
	public void testDoPlayerDied(){
		// Run doPlayerDied
		battle.doPlayerDied(game, 0);
		
		// Check that dialog was added to appropriately
		assertEquals(3, game.getdialog().size());
		assertEquals("You have died!", game.getdialog().get(1));
		assertEquals("Restart if you think you can do better!", game.getdialog().get(2));
		
		// Check that Battle is Done
		assertEquals(true, battle.isDone());
	}
	
	@Test
	public void testCalculateDamageEnemyToPlayer(){
		// Enemy can do 10-12, Player has 5 defense
		int min = 10;
		int max = 12;
		
		// Get damage calculated
		int damage = battle.calculateDamage(game, 1, 0);
		
		// Check that damage is calculated properly
		assertTrue(damage >= min && damage <= max);
	}
	
	@Test
	public void testCalculateDamagePlayerToEnemy(){
		// Player can do 36-44, Enemy has 0 defense
		int min = 36;
		int max = 44;
		
		// Get damage calculated
		int damage = battle.calculateDamage(game, 0, 1);
		
		// Check that damage is calculated properly
		assertTrue(damage >= min && damage <= max);
	}
	
	@Test
	public void testCalculateDamageOfLessThan0(){
		// Set Player defense to 20
		player.setdefense(20);
		
		// Enemy can do 13-17, so 20 defense = 0 damage
		assertEquals(0, battle.calculateDamage(game, 1, 0));
	}
}
