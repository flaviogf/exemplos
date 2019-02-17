import { usuarioRepository } from '@/domain/repositories/usuarioRepository'

export const usuarioService = {
  busca() {
    return usuarioRepository.buscaUsuario()
  },
}
