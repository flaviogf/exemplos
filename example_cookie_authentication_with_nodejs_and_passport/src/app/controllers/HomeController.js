class HomeController {
  constructor({ logger }) {
    this.logger = logger
  }

  index(req, res) {
    const { user } = req

    this.logger.info(`|> User ${user.name} is accessing HomeController.index <|`)

    return res.render('home/index', { title: 'Home', user: user.name })
  }
}

export default HomeController
