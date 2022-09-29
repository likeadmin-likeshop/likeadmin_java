<template>
    <div class="data-table">
        <popup
            ref="popupRef"
            :clickModalClose="false"
            title="选择表"
            width="900px"
            :async="true"
            @confirm="handleConfirm"
        >
            <template #trigger>
                <slot></slot>
            </template>
            <el-form class="ls-form" :model="formData" inline>
                <el-form-item label="表名称">
                    <el-input
                        class="w-[280px]"
                        v-model="formData.tableName"
                        clearable
                        @keyup.enter="resetPage"
                    />
                </el-form-item>
                <el-form-item label="表描述">
                    <el-input
                        class="w-[280px]"
                        v-model="formData.tableComment"
                        clearable
                        @keyup.enter="resetPage"
                    />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
            <div class="m-4" v-loading="pager.loading">
                <el-table
                    height="400"
                    size="large"
                    :data="pager.lists"
                    @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="55" />
                    <el-table-column label="表名称" prop="tableName" min-width="150" />
                    <el-table-column label="表描述" prop="tableComment" min-width="160" />
                    <el-table-column label="创建时间" prop="createTime" min-width="180" />
                </el-table>
            </div>
            <div class="flex justify-end mt-4">
                <pagination v-model="pager" @change="getLists" />
            </div>
        </popup>
    </div>
</template>

<script lang="ts" setup>
import Popup from '@/components/popup/index.vue'
import Pagination from '@/components/pagination/index.vue'
import { usePaging } from '@/hooks/usePaging'
import { dataTable, selectTable } from '@/api/tools/code'
import feedback from '@/utils/feedback'

const emit = defineEmits<{
    (event: 'success'): void
}>()

const popupRef = shallowRef<InstanceType<typeof Popup>>()

const formData = reactive({
    tableName: '', // 表名称
    tableComment: '' // 表描述
})

const { pager, getLists, resetParams, resetPage } = usePaging({
    fetchFun: dataTable,
    params: formData,
    size: 10
})

const selectData = ref<any[]>([])

const handleSelectionChange = (val: any[]) => {
    selectData.value = val.map(({ tableName }) => tableName)
}

const handleConfirm = async () => {
    if (!selectData.value.length) return feedback.msgError('请选择数据表')
    await selectTable({
        tables: selectData.value.join()
    })
    feedback.msgSuccess('导入成功')
    popupRef.value?.close()
    emit('success')
}

watch(
    () => popupRef.value?.visible,
    (value) => {
        if (value) getLists()
    }
)
</script>
