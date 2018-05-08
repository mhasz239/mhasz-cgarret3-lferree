package edu.ycp.cs320.middle_earth.model;

import java.util.ArrayList;
import java.util.Random;

import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

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
		game.setmode("combat");
		characterIDs = new ArrayList<Integer>();
		String combatString = "";
		for(int i = 0; i < players.length; i++){
			characterIDs.add(players[i]);
			if(i == 0){
				combatString += game.getcharacters().get(i).getname();
			}else if(i != players.length - 1){
				combatString += ", " + game.getcharacters().get(i).getname();
			}else{
				combatString += " and " + game.getcharacters().get(i).getname() + " have entered combat!";
			}
		}
		random = new Random(System.nanoTime());
		for(int i = 0; i < enemies; i++){
			Enemy enemy = createEnemy();
			// set enemy level to "areaDifficulty" of maptile == player location.
			// enemy.setlevel(game.map.get(player.getlocation).getAreaDifficulty);
	
			game.getcharacters().add(enemy);
			characterIDs.add(game.getcharacters().size() - 1);
			if(i == 0){
				combatString += " is staring into the eyes of a " + enemy.getrace();
			}else if(i != enemies - 1){
				combatString += ", a " + enemy.getrace();
			}else{
				combatString += " and a " + enemy.getrace(); 
			}
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
	
	public Enemy createEnemy(){
		// Grabs a random enemy
		InitDatabase.init();
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<String> enemyRaceList = db.getAllEnemyRaces();
		Random rand = new Random();
		return db.getEnemyByRace(enemyRaceList.get(rand.nextInt(enemyRaceList.size() - 1)));
	}
	
	public void playerAttackEnemy(Game game, int playerIndex, String target){
		// Check that it's the player's turn
		if(characterIDs.get(characterIDs.get(currentIDsIndex)) == playerIndex){
			Character enemy = null;
			int enemyIndex = -1;
			for(int characterIndex: characterIDs){
				// Find the enemy the Player specified
				Character chara = game.getcharacters().get(characterIndex);
				if(characterIndex != playerIndex && (chara.getname().equalsIgnoreCase(target) || 
						chara.getrace().equalsIgnoreCase(target))){
					enemy = chara;
					enemyIndex = characterIndex;
				}
			}
			if(enemy == null){
				// Enemy not found
				game.add_dialog("No one by the name/race of " + target + " was found in combat with you!");
			}else{
				// Do attack against enemy
				int enemyHP = enemy.gethit_points();
				int damage = calculateDamage(game, playerIndex, enemyIndex);
				enemy.sethit_points(enemyHP - damage);
				game.add_dialog("You attacked " + enemy.getname() + " for " + damage + " damage.");
				
				// Check if the enemy is dead
				if(enemy.gethit_points() <= 0){
					doPlayerWon(game, playerIndex, enemyIndex);
				}
				
				// Advance to next turn and check if it's an enemy to do their turn.
				advanceTurn(game);
			}
		}else{
			// Not player's turn
			game.add_dialog("It's not your turn!");
		}
	}
	
	public void advanceTurn(Game game){
		if(!done){
			// Advance index
			currentIDsIndex++;
			
			// Loop index if too high
			if(currentIDsIndex >= characterIDs.size()){
				currentIDsIndex = 0;
			}
			
			// Check if it's now a non-player turn (Enemy)
			if(!(game.getcharacters().get(characterIDs.get(currentIDsIndex)) instanceof Player)){
				// Do enemy turn
				enemyAttackPlayer(game);
			}
		}
	}
	
	public void enemyAttackPlayer(Game game){
		// Get number of Players
		ArrayList<Integer> playerIndices = new ArrayList<Integer>();
		for(int characterIndex: characterIDs){
			if(game.getcharacters().get(characterIndex) instanceof Player){
				playerIndices.add(characterIndex);
			}
		}
		
		// Determine target Player
		int playerNum = random.nextInt(playerIndices.size());
		int playerIndex = playerIndices.get(playerNum);
		
		// Get player
		Character player = game.getcharacters().get(playerIndex);
		
		// Get Enemy
		int enemyIndex = characterIDs.get(currentIDsIndex);
		Character enemy = game.getcharacters().get(enemyIndex);
		
		// Do damage to player
		int playerHP = player.gethit_points();
		int damage = calculateDamage(game, playerIndex, characterIDs.get(currentIDsIndex));
		player.sethit_points(playerHP - damage);
		game.add_dialog(enemy.getname() + " attacked you for " + damage + " damage.");
		//game.add_dialog("You have " + player.gethit_points() + " HP left.");
		
		// Check if player has died
		if(player.gethit_points() <= 0){
			player.sethit_points(0);
			doPlayerDied(game, playerIndex);
		}
		
		// Advance to next turn and check if it's an enemy to do their turn.
		advanceTurn(game);
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
		Character chr = game.getcharacters().get(character);
		int attack = chr.getattack();
		if(chr.gethelm() != null){
			attack += chr.gethelm().getattack_bonus();
		}
		if(chr.getbraces() != null){
			attack += chr.getbraces().getattack_bonus();
		}
		if(chr.getchest() != null){
			attack += chr.getchest().getattack_bonus();
		}
		if(chr.getlegs() != null){
			attack += chr.getlegs().getattack_bonus();
		}
		if(chr.getboots() != null){
			attack += chr.getboots().getattack_bonus();
		}
		if(chr.getl_hand() != null){
			attack += chr.getl_hand().getattack_bonus();
		}
		if(chr.getr_hand() != null){
			attack += chr.getr_hand().getattack_bonus();
		}
		int range = (int) (attack*0.2);
		attack = (int) (attack + (random.nextInt(range+1) - range/2.0));
		return attack;
	}
	
	public int calculateDefense(Game game, int character){
		Character chr = game.getcharacters().get(character);
		int defense = chr.getdefense();
		if(chr.gethelm() != null){
			defense += chr.gethelm().getdefense_bonus();
		}
		if(chr.getbraces() != null){
			defense += chr.getbraces().getdefense_bonus();
		}
		if(chr.getchest() != null){
			defense += chr.getchest().getdefense_bonus();
		}
		if(chr.getlegs() != null){
			defense += chr.getlegs().getdefense_bonus();
		}
		if(chr.getboots() != null){
			defense += chr.getboots().getdefense_bonus();
		}
		if(chr.getl_hand() != null){
			defense += chr.getl_hand().getdefense_bonus();
		}
		if(chr.getr_hand() != null){
			defense += chr.getr_hand().getdefense_bonus();
		}
		return defense;
	}
	
	public boolean isDone(){
		return done;
	}
	
	public void doPlayerWon(Game game, int playerIndex, int killedIndex){
		// Let player know what they have done.
		game.add_dialog("You killed " + game.getcharacters().get(killedIndex).getname() + "!");
		
		// Change player exp
		int currentXP = ((Player) game.getcharacters().get(playerIndex)).getexperience();
		((Player) game.getcharacters().get(playerIndex)).setexperience(currentXP + 300);
		System.out.println(((Player) game.getplayer()).getlevel() + " " + ((Player) game.getplayer()).getskill_points());
		// Let player know what they have earned.
		game.add_dialog("You have been awarded 10 experience!");
		
		// Default is done
		done = true;
		game.setmode("game");
		
		// Check for alive combatant that isn't the player.
		for(int characterIndex: characterIDs){
			if(characterIndex != playerIndex && game.getcharacters().get(characterIndex).gethit_points() > 0){
				// If any aren't dead, combat isn't over.
				done = false;
			}
		}
		
		if(done){
			// Let player know
			game.add_dialog("You have killed everyone! (in this combat situation here)");
		}
	}
	
	public void doPlayerDied(Game game, int playerIndex){
		// TODO: Change this for multiple players and stuff
		// Not sure how to notify correct player or whatever
		
		// Let player know they died
		game.add_dialog("You have died!");
		game.add_dialog("Restart if you think you can do better!");
		
		// Default is done
		done = true;
		
		// Check for alive players yet
		for(int characterIndex: characterIDs){
			if(characterIndex != playerIndex && game.getcharacters().get(characterIndex) instanceof Player &&
					game.getcharacters().get(characterIndex).gethit_points() > 0){
				done = false;
			}
		}
	}
}
