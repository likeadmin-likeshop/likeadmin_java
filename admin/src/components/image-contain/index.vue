<template>
    <el-image :style="styles" v-bind="props">
        <template v-slot:error>
            <div class="text-tx-secondary flex items-center justify-center bg-white h-full">
                <icon name="el-icon-Picture" :size="30" />
            </div>
        </template>
    </el-image>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import type { CSSProperties } from 'vue'
import { addUnit } from '@/utils/util'
import { imageProps } from 'element-plus'
const props = defineProps({
    width: {
        type: [String, Number],
        default: 'auto'
    },
    height: {
        type: [String, Number],
        default: 'auto'
    },
    radius: {
        type: [String, Number],
        default: 0
    },
    ...imageProps
})

const styles = computed<CSSProperties>(() => {
    return {
        width: addUnit(props.width),
        height: addUnit(props.height),
        borderRadius: addUnit(props.radius)
    }
})
</script>

<style lang="scss" scoped>
.el-image {
    font-size: 0;
    @apply bg-page;
    .el-image__error {
        @apply text-xs;
    }
}
</style>
