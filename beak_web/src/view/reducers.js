import { reducer as privateRoute } from '@/view/components/PrivateRoute/duck'
import { reducer as autenticacao } from '@/view/pages/Autenticacao/duck'
import { combineReducers } from 'redux'

export const reducers = combineReducers({
  autenticacao,
  privateRoute,
})
