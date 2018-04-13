package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class Player extends Character{
	private int experience;
	private int carry_weight; // Used for Stat purposes - not current carry weight.
	private ArrayList<Quest> quests;
	private Item helm;
	private Item braces;
	private Item chest;
	private Item legs;
	private Item boots;
	private Item l_hand;
	private Item r_hand;
	
	public Player(){
		quests = new ArrayList<Quest>();
	};
	
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
	
	public void add_quest(Quest quest){
		quests.add(quest);
	}
	
	public void set_quests(ArrayList<Quest> quests) {
		this.quests = quests;
	}
	
	public ArrayList<Quest> get_quests(){
		return quests;
	}
	
	public void set_helm(Item helm) {
		this.helm = helm;
	}
	
	public Item get_helm(){
		return helm;
	}
	
	public void set_braces(Item braces) {
		this.braces = braces;
	}
	
	public Item get_braces(){
		return braces;
	}
	
	public void set_chest(Item chest) {
		this.chest = chest;
	}
	
	public Item get_chest(){
		return chest;
	}
	
	public void set_legs(Item legs) {
		this.legs = legs;
	}
	
	public Item get_legs(){
		return legs;
	}
	
	public void set_boots(Item boots) {
		this.boots = boots;
	}
	
	public Item get_boots(){
		return boots;
	}
	
	public void set_l_hand(Item l_hand) {
		this.l_hand = l_hand;
	}
	
	public Item get_l_hand(){
		return l_hand;
	}
	
	public void set_r_hand(Item r_hand) {
		this.r_hand = r_hand;
	}
	
	public Item get_r_hand(){
		return r_hand;
	}
}
