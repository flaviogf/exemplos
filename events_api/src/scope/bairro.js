module.exports = (app) => {
  const utils = app.core.utils
  return {
    inserirAtualizar: (req, res, next) => {
      req.checkBody('nome')
        .isLength({ min: 3, max: 50 })
        .withMessage('Nome inválido')
      utils.verificarErros(req, res, next)
    }
  }
}
