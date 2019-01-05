const mongoose = require('mongoose')

module.exports = (app) => {
  const Ocorrencia = new mongoose.Schema(
    {
      dataAcontecimento: Date,
      resumo: String,
      periodo: String,
      numero: Number,
      usuario: {
        type: mongoose.SchemaTypes.ObjectId,
        ref: 'Usuario'
      },
      bairro: {
        type: mongoose.SchemaTypes.ObjectId,
        ref: 'Bairro'
      },
      tipoOcorrencia: {
        type: mongoose.SchemaTypes.ObjectId,
        ref: 'TipoOcorrencia'
      }
    },
    {
      timestamps: true,
      versionKey: false
    }
  )
  return mongoose.model('Ocorrencia', Ocorrencia)
}
