const path = require('path');
const webpack = require('webpack');

module.exports = {
    mode: 'development',
    entry: ['./src/main/js/index.js', "webpack/hot/dev-server"],
    devtool: 'sourcemaps',
    devServer: {
        publicPath: 'http://locahost:8081/',
        contentBase: './src/main/resources/static/built',
        port: 8081,
        headers: {
            'Access-Control-Allow-Origin': '*'
        },
        hot: true
    },
    cache: true,
    output: {
        path: path.resolve(__dirname, "build"),
        publicPath: 'http://localhost:8081/',
        filename: 'bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader'
                }]
            }
        ]
    },
    plugins: [
        new webpack.HotModuleReplacementPlugin()
    ]
};