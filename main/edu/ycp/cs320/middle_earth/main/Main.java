package edu.ycp.cs320.middle_earth.main;

import java.io.File;

import org.eclipse.jetty.server.Server;

public class Main {
	public static void main(String[] args) throws Exception {
		String webappCodeBase = "./war";
		File warFile = new File(webappCodeBase);
		Launcher launcher = new Launcher();
		
		// get a server for port 8081
		System.out.println("CREATING: web server on port 8081");
		Server server = launcher.launch(true, 8081, warFile.getAbsolutePath(), "/middle-earth");

        // Start things up!		
		System.out.println("STARTING: web server on port 8081");
		server.start();
		
		// dump the console output - this will produce a lot of red text - no worries, that is normal
		server.dumpStdErr();
		
		// Inform user that server is running
		System.out.println("RUNNING: web server on port 8081");
		
        // The use of server.join() the will make the current thread join and
        // wait until the server is done executing.
        // See http://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html#join()
		server.join();
	}
}
