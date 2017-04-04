var path = require('path');
var webpack = require('webpack');

module.exports = {
  entry: './src/js/WebsocketJ.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, './dist')
  },
  module: {
    loaders: [
      {test: /\.css$/, loader: 'style-loader!css-loader'}
    ]
  }
};