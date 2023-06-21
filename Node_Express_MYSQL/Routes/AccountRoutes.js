const express = require("express");

const { accountSignUp, accountLogIn, isNewAccount } = require("../Controllers/AccountAuthorizationController");
const { withdrawAmount, depositAmount, getAllTransactions} = require("../Controllers/AccountTransactionsController")

const router = express.Router();

//Account Authorization - Signup
router.post("/account/sign-up", accountSignUp);

//Account Authorization - Login
router.post("/account/login", accountLogIn);

//Account Authorization - Is new account
router.get("/account/is-new-account", isNewAccount);

//Account Transactions - Withdraw
router.post("/account/withdraw", withdrawAmount);

//Account Transactions - Deposit
router.post("/account/deposit", depositAmount);

//Account Transactions - Get all Transactions from an account
router.get("/account/transactions", getAllTransactions);

module.exports = router;