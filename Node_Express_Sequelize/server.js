require('dotenv').config()
const express = require("express");

const app = express();
const PORT = 8080;

// Import All Routes
const customerRoutes = require("./Routes/CustomerRoutes");
const bankRoutes = require("./Routes/BankRoutes");
const accountRoutes = require("./Routes/AccountRoutes");

// Request to /user
app.use("/user", customerRoutes);
// Request to /user/get-all-banks
app.use("/user", bankRoutes);
// Request to /user/account
app.use("/user", accountRoutes);

app.listen(PORT, ()=>{  
    console.log("Listening on PORT: " + PORT);
})