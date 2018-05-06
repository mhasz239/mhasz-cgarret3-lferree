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
					"Race = "+  enemy.get_race() + "\n"
					+ "Name = " + enemy.get_name() + "\n"
					+ "Gender = " + enemy.get_gender() + "\n"
					+ "Level = " + enemy.get_level() + "\n"
					+ "HP = " + enemy.get_hit_points() + "\n"
					
					+ "MP = " + enemy.get_magic_points() + "\n"
					+ "ATK = " + enemy.get_attack() + "\n"
					+ "DEF = " + enemy.get_defense() + "\n"
					+ "SPATK = " + enemy.get_special_attack() + "\n"
					+ "SPDEF = " + enemy.get_special_defense() + "\n"
				);
			}
		}
	}
}
