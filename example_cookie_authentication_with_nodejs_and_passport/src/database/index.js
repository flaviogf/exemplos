import User from '../app/models/User'

class Database {
  constructor({ connection }) {
    this.users = User.init({ sequelize: connection })
  }
}

export default Database
