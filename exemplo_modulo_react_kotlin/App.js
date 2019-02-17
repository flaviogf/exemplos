import React from 'react'

import {
  Button
} from 'react-native'

import ToastModuleKt from './toast'

export default class App extends React.Component {
  render() {
    return (
      <Button
        onPress={() => ToastModuleKt.showLong('Hello')}
        title="Pressionar"
      />
    )
  }
}
