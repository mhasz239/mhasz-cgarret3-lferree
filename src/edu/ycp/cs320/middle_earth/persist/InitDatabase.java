package edu.ycp.cs320.middle_earth.persist;

import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
//import edu.ycp.cs320.middle_earth.persist.FakeDatabase;

public class InitDatabase {
	public static void init() {
				DatabaseProvider.setInstance(new DerbyDatabase());
	}
}
