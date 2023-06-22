const { getConnection, closeConnection } = require('../Utils/SequelizeConnectionUtil');
const {DEBITED, CREDITED} = require("../Utils/Constants");

const updateAccount = async(seq, models, account, accountId, amount, updatedBalance, type) =>{
    try{
        const isTransactionCreated = await createTransaction(seq, models, accountId, updatedBalance, amount, type);

        if(isTransactionCreated){
            const isUpdated = account.update({ balance: updatedBalance})
                                        .then(()=>{                        
                                        return true;
                                        })
                                        .catch(()=>{                        
                                        return false;
                                        });
            
            return isUpdated;                
        }
        else{
            return false;
        }
    }catch(err){
        return false;
    }
}

const withdraw = async(accountId, pin, amount) => {
    const [seq, models] = await getConnection();
    try{
        const account = await models.account.findByPk(accountId);
        if (account === null) {
            closeConnection(seq);
            return false;
        } 
        else {
            if(account.pin !== pin){
                closeConnection(seq);
                return false;
            }
            
            const balance = account.balance;
            const updatedBalance = balance - amount;
            if(updatedBalance < 0){
                closeConnection(seq);
                return false;
            }
            const isUpdated = await updateAccount(seq, models, account, accountId, amount, updatedBalance, DEBITED);
            if(isUpdated){
                closeConnection(seq);
                return true;
            }
            else{
                closeConnection(seq);
                return false;
            }
        }
    }catch(err){
        closeConnection(seq);
        return false;
    }

}

const deposit = async(senderAccountId, pin, amount, receiverAccountId) => {
    const [seq, models] = await getConnection();
    try{
        const senderAccount = await models.account.findByPk(senderAccountId);
        if (senderAccount === null) {
            closeConnection(seq);
            return false;
        } 
        else {
            if(senderAccount.pin !== pin){
                closeConnection(seq);
                return false;
            }
            if(senderAccountId === receiverAccountId){
                const balance = senderAccount.balance;
                const updatedBalance = balance + amount;
                const isUpdated = await updateAccount(seq, models, senderAccount, senderAccountId, amount, updatedBalance, CREDITED);
                if(isUpdated){
                    closeConnection(seq);
                    return true;
                }
                else{
                    closeConnection(seq);
                    return false;
                }
            }
            else{
                //withdraw from sender
                const balance = senderAccount.balance;
                const updatedBalance = balance - amount;
                if(updatedBalance < 0){
                    closeConnection(seq);
                    return false;
                }
                const isSenderUpdated = updateAccount(seq, models, senderAccount, senderAccountId, amount, updatedBalance, DEBITED)
                // credit to receiver
                if(isSenderUpdated){
                    const receiverAccount = await models.account.findByPk(receiverAccountId);
                    if (receiverAccount === null) {
                        closeConnection(seq);
                        return false;
                    } 
                    else {
                        const balance = receiverAccount.balance;
                        const receiverUpdatedBalance = balance + amount;
                        const isReceiverUpdated = await updateAccount(seq, models, receiverAccount, receiverAccountId, amount, receiverUpdatedBalance, CREDITED);
                        if(isReceiverUpdated){
                            closeConnection(seq);
                            return true;
                        }
                        else{
                            closeConnection(seq);
                            return false;
                        }
                    }
                }
            }
        }
    }catch(err){
        closeConnection(seq);
        return false;
    }
}

const getTransactions = async(account_id) => {
    try{
        const [seq, models] = await getConnection();
        const data = models.transaction.findAll({where: {account_id} });
        return data;
    }catch(err){
        closeConnection(seq);
        return [];
    }
}

const createTransaction = async(seq, models, account_id, balance, amount, type) => {
    if(seq != null && models != null){
        const isCreated = models.transaction.create({
            account_id, 
            balance, 
            amount, 
            type,
            date: new Date().toISOString().slice(0, 19).replace('T', ' ')
        })
        .then(data=>true)
        .catch(err =>false)
        
        return isCreated;
    }  
    return false;
}

module.exports = {
    withdraw,
    deposit,
    getTransactions
}