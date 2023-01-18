<!-- 系统日志 -->
<template>
    <div class="login-log">
        <el-card class="!border-none" shadow="never">
            <el-form class="ls-form" :model="formData" inline>
                <el-form-item label="用户名称">
                    <el-input
                        class="w-[280px]"
                        placeholder="请输入"
                        v-model="formData.username"
                        clearable
                        @keyup.enter="resetPage"
                    />
                </el-form-item>

                <el-form-item label="状态">
                    <el-select class="w-[280px]" v-model="formData.status" placeholder="请选择">
                        <el-option label="全部" value="" />
                        <el-option label="失败" :value="0" />
                        <el-option label="成功" :value="1" />
                    </el-select>
                </el-form-item>

                <el-form-item label="登录时间">
                    <daterange-picker
                        v-model:startTime="formData.startTime"
                        v-model:endTime="formData.endTime"
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
                    <el-table-column label="用户名称" prop="username" min-width="120" />
                    <el-table-column label="登录地址" prop="ip" min-width="120" />
                    <el-table-column label="浏览器" prop="browser" min-width="120" />
                    <el-table-column label="摱作系統" prop="os" min-width="120" />
                    <el-table-column prop="status" label="状态" min-width="100">
                        <template #default="{ row }">
                            <el-tag v-if="row.status == 1" type="success">成功</el-tag>
                            <el-tag v-if="row.status == 0" type="danger">失败</el-tag>
                        </template>
                    </el-table-column>
                    <el-table-column label="登录时间" prop="createTime" min-width="180" />
                </el-table>
            </div>
            <div class="flex mt-4 justify-end">
                <pagination v-model="pager" @change="getLists" />
            </div>
        </el-card>
    </div>
</template>

<script setup lang="ts" name="journal">
import { loginLogLists } from '@/api/setting/system'
import { usePaging } from '@/hooks/usePaging'

// 查询表单
const formData = ref({
    username: '',
    status: '',
    startTime: '',
    endTime: ''
})

const { pager, getLists, resetParams, resetPage } = usePaging({
    fetchFun: loginLogLists,
    params: formData.value
})

getLists()
</script>

<style lang="scss" scoped></style>
