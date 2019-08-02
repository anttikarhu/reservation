var path = require('path');

module.exports = {
    mode: 'development',
    entry: './src/main/js/App.js',
    devtool: 'sourcemaps',
    devServer: {
        contentBase: './src/main/resources/static/built',
        port: 8081,
        headers: {
            'Access-Control-Allow-Origin': '*'
        }
    },
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }]
            }
        ]
    }
};