const isH5 = process.env.UNI_PLATFORM === 'h5'
const isApp = process.env.UNI_PLATFORM === 'app'
const weappTailwindcssDisabled = isH5 || isApp
import vwt from 'weapp-tailwindcss-webpack-plugin/vite'

export function wtwp() {
    return weappTailwindcssDisabled ? undefined : vwt()
}
