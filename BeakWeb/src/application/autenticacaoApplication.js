import { autenticacaoService } from '@/domain/services/autenticacaoService'

export const autenticacaoApplication = {
  autentica({ login, senha }) {
    return autenticacaoService.autentica({ login, senha })
  },
  verificaAutenticacao() {
    return autenticacaoService.verificaAutenticacao()
  },
}
