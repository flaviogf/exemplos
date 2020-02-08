import bcrypt from 'bcryptjs'

class SignUpController {
  constructor({ database, logger }) {
    this.database = database
    this.logger = logger
  }

  create(req, res) {
    this.logger.info('|> Accessing SignUpController.create <|')

    return res.render('signUp/create', { title: 'Sign Up' })
  }

  async store(req, res) {
    this.logger.info('|> Accessing SignUpController.store <|')

    const { name, email, password } = req.body

    const emailIsAlreadyTaken = await this.database.users.findOne({
      where: {
        email,
      },
    })

    if (emailIsAlreadyTaken) {
      this.logger.info(`|> The email ${email} is already taken <|`)

      req.flash('error', 'This email is already taken.')

      return res.render('signUp/create', { title: 'Sign Up' })
    }

    const password_hash = await bcrypt.hash(password, 8)

    const user = await this.database.users.create({
      name,
      email,
      password_hash,
    })

    this.logger.info(`|> User ${user.name} have been created <|`)

    return res.redirect('/sign-in')
  }
}

export default SignUpController
