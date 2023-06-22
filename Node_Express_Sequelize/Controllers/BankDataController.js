const {JSON_MESSAGE} = require("../Utils/Constants");
const {getAllBanksData} = require("../Services/BankService")

const getAllBanks = async(req, res) => {
    try{
        const data = await getAllBanksData();
        res.status(200).json(data);
    }
    catch(err){
        res.status(400).json(JSON_MESSAGE("Something went wrong...!!!"));
    }
}

module.exports = {
    getAllBanks
}