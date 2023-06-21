package com.application.container;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.application.dao.AppData;
import com.application.model.Transaction;

@WebServlet("/user/account/transactions")
public class TransactionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AppData app = AppData.getInstance();
		Set<Transaction> transactions = app.displayTransactions();
		request.setAttribute("transactions", transactions);
		request.getRequestDispatcher("transactions.jsp").forward(request, response);
	}

}
