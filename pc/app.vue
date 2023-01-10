<script lang="ts" setup>
import { ID_INJECTION_KEY, ElConfigProvider } from 'element-plus'
import zhCn from 'element-plus/es/locale/lang/zh-cn'
import { useAppStore } from './stores/app'
provide(ID_INJECTION_KEY, {
    prefix: 100,
    current: 0
})
const config = {
    locale: zhCn
}
const appStore = useAppStore()
const { pcTitle, pcIco, pcKeywords, pcDesc } = appStore.getWebsiteConfig
useHead({
    title: pcTitle,
    meta: [
        { name: 'description', content: pcDesc },
        { name: 'keywords', content: pcKeywords }
    ],
    link: [
        {
            rel: 'icon',
            href: pcIco
        }
    ]
})
</script>
<template>
    <ElConfigProvider v-bind="config">
        <NuxtLayout>
            <NuxtLoadingIndicator color="#4a5dff" :height="2" />
            <NuxtPage />
        </NuxtLayout>
    </ElConfigProvider>
</template>
