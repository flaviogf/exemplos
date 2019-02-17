const status = require('http-status')

module.exports = (app) => {
  app.get('/api/ping', (req, res) => {
    res.status(status.OK).json({ status: `[API - ${process.env.NAME}] rodando` })
  })
}
