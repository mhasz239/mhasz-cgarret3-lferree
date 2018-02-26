package edu.ycp.cs320.middle_earth.main;

import java.lang.management.ManagementFactory;

import org.eclipse.jetty.jmx.MBeanContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {
	/**
	 * Create a {@link Server}, but do not start it.
	 * 
	 * @param fromEclipse true if launching interactively (rather than from uberjar)
	 * @param port which port to listen on
	 * @param warUrl the URL of the webapp war directory
	 */
	public Server launch(boolean fromEclipse, int port, String warUrl, String contextPath) throws Exception {
		// This is adapted from the Embedded Jetty example from Jetty 9.4.x:
		//	    https://www.eclipse.org/jetty/documentation/9.4.x/embedded-examples.html#embedded-webapp-jsp

        // Create a basic jetty server object that will listen on the supplied port
        // Note that if you set this to port 0 then a randomly available port
		// will be assigned that you can either look in the logs for the port,
        // or programmatically obtain it for use in test cases.		
		Server server = new Server(port);
		
        // Setup JMX
		MBeanContainer mbContainer = new MBeanContainer(ManagementFactory.getPlatformMBeanServer());
		server.addBean(mbContainer);

        // The WebAppContext is the entity that controls the environment in
        // which a web application lives and breathes. In this example the
		// context path is being set to "/" so it is suitable for serving root
		// context requests and then we see it setting the location of the war.
		// A whole host of other configurations are available, ranging from
		// configuring to support annotation scanning in the webapp (through
        // PlusConfiguration) to choosing where the webapp will unpack itself.
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath(contextPath);
		webapp.setWar(warUrl);
		
		onCreateWebAppContext(webapp);

        // This webapp will use JSPs and JSTL. We need to enable the
        // AnnotationConfiguration in order to correctly set up the JSP container
		Configuration.ClassList classList = Configuration.ClassList.setServerDefault(server);
		classList.addBefore(
				"org.eclipse.jetty.webapp.JettyWebXmlConfiguration",
				"org.eclipse.jetty.annotations.AnnotationConfiguration");

        // Set the ContainerIncludeJarPattern so that Jetty examines these
        // container-path JARss for TLDs, web-fragments etc.
        // If you omit the JAR that contains the JSTL .TLDs, the JSP engine,
		// it will scan for them instead.		
		webapp.setAttribute(
				"org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern",
				".*/[^/]*servlet-api-[^/]*\\.jar$|.*/javax.servlet.jsp.jstl-.*\\.jar$|.*/[^/]*taglibs.*\\.jar$" );

		// Don't allow directory listings
		webapp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");

		// Allow the welcome file to be a servlet
		webapp.setInitParameter("org.eclipse.jetty.servlet.Default.welcomeServlets", "true");

		if (fromEclipse) {
			// Set up "extra" classpath directories/jarfiles from referenced
			// Eclipse projects.  When launching from the uberjar, these
			// aren't needed because all of the required classes are
			// contained in the webapp's WEB-INF/classes directory.
			
			// DHH - not needed?
		}
		
        // A WebAppContext is a ContextHandler as well so it needs to be set to
        // the server so it is aware of where to send the appropriate requests.
		server.setHandler(webapp);
		
		return server;
	}

	protected void onCreateWebAppContext(WebAppContext webapp) {
		// Does nothing by default, subclasses may override
	}
}
