<template>
    <div class="decoration-pages">
        <el-card shadow="never" class="!border-none flex-1" :body-style="{ height: '100%' }">
            <div class="flex h-full items-start">
                <el-scrollbar>
                    <Menu v-model="activeMenu" :menus="menus" />
                </el-scrollbar>
                <preview v-model="selectWidgetIndex" :pageData="getPageData" />
                <el-scrollbar class="flex-1">
                    <attr-setting :widget="getSelectWidget" />
                </el-scrollbar>
            </div>
        </el-card>
        <footer-btns>
            <el-button type="primary" @click="setData">保存</el-button>
        </footer-btns>
    </div>
</template>
<script lang="ts" setup>
import Menu from '../component/pages/menu.vue'
import Preview from '../component/pages/preview.vue'
import AttrSetting from '../component/pages/attr-setting.vue'
import widgets from '../component/widgets'
import { getDecoratePages, setDecoratePages } from '@/api/decoration'
import feedback from '@/utils/feedback'
enum pagesTypeEnum {
    HOME = '1',
    USER = '2',
    SERVICE = '3'
}

const generatePageData = (widgetNames: string[]) => {
    return widgetNames.map((widgetName) => widgets[widgetName]?.options() || {})
}

const menus: Record<
    string,
    {
        id: number
        name: string
        pageData: any[]
    }
> = reactive({
    [pagesTypeEnum.HOME]: {
        id: 1,
        pageType: 1,
        name: '商城首页',
        pageData: generatePageData(['search', 'banner', 'nav'])
    },
    [pagesTypeEnum.USER]: {
        id: 2,
        pageType: 2,
        name: '个人中心',
        pageData: generatePageData(['banner'])
    },
    [pagesTypeEnum.SERVICE]: {
        id: 3,
        pageType: 3,
        name: '客服设置',
        pageData: generatePageData(['banner'])
    }
})

const activeMenu = ref('1')
const selectWidgetIndex = ref(-1)
const getPageData = computed(() => {
    return menus[activeMenu.value]?.pageData ?? []
})
const getSelectWidget = computed(() => {
    return menus[activeMenu.value]?.pageData[selectWidgetIndex.value] ?? ''
})

const getData = async () => {
    const data = await getDecoratePages({ id: activeMenu.value })
    menus[String(data.id)].pageData = JSON.parse(data.pageData)
}
const setData = async () => {
    await setDecoratePages({
        ...menus[activeMenu.value],
        pageData: JSON.stringify(menus[activeMenu.value].pageData)
    })
    getData()
    feedback.msgSuccess('保存成功')
}
watch(
    activeMenu,
    () => {
        selectWidgetIndex.value = getPageData.value.findIndex((item) => !item.disabled)
        getData()
    },
    {
        immediate: true
    }
)
</script>
<style lang="scss" scoped>
.decoration-pages {
    height: calc(100vh - var(--navbar-height) - 80px);
    @apply flex flex-col;
}
</style>
