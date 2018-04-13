package edu.ycp.cs320.middle_earth.model;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Characters.Character;

public class CombatSituation{
	// Assuming for now that character 0 is Player, character 1 is Enemy
	private ArrayList<Character> characters;
	
	// Encounter chance
	// MapTile Level (in title)
	// Experience
	// Turns
	// Victory + Defeat Calls
	// Escape/Flee (chance on enemy)
	// Only 1 Enemy for now (grab possibilities from MapTile)
	
	public int calculateDamage(){
		return 0;
	}
	
	public int calculateDefense(){
		return 0;
	}
}
