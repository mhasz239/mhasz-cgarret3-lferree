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

      //ArrayList<Item> inventory_list =  game.get_player().get_inventory().get_items();
        String inventory_list_display = "";
        
        ArrayList<Item> inventory_list = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
        	Item item = new Item();
        	item.setID(i);
        	item.setIsQuestItem("false");
        	item.setItemWeight(5);
        	if (i < 5) {
        		item.setLongDescription("This is a poorly made dagger out of wood.\nDont see much use for this\nAttack +1");
        		item.setShortDescription("A dagger made of wood (+1 atk)");
        		item.setName("Wood Dagger");
        	} else {
        		item.setLongDescription("This is a well made steel dagger.\nYou can do some serious damage with this thing.");
        		item.setShortDescription("A dagger made of steel (+8 atk)");
        		item.setName("Steel Dagger");
        	}
        	inventory_list.add(item);
        }
        
        for (int j = 0; j < inventory_list.size(); j++){
        	inventory_list_display = inventory_list_display + inventory_list.get(j).getName() + ": " + inventory_list.get(j).getShortDescription()+";";
        }
        
        req.setAttribute("inventory", inventory_list_display);
        
        // call JSP to generate the inventory page
        req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Inventory Servlet: doPost");

        Game game = new Game();
        
        //ArrayList<Item> inventory_list =  game.get_player().get_inventory().get_items();
        String inventory_list_display = "";
        
        // FAKE ARRAY LIST TO TEST THE DISPLAYS (5 wood daggers, 5 steel daggers)
        ArrayList<Item> inventory_list = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
        	Item item = new Item();
        	item.setID(i);
        	item.setIsQuestItem("false");
        	item.setItemWeight(5);
        	if (i < 5) {
        		item.setLongDescription("This is a poorly made dagger out of wood.\nDont see much use for this\nAttack +1");
        		item.setShortDescription("A dagger made of wood (+1 atk)");
        		item.setName("Wood Dagger");
        	} else {
        		item.setLongDescription("This is a well made steel dagger.\nYou can do some serious damage with this thing.");
        		item.setShortDescription("A dagger made of steel (+8 atk)");
        		item.setName("Steel Dagger");
        	}
        	inventory_list.add(item);
        }
        
        for (int j = 0; j < inventory_list.size(); j++){
        	inventory_list_display = inventory_list_display + inventory_list.get(j).getName() + ": " + inventory_list.get(j).getShortDescription()+";";
        }
       
        
        //Display the string line of the inventory_list_display
        //System.out.println(inventory_list_display);
        
        
        // holds the error message text, if there is any
        String errorMessage = null;
        String command = req.getParameter("command");
        
        //System.out.println(inventory_list.size());
        
        //Parses the command line and calls appropriate commands or returns default Error String.
        errorMessage = game.handle_command(command);
        
        
        
        if (game.get_mode() == "game") {
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
        	req.setAttribute("inventory", inventory_list_display);
        	req.setAttribute("errorMessage", errorMessage);
        	// now call the JSP to render the new page
        	req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
        }
    }
}
