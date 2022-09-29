<!-- 系统日志 -->
<template>
    <div class="journal">
        <el-card class="!border-none" shadow="never">
            <el-form class="ls-form" :model="formData" inline>
                <el-form-item label="管理员">
                    <el-input
                        class="w-[280px]"
                        placeholder="请输入"
                        v-model="formData.username"
                        clearable
                        @keyup.enter="resetPage"
                    />
                </el-form-item>

                <el-form-item label="访问方式">
                    <el-select class="w-[280px]" v-model="formData.type" placeholder="请选择">
                        <el-option
                            v-for="(item, index) in visitType"
                            :key="index"
                            :label="item.label"
                            :value="item.value"
                        />
                    </el-select>
                </el-form-item>

                <el-form-item label="来源IP">
                    <el-input
                        class="w-[280px]"
                        placeholder="请输入"
                        v-model="formData.ip"
                        clearable
                        @keyup.enter="resetPage"
                    />
                </el-form-item>

                <el-form-item label="访问时间">
                    <daterange-picker
                        v-model:startTime="formData.startTime"
                        v-model:endTime="formData.endTime"
                    />
                </el-form-item>

                <el-form-item label="访问链接">
                    <el-input
                        class="w-[280px]"
                        placeholder="请输入"
                        v-model="formData.url"
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
            <div>
                <el-table :data="pager.lists" size="large">
                    <el-table-column label="记录ID" prop="id" />
                    <el-table-column label="操作" prop="title" min-width="120" />
                    <el-table-column label="管理员" prop="username" min-width="120" />
                    <el-table-column label="访问链接" prop="url" min-width="160" />
                    <el-table-column label="访问方式" prop="type" min-width="100" />
                    <el-table-column label="来源IP" prop="ip" min-width="160" />
                    <el-table-column label="错误信息" prop="error" min-width="160" />
                    <el-table-column label="执行耗时(毫秒)" prop="taskTime" min-width="100" />
                    <el-table-column label="日志时间" prop="createTime" min-width="180" />
                </el-table>
            </div>
            <div class="flex mt-4 justify-end">
                <pagination v-model="pager" @change="getLists" />
            </div>
        </el-card>
    </div>
</template>

<script setup lang="ts" name="journal">
import { systemLogLists } from '@/api/setting/system'
import { usePaging } from '@/hooks/usePaging'

// 查询表单
const formData = ref({
    username: '',
    url: '',
    ip: '',
    type: '',
    startTime: '',
    endTime: ''
})

// 访问方式
const visitType = ref<Array<any>>([
    {
        label: '全部',
        value: ''
    },
    {
        label: 'get',
        value: 'get'
    },
    {
        label: 'post',
        value: 'post'
    }
])

const { pager, getLists, resetParams, resetPage } = usePaging({
    fetchFun: systemLogLists,
    params: formData.value
})

getLists()
</script>

<style lang="scss" scoped></style>
