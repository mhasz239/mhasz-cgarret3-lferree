package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ycp.cs320.middle_earth.model.Quest;

public class Player extends Character{
	private int experience;
	private int carry_weight; // Used for Stat purposes - not current carry weight.
	private ArrayList<Quest> quests;
	private int skill_points = 0;
	private HashMap<Integer, Integer> lvl_up = new HashMap<Integer, Integer>();
	
	
	public Player(){
		quests = new ArrayList<Quest>();
		for (int i = 1; i <= 20; i ++) {
			lvl_up.put(i, i*50);
		}
		experience = 0;
	}
	
	public int getexperience(){
		return experience;
	}
	
	public void setexperience(int experience){
		this.experience = experience;
		while (this.experience > lvl_up.get(this.getlevel())) {
			this.experience = this.experience - lvl_up.get(this.getlevel());
			this.setlevel(this.getlevel() + 1);
			this.sethit_points(this.gethit_points() + 10);
			this.setattack(this.getattack()+1);
			this.setdefense(this.getdefense()+1);
			
			setskill_points(3);
		}
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

	public int getskill_points() {
		return skill_points;
	}

	public void setskill_points(int skill_points) {
		this.skill_points += skill_points;
	}
	
	
}
