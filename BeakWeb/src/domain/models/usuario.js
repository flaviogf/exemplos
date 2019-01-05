export class Usuario {
  constructor({ login, nome, token }) {
    Object.assign(this, { login, nome, token })
  }

  static of({ login, nome, token }) {
    return new Usuario({ login, nome, token })
  }
}
