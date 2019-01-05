module.exports = (app) => {
  const utils = app.core.utils
  return {
    cadastrarAtualizar: (req, res, next) => {
      req.checkBody('dataAcontecimento')
        .isISO8601()
        .withMessage('Data inválida')
      req.checkBody('resumo')
        .isLength({ min: 10, max: 50 })
        .withMessage('Resumo inválido')
      req.checkBody('periodo')
        .isIn(['MANHÃ', 'TARDE', 'NOITE', 'MADRUGADA'])
        .withMessage('Periodo inválido deve ser MANHÃ, TARDE, NOITE, MADRUGADA')
      req.checkBody('numero')
        .isInt()
        .withMessage('Numero inválido')
      req.checkBody('usuario')
        .isEmpty()
        .withMessage('Usuário não deve ser informado')
      req.checkBody('bairro')
        .isMongoId()
        .withMessage('Bairro inválido')
      req.checkBody('tipoOcorrencia')
        .isMongoId()
        .withMessage('Tipo de ocorrência inválida')
      utils.verificarErros(req, res, next)
    }
  }
}
