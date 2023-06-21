package com.application.container;

import java.io.IOException;
import com.application.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.dao.AppData;


public class AllBanks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bankName = request.getParameter("bank");
		AppData app = AppData.getInstance();
		
		Bank bank = app.selectBank(bankName);
		if(bank == null) {
			request.setAttribute("message", "Bank not available");
			request.setAttribute("url", "logIn.jsp");
			request.getRequestDispatcher("message.jsp").forward(request, response);;
		}else {
			request.setAttribute("bank", bank);
			request.setAttribute("customer", app.getCurrentCustomer());
			request.getRequestDispatcher("bankMenu.jsp").forward(request, response);
		}
		
	}


}
