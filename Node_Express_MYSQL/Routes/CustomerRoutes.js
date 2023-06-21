const express = require("express");
const { customerSignUp, customerLogIn } = require("../Controllers/CustomerAuthorizationController.js");

const router = express.Router();

//Customer Authorization - Signup
router.post("/sign-up", customerSignUp);

//Customer Authorization - Login
router.post("/login", customerLogIn);

module.exports = router;