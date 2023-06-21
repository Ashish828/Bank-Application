package com.application.container;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.dao.AppData;

@WebServlet("/user/account/details")
public class AccountDetailsContainer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppData app = AppData.getInstance();
		int accNo = app.getCurrentUser().getAccountNumber();
		String userName = app.getCurrentCustomer().getFirstName() + " " + app.getCurrentCustomer().getLastName();
		double balance = app.getCurrentUser().getBalance();
		String address = app.getCurrentCustomer().getAddress();
		int phoneNumber = app.getCurrentCustomer().getPhoneNumber();
		
		request.setAttribute("accNo", accNo);
		request.setAttribute("userName", userName);
		request.setAttribute("balance", balance);
		request.setAttribute("address", address);
		request.setAttribute("phoneNumber", phoneNumber);
		
		request.getRequestDispatcher("accountDetails.jsp")
			   .forward(request, response);
	}

}
