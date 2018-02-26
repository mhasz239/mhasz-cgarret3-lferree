package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

public abstract class Character implements CharacterAction{
	private String race;
	private String name;
	private String gender;
	private int level;
	private int hit_points;
	private int magic_points;
	private int attack;
	private int defense;
	private int special_attack;
	private int special_defense;
	private int coins;
	private MapTile location;
	private ArrayList<Item> inventory;
}
