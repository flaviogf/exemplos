const mongoose = require('mongoose')

module.exports = (app) => {
  const Usuario = new mongoose.Schema(
    {
      nome: String,
      sobrenome: String,
      email: String,
      senha: String,
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
  return mongoose.model('Usuario', Usuario)
}
