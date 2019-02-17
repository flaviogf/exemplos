module.exports = (app) => {
  const utils = app.core.utils
  return {
    inserirAtualizar: (req, res, next) => {
      req.checkBody('descricao')
        .notEmpty()
        .withMessage('Descrição inválida')
      req.checkBody('nomenclatura')
        .notEmpty()
        .withMessage('Nomenclatura inválida')
      utils.verificarErros(req, res, next)
    }
  }
}
