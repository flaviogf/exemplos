import { Router } from 'express'
import { makeInvoker } from 'awilix-express'
import passport from 'passport'
import { ensureLoggedIn } from 'connect-ensure-login'
import HomeController from './app/controllers/HomeController'
import SignInController from './app/controllers/SignInController'
import SignUpController from './app/controllers/SignUpController'

const routes = Router()

const homeController = makeInvoker(HomeController)
const signInController = makeInvoker(SignInController)
const signUpController = makeInvoker(SignUpController)

routes.get('/sign-up', signUpController('create'))
routes.post('/sign-up', signUpController('store'))

routes.get('/sign-in', signInController('create'))
routes.post(
  '/sign-in',
  passport.authenticate('local', {
    failureRedirect: '/sign-in',
    successRedirect: '/',
  })
)

routes.get('/', ensureLoggedIn('/sign-in'), homeController('index'))

export default routes
