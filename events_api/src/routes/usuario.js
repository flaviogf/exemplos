const status = require('http-status')

module.exports = (app) => {
  const authenticate = app.core.auth.authenticate()
  const usuarioRep = app.repository.usuario
  const usuarioScope = app.scope.usuario
  app.get('/api/usuario/autorizacao', authenticate, (req, res) => {
    res.sendStatus(status.ACCEPTED)
  })
  app.get('/api/usuario', authenticate, async (req, res, next) => {
    try {
      const usuarios = await usuarioRep.buscar(req.user)
      res.status(status.OK).json(usuarios)
    } catch (e) {
      next(e)
    }
  })
  app.get('/api/usuario/:id', authenticate, async (req, res, next) => {
    try {
      const usuario = await usuarioRep.buscarPorId(req.params.id)
      if (usuario) {
        res.status(status.OK).json(usuario)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
  app.post('/api/usuario', usuarioScope.inserir, async (req, res, next) => {
    try {
      const usuario = await usuarioRep.buscarPorEmail(req.body.email)
      if (usuario) {
        res.sendStatus(status.BAD_REQUEST)
        return
      }
      await usuarioRep.inserir(req.body)
      res.sendStatus(status.CREATED)
    } catch (e) {
      next(e)
    }
  })
  app.put('/api/usuario/:id', authenticate, usuarioScope.atualizar, async (req, res, next) => {
    try {
      const usuario = await usuarioRep.buscarPorId(req.params.id)
      if (usuario) {
        await usuarioRep.atualizar(req.params.id, req.body)
        res.sendStatus(status.NO_CONTENT)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
  app.delete('/api/usuario/:id', authenticate, async (req, res, next) => {
    try {
      const usuario = await usuarioRep.buscarPorId(req.params.id)
      if (usuario) {
        await usuarioRep.deletar(req.params.id)
        res.sendStatus(status.NO_CONTENT)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
}
