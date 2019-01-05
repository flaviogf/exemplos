module.exports = (app) => ({
  model: app.model.usuario,
  buscar(usuario) {
    return this.model.find({ email: { $ne: usuario.email } })
      .where('ativo', true)
      .select({ senha: 0, ativo: 0 })
  },
  buscarPorId(id) {
    return this.model.findOne({ _id: id })
      .where('ativo', true)
      .select({ senha: 0, ativo: 0 })
  },
  buscarPorEmail(email) {
    return this.model.findOne({ email })
      .where('ativo', true)
      .select({ senha: 0, ativo: 0 })
  },
  inserir(usuario) {
    return this.model.create(usuario)
  },
  atualizar(id, usuario) {
    return this.model.findOneAndUpdate({ _id: id }, usuario)
  },
  deletar(id) {
    return this.model.findOneAndUpdate({ _id: id }, { ativo: false })
  }
})
