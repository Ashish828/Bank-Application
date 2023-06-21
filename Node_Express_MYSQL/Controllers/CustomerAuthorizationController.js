const {JSON_MESSAGE} = require("../Utils/Constants");
const {createCustomer, loginCustomer} = require("../Services/CustomerService");

const customerSignUp = async(req, res) => {
    try{
        const {firstName, lastName, address, email, password} = req.query;
        const insertId = await createCustomer(firstName, lastName, address, email, password);
        res.status(201).json({
            customerId: insertId
        });
    }
    catch(err){
        res.status(400).json(JSON_MESSAGE("Something went wrong...!!!"));
    }
}

const customerLogIn = async(req, res) => {
    try{
        const {id, password} = req.query;
        const data = await loginCustomer(id, password);
        res.status(200).json(data.length > 0 ? data[0] : []);
    }
    catch(err){
        res.status(400).json(JSON_MESSAGE("Something went wrong...!!!"));
    }
}

module.exports = {
    customerLogIn, 
    customerSignUp
}