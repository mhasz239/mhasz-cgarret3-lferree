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
        
        
        game.setmode("game");
        req.setAttribute("mode", game.getmode());
        
        game.add_dialog(game.getmapTile_name());
        game.add_dialog(game.getmapTile_longDescription());
        

        req.setAttribute("dialog", game.getdisplay_text());
        // call JSP to generate empty form
        req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("GameView Servlet: doPost");
        
        //Exit not working yet. Still needs fix.
        
        if ((boolean) req.getSession().getAttribute("exit")) {
        	if (req.getParameter("exitAns").equalsIgnoreCase("Yes")) {
        		req.getSession().setAttribute("game", null);
        		req.getSession().setAttribute("headIMG", null);
        		req.getSession().setAttribute("chestIMG", null);
        		req.getSession().setAttribute("armsIMG", null);
        		req.getSession().setAttribute("lhandIMG", null);
        		req.getSession().setAttribute("rhandIMG", null);
        		req.getSession().setAttribute("legsIMG", null);
        		req.getSession().setAttribute("bootsIMG", null);
        		 req.getSession().setAttribute("command", null);
        		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);;
        	} else {
        		req.getSession().setAttribute("exit", false);
        		req.setAttribute("command", null);
        		req.setAttribute("mode", ((Game) req.getSession().getAttribute("game")).getmode());
        		req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
        	}
        } else {
        	Game game = (Game) req.getSession().getAttribute("game");
        	req.getSession().setAttribute("command", req.getParameter("command"));
        
        	if (req.getParameter("command") != null && req.getParameter("command").equalsIgnoreCase("exit")){
        		req.getSession().setAttribute("exit", true);
        	}
        
        	if(!game.getmode().equalsIgnoreCase("combat")){
        		if(game.mode_change(req.getParameter("command"))){
        			req.getSession().setAttribute("command", null);
        		}
        	}
        
       
        	req.setAttribute("mode", game.getmode());
        	req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
        }
    }
}
