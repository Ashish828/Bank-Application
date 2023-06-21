package com.application.container;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.dao.AppData;
import com.application.model.Account;
import com.application.model.Bank;

public class Withdraw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int amount = Integer.parseInt(request.getParameter("amount"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		
		AppData app = AppData.getInstance();
		Bank currentBank = app.getCurrentBank();
		Account currentAccount = app.getCurrentUser();
		String message;
		if(currentAccount.isPin(pin)) {
			message = currentBank.withdraw(currentAccount, amount);
		}
		else {
			message = "Invalid Pin";
		}
		
		request.setAttribute("message", message);
		request.setAttribute("url", "accountMenu.jsp");
		request.getRequestDispatcher("message.jsp").forward(request, response);
	}

}
