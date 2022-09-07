<template>
    <view class="banner h-[400rpx] bg-white">
        <swiper
            class="swiper h-full"
            indicator-dots
            indicator-active-color="#4173ff"
            :autoplay="true"
        >
            <swiper-item v-for="(item, index) in content.data" :key="index">
                <u-image mode="aspectFit" width="100%" height="100%" :src="item.image" />
            </swiper-item>
        </swiper>
    </view>
</template>

<script setup lang="ts">
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
</script>

<style></style>
