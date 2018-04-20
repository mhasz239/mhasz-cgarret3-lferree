package edu.ycp.cs320.middle_earth.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.middle_earth.controller.Account;
import edu.ycp.cs320.middle_earth.controller.Game;


public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		System.out.println("Login Servlet: doPost");

		Account account = new Account();
		
		String page = req.getParameter("Login");
		if (page.equalsIgnoreCase("Login")) {
			String check = account.login(req.getParameter("username"), req.getParameter("password"));
			if (check.equals("Success!")) {
				//req.getSession().setAttribute("account", account);
				req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMessage", check);
			}
		}
	}
}
