package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Quest;

public class Player extends Character{
	private int experience;
	private int carry_weight; // Used for Stat purposes - not current carry weight.
	private ArrayList<Quest> quests;
	
	public Player(){
		quests = new ArrayList<Quest>();
	};
	
	public int getexperience(){
		return experience;
	}
	
	public void setexperience(int experience){
		this.experience = experience;
	}
	
	public int getcarry_weight(){
		return carry_weight;
	}
	
	public void setcarry_weight(int carry_weight){
		this.carry_weight = carry_weight;
	}
	
	public void add_quest(Quest quest){
		quests.add(quest);
	}
	
	public void setquests(ArrayList<Quest> quests) {
		this.quests = quests;
	}
	
	public ArrayList<Quest> getquests(){
		return quests;
	}
	
	
}
