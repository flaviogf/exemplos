module.exports = (app) => ({
  model: app.model.tipoOcorrencia,
  buscar() {
    return this.model.find()
      .where('ativo', true)
      .select({ ativo: 0 })
  },
  buscarPorId(id) {
    return this.model.findOne({ _id: id })
      .where('ativo', true)
      .select({ ativo: 0 })
  },
  inserir(tipoOcorrencia) {
    return this.model.create(tipoOcorrencia)
  },
  atualizar(id, tipoOcorrencia) {
    return this.model.findOneAndUpdate({ _id: id }, tipoOcorrencia)
  },
  deletar(id) {
    return this.model.findOneAndUpdate({ _id: id }, { ativo: false })
  }
})
