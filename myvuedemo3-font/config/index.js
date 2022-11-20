'use strict'
// Template version: 1.3.1
// see http://vuejs-templates.github.io/webpack for documentation.

const path = require('path')

module.exports = {
  dev: {
    //配置跨域问题
    // devServer: {
    //   proxy: {
    //     '/api': {
    //       target: 'https://api.wmdb.tv/api/v1/top?type=Imdb&skip=0&limit=20&lang=Cn',
    //       changeOrigin: true,
    //       "secure": true,
    //       pathRewrite: {
    //         '^/api': ''
    //       }
    //     }
    //   }
    // },

    proxyTable: {
      "/api/": {//以/proxy/为开头的适合这个规则
        target: "https://api.wmdb.tv/api/v1/top?type=Imdb&skip=0&limit=20&lang=Cn",//目标地址
        secure: false,//false为http访问，true为https访问
        changeOrigin: true,//跨域访问设置，true代表跨域
        pathRewrite: {//路径改写规则
          "^/api": ""//以/proxy/为开头的改写为''
           //下面这种也行
          //  "^/api":"/list"//以/api/为开头的改写为'/list'
        },
        headers: {//设置请求头伪装成手机端的访问
          "User-Agent": "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Mobile Safari/537.36"
        }
      }
    },
    // Paths
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    proxyTable: {},

    // Various Dev Server settings
    host: 'localhost', // can be overwritten by process.env.HOST
    port: 8080, // can be overwritten by process.env.PORT, if port is in use, a free one will be determined
    autoOpenBrowser: false,
    errorOverlay: true,
    notifyOnErrors: true,
    poll: false, // https://webpack.js.org/configuration/dev-server/#devserver-watchoptions-


    /**
     * Source Maps
     */

    // https://webpack.js.org/configuration/devtool/#development
    devtool: 'cheap-module-eval-source-map',

    // If you have problems debugging vue-files in devtools,
    // set this to false - it *may* help
    // https://vue-loader.vuejs.org/en/options.html#cachebusting
    cacheBusting: true,

    cssSourceMap: true
  },

  build: {
    // Template for index.html
    index: path.resolve(__dirname, '../dist/index.html'),

    // Paths
    assetsRoot: path.resolve(__dirname, '../dist'),
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',

    /**
     * Source Maps
     */

    productionSourceMap: true,
    // https://webpack.js.org/configuration/devtool/#production
    devtool: '#source-map',

    // Gzip off by default as many popular static hosts such as
    // Surge or Netlify already gzip all static assets for you.
    // Before setting to `true`, make sure to:
    // npm install --save-dev compression-webpack-plugin
    productionGzip: false,
    productionGzipExtensions: ['js', 'css'],

    // Run the build command with an extra argument to
    // View the bundle analyzer report after build finishes:
    // `npm run build --report`
    // Set to `true` or `false` to always turn it on or off
    bundleAnalyzerReport: process.env.npm_config_report
  }
}
