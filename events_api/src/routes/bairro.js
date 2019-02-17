const status = require('http-status')

module.exports = (app) => {
  const authenticate = app.core.auth.authenticate()
  const bairroRep = app.repository.bairro
  const bairroScope = app.scope.bairro
  app.get('/api/bairro', authenticate, async (req, res, next) => {
    try {
      const bairros = await bairroRep.buscar()
      res.status(status.OK).json(bairros)
    } catch (e) {
      next(e)
    }
  })
  app.get('/api/bairro/:id', authenticate, async (req, res, next) => {
    try {
      const bairro = await bairroRep.buscarPorId(req.params.id)
      if (bairro) {
        res.status(status.OK).json(bairro)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
  app.post('/api/bairro', authenticate, bairroScope.inserirAtualizar, async (req, res, next) => {
    try {
      const bairro = await bairroRep.buscarPorNome(req.body.nome)
      if (bairro) {
        res.sendStatus(status.BAD_REQUEST)
        return
      }
      await bairroRep.inserir(req.body)
      res.sendStatus(status.CREATED)
    } catch (e) {
      next(e)
    }
  })
  app.put('/api/bairro/:id', bairroScope.inserirAtualizar, authenticate, async (req, res, next) => {
    try {
      const bairro = await bairroRep.buscarPorId(req.params.id)
      if (bairro) {
        await bairroRep.atualizar(req.params.id, req.body)
        res.sendStatus(status.NO_CONTENT)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
  app.delete('/api/bairro/:id', authenticate, async (req, res, next) => {
    try {
      const bairro = await bairroRep.buscarPorId(req.params.id)
      if (bairro) {
        await bairroRep.deletar(req.params.id)
        res.sendStatus(status.NO_CONTENT)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
}
