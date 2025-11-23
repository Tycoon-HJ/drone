// main.js
import { createApp } from 'vue'
import App from './App.vue'
import router from './router' // 我们将创建这个文件
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css' // 引入 Element Plus 样式
import * as ElementPlusIconsVue from '@element-plus/icons-vue' // 引入图标
import i18n from './i18n' // 引入配置好的 i18n

const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.use(i18n) // 挂载
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.mount('#app')