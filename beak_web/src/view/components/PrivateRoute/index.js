import { NaoAutenticado } from '@/view/components/NaoAutenticado'
import { verificaAutenticacao } from '@/view/components/PrivateRoute/duck'
import React, { Component } from 'react'
import { connect } from 'react-redux'
import { Route } from 'react-router-dom'
import { bindActionCreators } from 'redux'

const mapStateToProps = state => ({
  ...state.privateRoute,
})

const mapDispatchToProps = dispatch => bindActionCreators({ verificaAutenticacao }, dispatch)

export const PrivateRoute = connect(
  mapStateToProps,
  mapDispatchToProps,
)(
  class extends Component {
    componentWillMount() {
      this.props.verificaAutenticacao()
    }

    render() {
      const { component: Componente, autenticado, ...rest } = this.props
      return (
        <Route
          {...rest}
          render={props => (autenticado ? <Componente {...props} /> : <NaoAutenticado />)}
        />
      )
    }
  },
)
