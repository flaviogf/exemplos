module.exports = (app) => {
  app.use(app.core.auth.initialize())
}
