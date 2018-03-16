package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.middle_earth.controller.Game;


public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Game Servlet: doGet");

        // call JSP to generate empty form
        req.getRequestDispatcher("/_view/Game.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Game Servlet: doPost");

        Game game = new Game();

        // holds the error message text, if there is any
        String errorMessage = null;

        String command = req.getParameter("command");

        errorMessage = game.handle_command(command);


        if (command == "inventory") {
            req.getRequestDispatcher("/_view/Inventory.jsp").forward(req, resp);
        }
        req.setAttribute("Game", model);


        // now call the JSP to render the new page
        req.getRequestDispatcher("/_view/Game.jsp").forward(req, resp);

    }
}
