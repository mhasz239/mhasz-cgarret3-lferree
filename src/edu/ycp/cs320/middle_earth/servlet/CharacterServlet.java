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

        //Load data for the initial call to the inventory jsp

        Game game = (Game) req.getSession().getAttribute("game");
        String command = (String) req.getSession().getAttribute("command");
        
        game.set_mode("character");
        Player player = (Player) game.get_player();
        //player.set_attack(1);
        //player.set_coins(9999);
        //player.set_defense(1);
        //player.set_experience(10);
        //player.set_gender("male");
        //player.set_hit_points(100);
        //player.set_level(1);
        //player.set_magic_points(0);
        //player.set_name("BlueHawk");
        //player.set_race("human");
        //player.set_special_attack(0);
        //player.set_special_defense(0);
        
        req.setAttribute("attack", player.get_attack());
        req.setAttribute("coins", player.get_coins());
        req.setAttribute("defense", player.get_defense());
        req.setAttribute("experience", player.get_experience());
        req.setAttribute("gender", player.get_gender());
        req.setAttribute("hp", player.get_hit_points());
        req.setAttribute("level", player.get_level());
        req.setAttribute("magic", player.get_magic_points());
        req.setAttribute("name", player.get_name());
        req.setAttribute("race", player.get_race());
        req.setAttribute("specialAttack", player.get_special_attack());
        req.setAttribute("specialDefense", player.get_special_defense());
        
        req.getRequestDispatcher("/_view/character.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {}
}
