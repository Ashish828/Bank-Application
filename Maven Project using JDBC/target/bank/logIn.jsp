<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Log In</title>
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
<h2>Log In</h2>
	<form action="allBanks" method="get">
	Customer ID
	<input type="text" name="id" /><br/>
	Phone Number
	<input type="text" name="pNo" /><br/>
	<input type="submit" /><br/>
	don't have an account <a href="signIn.jsp">sign up</a>
	</form>
</div>
</body>
</html>