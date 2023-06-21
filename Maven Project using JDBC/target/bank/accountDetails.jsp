<%@page import="com.application.dao.AppData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Details</title>
</head>
<style>
div.container{
  padding-top: 35vh;
  margin-left: 40vw;
}
</style>
<body>
<%
AppData app = AppData.getInstance();
String[] data = app.getCurrentUser().toString().split(":");
int accNo = app.getCurrentUser().getAccountNumber();
String userName = app.getCurrentCustomer().getFirstName() + " " + app.getCurrentCustomer().getLastName();
double balance = app.getCurrentUser().getBalance();
String address = app.getCurrentCustomer().getAddress();
long phoneNumber = app.getCurrentCustomer().getPhoneNumber();
%>
<div class="container">
	<h2>Account Details</h2>
	<p>Account no: <%= accNo %></p>
	<p>User Name: <%= userName %></p>
	<p>Balance: <%= balance %></p>
	<p>Address: <%= address %></p>
	<p>Phone no: <%= phoneNumber %></p>
	<a href="accountMenu.jsp"><button>Back</button></a>
</div>
</body>
</html>