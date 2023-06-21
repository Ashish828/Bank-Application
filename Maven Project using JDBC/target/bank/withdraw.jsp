<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Withdraw</title>
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
		<h2>Withdraw Amount</h2>
		<form action="withdraw" method="post">
		<label>Withdraw Amount </label>
		<input type="text" name="amount" placeholder="amount"/><br/>
		<label>Pin </label>
		<input type="text" name="pin" placeholder="pin"/><br/>
		<input type="submit" />
	</form>
	</div>

</body>
</html>