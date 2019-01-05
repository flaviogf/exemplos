module.exports = (app) => {
  const utils = app.core.utils
  return {
    atualizar: (req, res, next) => {
      req.checkBody('nome')
        .isLength({ min: 6, max: 50 })
        .withMessage('Nome inválido')
      req.checkBody('sobrenome')
        .isLength({ min: 6, max: 50 })
        .withMessage('Sobrenome inválido')
      req.checkBody('email')
        .isEmpty()
        .withMessage('E-mail não pode ser alterado')
      req.checkBody('senha')
        .isEmpty()
        .withMessage('Senha não pode ser alterada')
      utils.verificarErros(req, res, next)
    },
    inserir: (req, res, next) => {
      req.checkBody('nome')
        .isLength({ min: 6, max: 50 })
        .withMessage('Nome inválido')
      req.checkBody('sobrenome')
        .isLength({ min: 6, max: 50 })
        .withMessage('Sobrenome inválido')
      req.checkBody('email')
        .isEmail()
        .withMessage('E-mail inválido')
      req.checkBody('senha')
        .isLength({ min: 6, max: 50 })
        .withMessage('Senha inválida')
      utils.verificarErros(req, res, next)
    }
  }
}
