/* eslint-disable no-underscore-dangle */

import { reducers } from '@/view/reducers'
import { applyMiddleware, compose, createStore } from 'redux'
import thunk from 'redux-thunk'

const middlewares = [thunk]

const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose

export const store = createStore(reducers, composeEnhancers(applyMiddleware(...middlewares)))
