const Contato = use('App/Models/Contato');

class ContatoController {
  async index({ auth }) {
    const user = await auth.getUser();
    return Contato.query()
      .where('user_id', user.id)
      .fetch();
  }

  async store({ request, auth }) {
    const user = await auth.getUser();
    const { nome, telefone } = request.only(['nome', 'telefone']);
    const imagem = `https://picsum.photos/${Math.ceil(Math.random() * 200 + 1)}`;
    return user.contatos().create({ nome, telefone, imagem });
  }

  async show({ params: { id }, auth }) {
    const user = await auth.getUser();
    return Contato.query()
      .where('id', id)
      .andWhere('user_id', user.id)
      .first();
  }

  async update({ params: { id }, request, auth }) {
    const user = await auth.getUser();
    const { nome, telefone } = request.only(['nome', 'telefone']);
    const imagem = `https://picsum.photos/${Math.ceil(Math.random() * 200 + 1)}`;
    await user
      .contatos()
      .where('id', id)
      .andWhere('user_id', user.id)
      .update({ nome, telefone, imagem });
  }

  async destroy({ params: { id }, auth }) {
    const user = await auth.getUser();
    await user
      .contatos()
      .where('id', id)
      .andWhere('user_id', user.id)
      .delete();
  }
}

module.exports = ContatoController;
