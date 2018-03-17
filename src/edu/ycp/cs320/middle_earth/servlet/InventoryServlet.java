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

        // call JSP to generate empty form
        req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        System.out.println("Inventory Servlet: doPost");

        Game game = new Game();
        Item item = new Item();
        ArrayList<Item> inventory_list = new ArrayList<Item>();
        for (int i = 0; i < 10; i++) {
        	item.setID(i);
        	item.setIsQuestItem("false");
        	item.setItemWeight(5);
        	item.setLongDescription("This is a poorly made dagger out of wood.\nDont see much use for this");
        	item.setShortDescription("Wooden dagger");
        	item.setName("Wood Dagger");
        	inventory_list.add(item);
        }
        // holds the error message text, if there is any
        String errorMessage = null;
        String command = req.getParameter("command");
        errorMessage = game.handle_command(command);
       
        if (command.matches("game")) {
            req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
        } else {
        	req.setAttribute("invetory", inventory_list);
        	req.setAttribute("errorMessage", errorMessage);
        	// now call the JSP to render the new page
        	req.getRequestDispatcher("/_view/inventory.jsp").forward(req, resp);
        }
    }
}
