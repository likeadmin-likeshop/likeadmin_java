<template>
    <div>
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" class="mb-[-16px]" :model="queryParams" :inline="true">
                <el-form-item label="用户信息">
                    <el-input
                        class="w-56"
                        v-model="queryParams.title"
                        placeholder="用户编号/昵称/手机号码"
                    />
                </el-form-item>
                <el-form-item label="注册时间">
                    <el-select class="w-56" v-model="queryParams.cid">
                        <el-option label="全部" value />
                        <el-option
                            v-for="item in optionsData.articleCate"
                            :key="item.id"
                            :label="item.name"
                            :value="item.id"
                        />
                    </el-select>
                </el-form-item>
                <el-form-item label="注册来源">
                    <el-select class="w-56" v-model="queryParams.isShow">
                        <el-option label="全部" value />
                        <el-option label="显示" :value="1" />
                        <el-option label="隐藏" :value="0" />
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="resetPage">查询</el-button>
                    <el-button @click="resetParams">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <el-table size="large" v-loading="pager.loading" :data="pager.lists">
                <el-table-column label="用户编号" prop="id" min-width="80" />
                <el-table-column label="头像" min-width="100">
                    <template #default="{ row }">
                        <image-contain
                            :src="row.image"
                            :width="60"
                            height="45"
                            :preview-src-list="[row.image]"
                        />
                    </template>
                </el-table-column>
                <el-table-column label="昵称" prop="category" min-width="100" />
                <el-table-column label="账号" prop="author" min-width="120" />
                <el-table-column label="手机号码" prop="visit" min-width="100" />
                <el-table-column label="性别" min-width="100">
                    <template #default="{ row }">
                        <el-switch
                            v-perms="['article:cate:change']"
                            v-if="row.id != 1"
                            v-model="row.isShow"
                            :active-value="0"
                            :inactive-value="1"
                            @change="changeStatus(row.id)"
                        />
                    </template>
                </el-table-column>
                <el-table-column label="注册来源" prop="sort" min-width="100" />
                <el-table-column label="注册时间" prop="createTime" min-width="120" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button v-perms="['consumer:lists:detail']" type="primary" link>
                            <router-link
                                :to="{
                                    path: getRoutePath('consumer:lists:detail'),
                                    query: {
                                        id: row.id
                                    }
                                }"
                            >
                                详情
                            </router-link>
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
            <div class="flex justify-end mt-4">
                <pagination v-model="pager" @change="getLists" />
            </div>
        </el-card>
    </div>
</template>
<script lang="ts" setup>
import { articleLists, articleDelete, articleStatus, articleCateAll } from '@/api/article'
import { useDictOptions } from '@/hooks/useDictOptions'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import { getRoutePath } from '@/router'
const queryParams = reactive({
    title: '',
    cid: '',
    isShow: ''
})

const { pager, getLists, resetPage, resetParams } = usePaging({
    fetchFun: articleLists,
    params: queryParams
})

const { optionsData } = useDictOptions<{
    articleCate: any[]
}>({
    articleCate: {
        api: articleCateAll
    }
})

const changeStatus = async (id: number) => {
    try {
        await articleStatus({ id })
        feedback.msgSuccess('修改成功')
        getLists()
    } catch (error) {
        getLists()
    }
}

const handleDelete = async (id: number) => {
    await feedback.confirm('确定要删除？')
    await articleDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
