module.exports = (app) => ({
  model: app.model.usuario,
  login(usuario) {
    return this.model.findOne(usuario)
      .where('ativo', true)
      .select({ senha: 0 })
  }
})
