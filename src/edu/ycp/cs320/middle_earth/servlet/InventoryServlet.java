package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

public class InventoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	
        System.out.println("Inventory Servlet: doGet");

        //Load data for the initial call to the inventory jsp

        Game game = (Game) req.getSession().getAttribute("game");
        String command = (String) req.getSession().getAttribute("command");
        int counter = 1;
        
        ArrayList<Item> itemlist = game.getplayer().getinventory().getitems();
        ArrayList<Item> cleanList = new ArrayList<Item>();
        for (int i = 0; i < itemlist.size(); i++){
        	Item item = itemlist.get(i);
        	cleanList.add(new Item(item.getItemWeight(), item.getattack_bonus(), item.getdefense_bonus(), item.gethp_bonus(), item.getlvl_requirement(), item.getItemType(), item.getName(), item.getID(), item.getShortDescription(), item.getLongDescription(), itemlist.get(i).getName().replaceAll(" ", "_") ));
        }
        
        req.setAttribute(("itemTest"), cleanList);
        
        req.setAttribute("numItems", counter);
        
        game.setmode("inventory");
        ArrayList<Item> inventory_list =  game.getplayer().getinventory().getitems();
        String inventory_display_list = "";
        for (int j = 0; j < inventory_list.size(); j++){
        	inventory_display_list = inventory_display_list + inventory_list.get(j).getName() + ": " + inventory_list.get(j).getShortDescription()+";";
        }
        	
        if (command == null) {
        	req.setAttribute("inventory", inventory_display_list);
        } else {
        	String inventory_dialog = "";
        	//Parses the command line and calls appropriate commands, returns the infomation requested in inventory, or the error messages associated with wrong calls.
            inventory_dialog = game.handle_command(command);
            
            req.setAttribute("inventory", inventory_display_list);
        	req.setAttribute("inventory_dialog", inventory_dialog);
        }
        	// call JSP to generate the inventory page
        	req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Inventory Servlet: doPost");

        
        Game game = (Game) req.getSession().getAttribute("game");
        game.setmode("inventory");
        // Gets the inventory of the player
        ArrayList<Item> inventory_list =  game.getplayer().getinventory().getitems();
        
        //Inventory is split into two display sections, the inventory list, then the responses to the commands in inventory
        String inventory_display_list = "";
        String inventory_dialog = "";
        
        for (int j = 0; j < inventory_list.size(); j++){
        	inventory_display_list = inventory_display_list + inventory_list.get(j).getName() + ": " + inventory_list.get(j).getShortDescription()+";";
        }

        String command = (String) req.getSession().getAttribute("command");
        
        //Parses the command line and calls appropriate commands, returns the infomation requested in inventory, or the error messages associated with wrong calls.
        inventory_dialog = game.handle_command(command);
        
        
        
        if (game.getmode() == "game") {
        	ArrayList<String> dialog = new ArrayList<String>();
        	dialog.add(game.getmapTile_name());
        	dialog.add(game.getmapTile_longDescription());
        	game.setdialog(dialog);
        	req.setAttribute("dialog", game.getdialog());
            
            req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
        } 
        else if (game.getmode() == "map") {
        	// TODO Implement
    		throw new UnsupportedOperationException("Not implemented yet!");
            //req.getRequestDispatcher("/_view/map.jsp").forward(req, resp);
        } 
        else if (game.getmode() == "character") {
        	// TODO Implement
    		throw new UnsupportedOperationException("Not implemented yet!");
        	//req.getRequestDispatcher("/_view/character.jsp").forward(req, resp);
        } 
        else {
        	req.setAttribute("inventory", inventory_display_list);
        	req.setAttribute("inventory_dialog", inventory_dialog);
        	// now call the JSP to render the new page
        	req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
        }
    }
}
