import { resolve } from 'path'
import express from 'express'
import { createContainer, asClass, asValue } from 'awilix'
import { scopePerRequest } from 'awilix-express'
import { Sequelize } from 'sequelize'
import { createLogger, transports } from 'winston'
import passport from 'passport'
import { Strategy } from 'passport-local'
import bcrypt from 'bcryptjs'
import session from 'express-session'
import flash from 'express-flash'
import databaseConfig from './config/database'
import routes from './routes'
import Database from './database'

class App {
  constructor() {
    this.server = express()
    this.container = createContainer()

    this.configureServices()
    this.configure()
  }

  configureServices() {
    this.server.set('view engine', 'ejs')
    this.server.set('views', resolve(__dirname, 'views'))

    this.container.register({
      logger: asValue(
        createLogger({
          level: 0,
          transports: [new transports.Console()],
        })
      ),
      connection: asValue(new Sequelize(databaseConfig)),
      database: asClass(Database),
    })
  }

  configure() {
    this.server.use('/static', express.static(resolve(__dirname, 'static')))

    this.server.use(express.urlencoded({ extended: true }))

    this.server.use(flash())

    this.server.use(
      session({ secret: 'AAAA', resave: false, saveUninitialized: false })
    )

    this.server.use(scopePerRequest(this.container))

    passport.serializeUser((user, done) => {
      return done(null, user.id)
    })

    passport.deserializeUser(async (id, done) => {
      const database = this.container.resolve('database')

      const user = await database.users.findOne({
        where: {
          id,
        },
      })

      done(null, user)
    })

    passport.use(
      new Strategy(
        { usernameField: 'email', passwordField: 'password' },
        async (email, password, done) => {
          const database = this.container.resolve('database')

          const user = await database.users.findOne({
            where: {
              email,
            },
          })

          if (!user) {
            return done(null, false)
          }

          if (!(await bcrypt.compare(password, user.password_hash))) {
            return done(null, false)
          }

          return done(null, user)
        }
      )
    )

    this.server.use(passport.initialize())

    this.server.use(passport.session())

    this.server.use(routes)
  }

  listen() {
    this.server.listen(3333, () => console.log('Its running on port 3333'))
  }
}

export default new App()
