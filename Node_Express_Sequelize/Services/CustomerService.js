const { getConnection, closeConnection } = require('../Utils/SequelizeConnectionUtil');

const createCustomer = async(first_name, last_name, address, email, password) =>{
    const [seq, models] = await getConnection();
    if(seq != null && models != null){
        const insertId = models.customer.create({
            first_name,
            last_name,
            email,
            address,
            password
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

const loginCustomer = async(id, password) => {
    const [seq, models] = await getConnection();
    if(seq != null && models != null){
        const data = models.customer.findAll({
           where: {
                id,
                password
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

module.exports = {
    createCustomer,
    loginCustomer
} 