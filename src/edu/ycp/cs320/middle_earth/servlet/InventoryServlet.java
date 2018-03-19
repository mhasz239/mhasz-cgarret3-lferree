package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;

public class InventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Inventory Servlet: doGet");

        //Load data for the initial call to the inventory jsp

        Game game = new Game();
        game.set_mode("inventory");
        ArrayList<Item> inventory_list =  game.get_player().get_inventory().get_items();
        
        
        String inventory_display_list = "";
        
        for (int j = 0; j < inventory_list.size(); j++){
        	inventory_display_list = inventory_display_list + inventory_list.get(j).getName() + ": " + inventory_list.get(j).getShortDescription()+";";
        }
       
        req.setAttribute("inventory", inventory_display_list);
        
        // call JSP to generate the inventory page
        req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Inventory Servlet: doPost");

        Game game = new Game();
        game.set_mode("inventory");
        
        // Gets the inventory of the player
        ArrayList<Item> inventory_list =  game.get_player().get_inventory().get_items();
        
        //Inventory is split into two display sections, the inventory list, then the responses to the commands in inventory
        String inventory_display_list = "";
        String inventory_dialog = "";
        
        for (int j = 0; j < inventory_list.size(); j++){
        	inventory_display_list = inventory_display_list + inventory_list.get(j).getName() + ": " + inventory_list.get(j).getShortDescription()+";";
        }

        String command = req.getParameter("command");
        
        //Parses the command line and calls appropriate commands, returns the infomation requested in inventory, or the error messages associated with wrong calls.
        inventory_dialog = game.handle_command(command);
        
        
        
        if (game.get_mode() == "game") {
        	
        	
        	req.setAttribute("dialog", "Temp Dialog Holder switching from Inventory to Game mode");
        	
        	/* Correct Code for when map and player are initialized
            
            req.setArrtibute("dialog", game.get_map_longDescription());
            */
            req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
        } 
        else if (game.get_mode() == "map") {
        	// TODO Implement
    		throw new UnsupportedOperationException("Not implemented yet!");
            //req.getRequestDispatcher("/_view/map.jsp").forward(req, resp);
        } 
        else if (game.get_mode() == "character") {
        	// TODO Implement
    		throw new UnsupportedOperationException("Not implemented yet!");
        	//req.getRequestDispatcher("/_view/character.jsp").forward(req, resp);
        } 
        else {
        	req.setAttribute("inventory", inventory_display_list);
        	req.setAttribute("dialog", inventory_dialog);
        	
        	// now call the JSP to render the new page
        	req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
        }
    }
}
