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
	private Random random;
	private boolean done;
	
	public CombatSituation(Game game){
		characters = new ArrayList<Character>();
		characters.add(game.get_player());
		random = new Random(System.nanoTime());
		try{
			ArrayList<Integer> enemyIDs = game.get_map().getMapTileByID(game.get_player().get_location()).getEnemyIDs();
			int enemyChoice = random.nextInt(enemyIDs.size());
			int enemyID = enemyIDs.get(enemyChoice);
			
		}catch(Exception e){
			characters.add(createEnemy());
		}
		game.add_dialog("A " + characters.get(1).get_name() + " appeared out of nowhere!");
		done = false;
		
		// TODO: Remove this
		// This is Temporary Stuff due to current stats setup
		characters.get(0).set_attack(40);
		characters.get(0).set_defense(5);
	}
	
	public ArrayList<Character> getCharacters(){
		return characters;
	}
	
	public Enemy createEnemy(){
		Enemy enemy = new Enemy();
		enemy.set_attack(15);
		enemy.set_defense(25);
		enemy.set_hit_points(100);
		enemy.set_level(1);
		enemy.set_name("Goblin");
		return enemy;
	}
	
	public void doRound(Game game){
		// Player attacks Enemy
		playerAttackEnemy(game);
		
		// Check if Enemy has died
		if(characters.get(1).get_hit_points() <= 0){
			// Do Player Won Battle
			doPlayerWon(game);
		}else{
			// Enemy attacks Player
			enemyAttackPlayer(game);
			
			// Check if Player has died
			if(characters.get(0).get_hit_points() <= 0){
				// Do Player Died
				doPlayerDied(game);
			}
		}
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
			Player player = (Player) chr;
			if(player.get_helm() != null){
				attack += player.get_helm().get_attack_bonus();
			}
			if(player.get_braces() != null){
				attack += player.get_braces().get_attack_bonus();
			}
			if(player.get_chest() != null){
				attack += player.get_chest().get_attack_bonus();
			}
			if(player.get_legs() != null){
				attack += player.get_legs().get_attack_bonus();
			}
			if(player.get_boots() != null){
				attack += player.get_boots().get_attack_bonus();
			}
			if(player.get_l_hand() != null){
				attack += player.get_l_hand().get_attack_bonus();
			}
			if(player.get_r_hand() != null){
				attack += player.get_r_hand().get_attack_bonus();
			}
		}
		int range = (int) (attack*0.2);
		attack = (int) (attack + (random.nextInt(range+1) - range/2.0));
		return attack;
	}
	
	public int calculateDefense(int character){
		Character chr = characters.get(character);
		int defense = chr.get_defense();
		if(chr instanceof Player){
			Player player = (Player) chr;
			if(player.get_helm() != null){
				defense += player.get_helm().get_defense_bonus();
			}
			if(player.get_braces() != null){
				defense += player.get_braces().get_defense_bonus();
			}
			if(player.get_chest() != null){
				defense += player.get_chest().get_defense_bonus();
			}
			if(player.get_legs() != null){
				defense += player.get_legs().get_defense_bonus();
			}
			if(player.get_boots() != null){
				defense += player.get_boots().get_defense_bonus();
			}
			if(player.get_l_hand() != null){
				defense += player.get_l_hand().get_defense_bonus();
			}
			if(player.get_r_hand() != null){
				defense += player.get_r_hand().get_defense_bonus();
			}
		}
		return defense;
	}
	
	public boolean isDone(){
		return done;
	}
	
	public void doPlayerWon(Game game){
		done = true;
		game.add_dialog("You killed the " + characters.get(1).get_name() + "!");
		game.add_dialog("You have been awarded 10 experience!");
		((Player) characters.get(0)).set_experience(((Player) characters.get(0)).get_experience() + 10);
	}
	
	public void doPlayerDied(Game game){
		done = true;
		game.add_dialog("You have died!");
		game.add_dialog("Restart if you think you can do better!");
	}
}
