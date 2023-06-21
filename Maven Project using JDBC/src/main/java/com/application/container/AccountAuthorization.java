package com.application.container;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.dao.AppData;

public class AccountAuthorization extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		
		AppData app = AppData.getInstance();
		
		if(app.loginUser(id, pin)) {
			//correct user
			RequestDispatcher rd = request.getRequestDispatcher("accountMenu.jsp");
			rd.forward(request, response);
		}
		else {
			//wrong user
			request.setAttribute("message", "Incorrect Account Id or Pin");
			request.setAttribute("url", "bankMenu?bank="+ app.getCurrentBank().getName());
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ammount = Integer.parseInt(request.getParameter("ammount"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		
		AppData app = AppData.getInstance();
		boolean isLogged = app.createAccount(ammount, pin);
		
		
		if(isLogged) {
			request.getRequestDispatcher("accountMenu.jsp").forward(request, response);
		}else {
			request.setAttribute("message", "Unable to create Account");
			request.setAttribute("url", "bankMenu?bank="+ app.getCurrentBank().getName());
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
		
		
	}

}
