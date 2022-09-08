<template>
    <view class="user-set">
        个人设置页面
    </view>
</template>

<script setup lang="ts">
import { getDecorate } from '@/api/shop'
import { useUserStore } from '@/stores/user'
import { onShow } from '@dcloudio/uni-app'
import { storeToRefs } from 'pinia'
import { reactive } from 'vue'
const state = reactive<{
    pages: any[]
}>({
    pages: []
})
const getData = async () => {
    const data = await getDecorate({ id: 2 })
    state.pages = JSON.parse(data.pages)
}
const userStore = useUserStore()
const { userInfo, isLogin } = storeToRefs(userStore)
onShow(() => {
    userStore.getUser()
})
getData()
</script>

<style></style>
