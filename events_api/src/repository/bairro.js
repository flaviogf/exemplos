module.exports = (app) => ({
  model: app.model.bairro,
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
  buscarPorNome(nome) {
    return this.model.findOne({ nome })
      .where('ativo', true)
      .select({ ativo: 0 })
  },
  inserir(bairro) {
    return this.model.create(bairro)
  },
  atualizar(id, bairro) {
    return this.model.findOneAndUpdate({ _id: id }, bairro)
  },
  deletar(id) {
    return this.model.findOneAndUpdate({ _id: id }, { ativo: false })
  }
})
