import { Usuario } from '@/domain/models/usuario'
import { usuarioRepository } from '@/domain/repositories/usuarioRepository'
import { requestService } from '@/infra/services/requestService'

export const autenticacaoService = {
  async autentica({ login, senha }) {
    const { body } = await requestService.post('/autenticacao', { login, senha })
    const nome = 'nome'
    const token = 'token'
    const usuario = Usuario.of({ ...body, nome, token })
    return { conteudo: usuarioRepository.insere(usuario) }
  },
  verificaAutenticacao() {
    return usuarioRepository.buscaToken() != null
  },
}
