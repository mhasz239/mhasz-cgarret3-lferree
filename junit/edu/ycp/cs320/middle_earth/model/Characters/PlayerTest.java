package edu.ycp.cs320.middle_earth.model.Characters;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

public class PlayerTest{
	private Player player;
	
	@Before
	public void setup(){
		player = new Player();
	}
	
	@Test
	public void testSet_Experience(){
		player.set_experience(938);
		
		assertEquals(938, player.get_experience());
		
		player.set_experience(2834);
		
		assertEquals(2834, player.get_experience());
	}
	
	@Test
	public void testSet_Carry_Weight(){
		player.set_carry_weight(837);
		
		assertEquals(837, player.get_carry_weight());
		
		player.set_carry_weight(29384);
		
		assertEquals(29384, player.get_carry_weight());
	}
	
	@Test
	public void testSet_Quests(){
		ArrayList<Quest> quests = new ArrayList<Quest>();
		Quest quest = new Quest();
		quests.add(quest);
		
		player.set_quests(quests);
		
		assertEquals(1, player.get_quests().size());
		assertEquals(quest, player.get_quests().get(0));
		
		ArrayList<Quest> quests2 = new ArrayList<Quest>();
		Quest quest1 = new Quest();
		Quest quest2 = new Quest();
		quests2.add(quest1);
		quests2.add(quest2);
		
		player.set_quests(quests2);
		
		assertEquals(2, player.get_quests().size());
		assertEquals(quest1, player.get_quests().get(0));
		assertEquals(quest2, player.get_quests().get(1));
	}
	
	@Test
	public void testAdd_Quest(){
		Quest quest1 = new Quest();
		Quest quest2 = new Quest();
		quest2.setRewardCoins(20);
		
		assertEquals(0, player.get_quests().size());
		
		player.add_quest(quest1);
		assertEquals(1, player.get_quests().size());
		assertEquals(quest1, player.get_quests().get(0));
		
		player.add_quest(quest2);
		assertEquals(2, player.get_quests().size());
		assertEquals(quest1, player.get_quests().get(0));
		assertEquals(quest2, player.get_quests().get(1));
	}
	
	@Test
	public void testSet_Helm(){
		// Create Helmet
		Item helmet = new Item();
		helmet.set_ItemType(ItemType.HELM);
		helmet.setName("Iron Helm");
		
		// Set the Helmet
		player.set_helm(helmet);
		
		// Ensure it's right
		assertEquals(helmet, player.get_helm());
		
		// Create another Helmet
		Item helmet2 = new Item();
		helmet2.set_ItemType(ItemType.HELM);
		helmet2.setName("Golden Helmet");
		
		// Set the new helmet
		player.set_helm(helmet2);
		
		// Check it again
		assertEquals(helmet2, player.get_helm());
	}
	
	@Test
	public void testSet_HelmNotHelm(){
		// Create fake helmet
		Item notHelmet = new Item();
		notHelmet.set_ItemType(ItemType.MISC);
		
		// Ensure an IllegalArgumentException
		try{
			player.set_helm(notHelmet);
			assertEquals("You are ", "not gucci");
		}catch(IllegalArgumentException e){
			assertEquals("You gucci", "You gucci");
		}
	}
}
