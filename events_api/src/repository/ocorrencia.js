module.exports = (app) => ({
  model: app.model.ocorrencia,
  buscarPorUsuario(usuario) {
    return this.model.find({ usuario })
      .populate('usuario', { senha: 0 })
      .populate('tipoOcorrencia')
      .populate('bairro')
      .sort({ dataAcontecimento: -1 })
  },
  buscar() {
    return this.model.find()
      .populate('usuario', { senha: 0 })
      .populate('tipoOcorrencia')
      .populate('bairro')
      .sort({ dataAcontecimento: -1 })
  },
  buscarPorId(id) {
    return this.model.findOne({ _id: id })
  },
  inserir(ocorrencia) {
    return this.model.create(ocorrencia)
  },
  atualizar(id, ocorrencia) {
    return this.model.findOneAndUpdate({ _id: id }, ocorrencia)
  },
  deletar(id) {
    return this.model.deleteOne({ _id: id })
  }
})
