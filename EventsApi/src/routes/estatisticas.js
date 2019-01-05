module.exports = (app) => {
  const estatisticaRep = app.repository.estatisticas
  app.get('/api/estatistica/tipoOcorrencia', async (req, res, next) => {
    try {
      const tipoOcorrencia = await estatisticaRep.tipoOcorrencia()
      res.status(200).json(tipoOcorrencia)
    } catch (e) {
      next(e)
    }
  })
  app.get('/api/estatistica/bairro', async (req, res, next) => {
    try {
      const ocorrencias = await estatisticaRep.bairro()
      res.status(200).json(ocorrencias)
    } catch (e) {
      next(e)
    }
  })
  app.get('/api/estatistica/periodo', async (req, res, next) => {
    try {
      const periodos = await estatisticaRep.periodo()
      res.status(200).json(periodos)
    } catch (e) {
      next(e)
    }
  })
}
