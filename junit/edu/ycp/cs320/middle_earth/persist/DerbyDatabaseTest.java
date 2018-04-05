package edu.ycp.cs320.middle_earth.persist;

import org.junit.Before;

public class DerbyDatabaseTest{
	private IDatabase database;
	
	@Before
	public void setup(){
		// TODO: JUNIT: load DerbyDatabase here when Chris pushes it to GitHub
		// creating DB instance here
		//DatabaseProvider.setInstance(new DerbyDatabase());
		database = DatabaseProvider.getInstance();
	}
}
