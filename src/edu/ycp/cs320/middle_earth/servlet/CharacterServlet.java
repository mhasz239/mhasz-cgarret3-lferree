package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Characters.Player;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;
import edu.ycp.cs320.middle_earth.model.Constructs.ItemType;

public class CharacterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Character Servlet: doGet");

        //Load data for the initial call to the inventory jsp

        Game game = (Game) req.getSession().getAttribute("game");
        String command = (String) req.getSession().getAttribute("command");
        
        Player player = (Player) game.getplayer();
        req.setAttribute("sp", player.getskill_points());
        
        ArrayList<Item> itemlist = game.getplayer().getinventory().getitems();
        ArrayList<Item> cleanList = new ArrayList<Item>();
        for (int i = 0; i < itemlist.size(); i++){
        	Item item = itemlist.get(i);
        	cleanList.add(new Item(item.getItemWeight(), item.getattack_bonus(), item.getdefense_bonus(), item.gethp_bonus(), item.getlvl_requirement(), item.getItemType(), item.getName(), item.getID(), item.getShortDescription(), item.getLongDescription(), itemlist.get(i).getName().replaceAll(" ", "_") ));
        }
        
        req.setAttribute(("itemTest"), cleanList);
        
        game.setmode("character");
        
        req.setAttribute("attack", player.getattack());
        req.setAttribute("coins", player.getcoins());
        req.setAttribute("defense", player.getdefense());
        req.setAttribute("experience", player.getexperience());
        req.setAttribute("gender", player.getgender());
        req.setAttribute("hp", player.gethit_points());
        req.setAttribute("level", player.getlevel());
        req.setAttribute("magic", player.getmagic_points());
        req.setAttribute("name", player.getname());
        req.setAttribute("race", player.getrace());
        req.setAttribute("specialAttack", player.getspecial_attack());
        req.setAttribute("specialDefense", player.getspecial_defense());
        
        req.setAttribute("helm", player.gethelm().getName());
        req.setAttribute("chest", player.getchest().getName());
        req.setAttribute("braces", player.getbraces().getName());
        req.setAttribute("legs", player.getlegs().getName());
        req.setAttribute("boots", player.getboots().getName());
        req.setAttribute("l_hand", player.getl_hand().getName());
        req.setAttribute("r_hand", player.getr_hand().getName());
        
        req.getRequestDispatcher("/_view/character.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	System.out.println("Character: doPost");
    	System.out.println(req.getParameter("head"));
    	if (req.getParameter("remove")!= null) {
    		req.getSession().setAttribute(req.getParameter("remove"), null);
    	}
    	
    	Game game = (Game) req.getSession().getAttribute("game");
    	
    	Player player = (Player) game.getplayer();
        System.out.println(Integer.parseInt(req.getParameter("skillpoints")));
        System.out.println(req.getParameter("updateskillpoints"));
        if (req.getParameter("updateskillpoints").equalsIgnoreCase("true")){
        	player.setattack(Integer.parseInt(req.getParameter("attack")));
        	player.setdefense(Integer.parseInt(req.getParameter("defense")));
        	player.setspecial_attack(Integer.parseInt(req.getParameter("specialattack")));
        	player.setspecial_defense(Integer.parseInt(req.getParameter("specialdefense")));
        	player.setmagic_points(Integer.parseInt(req.getParameter("magic")));
        	player.sethit_points(Integer.parseInt(req.getParameter("hitpoints")));
        	player.setskill_points(-(player.getskill_points()-Integer.parseInt(req.getParameter("skillpoints"))));
        	req.setAttribute("updateskillpoints", "false");
        }
        
        req.setAttribute("sp", player.getskill_points());
        
        ArrayList<Item> itemlist = game.getplayer().getinventory().getitems();
        ArrayList<Item> cleanList = new ArrayList<Item>();
        for (int i = 0; i < itemlist.size(); i++){
        	Item item = itemlist.get(i);
        	cleanList.add(new Item(item.getItemWeight(), item.getattack_bonus(), item.getdefense_bonus(), item.gethp_bonus(), item.getlvl_requirement(), item.getItemType(), item.getName(), item.getID(), item.getShortDescription(), item.getLongDescription(), itemlist.get(i).getName().replaceAll(" ", "_") ));
        }
        
        for (Item item : cleanList) {
        	if (item.getName().equals(req.getParameter("head"))){
        		if (item.getItemType() == ItemType.HELM) {
        			game.getplayer().sethelm(item);
        			req.getSession().setAttribute("headIMG", item.getdescription_update());
        		} else {
        			req.setAttribute("errorMessage", "Only a Hemlet can be equiped there.");
        		}
        	} else if (item.getName().equals(req.getParameter("chest"))){
        		if (item.getItemType() == ItemType.CHEST) {
        			game.getplayer().sethelm(item);
        			req.getSession().setAttribute("chestIMG", item.getdescription_update());
        		} else {
        			req.setAttribute("errorMessage", "Only a Chestplate can be equiped there.");
        		}
        	} else if (item.getName().equals(req.getParameter("arms"))){
        		if (item.getItemType() == ItemType.BRACES) {
        			game.getplayer().sethelm(item);
        			req.getSession().setAttribute("armsIMG", item.getdescription_update());
        		} else {
        			req.setAttribute("errorMessage", "Only arm braces can be equiped there.");
        		}
        	} else if (item.getName().equals(req.getParameter("lhand"))){
        		if (item.getItemType() == ItemType.L_HAND) {
        			game.getplayer().sethelm(item);
        			req.getSession().setAttribute("lhandIMG", item.getdescription_update());
        		} else {
        			req.setAttribute("errorMessage", "Only left handed items can be equiped there.");
        		}
        	} else if (item.getName().equals(req.getParameter("rhand"))){
        		if (item.getItemType() == ItemType.R_HAND) {
        			game.getplayer().sethelm(item);
        			req.getSession().setAttribute("rhandIMG", item.getdescription_update());
        		} else {
        			req.setAttribute("errorMessage", "Only right handed items can be equiped there.");
        		}
        	} else if (item.getName().equals(req.getParameter("legs"))){
        		if (item.getItemType() == ItemType.LEGS) {
        			game.getplayer().sethelm(item);
        			req.getSession().setAttribute("legsIMG", item.getdescription_update());
        		} else {
        			req.setAttribute("errorMessage", "Only leg guards can be equiped there.");
        		}
        	}
        }
        
        req.setAttribute(("itemTest"), cleanList);
        
        game.setmode("character");
        
        req.setAttribute("attack", player.getattack());
        req.setAttribute("coins", player.getcoins());
        req.setAttribute("defense", player.getdefense());
        req.setAttribute("experience", player.getexperience());
        req.setAttribute("gender", player.getgender());
        req.setAttribute("hp", player.gethit_points());
        req.setAttribute("level", player.getlevel());
        req.setAttribute("magic", player.getmagic_points());
        req.setAttribute("name", player.getname());
        req.setAttribute("race", player.getrace());
        req.setAttribute("specialAttack", player.getspecial_attack());
        req.setAttribute("specialDefense", player.getspecial_defense());
    	
    	req.getRequestDispatcher("/_view/character.jsp").forward(req, resp);
    }
    
}
