<%@page import="com.application.model.Transaction"%>
<%@page import="com.application.dao.AppData"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>
</head>
<style>
div{
  min-height: 100vh;
  padding-top: 35vh;
  margin-left: 28vw;
}
</style>
<body>
	<div>
		<h2>Transactions</h2>
		<%
		AppData app = AppData.getInstance();
		List<Transaction> transactions = app.getCurrentBank().displayTransactions(app.getCurrentUser());
		
		if(transactions.size() == 0){
			%>
			<p>No transactions yet</p>
			<%
		}
		else{
			int i = 0;
			for(Transaction transaction : transactions){
				++i;
				%>
				<p><%= i %>. <%= transaction%></p>
				<%
			}
		}
		%>
		<a href="accountMenu.jsp"><button>Back</button></a>
	</div>
</body>
</html>