module.exports = (app) => ({
  ocorrenciaModel: app.model.ocorrencia,
  tipoOcorrencia() {
    return this.ocorrenciaModel.aggregate()
      .lookup({ from: 'tipoocorrencias', localField: 'tipoOcorrencia', foreignField: '_id', as: 'tipoOcorrencia' })
      .unwind('tipoOcorrencia')
      .group({ _id: '$tipoOcorrencia._id', nomenclatura: { $first: '$tipoOcorrencia.nomenclatura' }, total: { $sum: 1 } })
  },
  bairro() {
    return this.ocorrenciaModel.aggregate()
      .lookup({ from: 'bairros', localField: 'bairro', foreignField: '_id', as: 'bairro' })
      .unwind('bairro')
      .group({ _id: '$bairro._id', nome: { $first: '$bairro.nome' }, total: { $sum: 1 } })
  },
  periodo() {
    return this.ocorrenciaModel.aggregate()
      .group({ _id: '$periodo', nome: { $first: '$periodo' }, total: { $sum: 1 } })
  }
})
