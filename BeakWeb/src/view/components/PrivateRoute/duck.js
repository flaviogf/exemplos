import { autenticacaoApplication } from '@/application/autenticacaoApplication'

export const TYPES = {
  VERIFICA_AUTENTICACAO: 'privateRoute/verificaAutenticacao',
}

const STATE = {
  autenticado: autenticacaoApplication.verificaAutenticacao(),
}

export const reducer = (state = STATE, action) => {
  switch (action.type) {
    case TYPES.VERIFICA_AUTENTICACAO:
      return { ...state, autenticado: action.payload.autenticado }
    default:
      return state
  }
}

export const verificaAutenticacao = () => (dispatch) => {
  const autenticado = autenticacaoApplication.verificaAutenticacao()
  dispatch({
    type: TYPES.VERIFICA_AUTENTICACAO,
    payload: {
      autenticado,
    },
  })
}
