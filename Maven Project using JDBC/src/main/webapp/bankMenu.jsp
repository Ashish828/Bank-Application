<%@page import="com.application.dao.AppData"%>
<%@page import="com.application.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bank Menu</title>
</head>
<style>
div{
  min-height: 100vh;
  padding-top: 30vh;
  padding-left: 40vw;
}
input{
	margin-bottom: 1%;
	
}
</style>
<body>
<div>
<%
Bank bank = (Bank) request.getAttribute("bank");
Customer customerDetails = (Customer) request.getAttribute("customer");
Boolean hasAccount = AppData.isNewAccount(bank.getId());
%>
	<h2>welcome to <%= bank.getName()%></h2>
	<%
	if(!hasAccount){
		%>
		<form action="account" method="get">
		Account Id
			<input type="text" name="id" /><br/>
		Pin 
			<input type="text" name="pin" /><br/>
			<input type="submit" />
		</form>	
		<%
	}
	else{
		%>
		<form action="account" method="post">
		Initial Amount: 
			<input type="text" name="ammount" /><br/>
		Pin:
			<input type="text" name="pin" /><br/>
			<input type="submit" />
		</form>	
		<%
	}
	%>
	</div>
</body>
</html>