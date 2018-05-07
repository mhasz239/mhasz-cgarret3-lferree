package edu.ycp.cs320.middle_earth.persist.queries;

import java.util.ArrayList;

import edu.ycp.cs320.middle_earth.model.Characters.Enemy;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.IDatabase;
import edu.ycp.cs320.middle_earth.persist.InitDatabase;

public class QueryForEnemies {
	public static void main(String[] args) throws Exception {
		
		InitDatabase.init();
		// get the DB instance and execute transaction

		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Enemy> enemyList = db.getAllEnemies();
		
		// check if anything was returned and output the list
		if (enemyList == null) {
			System.out.println("No enemies found");
		}
		else {
			System.out.println("Please note: names and genders are random\n");
			for(Enemy enemy : enemyList) {
				System.out.println(
					"Race = "+  enemy.getrace() + "\n"
					+ "Name = " + enemy.getname() + "\n"
					+ "Gender = " + enemy.getgender() + "\n"
					+ "Level = " + enemy.getlevel() + "\n"
					+ "HP = " + enemy.gethit_points() + "\n"
					
					+ "MP = " + enemy.getmagic_points() + "\n"
					+ "ATK = " + enemy.getattack() + "\n"
					+ "DEF = " + enemy.getdefense() + "\n"
					+ "SPATK = " + enemy.getspecial_attack() + "\n"
					+ "SPDEF = " + enemy.getspecial_defense() + "\n"
				);
			}
		}
	}
}
