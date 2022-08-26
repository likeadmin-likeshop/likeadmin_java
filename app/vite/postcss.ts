import tailwindcss from 'tailwindcss'
import autoprefixer from 'autoprefixer'
import postcssRemToResponsivePixel from 'postcss-rem-to-responsive-pixel'
import postcssWeappTailwindcssRename from 'weapp-tailwindcss-webpack-plugin/postcss'
import { Plugin } from 'postcss'
const postcssPlugin = [
    autoprefixer(),
    tailwindcss(),
    postcssRemToResponsivePixel({
        rootValue: 32,
        propList: ['*'],
        transformUnit: 'rpx'
    }),
    postcssWeappTailwindcssRename()
]

export default postcssPlugin as Plugin[]
