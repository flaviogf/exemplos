const validator = require('express-validator')

module.exports = (app) => {
  app.use(validator())
}
