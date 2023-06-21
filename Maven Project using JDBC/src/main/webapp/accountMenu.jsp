<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Account</title>
</head>
<style>
div{
  min-height: 100vh;
  padding-top: 30vh;
  text-align: center;
}
a{
display:block;
margin-bottom: 0.5%;
}
button{
	padding: 0.5em 1em;
	width: 10rem;
	max-width: 10rem;
}
</style>
<body>
<div>
	  <h2>My Account</h2>

	  <a href="deposit.jsp"><button>Deposit</button></a>
	  <a href="withdraw.jsp"><button>Withdraw</button></a>
	  <a href="accountDetails.jsp"><button>Account Details</button></a>
	  <a href="transactions.jsp"><button>Display all Transactions</button></a>
	  <a href="allBanks.jsp"><button>Select a different Bank</button></a>
	  <a href="logout"><button>Logout</button></a>
</div>

</body>
</html>