import React from 'react'
import PropTypes from 'prop-types'

export const Conteudo = (props) => (
  <main className="container-fluid">
    {props.children}
  </main>
)

Conteudo.propTypes = {
  children: PropTypes.node.isRequired
}
