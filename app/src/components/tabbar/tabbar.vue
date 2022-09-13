<template>
    <u-tabbar v-bind="tabbarStyle" :list="tabbarList" @change="handleChange"></u-tabbar>
</template>

<script lang="ts" setup>
import { useAppStore } from '@/stores/app'
import { currentPage, navigateTo } from '@/utils/util'
import { onLoad } from '@dcloudio/uni-app'
import { computed, onMounted, ref } from 'vue'
const appStore = useAppStore()
const tabbarList = computed(() => {
    return appStore.getTabbarConfig.map((item: any) => ({
        iconPath: item.unselected,
        selectedIconPath: item.selected,
        text: item.name,
        link: JSON.parse(item.link),
        pagePath: JSON.parse(item.link).path
    }))
})

const tabbarStyle = computed(() => ({
    activeColor: appStore.getStyleConfig.selectedColor,
    inactiveColor: appStore.getStyleConfig.defaultColor
}))
const handleChange = (index: number) => {
    const selectTab = tabbarList.value[index]
    navigateTo(selectTab.link, 'reLaunch')
}
// onMounted(() => {
//     const page = currentPage()
//     console.log(page)
// })
</script>
