const mongoose = require('mongoose')

module.exports = (app) => {
  const Bairro = new mongoose.Schema(
    {
      nome: String,
      ativo: {
        type: Boolean,
        default: true
      }
    },
    {
      timestamps: true,
      versionKey: false
    }
  )
  return mongoose.model('Bairro', Bairro)
}
