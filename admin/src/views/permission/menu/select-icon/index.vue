<template>
    <div class="select-icon">
        <div>
            <el-popover placement="bottom" :width="400" trigger="click">
                <template #reference>
                    <el-button style="margin-right: 16px">
                        <el-icon><arrow-down /> </el-icon>
                    </el-button>
                </template>

                <div v-for="(item, index) in selectIcon" :key="index"></div>
            </el-popover>
        </div>
    </div>
</template>

<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue'

const selectIcon = ref<any>([])
</script>

<style lang="scss" scoped></style>
