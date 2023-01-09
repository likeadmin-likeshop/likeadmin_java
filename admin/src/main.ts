import { createApp } from 'vue'
import App from './App.vue'
import install from './install'
import './permission'
import './styles/index.scss'
import 'virtual:svg-icons-register'

const app = createApp(App)
console.log(app)
app.use(install)
app.mount('#app')
