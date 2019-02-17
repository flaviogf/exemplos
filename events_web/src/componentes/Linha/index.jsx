import React from 'react'
import PropTypes from 'prop-types'

export const Linha = (props) => (
  <div className="row">
    {props.children}
  </div>
)

Linha.propTypes = {
  children: PropTypes.node.isRequired
}
