<template>
    <div>
        <el-card class="!border-none" shadow="never">
            <el-alert
                type="warning"
                title="温馨提示：用户账户变动记录"
                :closable="false"
                show-icon
            ></el-alert>
            <el-form ref="formRef" class="mb-[-16px] mt-[16px]" :model="queryParams" :inline="true">
                <el-form-item label="用户信息">
                    <el-input
                        class="w-[280px]"
                        v-model="queryParams.keyword"
                        placeholder="请输入用户编号/昵称/手机号"
                        clearable
                        @keyup.enter="resetPage"
                    />
                </el-form-item>
                <el-form-item label="变动类型">
                    <el-select class="w-[280px]" v-model="queryParams.type">
                        <el-option label="全部" value />
                        <el-option
                            v-for="(value, key) in changeType"
                            :key="key"
                            :label="value"
                            :value="key"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="记录时间">
                    <daterange-picker
                        v-model:startTime="queryParams.startTime"
                        v-model:endTime="queryParams.endTime"
                    />
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <el-table size="large" v-loading="pager.loading" :data="pager.lists">
                <el-table-column label="用户编号" prop="userSn" min-width="100" />
                <el-table-column label="用户昵称" min-width="160">
                    <template #default="{ row }">
                        <div class="flex items-center">
                            <image-contain
                                class="flex-none mr-2"
                                :src="row.avatar"
                                :width="40"
                                :height="40"
                                preview-teleported
                                fit="contain"
                            />
                            {{ row.nickname }}
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="变动金额" prop="changeAmount" min-width="100">
                    <template #default="{ row }">
                        <span :class="{ 'text-error': row.action == 2 }">
                            {{ row.changeAmount }}
                        </span>
                    </template>
                </el-table-column>
                <el-table-column label="剩余金额" prop="leftAmount" min-width="100" />
                <el-table-column label="变动类型" prop="changeType" min-width="120" />

                <el-table-column label="来源单号" prop="sourceSn" min-width="100" />
                <el-table-column label="记录时间" prop="createTime" min-width="120" />
            </el-table>
            <div class="flex justify-end mt-4">
                <pagination v-model="pager" @change="getLists" />
            </div>
        </el-card>
    </div>
</template>
<script lang="ts" setup name="articleLists">
import { accountLog } from '@/api/finance'
import { usePaging } from '@/hooks/usePaging'
const queryParams = reactive({
    keyword: '',
    type: '',
    startTime: '',
    endTime: ''
})

const changeType = ref<any[]>([])

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: accountLog,
    params: queryParams,
    afterRequest(res) {
        changeType.value = res.extend?.changeType
    }
})

getLists()
</script>
