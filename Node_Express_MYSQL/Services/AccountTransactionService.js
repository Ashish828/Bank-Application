const {SELECT_ACCOUNT, INSERT_INTO_TRANSACTION, SELECT_TRANSACTION, CREDITED, DEBITED} = require("../Utils/Constants");
const {executeQuery} = require("../Utils/MysqlConnectionUtil");

const withdraw = async(accountId, pin, amount) => {
    
    const query = `${SELECT_ACCOUNT} WHERE id= ${accountId}`;
    const data = await executeQuery(query);
    
    const balance = data[0].balance;
    const currentPin = data[0].pin;
    
    if(pin !== currentPin || amount > balance)return false;
    
    const updatedBalance = balance - amount;

    const query2 = INSERT_INTO_TRANSACTION(accountId, updatedBalance, amount, DEBITED);
    const {insertId} = await executeQuery(query2);
    
    const query3 = `UPDATE account SET balance=${updatedBalance} WHERE id=${accountId}`;
    const data3 = await executeQuery(query3);
    
    return true;
}

const depositToAccount = async( amount, receiverAccountId) => {
    const query = `${SELECT_ACCOUNT} WHERE id= ${receiverAccountId}`;
    const data = await executeQuery(query);
    
    const balance = data[0].balance;
    const updatedBalance = balance + amount;

    const query2 = INSERT_INTO_TRANSACTION(receiverAccountId, updatedBalance, amount, CREDITED);
    const {insertId} = await executeQuery(query2);
    
    const query3 = `UPDATE account SET balance=${updatedBalance} WHERE id=${receiverAccountId}`;
    const data3 = await executeQuery(query3);
    
    return true;
}

const deposit = async(senderAccountId, pin, amount, receiverAccountId) => {
    
    if(senderAccountId === receiverAccountId){
        const query = `${SELECT_ACCOUNT} WHERE id= ${senderAccountId}`;
        const data = await executeQuery(query);
        
        const balance = data[0].balance;
        const currentPin = data[0].pin;
        
        if(pin !== currentPin)return false;
        return depositToAccount(amount, senderAccountId);
    }
    else{
        let isWithdrawn = await withdraw(senderAccountId, pin, amount);
        
        if(isWithdrawn && depositToAccount(amount, receiverAccountId))return true;
        else return false;
    }
}

const getTransactions = async(accountId) => {
    const query = `${SELECT_TRANSACTION} WHERE account_id= ${accountId}`;
    const data = await executeQuery(query);
    return data;
}

module.exports = {
    withdraw,
    deposit,
    getTransactions
}