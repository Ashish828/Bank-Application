const { Sequelize } = require('sequelize');
const initModels = require("../Models/init-models");

const getConnection = async () => {
    const sequelize = new Sequelize(process.env.MYSQL_DB_NAME, process.env.MYSQL_USERNAME, process.env.MYSQL_DB_PASSWORD, {
                                    host: process.env.MYSQL_HOST_NAME,
                                    dialect:'mysql',
                                    pool: {
                                        max: 5,
                                        min: 0,
                                        acquire: 30000,
                                        idle: 30000
    }})
    try{
        await sequelize.authenticate();
        var models = initModels(sequelize);
        console.log('Connection has been established successfully.');
        return [sequelize, models];
    }
    catch(err){
        console.error('Unable to connect to the database:', err);
        return [null, null]
    }
    
}

const closeConnection = async(sequelize) => {
    sequelize.close()
        .then(()=>console.log("Connection closed"))
        .catch(()=>console.log("unable o close the connection"));
}

module.exports = {
    getConnection,
    closeConnection
}