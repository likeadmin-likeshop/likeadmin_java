/** @type {import('tailwindcss').Config} */
module.exports = {
    content: ['./index.html', './src/**/*.{html,js,ts,jsx,tsx,vue}'],
    theme: {
        colors: {
            white: '#ffffff',
            black: '#000000',
            main: '#333333',
            content: '#666666',
            muted: '#999999',
            light: '#e5e5e5',
            primary: {
                DEFAULT: '#4173ff'
            },
            success: '#5ac725',
            warning: '#f9ae3d',
            error: '#f56c6c',
            info: '#909399',
            page: '#f6f6f6'
        }
    },
    plugins: [],
    corePlugins: {
        preflight: false
    }
}
