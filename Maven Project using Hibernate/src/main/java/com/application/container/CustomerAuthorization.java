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

@WebServlet("/user")
public class CustomerAuthorization extends HttpServlet {

	private static final long serialVersionUID = -3369831126550873153L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Long phoneNumber = Long.parseLong(request.getParameter("pNo"));
		
		AppData app = AppData.getInstance();
		Boolean isLogged = app.loginCustomer(id, phoneNumber);
		HttpSession session = request.getSession();
		
		if(isLogged) {
			session.setAttribute("customerID", id);
			RequestDispatcher rd = request.getRequestDispatcher("allBanks.jsp");
			rd.forward(request, response);
		}else {
			
			session.setAttribute("message", "Incorrect Customer ID or Phone Number...!!!");
			session.setAttribute("url", "logIn.jsp");
			response.sendRedirect("/bank/message.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("fName");
		String lastName = request.getParameter("lName");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		int phoneNumber = Integer.parseInt(request.getParameter("pNo"));
		HttpSession session = request.getSession();
		
		AppData app = AppData.getInstance();
		boolean isCreated = app.createCustomer(firstName, lastName, address, email, phoneNumber);
		if(isCreated) {
			session.setAttribute("customerID", app.getCurrentCustomer().getId());
			RequestDispatcher rd = request.getRequestDispatcher("allBanks.jsp");
			rd.forward(request, response);
		}else {
			session.setAttribute("message", "unable to create customer account");
			session.setAttribute("url", "signIn.jsp");
			response.sendRedirect("/bank/message.jsp");
		}

	}

}
