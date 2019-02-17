const status = require('http-status')

module.exports = (app) => {
  app.use((err, req, res, next) => {
    console.log(err.message)
    res.status(status.INTERNAL_SERVER_ERROR).json('Ocorreu um erro, entre em contato com o administrador')
  })
}
