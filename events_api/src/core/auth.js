const passport = require('passport')
const { ExtractJwt, Strategy } = require('passport-jwt')
const jwt = require('jwt-simple')

module.exports = (app) => {
  const usuarioRep = app.repository.usuario
  passport.use(new Strategy({ secretOrKey: process.env.SECRET, jwtFromRequest: ExtractJwt.fromAuthHeaderWithScheme('jwt') }, async (payload, done) => {
    const usuario = await usuarioRep.buscarPorId(payload.id)
    if (usuario) {
      done(null, usuario)
      return
    }
    done(null, false)
  }))
  return {
    initialize: () => {
      return passport.initialize()
    },
    authenticate: () => {
      return passport.authenticate('jwt', { session: false })
    }
  }
}
