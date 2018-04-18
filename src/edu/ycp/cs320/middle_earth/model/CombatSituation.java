package edu.ycp.cs320.middle_earth.model;

import java.util.ArrayList;
import java.util.Random;

import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.model.Characters.Player;

public class CombatSituation{
	// Assuming for now that character 0 is Player, character 1 is Enemy
	private ArrayList<Character> characters;
	
	public CombatSituation(Game game){
		characters = new ArrayList<Character>();
		characters.add(game.get_player());
		Random rand = new Random(System.currentTimeMillis());
		try{
			ArrayList<Integer> enemyIDs = game.get_map().getMapTileByID(game.get_player().get_location()).getEnemyIDs();
			int enemyChoice = rand.nextInt(enemyIDs.size());
			int enemyID = enemyIDs.get(enemyChoice);
			
		}catch(Exception e){
			characters.add(createEnemy());
		}
		game.add_dialog("A " + characters.get(1).get_name() + " appeared out of nowhere!");
	}
	
	public ArrayList<Character> getCharacters(){
		return characters;
	}
	
	public Enemy createEnemy(){
		Enemy enemy = new Enemy();
		enemy.set_attack(10);
		enemy.set_defense(25);
		enemy.set_hit_points(200);
		enemy.set_level(1);
		enemy.set_name("Goblin");
		return enemy;
	}
	
	public void doRound(Game game){
		playerAttackEnemy(game);
		enemyAttackPlayer(game);
	}
	
	public void playerAttackEnemy(Game game){
		Character enemy = characters.get(1);
		int enemyHP = enemy.get_hit_points();
		int damage = calculateDamage(0, 1);
		enemy.set_hit_points(enemyHP - damage);
		game.add_dialog("You attacked " + enemy.get_name() + " for " + damage + " damage.");
	}
	
	public void enemyAttackPlayer(Game game){
		Character player = characters.get(0);
		int playerHP = player.get_hit_points();
		int damage = calculateDamage(1, 0);
		player.set_hit_points(playerHP - damage);
		game.add_dialog(characters.get(1).get_name() + " attacked you for " + damage + " damage.");
		game.add_dialog("You have " + player.get_hit_points() + " HP left.");
	}
	
	public int calculateDamage(int attacker, int defender){
		int attackDamage = calculateAttack(attacker);
		int defense = calculateDefense(defender);
		int damage = attackDamage - defense;
		if(damage < 0){
			damage = 0;
		}
		return damage;
	}
	
	// Encounter chance
	// MapTile Level (in title)
	// Experience
	// Turns
	// Victory + Defeat Calls
	// Escape/Flee (chance on enemy)
	// Only 1 Enemy for now (grab possibilities from MapTile)
	
	public int calculateAttack(int character){
		Character chr = characters.get(character);
		int attack = chr.get_attack();
		if(chr instanceof Player){
			attack += ((Player) chr).get_helm().get_attack_bonus();
			attack += ((Player) chr).get_braces().get_attack_bonus();
			attack += ((Player) chr).get_chest().get_attack_bonus();
			attack += ((Player) chr).get_legs().get_attack_bonus();
			attack += ((Player) chr).get_boots().get_attack_bonus();
			attack += ((Player) chr).get_l_hand().get_attack_bonus();
			attack += ((Player) chr).get_r_hand().get_attack_bonus();
		}
		Random rand = new Random(System.currentTimeMillis());
		int range = (int) (attack*0.2);
		attack = (int) (attack + (rand.nextInt(range+1) - range/2.0));
		return attack;
	}
	
	public int calculateDefense(int character){
		Character chr = characters.get(character);
		int defense = chr.get_defense();
		if(chr instanceof Player){
			defense += ((Player) chr).get_helm().get_defense_bonus();
			defense += ((Player) chr).get_braces().get_defense_bonus();
			defense += ((Player) chr).get_chest().get_defense_bonus();
			defense += ((Player) chr).get_legs().get_defense_bonus();
			defense += ((Player) chr).get_boots().get_defense_bonus();
			defense += ((Player) chr).get_l_hand().get_defense_bonus();
			defense += ((Player) chr).get_r_hand().get_defense_bonus();
		}
		return defense;
	}
}
