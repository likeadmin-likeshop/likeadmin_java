<template>
    <div class="code-generation">
        <el-card class="!border-none" shadow="never">
            <el-form class="mb-[-16px]" :model="formData" inline>
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
        </el-card>
        <el-card class="!border-none mt-4" shadow="never" v-loading="pager.loading">
            <div class="flex">
                <data-table
                    v-perms="['gen:importTable']"
                    class="inline-block mr-[10px]"
                    @success="getLists"
                >
                    <el-button type="primary">
                        <template #icon>
                            <icon name="el-icon-Plus" />
                        </template>
                        导入数据表
                    </el-button>
                </data-table>
                <el-button
                    v-perms="['gen:delTable']"
                    :disabled="!selectData.length"
                    @click="handleDelete()"
                    type="danger"
                >
                    <template #icon>
                        <icon name="el-icon-Delete" />
                    </template>
                    删除
                </el-button>
                <el-button
                    v-perms="['gen:genCode', 'gen:downloadCode']"
                    :disabled="!selectData.length"
                    @click="handleGenerate(selectData)"
                >
                    生成代码
                </el-button>
            </div>
            <div class="mt-4">
                <el-table
                    :data="pager.lists"
                    size="large"
                    @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="55" />
                    <el-table-column label="表名称" prop="tableName" min-width="180" />
                    <el-table-column label="表描述" prop="tableComment" min-width="180" />
                    <el-table-column label="创建时间" prop="createTime" min-width="180" />
                    <el-table-column label="更新时间" prop="updateTime" min-width="180" />
                    <el-table-column label="操作" width="160" fixed="right">
                        <template #default="{ row }">
                            <div class="flex items-center">
                                <el-button
                                    v-perms="['gen:previewCode']"
                                    type="primary"
                                    link
                                    @click="handlePreview(row.id)"
                                >
                                    预览
                                </el-button>

                                <el-button type="primary" link v-perms="['gen:editTable']">
                                    <router-link
                                        :to="{
                                            path: getRoutePath('gen:editTable'),
                                            query: {
                                                id: row.id
                                            }
                                        }"
                                    >
                                        编辑
                                    </router-link>
                                </el-button>
                                <el-dropdown
                                    class="ml-2"
                                    @command="handleCommand($event, row)"
                                    v-perms="[
                                        'gen:genCode',
                                        'gen:downloadCode',
                                        'gen:syncTable',
                                        'gen:delTable'
                                    ]"
                                >
                                    <el-button type="primary" link>
                                        更多
                                        <icon name="el-icon-ArrowDown" :size="14" />
                                    </el-button>

                                    <template #dropdown>
                                        <el-dropdown-menu>
                                            <div v-perms="['gen:genCode', 'gen:downloadCode']">
                                                <el-dropdown-item command="generate">
                                                    <el-button type="primary" link>
                                                        生成代码
                                                    </el-button>
                                                </el-dropdown-item>
                                            </div>
                                            <div v-perms="['gen:syncTable']">
                                                <el-dropdown-item command="sync">
                                                    <el-button type="primary" link>
                                                        同步
                                                    </el-button>
                                                </el-dropdown-item>
                                            </div>
                                            <div v-perms="['gen:delTable']">
                                                <el-dropdown-item command="delete">
                                                    <el-button type="danger" link> 删除 </el-button>
                                                </el-dropdown-item>
                                            </div>
                                        </el-dropdown-menu>
                                    </template>
                                </el-dropdown>
                            </div>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="flex justify-end mt-4">
                <pagination v-model="pager" @change="getLists" />
            </div>
        </el-card>
        <code-preview
            v-if="previewState.show"
            v-model="previewState.show"
            :code="previewState.code"
        />
    </div>
</template>

<script lang="ts" setup name="codeGenerate">
import {
    generateTable,
    syncColumn,
    generateDelete,
    generatePreview,
    generateCode,
    downloadCode
} from '@/api/tools/code'
import { usePaging } from '@/hooks/usePaging'
import DataTable from '../components/data-table.vue'
import CodePreview from '../components/code-preview.vue'
import feedback from '@/utils/feedback'
import { streamFileDownload } from '@/utils/file'
import { getRoutePath } from '@/router'

const formData = reactive({
    tableName: '',
    tableComment: ''
})

const previewState = reactive({
    show: false,
    loading: false,
    code: {}
})

const { pager, getLists, resetParams, resetPage } = usePaging({
    fetchFun: generateTable,
    params: formData
})

const selectData = ref<any[]>([])
const handleSelectionChange = (val: any[]) => {
    selectData.value = val
}

const handleSync = async (id: number) => {
    await feedback.confirm('确定要同步表结构？')
    await syncColumn({ id })
    feedback.msgSuccess('操作成功')
}

const handleDelete = async (ids?: number[]) => {
    if (!ids) ids = selectData.value.map(({ id }) => id)
    await feedback.confirm('确定要删除？')
    await generateDelete({ ids })
    feedback.msgSuccess('删除成功')
    getLists()
}

const handlePreview = async (id: number) => {
    const data: any = await generatePreview({ id })
    previewState.code = data
    previewState.show = true
}

const handleGenerate = async (selectData: any[]) => {
    const downloadTables = getTables(selectData, 0)
    const genTables = getTables(selectData, 1)
    if (downloadTables) {
        const file = await downloadCode({ tables: downloadTables })
        streamFileDownload(file, 'likeadmin-curd.zip')
    }
    if (genTables) {
        await generateCode({ tables: genTables })
        feedback.msgSuccess('生成成功')
    }
}

const getTables = (selectData: any[], type: 0 | 1) => {
    return selectData
        .filter(({ genType }) => genType == type)
        .map(({ tableName }) => tableName)
        .join()
}

const handleCommand = (command: any, row: any) => {
    switch (command) {
        case 'generate':
            handleGenerate([row])
            break
        case 'sync':
            handleSync(row.id)
            break
        case 'delete':
            handleDelete([row.id])
    }
}

onActivated(() => {
    getLists()
})

getLists()
</script>
