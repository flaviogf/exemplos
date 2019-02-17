const webpack = require('webpack')
const path = require('path')

const caminhoEntrada = path.resolve(__dirname, 'src', 'index.js')
const caminhoSaida = path.resolve(__dirname, 'public')

module.exports = {
  entry: ['babel-polyfill', caminhoEntrada],
  output: {
    filename: 'bundle.js',
    path: caminhoSaida
  },
  module: {
    rules: [
      {
        test: /\.jsx?$/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: [
              'react',
              'env'
            ],
            plugins: [
              'transform-class-properties',
              'transform-react-jsx'
            ]
          }
        },
        exclude: [/node_modules/]
      },
      {
        test: /(.styl|.css)$/,
        use: [
          { loader: 'style-loader' },
          { loader: 'css-loader' },
          { loader: 'stylus-loader' },
        ]
      }
    ]
  },
  resolve: {
    extensions: ['.js', '.jsx', '.json']
  },
  plugins: [
    new webpack.ProvidePlugin({
      React: 'react'
    })
  ],
  devServer: {
    contentBase: caminhoSaida,
    compress: true,
    port: 8080
  },
  mode: 'development'
}
