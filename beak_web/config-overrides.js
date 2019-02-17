const { injectBabelPlugin } = require('react-app-rewired')

const rootImport = [
  'root-import',
  {
    rootPathSuffix: './src',
    rootPathPrefix: '@',
  },
]

module.exports = config => injectBabelPlugin(rootImport, config)
