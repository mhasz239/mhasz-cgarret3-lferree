package edu.ycp.cs320.middle_earth.persist;

import java.util.List;

import edu.ycp.cs320.middle_earth.model.Characters.Inventory;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.Object;
import edu.ycp.cs320.middle_earth.model.Constructs.Map;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

public interface IDatabase {
	public List<Item> getAllItems();
	public List<Object> getAllObjects();
	public List<MapTile> getAllMapTiles();
	public Map getMap();
	public List<Character> getAllCharacters();
	public Player getPlayer();
	public Inventory getInventory();
	public List<Quest> getAllQuests();
}
