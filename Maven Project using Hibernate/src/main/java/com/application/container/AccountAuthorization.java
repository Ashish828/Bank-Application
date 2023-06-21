package com.application.container;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.application.dao.AppData;

@WebServlet("/user/account")
public class AccountAuthorization extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		
		AppData app = AppData.getInstance();
		HttpSession session = request.getSession();
		
		if(app.loginUser(id, pin)) {
			//correct user
			session.setAttribute("accountID", id);
			RequestDispatcher rd = request.getRequestDispatcher("accountMenu.jsp");
			rd.forward(request, response);
		}
		else {
			//wrong user
			session.setAttribute("message", "Incorrect Account Id or Pin");
			session.setAttribute("url", "user/select-bank?bank="+ app.getCurrentBank().getName());
			response.sendRedirect("/bank/message.jsp");
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ammount = Integer.parseInt(request.getParameter("ammount"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		
		AppData app = AppData.getInstance();
		HttpSession session = request.getSession();
		boolean isLogged = app.createAccount(ammount, pin);
		
		
		if(isLogged) {
			session.setAttribute("accountID", app.getCurrentUser().getAccountNumber());
			request.getRequestDispatcher("accountMenu.jsp").forward(request, response);
		}else {
			session.setAttribute("message", "Unable to create Account");
			session.setAttribute("url", "user/select-bank?bank="+ app.getCurrentBank().getName());
			response.sendRedirect("/bank/message.jsp");
		}
		
		
	}

}
