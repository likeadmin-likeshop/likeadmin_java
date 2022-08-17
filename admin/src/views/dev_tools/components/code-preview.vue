<template>
    <div class="code-preview">
        <el-dialog v-model="show" width="900px" title="代码预览">
            <el-tabs v-model="activeTab">
                <el-tab-pane
                    v-for="(item, key, index) in code"
                    :label="key"
                    :name="`index${index}`"
                    :key="key"
                >
                    <div class="flex" style="height: 50vh">
                        <el-scrollbar class="flex-1">
                            <highlightjs autodetect :code="item" />
                        </el-scrollbar>
                        <div>
                            <el-button @click="handleCopy(item)" type="primary" link>
                                <template #icon>
                                    <icon name="el-icon-CopyDocument" />
                                </template>
                                复制
                            </el-button>
                        </div>
                    </div>
                </el-tab-pane>
            </el-tabs>
        </el-dialog>
    </div>
</template>

<script lang="ts" setup>
import feedback from '@/utils/feedback'
import useClipboard from 'vue-clipboard3'

const props = defineProps<{
    modelValue: boolean
    code: Record<string, string>
}>()

const emit = defineEmits<{
    (event: 'update:modelValue', value: boolean): void
}>()
const { toClipboard } = useClipboard()

const activeTab = ref('index0')

const handleCopy = async (text: string) => {
    try {
        await toClipboard(text)
        feedback.msgSuccess('复制成功')
    } catch (e) {
        feedback.msgError('复制失败')
    }
}

const show = computed<boolean>({
    get() {
        return props.modelValue
    },
    set(value) {
        emit('update:modelValue', value)
    }
})
</script>
