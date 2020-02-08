class SignInController {
  constructor({ logger }) {
    this.logger = logger
  }

  create(req, res) {
    this.logger.info('|> Accessing SignInController.create <|')

    return res.render('signIn/create', { title: 'Sign In' })
  }
}

export default SignInController
