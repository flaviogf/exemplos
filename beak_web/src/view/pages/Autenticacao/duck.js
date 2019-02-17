import { autenticacaoApplication } from '@/application/autenticacaoApplication'
import { usuarioApplication } from '@/application/usuarioApplication'

export const TYPES = {
  AUTENTICA: 'autenticacao/autentica',
}

const STATE = {
  usuario: usuarioApplication.busca(),
}

export const reducer = (state = STATE, action) => {
  switch (action.type) {
    case TYPES.AUTENTICA:
      return { ...state, usuario: action.payload.usuario }
    default:
      return state
  }
}

export const autentica = ({ login, senha }, { sucesso, falha }) => async (dispatch) => {
  const { mensagens = [], conteudo: usuario } = await autenticacaoApplication.autentica({
    login,
    senha,
  })

  if (mensagens.length) {
    falha(mensagens)
    return
  }

  dispatch({
    type: TYPES.AUTENTICA,
    payload: {
      usuario,
    },
  })

  sucesso()
}
