//dependencias
const mongodb = require('mongodb');

//conexao com db
const connection = () => {
    const server = new mongodb.Server('localhost', 27017);
    return new mongodb.Db('bot-mensagens-db', server);
}

module.exports = connection;
