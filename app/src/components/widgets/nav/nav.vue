<template>
    <view class="nav pt-[30rpx] pb-[16rpx] bg-white" v-if="content.data.length && content.enabled">
        <view class="nav-item flex flex-wrap">
            <view
                v-for="(item, index) in content.data"
                :key="index"
                class="flex flex-col items-center w-1/5 mb-[30rpx]"
            >
                <u-image width="41px" height="41px" :src="getImageUrl(item.image)" alt="" />
                <view class="mt-[14rpx]">{{ item.name }}</view>
            </view>
        </view>
    </view>
</template>

<script setup lang="ts">
import { useAppStore } from '@/stores/app'

const props = defineProps({
    content: {
        type: Object,
        default: () => ({})
    },
    styles: {
        type: Object,
        default: () => ({})
    }
})

const { getImageUrl } = useAppStore()
</script>

<style></style>
