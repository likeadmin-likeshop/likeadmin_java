<template>
    <div class="post">
        <el-card shadow="never">
            <el-form class="ls-form" :model="formData" label-width="80px" size="small" inline>
                <el-form-item label="岗位编码">
                    <el-input v-model="formData.code" class="ls-input" />
                </el-form-item>

                <el-form-item label="岗位名称">
                    <el-input v-model="formData.name" class="ls-input" />
                </el-form-item>

                <el-form-item label="岗位状态：">
                    <el-select v-model="formData.isStop" placeholder="全部">
                        <el-option label="全部" value=""></el-option>
                        <el-option label="正常" :value="0"></el-option>
                        <el-option label="停用" :value="1"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <div class="m-l-20">
                        <el-button type="primary" @click="resetPage">查询</el-button>
                        <el-button @click="resetParams">重置</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <el-card shadow="never" class="m-t-15">
            <router-link to="/organize/post/edit">
                <el-button v-perm="['system:post:add']" type="primary" size="small"> 新增岗位 </el-button>
            </router-link>

            <el-table :data="pager.lists" size="small" row-key="id" class="m-t-15">
                <el-table-column label="岗位编码" prop="code" min-width="100"></el-table-column>

                <el-table-column label="岗位名称" prop="name" min-width="100"></el-table-column>

                <el-table-column label="岗位状态" prop="isStop" min-width="100">
                    <template #default="{ row }">
                        <el-tag class="ml-2" :type="row.isStop ? 'danger' : ''">
                            {{ row.isStop == 0 ? '正常' : '停用' }}
                        </el-tag>
                    </template>
                </el-table-column>

                <el-table-column label="排序" prop="sort" min-width="100"></el-table-column>

                <el-table-column label="添加时间" prop="createTime" min-width="100"></el-table-column>

                <el-table-column label="操作" min-width="100" fixed="right">
                    <template #default="{ row }">
                        <router-link
                            class="m-r-10"
                            :to="{
                                path: '/organize/post/edit',
                                query: {
                                    id: row.id,
                                },
                            }"
                        >
                            <el-button type="text">编辑</el-button>
                        </router-link>

                        <popup class="m-r-10 inline" @confirm="handleDelete(row.id)">
                            <template #trigger>
                                <el-button type="text">删除</el-button>
                            </template>
                        </popup>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script lang="ts" setup>
    import { onMounted, reactive, ref } from 'vue'
    import Popup from '@/components/popup/index.vue'
    import { usePages } from '@/core/hooks/pages'
    import { apiPostLists, apiPostDelete } from '@/api/organize'

    // 表单数据
    const formData = reactive({
        code: '', // 编码
        name: '', // 岗位名称
        isStop: '', // 	是否停用 0=否,1=是,不传=全部
    })

    // 分页请求
    const { pager, requestApi, resetParams, resetPage } = usePages({
        callback: apiPostLists,
        params: formData,
    })

    // 删除
    const handleDelete = (id: number) => {
        apiPostDelete({
            id,
        }).then(() => {
            requestApi()
        })
    }

    onMounted(() => {
        requestApi()
    })
</script>

<style lang="scss" scoped></style>
