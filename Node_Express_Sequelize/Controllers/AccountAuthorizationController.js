const {JSON_MESSAGE} = require("../Utils/Constants");
const {createAccount, loginAccount, isNewAccount: hasAccount} = require("../Services/AccountService");

const accountSignUp = async(req, res) => {
    try{
        const {amount, pin, customerId, bankId} = req.query;
        const insertId = await createAccount(amount, pin, customerId, bankId);
        if(insertId == -1)throw "";
        res.status(201).json({
            accountId: insertId
        });
    }
    catch(err){
        res.status(400).json(JSON_MESSAGE("Something went wrong...!!!"));
    }
}

const accountLogIn = async(req, res) => {
    try{
        const {id, pin} = req.query;
        const data = await loginAccount(id, pin);
        if(data.length === 0)throw "";
        res.status(200).json(data[0]);
    }
    catch(err){
        res.status(400).json(JSON_MESSAGE("Something went wrong...!!!"));
    }
}

const isNewAccount = async(req, res) => {
    try{
        const {customerId, bankId} = req.query;
        const data = await hasAccount(customerId, bankId);
        if(data === -1)throw "";
        res.status(200).json({isNewAccount: data === 0 ? true : false});
    }
    catch(err){
        res.status(400).json(JSON_MESSAGE("Something went wrong...!!!"));
    }
}

module.exports = {
    accountLogIn, 
    accountSignUp,
    isNewAccount
}