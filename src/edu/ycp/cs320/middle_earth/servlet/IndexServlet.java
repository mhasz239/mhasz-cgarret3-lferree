package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.middle_earth.controller.Account;
import edu.ycp.cs320.middle_earth.controller.Game;
import edu.ycp.cs320.middle_earth.persist.DatabaseProvider;
import edu.ycp.cs320.middle_earth.persist.DerbyDatabase;
import edu.ycp.cs320.middle_earth.persist.IDatabase;


public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		String playerToken = (String) req.getSession().getAttribute("player");
		req.setAttribute("playerToken", playerToken);
		req.getSession().setAttribute("create", false);
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		System.out.println("Index Servlet: doPost");
		
		
		String errorMessage = null;

		
		
		String form = req.getParameter("submit");
		
		if (form.equalsIgnoreCase("Login")) {
			Account account = new Account();
			String check = account.login(req.getParameter("username"), req.getParameter("password"));
			if (check.equals("Success!") && !req.getParameter("password").equalsIgnoreCase("")) {
				//req.getSession().setAttribute("account", account);
				//account.setuser_token(user_id);
				
				//Placeholder for now is just setting the player attribute as the username only if login succeeds.
				req.getSession().setAttribute("player", req.getParameter("username"));
				req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMessage", check);
				req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
			}
		}
		
		else if(form.equalsIgnoreCase("Create Game 1") || form.equalsIgnoreCase("Load Game 1")){
			//Account account = (Account)req.getSession().getAttribute("account");
			DatabaseProvider.setInstance(new DerbyDatabase());
			IDatabase db = DatabaseProvider.getInstance();
			Game game = db.loadGame();
			game.startMap();
			req.getSession().setAttribute("game", game);
			req.setAttribute("mode", game.getmode());
			req.getSession().setAttribute("game1", "True");
			req.getSession().setAttribute("exit", false);
			req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
		} else if(form.equalsIgnoreCase("Log out")){
			req.getSession().setAttribute("player", "");
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		} else if(form.equalsIgnoreCase("Create Account")){
			req.getRequestDispatcher("/_view/account.jsp").forward(req, resp);
		} else{
			// Shouldn't reach this...
			errorMessage = "Shouldn't be possible";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
	}
}
