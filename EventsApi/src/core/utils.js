const status = require('http-status')

module.exports = {
  verificarErros: async (req, res, next) => {
    const result = await req.getValidationResult()
    if (result.isEmpty()) {
      return next()
    }
    const erros = result.formatWith((e) => e.msg).array()
    console.log(erros)
    res.status(status.BAD_REQUEST).json(erros)
  }
}
