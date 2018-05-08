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

public class CharacterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Character Servlet: doGet");
        System.out.println(req.getParameter("head"));

        //Load data for the initial call to the inventory jsp

        Game game = (Game) req.getSession().getAttribute("game");
        String command = (String) req.getSession().getAttribute("command");
        
        ArrayList<Item> itemlist = game.getplayer().getinventory().getitems();
        ArrayList<Item> cleanList = new ArrayList<Item>();
        for (int i = 0; i < itemlist.size(); i++){
        	Item item = itemlist.get(i);
        	cleanList.add(new Item(item.getItemWeight(), item.getattack_bonus(), item.getdefense_bonus(), item.gethp_bonus(), item.getlvl_requirement(), item.getItemType(), item.getName(), item.getID(), item.getShortDescription(), item.getLongDescription(), itemlist.get(i).getName().replaceAll(" ", "_") ));
        }
        
        req.setAttribute(("itemTest"), cleanList);
        
        game.setmode("character");
        Player player = (Player) game.getplayer();
        //player.setattack(1);
        //player.setcoins(9999);
        //player.setdefense(1);
        //player.setexperience(10);
        //player.setgender("male");
        //player.sethit_points(100);
        //player.setlevel(1);
        //player.setmagic_points(0);
        //player.setname("BlueHawk");
        //player.setrace("human");
        //player.setspecial_attack(0);
        //player.setspecial_defense(0);
        
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
    	
    }
    
}
