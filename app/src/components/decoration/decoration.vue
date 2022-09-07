<template>
    <view>
        <view v-for="(item, index) in pages" :key="index">
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
    </view>
</template>

<script setup lang="ts">
import { PropType } from 'vue'

defineProps({
    pages: {
        type: Array as PropType<any[]>
    }
})
</script>

<style></style>
