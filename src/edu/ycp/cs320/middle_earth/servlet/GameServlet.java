package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;


public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Game Servlet: doGet");
        
        Game game = new Game();
        
        
        game.set_mode("game");
        
        
        game.add_dialog(game.get_mapTile_name());
        game.add_dialog(game.get_mapTile_longDescription());
        

        req.setAttribute("dialog", game.get_display_text());
        // call JSP to generate empty form
        req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Game Servlet: doPost");
        
        Game game = new Game();
        game.set_mode("game");
        
        //Gets the last dialog box from the string, posted to the page, then resort it into an ArrayList<String>
        String dialog_text = req.getParameter("dialog");
        if (!dialog_text.isEmpty()) {
        	String[] dialog_text_array = dialog_text.split(";");
        	for (int i = 0; i < dialog_text_array.length; i++) {
        		game.add_dialog(dialog_text_array[i]);
        	}
        }
        // holds the error message text, if there is any
        String errorMessage = null;
        String display_text = null;
        String command = req.getParameter("command");
        errorMessage = game.handle_command(command);
        if (errorMessage != null) {
        	game.add_dialog(errorMessage);
        }
        
        display_text = game.get_display_text();
        
        if (game.get_mode() == "inventory") {
        	
        	ArrayList<Item> inventory_list =  game.get_player().get_inventory().get_items();
            
            String inventory_display_list = "";
        	for (int j = 0; j < inventory_list.size(); j++){
            	inventory_display_list = inventory_display_list + inventory_list.get(j).getName() + ": " + inventory_list.get(j).getShortDescription()+";";
            }
        	
        	req.setAttribute("inventory", inventory_display_list);
        	
            req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
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
        	req.setAttribute("dialog", display_text);
        	// now call the JSP to render the new page
        	req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
        }
    }
}
