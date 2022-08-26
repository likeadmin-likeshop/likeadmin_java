import { createSSRApp } from 'vue'
// import { createPinia } from 'pinia'
import App from './App.vue'
import plugins from './plugins'
import './styles/index.scss'
export function createApp() {
    const app = createSSRApp(App)
    console.log(app.config.globalProperties)
    app.use(plugins)
    return {
        app
    }
}
