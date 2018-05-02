package edu.ycp.cs320.middle_earth.persist;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

public class InitialDataTest{
	
	private ArrayList<Item> getActualItems() throws Exception{
		ArrayList<Item> items = new ArrayList<Item>();
		
		ReadCSV itemsReader = new ReadCSV("items.csv");
		List<String> tuple = itemsReader.next();
		while(tuple != null){
			Item item = new Item();
			item.setName(tuple.get(0));
			item.setLongDescription(tuple.get(1));
			item.setShortDescription(tuple.get(2));
			item.set_description_update(tuple.get(3));
			item.set_attack_bonus(Integer.parseInt(tuple.get(4)));
			item.set_defense_bonus(Integer.parseInt(tuple.get(5)));
			item.set_hp_bonus(Integer.parseInt(tuple.get(6)));
			item.setItemWeight(Integer.parseInt(tuple.get(7)));
			item.set_ItemType(ItemType.valueOf(tuple.get(8)));
			item.set_lvl_requirement(Integer.parseInt(tuple.get(9)));
			
			tuple = itemsReader.next();
		}
		itemsReader.close();
		
		return items;
	}
	
	@Test
	public void testGetItems() throws Exception{
		ArrayList<Item> actualItems = getActualItems();
		ArrayList<Item> testItems = InitialData.getItems();
		for(int i = 0; i < actualItems.size(); i++){
			assertEquals(actualItems.get(i), testItems.get(i));
		}
	}
}
