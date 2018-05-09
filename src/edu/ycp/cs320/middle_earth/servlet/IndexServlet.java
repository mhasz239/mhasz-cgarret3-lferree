package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;
import java.util.ArrayList;

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
		Account account = (Account) req.getSession().getAttribute("account");
		DatabaseProvider.setInstance(new DerbyDatabase());
		IDatabase db = DatabaseProvider.getInstance();
		ArrayList<Integer> games = new ArrayList<Integer>();
		String errorMessage = null;
		if (req.getSession().getAttribute("player") != null) {
			games = db.getGameIDs((String) req.getSession().getAttribute("player"));
			for (int i = 0; i < games.size(); i++) {
				if (i == 0) {
					req.getSession().setAttribute("game1", "True");
				} else if (i == 1){
					req.getSession().setAttribute("game2", "True");
				} else if (i == 2){
					req.getSession().setAttribute("game3", "True");
				} else if (i == 3){
					req.getSession().setAttribute("game4", "True");
				} else if (i == 4){
					req.getSession().setAttribute("game5", "True");
				} else if (i == 5){
					req.getSession().setAttribute("game6", "True");
				}
			}
		}
		
		
		
		String form = req.getParameter("submit");
		
		if (form.equalsIgnoreCase("Login")) {
			account = new Account(req.getParameter("username"));
			String check = account.login(account.getusername(), req.getParameter("password"));
			if (check.equals("Success!") && !req.getParameter("password").equalsIgnoreCase("")) {
				//req.getSession().setAttribute("account", account);
				//account.setuser_token(user_id);
				
				//Placeholder for now is just setting the player attribute as the username only if login succeeds.
				req.getSession().setAttribute("player", req.getParameter("username"));
				account.setgame_ids(db.getGameIDs((String) req.getSession().getAttribute("player")));
				for (int i = 0; i < account.getgame_ids().size(); i++) {
					if (i == 0) {
						req.getSession().setAttribute("game1", "True");
					} else if (i == 1){
						req.getSession().setAttribute("game2", "True");
					} else if (i == 2){
						req.getSession().setAttribute("game3", "True");
					} else if (i == 3){
						req.getSession().setAttribute("game4", "True");
					} else if (i == 4){
						req.getSession().setAttribute("game5", "True");
					} else if (i == 5){
						req.getSession().setAttribute("game6", "True");
					}
				}
				req.getSession().setAttribute("account", account);
				req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMessage", check);
				req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
			}
		}
		else if (form.startsWith("Create Game")){
			int size = db.getGameIDs(account.getusername()).size();
			
			req.getSession().setAttribute("game"+(size+1), "True");

			int id = db.createNewGame((String) req.getSession().getAttribute("player"));
			req.getSession().setAttribute("gameID", id);
			account.setcurrent_game(id);
			Game game = db.loadGame(id);
			game.startMap("new", id);
			game.setuser(account);
			game.save();
			req.setAttribute("mode", game.getmode());
			req.setAttribute("dialog", game.getdisplay_text());
			req.getSession().setAttribute("game", game);
			req.getSession().setAttribute("exit", false);
			req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
		}
		else if (form.startsWith("Load Game")){
			Game game = null;
			for (int i = 1; i <= 6; i++) {
				if (form.endsWith(""+i)) {
					game = db.loadGame(games.get(i-1));
					account.setcurrent_game(games.get(i-1));
				}
			}
			game.setuser(account);
			game.startMap(account.getusername(), account.getcurrent_game());
			req.getSession().setAttribute("game", game);
			req.setAttribute("dialog", game.getdisplay_text());
			req.setAttribute("mode", game.getmode());
			req.getSession().setAttribute("exit", false);
			req.getRequestDispatcher("/_view/GameView.jsp").forward(req, resp);
		} else if(form.equalsIgnoreCase("Log out")){
			req.getSession().setAttribute("player", "");
			req.getSession().setAttribute("game1", "");
			req.getSession().setAttribute("game2", "");
			req.getSession().setAttribute("game3", "");
			req.getSession().setAttribute("game4", "");
			req.getSession().setAttribute("game5", "");
			req.getSession().setAttribute("game6", "");
			req.getSession().setAttribute("game", null);
			
			req.getSession().getAttributeNames();
				
			
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
