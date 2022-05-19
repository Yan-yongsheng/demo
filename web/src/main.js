import { createApp } from 'vue'
import App from './App.vue'
import router from './router/router.js'
import { ElMessage, ElLoading } from 'element-plus'
import "element-plus/dist/index.css"
import axios from 'axios'

const app = createApp(App)
app.use(router)
app.config.globalProperties.$message = ElMessage

axios.defaults.baseURL = "http://localhost:3000"
let loadingInstance = null
axios.interceptors.request.use(config => {
    loadingInstance = ElLoading.service({
        fullscreen: true,
        text: 'Loading'
    })
    return config
})

axios.interceptors.response.use(config => {
    loadingInstance.close()
    return config
})

app.config.globalProperties.$http = axios

app.mount('#app')
