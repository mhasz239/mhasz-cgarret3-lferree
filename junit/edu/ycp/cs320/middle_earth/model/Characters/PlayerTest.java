package edu.ycp.cs320.middle_earth.model.Characters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Quest;

public class PlayerTest{
	private Player player;
	
	@Before
	public void setup(){
		player = new Player();
	}
	
	@Test
	public void testsetExperience(){
		player.setexperience(6);
		
		assertEquals(6, player.getexperience());
		
		player.setexperience(42);
		
		assertEquals(42, player.getexperience());
	}
	
	@Test
	public void testsetCarry_Weight(){
		player.setcarry_weight(837);
		
		assertEquals(837, player.getcarry_weight());
		
		player.setcarry_weight(29384);
		
		assertEquals(29384, player.getcarry_weight());
	}
	
	@Test
	public void testsetQuests(){
		ArrayList<Quest> quests = new ArrayList<Quest>();
		Quest quest = new Quest();
		quests.add(quest);
		
		player.setquests(quests);
		
		assertEquals(1, player.getquests().size());
		assertEquals(quest, player.getquests().get(0));
		
		ArrayList<Quest> quests2 = new ArrayList<Quest>();
		Quest quest1 = new Quest();
		Quest quest2 = new Quest();
		quests2.add(quest1);
		quests2.add(quest2);
		
		player.setquests(quests2);
		
		assertEquals(2, player.getquests().size());
		assertEquals(quest1, player.getquests().get(0));
		assertEquals(quest2, player.getquests().get(1));
	}
	
	@Test
	public void testAdd_Quest(){
		Quest quest1 = new Quest();
		Quest quest2 = new Quest();
		quest2.setRewardCoins(20);
		
		assertEquals(0, player.getquests().size());
		
		player.add_quest(quest1);
		assertEquals(1, player.getquests().size());
		assertEquals(quest1, player.getquests().get(0));
		
		player.add_quest(quest2);
		assertEquals(2, player.getquests().size());
		assertEquals(quest1, player.getquests().get(0));
		assertEquals(quest2, player.getquests().get(1));
	}
}
