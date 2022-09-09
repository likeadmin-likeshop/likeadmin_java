<template>
    <view class="index">
        <view v-for="(item, index) in state.pages" :key="index">
            <template v-if="item.name == 'search'">
                <w-search :content="item.content" :styles="item.styles" />
            </template>
            <template v-if="item.name == 'banner'">
                <w-banner :content="item.content" :styles="item.styles" />
            </template>
            <template v-if="item.name == 'nav'">
                <w-nav :content="item.content" :styles="item.styles" />
            </template>
        </view>
        <view class="article"> </view>
    </view>
</template>

<script setup lang="ts">
import { getIndex } from '@/api/shop'
import { reactive, ref } from 'vue'
const state = reactive<{
    pages: any[]
}>({
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
