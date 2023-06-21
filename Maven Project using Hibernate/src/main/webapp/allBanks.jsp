<%@page import="java.util.Set"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.application.dao.AppData"%>
<%@ page import="com.application.model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Select Bank</title>
</head>
<style>
div{
  min-height: 100vh;
  padding-top: 30vh;
  padding-left: 35vw;
}
input{
	margin-bottom: 1%;
	
}
</style>
<body>
<% 
AppData app = AppData.getInstance();
Customer customer = app.getCurrentCustomer();
Set<Bank> banks = AppData.getBanks();
%>
	<div>
		<h2>Select a Bank</h2>
		<p>Hi <%= customer.getFirstName()%></p>
		<form action="user/select-bank">
			Select a Bank:
			<%
				for(Bank bank : banks){
					String name = bank.getName();
			%>
			<input type="radio" name="bank" value=<%=name %> /><%=name %>
			<%
				}
			%>
			<br/><br/>
			<button type="submit">Open</button>
			
		</form>
	</div>

</body>
</html>