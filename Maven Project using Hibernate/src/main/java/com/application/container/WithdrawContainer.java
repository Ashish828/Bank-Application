package com.application.container;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.application.dao.AppData;

@WebServlet("/user/account/withdraw")
public class WithdrawContainer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("withdraw.jsp")
		       .forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int amount = Integer.parseInt(request.getParameter("amount"));
		int pin = Integer.parseInt(request.getParameter("pin"));
		
		AppData app = AppData.getInstance();;
		String message = app.withdraw(amount, pin);
		
		HttpSession session = request.getSession();
		session.setAttribute("message", message);
		session.setAttribute("url", "user/accountMenu.jsp");
		response.sendRedirect("/bank/message.jsp");
	}

}
