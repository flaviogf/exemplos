import { Routes } from '@/view/routes'
import { store } from '@/view/store'
import React from 'react'
import { Provider } from 'react-redux'

export const App = () => (
  <Provider store={store}>
    <Routes />
  </Provider>
)
