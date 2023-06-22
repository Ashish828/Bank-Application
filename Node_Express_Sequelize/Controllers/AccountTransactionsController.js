const {JSON_MESSAGE} = require("../Utils/Constants");
const {withdraw, deposit, getTransactions} = require("../Services/AccountTransactionService");

const withdrawAmount = async (req, res) => {
    try{
        const {accountId, pin, amount} = req.query;
        const data = await withdraw(parseInt(accountId), parseInt(pin), parseInt(amount));

        res.status(201).json({message: data ? "Transaction Successful" :  "Transaction not successful"});
    }
    catch(err){
        res.status(400).json({message: "Transaction not Successful"});
    }
}

const depositAmount = async(req, res) => {
    try{
        const {senderAccountId, receiverAccountId, pin, amount} = req.query;
        const data = await deposit(parseInt(senderAccountId), parseInt(pin), parseInt(amount), parseInt(receiverAccountId));

        res.status(201).json({message: data ? "Transaction Successful" :  "Transaction not successful"});
    }
    catch(err){
        res.status(400).json({message: "Transaction not Successful"});
    }
}

const getAllTransactions = async(req, res) => {
    try{

    }
    catch(err){
        res.status(400).json(JSON_MESSAGE("Something went wrong"))
    }
    const {accountId} = req.query;
    const data = await getTransactions(parseInt(accountId));

    res.status(200).json(data)
}

module.exports = { 
    withdrawAmount, 
    depositAmount, 
    getAllTransactions
}