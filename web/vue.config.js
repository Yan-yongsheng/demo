const Components = require('unplugin-vue-components/webpack')
const { ElementPlusResolver } = require('unplugin-vue-components/resolvers')
module.exports = {
    configureWebpack: {
        plugins: [
            require('unplugin-vue-components/webpack')({ /* options */
            }),
            Components({
                resolvers: [ElementPlusResolver()],
            })
        ],

    },

    devServer: {
        port: 3000,
        proxy: 'http://localhost:9001'
    }
}