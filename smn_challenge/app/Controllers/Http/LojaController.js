const Loja = use('App/Models/Loja');

class LojaController {
  index() {
    return Loja.query()
      .atividades()
      .fetch();
  }

  show({ params: { id } }) {
    return Loja.query()
      .atividades()
      .where({ id })
      .first();
  }
}

module.exports = LojaController;
