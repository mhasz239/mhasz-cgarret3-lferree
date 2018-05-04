package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.model.Constructs.Item;


public class GameViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("GameView Servlet: doGet");
        
        Game game = (Game) req.getSession().getAttribute("game");
        
        
        game.set_mode("game");
        req.setAttribute("mode", game.get_mode());
        
        game.add_dialog(game.get_mapTile_name());
        game.add_dialog(game.get_mapTile_longDescription());
        

        req.setAttribute("dialog", game.get_display_text());
        // call JSP to generate empty form
        req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("GameView Servlet: doPost");
        
        Game game = (Game) req.getSession().getAttribute("game");
        req.getSession().setAttribute("command", req.getParameter("command"));
        
        if (req.getParameter("command").equalsIgnoreCase("testing")) {
        	System.out.println((String) req.getSession().getAttribute("player"));
        	game.testing((String) req.getSession().getAttribute("player"));
        }
        if(game.mode_change(req.getParameter("command"))){
        	req.getSession().setAttribute("command", null);
        }
        
        req.setAttribute("mode", game.get_mode());
        req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
        
    }
}
