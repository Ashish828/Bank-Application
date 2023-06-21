<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Deposit</title>
</head>
<style>
div{
  min-height: 100vh;
  padding-top: 35vh;
  padding-left: 40vw;
}
input{
	margin-bottom: 1%;
	
}
</style>
<body>
	<div>
	<h2>Deposit Money</h2>
	<form action="deposit" method="post">
		<label>Deposit Amount </label>
		<input type="text" name="amount" /><br/>
		<label>Pin </label>
		<input type="text" name="pin" /><br/>
		<label>Receive Account No </label>
		<input type="text" name="recieveAccountNo" /><br/>
		<input type="submit" />
	</form>
	</div>
</body>
</html>