class AutenticacaoController {
  async autentica({ request, auth }) {
    const { email, senha } = request.only(['email', 'senha']);
    const { token } = await auth.attempt(email, senha);
    return { token };
  }
}

module.exports = AutenticacaoController;
