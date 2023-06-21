<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign up</title>
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
<h2>Sign Up</h2>
	<form action="user" method="post">
	First Name 
	<input type="text" name="fName" /><br/>
	Last Name
	<input type="text" name="lName" /><br/>
	Email
	<input type="text" name="email" /><br/>
	Address
	<input type="text" name="address" /><br/>
	Phone Number
	<input type="text" name="pNo" /><br/>
	<input type="submit" /><br/>
	Have an account? <a href="logIn.jsp">Log In</a>
	</form>
</div>
</body>
</html>