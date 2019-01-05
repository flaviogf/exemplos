import React from 'react'
import PropTypes from 'prop-types'

export const Cartao = (props) => (
  <div className="card my-3">
    <div className="card-body">
      {props.children}
    </div>
  </div>
)

Cartao.proptypes = {
  children: PropTypes.node.isRequired
}
