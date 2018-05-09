package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Characters.Character;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;


public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Game Servlet: doGet");
        
        Game game = (Game) req.getSession().getAttribute("game");
        String command = (String) req.getSession().getAttribute("command");
        
        game.setmode("game");
        if (command != null) {
        	// holds the error message text, if there is any
        	String errorMessage = null;
        	
        	errorMessage = game.handle_command(command);
        	if (errorMessage != null) {
        		game.add_dialog(errorMessage);
        	}
        } else {
        	game.add_dialog(game.getmapTile_name());
        	game.add_dialog(game.getmapTile_longDescription());
        }
        req.setAttribute("mode", game.getmode());
        req.setAttribute("dialog", game.getdisplay_text());
        // call JSP to generate empty form
        
        if (game.getmode().equalsIgnoreCase("combat")) {
        	req.setAttribute("dialog", game.getcombat_text());
        	// Set the players in combat mode
    		
    		/* Need to split get enemyID's and getPlayerID's in CombatMode
    		Player p1 = null;
    		Player p2 = null;
    		ArrayList<Integer> players = game.getBattle().getPlayerIDs()
    		for (int i = 0; i < players.size(); i++) {
    			if (i == 0) {
    				p1 = (Player) game.getcharacters().get(players.get(i));
    			} else if (i == 1) {
    				p2 = (Player) game.getcharacters().get(players.get(i));
    			} 
    		}
    		
    		if (p1 != null) {
    			req.setAttribute("p1", p1);
    			req.setAttribute("p1Name", p1.getname());
    			req.setAttribute("p1Health", p1.gethit_points());
    		}
    		if (p2 != null) {
    			req.setAttribute("e2", p2);
    			req.setAttribute("e2Name", p2.getname());
    			req.setAttribute("e2Health", p2.gethit_points());
    		}
    		*/
    		
    		//Set the Enemies in combat mode
    		
    		Character e1 = null;
    		Character e2 = null;
    		Character e3 = null;
    		ArrayList<Integer> enemy = game.getBattle().getCharacterIDs();
    		for (int i = 0; i < enemy.size(); i++) {
    			if (i == 0) {
    				req.setAttribute("p1", "player1");
    				req.setAttribute("p1Name", game.getplayer().getname());
    				req.setAttribute("p1Health", game.getplayer().gethit_points());
    			}
    			else if (i == 1) {
    				e1 = game.getcharacters().get(enemy.get(i));
    			} else if (i == 2) {
    				e2 = game.getcharacters().get(enemy.get(i));
    			} else if (i == 3) {
    				e3 = game.getcharacters().get(enemy.get(i));
    			}
    		}
    		
    		
    		if (e1 != null) {
    			req.setAttribute("e1", e1);
    			req.setAttribute("e1Name", e1.getname());
    			req.setAttribute("e1Health", e1.gethit_points());
    			req.setAttribute("e1Race", e1.getrace().replaceAll(" ", ""));
    		}
    		if (e2 != null) {
    			req.setAttribute("e2", e2);
    			req.setAttribute("e2Name", e2.getname());
    			req.setAttribute("e2Health", e2.gethit_points());
    			req.setAttribute("e2Race", e2.getrace().replaceAll(" ", ""));
    		}
    		if (e3 != null) {
    			req.setAttribute("e3", e3);
    			req.setAttribute("e3Name", e3.getname());
    			req.setAttribute("e3Health", e3.gethit_points());
    			req.setAttribute("e3Race", e3.getrace().replaceAll(" ", ""));
    		}
        	req.getRequestDispatcher("/_view/combat.jsp").forward(req, resp);
        }
        else {
        	req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Game Servlet: doPost");
        
        Game game = (Game) req.getSession().getAttribute("game");
        game.setmode("game");
        
        // holds the error message text, if there is any
        String errorMessage = null;
        String display_text = null;
        String command = req.getParameter("command");
        errorMessage = game.handle_command(command);
        if (errorMessage != null) {
        	game.add_dialog(errorMessage);
        }
        
        display_text = game.getdisplay_text();
        
        if (game.getmode() == "inventory") {
        	
        	ArrayList<Item> inventory_list =  game.getplayer().getinventory().getitems();
            
            String inventory_display_list = "";
        	for (int j = 0; j < inventory_list.size(); j++){
            	inventory_display_list = inventory_display_list + inventory_list.get(j).getName() + ": " + inventory_list.get(j).getShortDescription()+";";
            }
        	
        	req.setAttribute("inventory", inventory_display_list);
        	req.setAttribute("mode", game.getmode());
            req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
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
        	req.setAttribute("dialog", display_text);
        	req.setAttribute("mode", game.getmode());
        	// now call the JSP to render the new page
        	req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
        }
    }
}
