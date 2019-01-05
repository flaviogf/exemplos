const mongoose = require('mongoose')

module.exports = (app) => {
  const TipoOcorrencia = new mongoose.Schema(
    {
      descricao: String,
      nomenclatura: String,
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
  return mongoose.model('TipoOcorrencia', TipoOcorrencia)
}
