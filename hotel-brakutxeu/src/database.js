const mysql = require('mysql');
const { promisify }= require('util');

const { database } = require('./keys');

const pool = mysql.createPool(database);

pool.getConnection((err, connection) => {
  if (err) {
    if (err.code === 'PROTOCOL_CONNECTION_LOST') {
      console.error('Conecção a base de dados fechada.');
    }
    if (err.code === 'ER_CON_COUNT_ERROR') {
      console.error('Base de dados com várias conecções');
    }
    if (err.code === 'ECONNREFUSED') {
      console.error('Conecção a base de dados rejeitada.');
    }
  }

  if (connection) connection.release();
  console.log('Base de dados conectada.');

  return;
});

// Promisify Pool Querys
pool.query = promisify(pool.query);

module.exports = pool;