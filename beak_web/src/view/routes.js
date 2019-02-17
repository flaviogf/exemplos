import { PrivateRoute } from '@/view/components/PrivateRoute'
import { Autenticacao } from '@/view/pages/Autenticacao'
import { Home } from '@/view/pages/Home'
import React from 'react'
import {
  BrowserRouter as Router, Redirect, Route, Switch,
} from 'react-router-dom'

export const Routes = () => (
  <Router>
    <Switch>
      <Route path="/autenticacao" component={Autenticacao} />
      <PrivateRoute path="/home" component={Home} />
      <Redirect from="*" to="/home" />
    </Switch>
  </Router>
)
