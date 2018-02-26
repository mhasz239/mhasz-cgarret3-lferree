package edu.ycp.cs320.middle_earth.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ycp.cs320.middle_earth.model.Characters.NPC;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class Quest{
	private ArrayList<Item> reward_items;
	private int reward_coins;
	private HashMap<String, NPC> dialogue;
}
