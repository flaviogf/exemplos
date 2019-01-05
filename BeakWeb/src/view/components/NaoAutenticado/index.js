import React from 'react'
import { Link } from 'react-router-dom'

export const NaoAutenticado = () => (
  <>
    <h1>Não Autenticado</h1>
    <Link to="/autenticacao">Autenticar</Link>
  </>
)
