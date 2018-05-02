package edu.ycp.cs320.middle_earth.model;

import java.util.ArrayList;
import java.util.Random;

import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;

public class CombatSituation{
	private ArrayList<Integer> characterIDs;
	private int currentIDsIndex;
	private Random random;
	private boolean done;
	
	/**
	 * @param game The Game this is happening in
	 * @param enemies How many Enemies in this CombatSituation
	 * @param players Any Players involved in the Combat.
	 */
	public CombatSituation(Game game, int enemies, int ... players){
		characterIDs = new ArrayList<Integer>();
		String combatString = "";
		for(int i = 0; i < players.length; i++){
			characterIDs.add(players[i]);
			if(i == 0){
				combatString += game.get_characters().get(i).get_name();
			}else if(i != players.length - 1){
				combatString += ", " + game.get_characters().get(i).get_name();
			}else{
				combatString += " and " + game.get_characters().get(i).get_name() + " have entered combat!";
			}
		}
		random = new Random(System.nanoTime());
		for(int i = 0; i < enemies; i++){
			Enemy enemy = null;
			try{
				ArrayList<Integer> enemyIDs = game.get_map().getMapTileByID(game.get_player().get_location()).getEnemyIDs();
				int enemyChoice = random.nextInt(enemyIDs.size());
				int enemyID = enemyIDs.get(enemyChoice);
				// TODO: Ask Chris about either get Character or Enemy by ID (either would work for me)
				//DatabaseProvider.getInstance().getEnemyByID(enemyID);
				// Enemy will already have name, race, gender?
				throw new IllegalArgumentException("This isn't setup yet!");
			}catch(Exception e){
				enemy = createEnemy(game);
			}
			game.get_characters().add(enemy);
			characterIDs.add(game.get_characters().size() - 1);
			combatString += " is staring into the eyes of a " + enemy.get_race();
		}
		game.add_dialog(combatString);
		done = false;
		currentIDsIndex = 0;
	}
	
	public ArrayList<Integer> getCharacterIDs(){
		return characterIDs;
	}
	
	public int getCurrentIDsIndex(){
		return currentIDsIndex;
	}
	
	// TODO: Change this to randomize enemy
	// Set level, hp, mp, att, def, spAtt, spDef, coins, equipment, inventory
	// inventory = call .getItemByID
	// wood, steel, dwarven, elven, legendary (item types, increasing rarity/goodness)
	public Enemy createEnemy(Game game){
		Enemy enemy = new Enemy();
		enemy.set_attack(15);
		enemy.set_defense(0);
		enemy.set_hit_points(100);
		enemy.set_level(1);
		enemy.set_name("Goblin");
		return enemy;
	}
	
	public void playerAttackEnemy(Game game, int playerIndex, String target){
		if(characterIDs.get(characterIDs.get(currentIDsIndex)) == playerIndex){
			Character enemy = null;
			int enemyIndex = -1;
			for(int characterIndex: characterIDs){
				Character chara = game.get_characters().get(characterIndex);
				if(characterIndex != playerIndex && (chara.get_name().equalsIgnoreCase(target) || 
						chara.get_race().equalsIgnoreCase("target"))){
					enemy = chara;
					enemyIndex = characterIndex;
				}
			}
			if(enemy == null){
				game.add_dialog("No one by the name/race of " + target + " was found in combat with you!");
			}else{
				int enemyHP = enemy.get_hit_points();
				int damage = calculateDamage(game, playerIndex, enemyIndex);
				enemy.set_hit_points(enemyHP - damage);
				game.add_dialog("You attacked " + enemy.get_name() + " for " + damage + " damage.");
				
				// Advance to next turn and check if it's an enemy to do their turn.
				currentIDsIndex++;
				if(!(game.get_characters().get(characterIDs.get(currentIDsIndex)) instanceof Player)){
					enemyAttackPlayer(game);
				}
			}
		}else{
			game.add_dialog("It's not your turn!");
		}
	}
	
	public void enemyAttackPlayer(Game game){
		// Get number of Players
		ArrayList<Integer> playerIndices = new ArrayList<Integer>();
		for(int characterIndex: characterIDs){
			if(game.get_characters().get(characterIndex) instanceof Player){
				playerIndices.add(characterIndex);
			}
		}
		
		// Determine target Player
		int playerNum = random.nextInt(playerIndices.size());
		int playerIndex = playerIndices.get(playerNum);
		
		// Get player
		Character player = game.get_characters().get(playerIndex);
		
		// Get Enemy
		int enemyIndex = characterIDs.get(currentIDsIndex);
		Character enemy = game.get_characters().get(enemyIndex);
		
		// Do damage to player
		int playerHP = player.get_hit_points();
		int damage = calculateDamage(game, playerIndex, characterIDs.get(currentIDsIndex));
		player.set_hit_points(playerHP - damage);
		game.add_dialog(enemy.get_name() + " attacked you for " + damage + " damage.");
		game.add_dialog("You have " + player.get_hit_points() + " HP left.");
		
		// Advance to next turn and check if it's an enemy to do their turn.
		currentIDsIndex++;
		if(!(game.get_characters().get(characterIDs.get(currentIDsIndex)) instanceof Player)){
			enemyAttackPlayer(game);
		}
	}
	
	public int calculateDamage(Game game, int attacker, int defender){
		int attackDamage = calculateAttack(game, attacker);
		int defense = calculateDefense(game, defender);
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
	
	public int calculateAttack(Game game, int character){
		Character chr = game.get_characters().get(character);
		int attack = chr.get_attack();
		if(chr.get_helm() != null){
			attack += chr.get_helm().get_attack_bonus();
		}
		if(chr.get_braces() != null){
			attack += chr.get_braces().get_attack_bonus();
		}
		if(chr.get_chest() != null){
			attack += chr.get_chest().get_attack_bonus();
		}
		if(chr.get_legs() != null){
			attack += chr.get_legs().get_attack_bonus();
		}
		if(chr.get_boots() != null){
			attack += chr.get_boots().get_attack_bonus();
		}
		if(chr.get_l_hand() != null){
			attack += chr.get_l_hand().get_attack_bonus();
		}
		if(chr.get_r_hand() != null){
			attack += chr.get_r_hand().get_attack_bonus();
		}
		int range = (int) (attack*0.2);
		attack = (int) (attack + (random.nextInt(range+1) - range/2.0));
		return attack;
	}
	
	public int calculateDefense(Game game, int character){
		Character chr = game.get_characters().get(character);
		int defense = chr.get_defense();
		if(chr.get_helm() != null){
			defense += chr.get_helm().get_defense_bonus();
		}
		if(chr.get_braces() != null){
			defense += chr.get_braces().get_defense_bonus();
		}
		if(chr.get_chest() != null){
			defense += chr.get_chest().get_defense_bonus();
		}
		if(chr.get_legs() != null){
			defense += chr.get_legs().get_defense_bonus();
		}
		if(chr.get_boots() != null){
			defense += chr.get_boots().get_defense_bonus();
		}
		if(chr.get_l_hand() != null){
			defense += chr.get_l_hand().get_defense_bonus();
		}
		if(chr.get_r_hand() != null){
			defense += chr.get_r_hand().get_defense_bonus();
		}
		return defense;
	}
	
	public boolean isDone(){
		return done;
	}
	
	public void doPlayerWon(Game game){
		// TODO: Change this for multiple enemies and players and stuff
		done = true;
		game.add_dialog("You killed the " + game.get_characters().get(1).get_name() + "!");
		game.add_dialog("You have been awarded 10 experience!");
		((Player) game.get_characters().get(0)).set_experience(((Player) game.get_characters().get(0)).get_experience() + 10);
	}
	
	public void doPlayerDied(Game game){
		done = true;
		game.add_dialog("You have died!");
		game.add_dialog("Restart if you think you can do better!");
	}
}
