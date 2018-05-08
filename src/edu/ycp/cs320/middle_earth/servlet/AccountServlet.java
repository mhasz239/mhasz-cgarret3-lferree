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


public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Account Servlet: doGet");
		req.setAttribute("accountCreated", false);
		req.getRequestDispatcher("/_view/account.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
				
		System.out.println("Account Servlet: doPost");
		
		req.setAttribute("accountCreated", false);
		
		
		String form = req.getParameter("submit");
		
		if (form.equalsIgnoreCase("Submit")) {
			Account account = new Account();
			 
			
			String accountcreation = account.create_account(req.getParameter("username"), req.getParameter("password"), req.getParameter("email"));
			if (accountcreation.equalsIgnoreCase("Successful")) {
				req.setAttribute("accountCreated", true);
				req.getRequestDispatcher("/_view/account.jsp").forward(req, resp);
			} else {
				req.setAttribute("errorMessage", accountcreation);
				req.getRequestDispatcher("/_view/account.jsp").forward(req, resp);
			}
		}
		
		else if(form.equalsIgnoreCase("Log In")){
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		} 
		else {
			req.getRequestDispatcher("/_view/account.jsp").forward(req, resp);
		}
	}
}
