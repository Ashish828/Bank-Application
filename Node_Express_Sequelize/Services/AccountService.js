const { getConnection, closeConnection } = require('../Utils/SequelizeConnectionUtil');

const createAccount = async(balance, pin, customer_id, bank_id) =>{
    const [seq, models] = await getConnection();
    if(seq != null && models != null){
        const insertId = models.account.create({
            balance,
            pin,
            customer_id,
            bank_id,
        }).then((data)=>{
            closeConnection(seq);
            return data.id;
        }).catch(err => {
            closeConnection(seq);
            return -1;
        })
        return insertId;
    }
    else if(seq != null)closeConnection(seq);  
    return -1;
}

const loginAccount = async(id, pin) => {
    const [seq, models] = await getConnection();
    if(seq != null && models != null){
        const data = models.account.findAll({
           where: {
                id,
                pin
            }
        }).then((data)=>{
            closeConnection(seq);
            return data;
        }).catch(err => {
            closeConnection(seq);
            return [];
        })
        return data;
    }
    else if(seq != null)closeConnection(seq);  
    return [];
}

const isNewAccount = async(customer_id, bank_id) => {
    const [seq, models] = await getConnection();
    if(seq != null && models != null){
        const data =await models.account.findAll({
           where: {
                customer_id, 
                bank_id
            }
        })
        closeConnection(seq);
        return data.length > 0 ? 1 : 0;
    }
    else if(seq != null)closeConnection(seq);  
    return -1;
}

module.exports = {
    createAccount,
    loginAccount,
    isNewAccount
}