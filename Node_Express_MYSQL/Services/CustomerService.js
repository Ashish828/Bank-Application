const { INSERT_INTO_CUSTOMER, SELECT_CUSTOMER} = require("../Utils/Constants");
const {executeQuery} = require("../Utils/MysqlConnectionUtil")

const createCustomer = async(firstName, lastName, address, email, password) =>{
    const query = INSERT_INTO_CUSTOMER(firstName, lastName, email, address, password);
    const {insertId} = await executeQuery(query);
    return insertId;
}

const loginCustomer = async(id, password) => {
    const query = `${SELECT_CUSTOMER} WHERE id= ${id} and password= ${password}`;
    const data = await executeQuery(query);
    return data;
}

module.exports = {
    createCustomer,
    loginCustomer
}