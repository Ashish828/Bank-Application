const { getConnection, closeConnection } = require('../Utils/SequelizeConnectionUtil');

const getAllBanksData = async() =>{
    const [seq, models] = await getConnection();
    if(seq != null && models != null){
        const data = models.bank.findAll().then((data)=>{
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
    getAllBanksData
}