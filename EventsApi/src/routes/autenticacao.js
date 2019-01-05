const status = require('http-status')
const jwt = require('jwt-simple')

module.exports = (app) => {
  const autenticacaoRep = app.repository.autenticacao
  const autenticacaoScope = app.scope.autenticacao
  app.post('/api/login', autenticacaoScope.autenticar, async (req, res) => {
    const usuario = await autenticacaoRep.login(req.body)
    if (usuario) {
      const token = jwt.encode({ id: usuario._id }, process.env.SECRET)
      res.status(status.OK).json({ usuario, token })
      return
    }
    res.sendStatus(status.UNAUTHORIZED)
  })
}
