<template>
    <view class="index">
        <decoration :pages="state.pages" />
        <view class="article"> </view>
    </view>
</template>

<script setup lang="ts">
import { getIndex } from '@/api/shop'
import { reactive } from 'vue'
const state = reactive({
    pages: []
})
const getData = async () => {
    const data = await getIndex()
    state.pages = JSON.parse(data.pages)
    console.log(state.pages)
}
getData()
</script>

<style></style>
