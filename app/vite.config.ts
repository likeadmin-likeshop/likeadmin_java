import { defineConfig } from 'vite'
import uni from '@dcloudio/vite-plugin-uni'
import postcssPlugins from './vite/postcss'
import { wtwp } from './vite/wtwp'
// https://vitejs.dev/config/
export default defineConfig({
    plugins: [uni(), wtwp()],
    css: {
        postcss: {
            plugins: postcssPlugins
        }
    }
})
