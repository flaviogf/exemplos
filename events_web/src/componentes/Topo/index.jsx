import React from 'react'
import PropTypes from 'prop-types'

export const Topo = (props) => (
  <nav className="navbar navbar-light bg-dark text-white">
    <a className="navbar-brand">{props.titulo}</a>
  </nav>
)

Topo.propTypes = {
  titulo: PropTypes.string.isRequired
}
