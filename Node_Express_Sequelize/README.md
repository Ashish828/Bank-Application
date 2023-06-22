## ROUTES

## Customer Routes

//Customer Authorization
//1. Signup - /user/sign-up?firstName=Barry&lastName=Allen&address=DC&email=barry.alllen@dc.com&password=1231
//2. Login - /user/login?id=20&password=1231

## Bank Routes

//Get All Banks - /user/get-all-banks

## Account Routes

//Account Authorization
//1. Signup - /user/account/sign-up?amount=10500&pin=1231&customerId=20&bankId=1
//2. Login - /user/account/login?id=12&pin=1231
//3. Is New Account - /user/account/is-new-account?customerId=20&bankId=1

//Account Transactions
//1. Deposit - /user/account/deposit?amount=2500&pin=1231&senderAccountId=12&receiverAccountId=13
//2. Withdraw - /user/account/withdraw?amount=500&pin=1231&accountId=12
//3. Get all Transactions - /user/account/transactions?accountId=12
