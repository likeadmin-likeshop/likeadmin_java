<template>
    <router-view v-if="routerAlive" />
</template>

<script lang="ts">
import { defineComponent, ref, nextTick, provide, onMounted } from 'vue'
import { useAdmin } from './core/hooks/app'
export default defineComponent({
    setup() {
        const { store, route } = useAdmin()
        const routerAlive = ref(true)
        const reload = () => {
            routerAlive.value = false
            nextTick(() => {
                routerAlive.value = true
            })
        }
        provide('reload', reload)
        onMounted(async () => {
            // 获取配置
            const data = await store.dispatch('app/getConfig')
            console.log('data', data)
            // 设置网站logo
            let favicon: HTMLLinkElement = document.querySelector('link[rel="icon"]')!
            if (favicon) {
                favicon.href = data.webFavicon
                return
            }
            favicon = document.createElement('link')
            favicon.rel = 'icon'
            favicon.href = data.webFavicon
            document.head.appendChild(favicon)
        })
        return {
            routerAlive
        }
    }
})
</script>

<style lang="scss">
@import "./assets/font/iconfont.css";
@import "./styles/index.scss";
</style>
