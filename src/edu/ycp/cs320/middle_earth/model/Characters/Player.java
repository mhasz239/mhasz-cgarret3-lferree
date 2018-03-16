package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Quest;

public class Player extends Character{
	private int experience;
	private int carry_weight; // Used for Stat purposes - not current carry weight.
	private ArrayList<Quest> quests;
	
	public int get_experience(){
		return experience;
	}
	
	public void set_experience(int experience){
		this.experience = experience;
	}
	
	public int get_carry_weight(){
		return carry_weight;
	}
	
	public void set_carry_weight(int carry_weight){
		this.carry_weight = carry_weight;
	}
	
	public ArrayList<Quest> get_quests(){
		return quests;
	}
	
	public void set_quests(ArrayList<Quest> quests){
		this.quests = quests;
	}
}
