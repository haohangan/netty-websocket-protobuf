var path = require('path');
var webpack = require('webpack');

module.exports = {
  entry: './src/cloudjs/WebsocketCloudJ.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, './dist/cloud')
  },
  module: {
    loaders: [
      {test: /\.css$/, loader: 'style-loader!css-loader'}
    ]
  }
};