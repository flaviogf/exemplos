module.exports = (app) => {
  const utils = app.core.utils
  return {
    autenticar: (req, res, next) => {
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
