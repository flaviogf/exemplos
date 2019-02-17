import { usuarioService } from '@/domain/services/usuarioService'

export const usuarioApplication = {
  busca() {
    return usuarioService.busca()
  },
}
