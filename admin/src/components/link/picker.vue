<template>
    <div class="link-picker flex-1" @click="popupRef?.open()">
        <el-input
            class="cursor-pointer"
            :model-value="modelValue?.name ?? modelValue?.path"
            placeholder="请选择链接"
            readonly
        >
            <template #suffix>
                <icon v-if="!modelValue?.path" name="el-icon-ArrowRight" />
                <icon v-else name="el-icon-Close" @click.stop="emit('update:modelValue', {})" />
            </template>
        </el-input>
        <popup ref="popupRef" width="700px" title="链接选择" @confirm="handleConfirm">
            <link-content v-model="activeLink" />
        </popup>
    </div>
</template>

<script lang="ts" setup>
import { LinkTypeEnum, type Link } from '.'
import LinkContent from './index.vue'
import Popup from '@/components/popup/index.vue'
const props = defineProps({
    modelValue: {
        type: Object
    }
})
const emit = defineEmits<{
    (event: 'update:modelValue', value: any): void
}>()

const popupRef = shallowRef<InstanceType<typeof Popup>>()
const activeLink = ref<Link>({ path: '', type: LinkTypeEnum.SHOP_PAGES })
const handleConfirm = () => {
    emit('update:modelValue', activeLink.value)
}
watch(
    () => props.modelValue,
    (value) => {
        if (value?.type) {
            activeLink.value = value as Link
        }
    },
    {
        immediate: true
    }
)
</script>
