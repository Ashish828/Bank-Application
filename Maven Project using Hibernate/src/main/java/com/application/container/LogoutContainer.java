package com.application.container;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.application.dao.AppData;

@WebServlet("/user/account/logout")
public class LogoutContainer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AppData app = AppData.getInstance();
		app.logout();
		HttpSession session = request.getSession();
		session.setAttribute("customerID", null);
		session.setAttribute("accountID", null);
		response.sendRedirect("/bank");
	}


}
