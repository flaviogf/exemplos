const mongoose = require('mongoose')

module.exports = async (app) => {
  mongoose.Promise = global.Promise
  try {
    const uri = process.env.MONGO
    await mongoose.connection.openUri(uri)
    console.log('[MONGO] conectado')
  } catch (e) {
    console.log('[MONGO] não conectado')
    console.log(e)
    process.exit(1)
  }
}
