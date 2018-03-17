package edu.ycp.cs320.middle_earth.model.Constructs;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * These tests are meant to test solely Construct.
 */
public class ConstructTest{
	private Construct construct;
	
	@Before
	public void setup(){
		construct = new Construct();
	}
	
	@Test
	public void testSetID(){
		assertEquals(0, construct.getID());
		
		construct.setID(1);
		
		assertEquals(1, construct.getID());
	}
	
	@Test
	public void testSetName(){
		assertEquals(null, construct.getName());
		
		construct.setName("Millenium Falcon Cockpit");
		
		assertEquals("Millenium Falcon Cockpit", construct.getName());
	}
	
	@Test
	public void testSetShortDescription(){
		assertEquals(null, construct.getShortDescription());
		
		construct.setShortDescription("What do you think it is?");
		
		assertEquals("What do you think it is?", construct.getShortDescription());
	}
	
	@Test
	public void testSetLongDescription(){
		assertEquals(null, construct.getLongDescription());
		
		construct.setLongDescription("Here you are: The cockpit of Han Solo's Millenium Falcon.");
		
		assertEquals("Here you are: The cockpit of Han Solo's Millenium Falcon.", construct.getLongDescription());
	}
}
