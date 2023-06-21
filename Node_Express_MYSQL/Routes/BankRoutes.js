const express = require("express");
const { getAllBanks } = require("../Controllers/BankDataController");

const router = express.Router();

//Bank - Get all Banks
router.get("/get-all-banks", getAllBanks);


module.exports = router;