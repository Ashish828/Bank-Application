// get the client
const mysql = require('mysql2/promise');

// get the promise implementation, we will use bluebird
const bluebird = require('bluebird');

const config = {
    host: process.env.MYSQL_HOST_NAME,
    user: process.env.MYSQL_USERNAME,
    password: process.env.MYSQL_DB_PASSWORD,
    database: process.env.MYSQL_DB_NAME, 
    Promise: bluebird
}

const executeQuery = async (query) => {
    try{
        const connection = await mysql.createConnection(config);
        const [rows, fields] = await connection.execute(query);
 
        return rows
    }
    catch(err){
        
        return null;
    }
}

module.exports ={
    executeQuery
}