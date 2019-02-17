const status = require('http-status')

module.exports = (app) => {
  const authenticate = app.core.auth.authenticate()
  const tipoOcorrenciaRep = app.repository.tipoOcorrencia
  const tipoOcorrenciaScope = app.scope.tipoOcorrencia
  app.get('/api/tipoOcorrencia', authenticate, async (req, res, next) => {
    try {
      const tiposOcorrencia = await tipoOcorrenciaRep.buscar()
      res.status(status.OK).json(tiposOcorrencia)
    } catch (e) {
      next(e)
    }
  })
  app.get('/api/tipoOcorrencia/:id', authenticate, async (req, res, next) => {
    try {
      const tipoOcorrencia = await tipoOcorrenciaRep.buscarPorId(req.params.id)
      if (tipoOcorrencia) {
        res.status(status.OK).json(tipoOcorrencia)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
  app.post('/api/tipoOcorrencia', authenticate, tipoOcorrenciaScope.inserirAtualizar, async (req, res, next) => {
    try {
      await tipoOcorrenciaRep.inserir(req.body)
      res.sendStatus(status.CREATED)
    } catch (e) {
      next(e)
    }
  })
  app.put('/api/tipoOcorrencia/:id', authenticate, tipoOcorrenciaScope.inserirAtualizar, async (req, res, next) => {
    try {
      const tipoOcorrencia = await tipoOcorrenciaRep.buscarPorId(req.params.id)
      if (tipoOcorrencia) {
        await tipoOcorrenciaRep.atualizar(req.params.id, req.body)
        res.sendStatus(status.NO_CONTENT)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
  app.delete('/api/tipoOcorrencia/:id', authenticate, async (req, res, next) => {
    try {
      const tipoOcorrencia = await tipoOcorrenciaRep.buscarPorId(req.params.id)
      if (tipoOcorrencia) {
        await tipoOcorrenciaRep.deletar(req.params.id)
        res.sendStatus(status.NO_CONTENT)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
}
