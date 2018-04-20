package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.middle_earth.controller.Account;
import edu.ycp.cs320.middle_earth.controller.Game;


public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		//req.getSession().getAttribute("userToken");
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		System.out.println("Index Servlet: doPost");

		
		String errorMessage = null;
		
		String page = req.getParameter("submit");
		if(page.equalsIgnoreCase("Start Game")){
			//Account account = (Account)req.getSession().getAttribute("account");
			Game game = new Game();
			req.getSession().setAttribute("game", game);
			req.setAttribute("mode", game.get_mode());
			req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
		}else{
			// Shouldn't reach this...
			errorMessage = "Invalid Option";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
	}
}
