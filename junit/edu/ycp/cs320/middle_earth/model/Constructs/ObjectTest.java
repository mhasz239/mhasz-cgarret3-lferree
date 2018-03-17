package edu.ycp.cs320.middle_earth.model.Constructs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ObjectTest{
	private Object object;
	
	@Before
	public void setup(){
		object = new Object();
	}
	
	@Test
	public void testSetCommandResponses(){
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("open", "what'd you say open?");
		
		object.setCommandResponses(responses);
		
		assertEquals("what'd you say open?", object.getCommandResponses().get("open"));
	}
	
	@Test
	public void testResetCommandResponses(){
		HashMap<String, String> responses = new HashMap<String, String>();
		responses.put("open", "what'd you say open?");
		
		object.setCommandResponses(responses);
		
		assertEquals("what'd you say open?", object.getCommandResponses().get("open"));
		
		HashMap<String, String> responses2 = new HashMap<String, String>();
		responses2.put("climb", "Climb? Are you crazy?");
		
		object.setCommandResponses(responses2);
		
		assertEquals("Climb? Are you crazy?", object.getCommandResponses().get("climb"));
		assertEquals(null, object.getCommandResponses().get("open"));
	}
	
	@Test
	public void testSetItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		Item derp = new Item();
		derp.setName("Derpster");
		items.add(derp);
		
		object.setItems(items);
		
		assertEquals(1, object.getItems().size());
		assertEquals(derp, object.getItems().get(0));
	}
	
	@Test
	public void testResetItems(){
		ArrayList<Item> items = new ArrayList<Item>();
		Item derp = new Item();
		derp.setName("Derpster");
		items.add(derp);
		
		object.setItems(items);
		
		assertEquals(1, object.getItems().size());
		assertEquals(derp, object.getItems().get(0));
		
		ArrayList<Item> items2 = new ArrayList<Item>();
		Item somethingElse = new Item();
		somethingElse.setName("This is different from the other");
		items2.add(somethingElse);
		
		object.setItems(items2);
		
		assertEquals(1, object.getItems().size());
		assertEquals(somethingElse, object.getItems().get(0));
	}
}
