import { autentica } from '@/view/pages/Autenticacao/duck'
import React from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

const mapDispatchToProps = dispatch => bindActionCreators({ autentica }, dispatch)

export const Autenticacao = connect(
  null,
  mapDispatchToProps,
)((props) => {
  let inputLogin
  let inputSenha

  const sucessoLogin = () => {
    props.history.push('/home')
  }

  const falhaLogin = (mensagens) => {
    mensagens.forEach(alert)
  }

  const onSubmit = (e) => {
    e.preventDefault()
    const [{ value: login }, { value: senha }] = [inputLogin, inputSenha]
    props.autentica({ login, senha }, { sucesso: sucessoLogin, falha: falhaLogin })
  }

  return (
    <form onSubmit={onSubmit}>
      <input
        placeholder="Login"
        ref={(input) => {
          inputLogin = input
        }}
      />
      <input
        placeholder="Senha"
        type="password"
        ref={(input) => {
          inputSenha = input
        }}
      />
      <button type="submit">Entrar</button>
    </form>
  )
})
