//dependencias
const connection = require('./dbConnection');

class Bots {
    find() {
        const db = connection();
        return db.open()
            .then(Db => Db.collection('bots'))
            .then(collection => collection.find().toArray())
            .then(documents => {

                return documents;

                db.close();
            });
    }
}

module.exports = Bots;
