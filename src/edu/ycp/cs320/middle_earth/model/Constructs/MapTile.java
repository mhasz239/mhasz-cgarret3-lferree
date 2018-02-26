package edu.ycp.cs320.middle_earth.model.Constructs;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ycp.cs320.middle_earth.model.CombatSituation;

public class MapTile extends Construct{
	private HashMap<String, MapTile> connections;
	private ArrayList<Object> objects;
	private ArrayList<Item> items;
	private ArrayList<CombatSituation> random_encounters;
}
