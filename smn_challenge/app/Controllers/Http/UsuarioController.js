const User = use('App/Models/User');

class UsuarioController {
  index() {
    return User.all()
  }

  store({ request }) {
    const user = request.only(['username', 'email', 'password']);
    return User.create(user);
  }
}

module.exports = UsuarioController;
