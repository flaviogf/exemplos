const status = require('http-status')

module.exports = (app, io) => {
  const authenticate = app.core.auth.authenticate()
  const ocorrenciaRep = app.repository.ocorrencia
  const ocorrenciaScope = app.scope.ocorrencia
  app.get('/api/ocorrencia', async (req, res, next) => {
    try {
      const ocorrencias = await ocorrenciaRep.buscar()
      return res.status(status.OK).json(ocorrencias)
    } catch (e) {
      next(e)
    }
  })
  app.get('/api/ocorrencia/usuario', authenticate, async (req, res, next) => {
    try {
      const ocorrencias = await ocorrenciaRep.buscarPorUsuario(req.user)
      return res.status(status.OK).json(ocorrencias)
    } catch (e) {
      next(e)
    }
  })
  app.get('/api/ocorrencia/:id', async (req, res, next) => {
    try {
      const ocorrencia = await ocorrenciaRep.buscarPorId(req.params.id)
      if (ocorrencia) {
        res.status(status.OK).json(ocorrencia)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
  app.post('/api/ocorrencia', authenticate, ocorrenciaScope.cadastrarAtualizar, async (req, res, next) => {
    try {
      const ocorrencia = Object.assign(req.body, { usuario: req.user._id })
      await ocorrenciaRep.inserir(ocorrencia)
      io.sockets.emit('servidor_ocorrencias')
      res.sendStatus(status.CREATED)
    } catch (e) {
      next(e)
    }
  })
  app.put('/api/ocorrencia/:id', authenticate, ocorrenciaScope.cadastrarAtualizar, async (req, res, next) => {
    try {
      const ocorrencia = ocorrenciaRep.buscarPorId(req.params.id)
      if (ocorrencia) {
        await ocorrenciaRep.atualizar(req.params.id, req.body)
        io.sockets.emit('servidor_ocorrencias')
        res.sendStatus(status.NO_CONTENT)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
  app.delete('/api/ocorrencia/:id', authenticate, async (req, res, next) => {
    try {
      const ocorrencia = await ocorrenciaRep.buscarPorId(req.params.id)
      if (ocorrencia) {
        await ocorrenciaRep.deletar(req.params.id)
        io.sockets.emit('servidor_ocorrencias')
        res.sendStatus(status.NO_CONTENT)
        return
      }
      res.sendStatus(status.NOT_FOUND)
    } catch (e) {
      next(e)
    }
  })
}
