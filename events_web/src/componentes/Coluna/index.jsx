import React from 'react'
import PropTypes from 'prop-types'

export const Coluna = (props) => (
  <div className="col">
    {props.children}
  </div>
)

Coluna.proptypes = {
  children: PropTypes.node.isRequired
}
