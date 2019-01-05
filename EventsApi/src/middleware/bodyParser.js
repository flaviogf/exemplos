const bodyParser = require('body-parser')

module.exports = (app) => {
  app.use(bodyParser.urlencoded({ extended: true, limit: '3mb' }))
  app.use(bodyParser.json({ limit: '3mb' }))
}
