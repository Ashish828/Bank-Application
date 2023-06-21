const { SELECT_BANK} = require("../Utils/Constants");
const {executeQuery} = require("../Utils/MysqlConnectionUtil")

const getAllBanksData = async() =>{
    const query = SELECT_BANK;
    const data = await executeQuery(query);
    return data;
}

module.exports = {
    getAllBanksData
}