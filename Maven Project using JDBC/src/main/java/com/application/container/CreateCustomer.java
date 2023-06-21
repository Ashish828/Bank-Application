package com.application.container;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.dao.AppData;
import com.application.model.*;

public class CreateCustomer extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Long phoneNumber = Long.parseLong(request.getParameter("pNo"));
		
		AppData app = AppData.getInstance();
		Boolean isLogged = app.loginCustomer(id, phoneNumber);
		
		if(isLogged) {
			RequestDispatcher rd = request.getRequestDispatcher("allBanks.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("message", "Incorreect Customer ID or Phone Number...!!!");
			request.setAttribute("url", "logIn.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("fName");
		String lastName = request.getParameter("lName");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		Long phoneNumber = Long.parseLong(request.getParameter("pNo"));
		
		AppData app = AppData.getInstance();
		boolean isCreated = app.createCustomer(firstName, lastName, address, email, phoneNumber);
		if(isCreated) {
			RequestDispatcher rd = request.getRequestDispatcher("allBanks.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("message", "unable to create customer account");
			request.setAttribute("url", "signIn.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("message.jsp");
			rd.forward(request, response);
		}

	}

}
