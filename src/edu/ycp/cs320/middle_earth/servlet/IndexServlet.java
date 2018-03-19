package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		System.out.println("Index Servlet: doPost");

		
		String errorMessage = null;
		
		String page = req.getParameter("submit");
		if(page.equalsIgnoreCase("Start Game")){

			req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
		}else{
			// Shouldn't reach this...
			errorMessage = "Invalid Option";
			req.setAttribute("errorMessage", errorMessage);
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		}
	}
}
