<template>
    <view class="content">
        <z-paging ref="paging" v-model="dataList">
            <!-- 之后-vue3 -->
            <template v-slot:top>
                <view>我是固定在顶部的view</view>
            </template>
        </z-paging>
    </view>
</template>

<script setup lang="ts">
import { ref } from 'vue'

const dataList = ref([])
</script>

<style></style>
