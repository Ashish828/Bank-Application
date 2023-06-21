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
	int accNo = (int)request.getAttribute("accNo");
	String userName = (String)request.getAttribute("userName");
	double balance = (double)request.getAttribute("balance");
	String address = (String)request.getAttribute("address");
	int phoneNumber = (int)request.getAttribute("phoneNumber");
%>
<div class="container">
	<h2>Account Details</h2>
	<p>Account no: <%= accNo %></p>
	<p>User Name: <%= userName %></p>
	<p>Balance: <%= balance %></p>
	<p>Address: <%= address %></p>
	<p>Phone no: <%= phoneNumber %></p>
	<a href="/bank/user/accountMenu.jsp"><button>Back</button></a>
</div>
</body>
</html>