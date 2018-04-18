package edu.ycp.cs320.middle_earth.model.Characters;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Quest;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

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
		if(helm.get_ItemType() != ItemType.HELM){
			throw new IllegalArgumentException("This must be a helm!");
		}
		this.helm = helm;
	}
	
	public Item get_helm(){
		return helm;
	}
	
	public void set_braces(Item braces) {
		if(braces.get_ItemType() != ItemType.BRACES){
			throw new IllegalArgumentException("This must be braces!");
		}
		this.braces = braces;
	}
	
	public Item get_braces(){
		return braces;
	}
	
	public void set_chest(Item chest) {
		if(chest.get_ItemType() != ItemType.CHEST){
			throw new IllegalArgumentException("This must be a chest!");
		}
		this.chest = chest;
	}
	
	public Item get_chest(){
		return chest;
	}
	
	public void set_legs(Item legs) {
		if(legs.get_ItemType() != ItemType.LEGS){
			throw new IllegalArgumentException("This must be legs!");
		}
		this.legs = legs;
	}
	
	public Item get_legs(){
		return legs;
	}
	
	public void set_boots(Item boots) {
		if(boots.get_ItemType() != ItemType.BOOTS){
			throw new IllegalArgumentException("This must be boots!");
		}
		this.boots = boots;
	}
	
	public Item get_boots(){
		return boots;
	}
	
	public void set_l_hand(Item l_hand) {
		if(l_hand.get_ItemType() != ItemType.L_HAND){
			// TODO: Maybe just HAND?
			throw new IllegalArgumentException("This must be L HAND!");
		}
		this.l_hand = l_hand;
	}
	
	public Item get_l_hand(){
		return l_hand;
	}
	
	public void set_r_hand(Item r_hand) {
		if(r_hand.get_ItemType() != ItemType.R_HAND){
			// TODO: Maybe just HAND?
			throw new IllegalArgumentException("This must be R HAND!");
		}
		this.r_hand = r_hand;
	}
	
	public Item get_r_hand(){
		return r_hand;
	}
}
