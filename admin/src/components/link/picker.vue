<template>
    <div class="link-picker">
        <popup width="700px" title="链接选择" @confirm="handleConfirm">
            <template v-slot:trigger>
                <div class="cursor-pointer">
                    <slot>
                        <el-input
                            :model-value="modelValue?.name ?? modelValue?.path"
                            placeholder="请选择链接"
                            readonly
                        >
                            <template #suffix>
                                <icon v-if="!modelValue?.path" name="el-icon-ArrowRight" />
                                <icon
                                    v-else
                                    name="el-icon-Close"
                                    @click.stop="emit('update:modelValue', {})"
                                />
                            </template>
                        </el-input>
                    </slot>
                </div>
            </template>
            <link-content v-model="activeLink" />
        </popup>
    </div>
</template>

<script lang="ts" setup>
import type { Link } from '.'
import LinkContent from './index.vue'
const props = defineProps({
    modelValue: {
        type: Object
    }
})
const emit = defineEmits<{
    (event: 'update:modelValue', value: any): void
}>()
const activeLink = ref<Link>()
const handleConfirm = () => {
    emit('update:modelValue', activeLink.value)
}
watch(
    () => props.modelValue,
    (value) => {
        activeLink.value = value as Link
    },
    {
        immediate: true
    }
)
</script>
