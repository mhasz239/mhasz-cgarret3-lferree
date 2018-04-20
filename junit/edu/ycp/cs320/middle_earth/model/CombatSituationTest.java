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
		player.set_hit_points(100);
		player.set_attack(40);
		player.set_defense(5);
		
		// Add Player to Game
		ArrayList<Character> characters = new ArrayList<Character>();
		characters.add(player);
		game.set_characters(characters);
		
		// Create CombatSituation
		battle = new CombatSituation(game);
	}
	
	@Test
	public void testConstructor(){
		// Check that 2 Characters are involved in the CombatSituation
		assertEquals(2, battle.getCharacters().size());
		
		// Check that Player is present
		assertEquals(player, battle.getCharacters().get(0));
		
		// Check that dialog was updated appropriately
		assertEquals(1, game.get_dialog().size());
		assertEquals("A Goblin appeared out of nowhere!", game.get_dialog().get(0));
		
		// Check that done is false
		assertEquals(false, battle.isDone());
	}
	
	@Test
	public void testCreateEnemy(){
		// Create the Enemy
		Enemy enemy = battle.createEnemy();
		
		// Check that Stats and Stuff are correct (based on current setup)
		assertEquals(15, enemy.get_attack());
		assertEquals(25, enemy.get_defense());
		assertEquals(100, enemy.get_hit_points());
		assertEquals(1, enemy.get_level());
		assertEquals("Goblin", enemy.get_name());
	}
	
	@Test
	public void testCalculateAttackEnemy(){
		int mins = 0;
		int minmids = 0;
		int mids = 0;
		int midmaxs = 0;
		int maxs = 0;
		
		for(int i = 0; i < 300; i++){
			int result = battle.calculateAttack(1);
			if(result == 13){
				mins++;
			}else if(result == 14){
				minmids++;
			}else if(result == 15){
				mids++;
			}else if(result == 16){
				midmaxs++;
			}else if(result == 17){
				maxs++;
			}else{
				// Should never get here!
				assertEquals(1, 0);
			}
		}
		
		System.out.println("CalculateAttack for Enemy Distribution");
		System.out.println("Min(13):  " + mins);
		System.out.println("Mmid(14): " + minmids);
		System.out.println("Mid(15):  " + mids);
		System.out.println("Mmax(16): " + midmaxs);
		System.out.println("Max(17):  " + maxs);
	}
	
	@Test
	public void testCalculateAttackPlayerNoArmor(){
		// Min and Max based off of 40 as base player attack
		int min = 36;
		int max = 44;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerHelmet(){
		// Create Helmet
		Item helmet = new Item();
		helmet.set_ItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.set_attack_bonus(10);
		
		// Give the Player the Helmet
		player.set_helm(helmet);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerBraces(){
		// Create Braces
		Item braces = new Item();
		braces.set_ItemType(ItemType.BRACES);
		braces.setName("Generic Braces");
		braces.set_attack_bonus(10);
		
		// Give the Player the Braces
		player.set_braces(braces);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerChest(){
		// Create Chest
		Item chest = new Item();
		chest.set_ItemType(ItemType.CHEST);
		chest.setName("Generic Chest");
		chest.set_attack_bonus(10);
		
		// Give the Player the Chest
		player.set_chest(chest);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerLegs(){
		// Create Legs
		Item legs = new Item();
		legs.set_ItemType(ItemType.LEGS);
		legs.setName("Generic Legs");
		legs.set_attack_bonus(10);
		
		// Give the Player the Legs
		player.set_legs(legs);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerBoots(){
		// Create Boots
		Item boots = new Item();
		boots.set_ItemType(ItemType.BOOTS);
		boots.setName("Generic Boots");
		boots.set_attack_bonus(10);
		
		// Give the Player the Boots
		player.set_boots(boots);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerLHand(){
		// Create Shield
		Item shield = new Item();
		shield.set_ItemType(ItemType.HAND);
		shield.setName("Generic Shield");
		shield.set_attack_bonus(10);
		
		// Give the Player the Shield
		player.set_l_hand(shield);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerRHand(){
		// Create Sword
		Item sword = new Item();
		sword.set_ItemType(ItemType.HAND);
		sword.setName("Generic Sword");
		sword.set_attack_bonus(10);
		
		// Give the Player the Sword
		player.set_r_hand(sword);
		
		// These based off of 50 as base attack (40 + 10)
		int min = 45;
		int max = 55;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateAttackPlayerFullArmorAndHands(){
		// Create Helmet
		Item helmet = new Item();
		helmet.set_ItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.set_attack_bonus(10);
		
		// Give the Player the Helmet
		player.set_helm(helmet);
		
		// Create Braces
		Item braces = new Item();
		braces.set_ItemType(ItemType.BRACES);
		braces.setName("Generic Braces");
		braces.set_attack_bonus(10);
		
		// Give the Player the Braces
		player.set_braces(braces);
		
		// Create Chest
		Item chest = new Item();
		chest.set_ItemType(ItemType.CHEST);
		chest.setName("Generic Chest");
		chest.set_attack_bonus(10);
		
		// Give the Player the Chest
		player.set_chest(chest);
		
		// Create Legs
		Item legs = new Item();
		legs.set_ItemType(ItemType.LEGS);
		legs.setName("Generic Legs");
		legs.set_attack_bonus(10);
		
		// Give the Player the Legs
		player.set_legs(legs);
		
		// Create Boots
		Item boots = new Item();
		boots.set_ItemType(ItemType.BOOTS);
		boots.setName("Generic Boots");
		boots.set_attack_bonus(10);
		
		// Give the Player the Boots
		player.set_boots(boots);
		
		// Create Shield
		Item shield = new Item();
		shield.set_ItemType(ItemType.HAND);
		shield.setName("Generic Shield");
		shield.set_attack_bonus(10);
		
		// Give the Player the Shield
		player.set_l_hand(shield);

		// Create Sword
		Item sword = new Item();
		sword.set_ItemType(ItemType.HAND);
		sword.setName("Generic Sword");
		sword.set_attack_bonus(10);
		
		// Give the Player the Sword
		player.set_r_hand(sword);
		
		// These based off of 110 as base attack (40 + 10*7 pieces)
		int min = 99;
		int max = 121;
		
		// Get attack calculated and ensure it falls inside range
		int attack = battle.calculateAttack(0);
		assertTrue(attack >= min && attack <= max);
	}
	
	@Test
	public void testCalculateDefenseEnemy(){
		// Default Enemy's defense is 25
		assertEquals(25, battle.calculateDefense(1));
	}
	
	@Test
	public void testCalculateDefensePlayerNoArmor(){
		// Default Player's defense is 5
		assertEquals(5, battle.calculateDefense(0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithHelmet(){
		// Create Helmet
		Item helmet = new Item();
		helmet.set_ItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.set_defense_bonus(10);
		
		// Give Helmet to Player
		player.set_helm(helmet);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithBraces(){
		// Create Braces
		Item braces = new Item();
		braces.set_ItemType(ItemType.BRACES);
		braces.setName("Generic Braces");
		braces.set_defense_bonus(10);
		
		// Give Braces to Player
		player.set_braces(braces);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithChest(){
		// Create Chest
		Item chest = new Item();
		chest.set_ItemType(ItemType.CHEST);
		chest.setName("Generic Chest");
		chest.set_defense_bonus(10);
		
		// Give Chest to Player
		player.set_chest(chest);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithLegs(){
		// Create Legs
		Item legs = new Item();
		legs.set_ItemType(ItemType.LEGS);
		legs.setName("Generic Legs");
		legs.set_defense_bonus(10);
		
		// Give Legs to Player
		player.set_legs(legs);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithBoots(){
		// Create Boots
		Item boots = new Item();
		boots.set_ItemType(ItemType.BOOTS);
		boots.setName("Generic Boots");
		boots.set_defense_bonus(10);
		
		// Give Boots to Player
		player.set_boots(boots);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithShield(){
		// Create Shield
		Item shield = new Item();
		shield.set_ItemType(ItemType.HAND);
		shield.setName("Generic Shield");
		shield.set_defense_bonus(10);
		
		// Give Shield to Player
		player.set_l_hand(shield);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(0));
	}
	
	@Test
	public void testCalculateDefensePlayerWithSword(){
		// Create Sword
		Item sword = new Item();
		sword.set_ItemType(ItemType.HAND);
		sword.setName("Generic Sword");
		sword.set_defense_bonus(10);
		
		// Give Sword to Player
		player.set_r_hand(sword);
		
		// Check that defense is 15
		assertEquals(15, battle.calculateDefense(0));
	}
	
	@Test
	public void testCalculateDefensePlayerFullArmor(){
		// Create Helmet
		Item helmet = new Item();
		helmet.set_ItemType(ItemType.HELM);
		helmet.setName("Generic Helmet");
		helmet.set_defense_bonus(10);
		
		// Give Helmet to Player
		player.set_helm(helmet);
		
		// Create Braces
		Item braces = new Item();
		braces.set_ItemType(ItemType.BRACES);
		braces.setName("Generic Braces");
		braces.set_defense_bonus(10);
		
		// Give Braces to Player
		player.set_braces(braces);
		
		// Create Chest
		Item chest = new Item();
		chest.set_ItemType(ItemType.CHEST);
		chest.setName("Generic Chest");
		chest.set_defense_bonus(10);
		
		// Give Chest to Player
		player.set_chest(chest);
		
		// Create Legs
		Item legs = new Item();
		legs.set_ItemType(ItemType.LEGS);
		legs.setName("Generic Legs");
		legs.set_defense_bonus(10);
		
		// Give Legs to Player
		player.set_legs(legs);
		
		// Create Boots
		Item boots = new Item();
		boots.set_ItemType(ItemType.BOOTS);
		boots.setName("Generic Boots");
		boots.set_defense_bonus(10);
		
		// Give Boots to Player
		player.set_boots(boots);
		
		// Create Shield
		Item shield = new Item();
		shield.set_ItemType(ItemType.HAND);
		shield.setName("Generic Shield");
		shield.set_defense_bonus(10);
		
		// Give Shield to Player
		player.set_l_hand(shield);

		// Create Sword
		Item sword = new Item();
		sword.set_ItemType(ItemType.HAND);
		sword.setName("Generic Sword");
		sword.set_defense_bonus(10);
		
		// Give Helmet to Player
		player.set_r_hand(sword);
		
		// Check that defense is 75
		assertEquals(75, battle.calculateDefense(0));
	}
	
	@Test
	public void testDoPlayerWon(){
		// Check that Player has 0 experience (to confirm 10 was added later)
		assertEquals(0, player.get_experience());
		
		// Run doPlayerWon
		battle.doPlayerWon(game);
		
		// Check that dialog was added to appropriately
		assertEquals(3, game.get_dialog().size());
		assertEquals("You killed the Goblin!", game.get_dialog().get(1));
		assertEquals("You have been awarded 10 experience!", game.get_dialog().get(2));
		
		// Check that Player got 10 experience
		assertEquals(10, player.get_experience());
		
		// Check that Battle is Done
		assertEquals(true, battle.isDone());
	}
	
	@Test
	public void testDoPlayerDied(){
		// Run doPlayerDied
		battle.doPlayerDied(game);
		
		// Check that dialog was added to appropriately
		assertEquals(3, game.get_dialog().size());
		assertEquals("You have died!", game.get_dialog().get(1));
		assertEquals("Restart if you think you can do better!", game.get_dialog().get(2));
		
		// Check that Battle is Done
		assertEquals(true, battle.isDone());
	}
	
	@Test
	public void testCalculateDamageEnemyToPlayer(){
		// Enemy can do 13-17, Player has 5 defense
		int min = 8;
		int max = 12;
		
		// Get damage calculated
		int damage = battle.calculateDamage(1, 0);
		
		// Check that damage is calculated properly
		assertTrue(damage >= min && damage <= max);
	}
	
	@Test
	public void testCalculateDamagePlayerToEnemy(){
		// Player can do 36-44, Enemy has 25 defense
		int min = 11;
		int max = 19;
		
		// Get damage calculated
		int damage = battle.calculateDamage(0, 1);
		
		// Check that damage is calculated properly
		assertTrue(damage >= min && damage <= max);
	}
	
	@Test
	public void testCalculateDamageOfLessThan0(){
		// Set Player defense to 20
		player.set_defense(20);
		
		// Enemy can do 13-17, so 20 defense = 0 damage
		assertEquals(0, battle.calculateDamage(1, 0));
	}
}
