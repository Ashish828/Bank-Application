<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Message</title>
</head>
<style>
div{
  min-height: 100vh;
  padding-top: 35vh;
  text-align: center;
}
</style>
<body>
	<%
	String message = (String)request.getAttribute("message");
	String url = (String)request.getAttribute("url");
	%>
	<div>
		<p><%= message %></p>
		<a href=<%=url %>><button>back</button></a>
	</div>
</body>
</html>