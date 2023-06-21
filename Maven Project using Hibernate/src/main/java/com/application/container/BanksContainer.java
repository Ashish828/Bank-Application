package com.application.container;

import java.io.IOException;
import com.application.model.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.application.dao.AppData;

@WebServlet("/user/select-bank")
public class BanksContainer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bankName = request.getParameter("bank");
		AppData app = AppData.getInstance();
		
		Bank bank = app.selectBank(bankName);
		if(bank == null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("message", "Bank not available");
			session.setAttribute("url", "logIn.jsp");
			
			response.sendRedirect("/bank/message.jsp");
		}else {
			request.setAttribute("bank", bank);
			request.setAttribute("customer", app.getCurrentCustomer());
			request.getRequestDispatcher("bankMenu.jsp").forward(request, response);
		}
		
	}


}
