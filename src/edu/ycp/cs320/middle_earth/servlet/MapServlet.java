package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.MapTile;

public class MapServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Map Servlet: doGet");

        //Load data for the initial call to the inventory jsp
        //Game game = (Game) req.getSession().getAttribute("game");
        
        /*
        for( MapTile tile : game.get_map().getMapTiles()) {
        	String attrName = "tile"+tile.getID();
        	req.setAttribute(attrName, tile.getVisited());
        	System.out.println(attrName + ": " + tile.getVisited());
        }
        */
        
        
        req.getRequestDispatcher("/_view/map.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Map Servlet: doPost");
    }
}
