const { INSERT_INTO_ACCOUNT, SELECT_ACCOUNT} = require("../Utils/Constants");
const {executeQuery} = require("../Utils/MysqlConnectionUtil")

const createAccount = async(amount, pin, customerId, bankId) =>{
    const query = INSERT_INTO_ACCOUNT(amount,pin, bankId, customerId);
    const {insertId} = await executeQuery(query);
    return insertId;
}

const loginAccount = async(id, pin) => {
    const query = `${SELECT_ACCOUNT} WHERE id= ${id} and pin= ${pin}`;
    const data = await executeQuery(query);
    return data;
}

const isNewAccount = async(customerId, bankId) => {
    const query = `${SELECT_ACCOUNT} WHERE customer_id= ${customerId} and bank_id= ${bankId}`;
    const data = await executeQuery(query);
    return data.length > 0;
}

module.exports = {
    createAccount,
    loginAccount,
    isNewAccount
}