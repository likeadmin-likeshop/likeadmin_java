<template>
    <div class="shop-pages">
        <div class="link-list flex flex-wrap">
            <div
                class="link-item border border-br px-5 py-[5px] rounded-[3px] cursor-pointer"
                v-for="(item, index) in linkList"
                :class="{ 'border-primary text-primary': modelValue.path == item.path }"
                :key="index"
                @click="handleSelect(item)"
            >
                {{ item.name }}
            </div>
        </div>
    </div>
</template>

<script lang="ts" setup>
import type { PropType } from 'vue'
import { LinkTypeEnum, type Link } from '.'

defineProps({
    modelValue: {
        type: Object as PropType<Link>,
        default: () => ({})
    }
})
const emit = defineEmits<{
    (event: 'update:modelValue', value: Link): void
}>()

const linkList = ref([
    {
        path: '/pages/index/index',
        name: '商城首页',
        type: LinkTypeEnum.SHOP_PAGES
    }
])

const handleSelect = (value: Link) => {
    emit('update:modelValue', value)
}
</script>
