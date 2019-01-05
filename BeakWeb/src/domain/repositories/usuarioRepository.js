import { Usuario } from '@/domain/models/usuario'

export const usuarioRepository = {
  insere(usuario) {
    localStorage.setItem('usuario', JSON.stringify(usuario))
    localStorage.setItem('token', usuario.token)
    return usuario
  },
  buscaToken() {
    return localStorage.getItem('token')
  },
  buscaUsuario() {
    const json = localStorage.getItem('usuario')
    if (!json) return null
    const usuario = JSON.parse(json)
    return Usuario.of(usuario)
  },
}
