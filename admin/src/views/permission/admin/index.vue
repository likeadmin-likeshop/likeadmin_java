<template>
    <div class="admin">
        <el-card shadow="never">
            <el-form class="ls-form" :model="formData" label-width="80px" size="small" inline>
                <el-form-item label="账号：">
                    <el-input v-model="formData.username" class="ls-input" />
                </el-form-item>
                <el-form-item label="名称：">
                    <el-input v-model="formData.nickname" class="ls-input" />
                </el-form-item>
                <el-form-item label="角色：">
                    <el-select v-model="formData.role" placeholder="全部">
                        <el-option
                            v-for="(item, index) in roleList"
                            :key="index"
                            :label="item.name"
                            :value="item.id"
                        ></el-option>
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
        <el-card v-loading="pager.loading" class="m-t-15" shadow="never">
            <router-link to="/permission/admin/edit">
                <el-button v-perm="['system:admin:add']" type="primary" size="small"
                    >新增管理员</el-button
                >
            </router-link>
            <div class="m-t-15">
                <el-table :data="pager.lists">
                    <el-table-column label="ID" prop="id" min-width="60"></el-table-column>
                    <el-table-column label="头像" min-width="100">
                        <template #default="{ row }">
                            <el-avatar :size="50" :src="row.avatar"></el-avatar>
                        </template>
                    </el-table-column>
                    <el-table-column label="账号" prop="username" min-width="100"></el-table-column>
                    <el-table-column label="名称" prop="nickname" min-width="100"></el-table-column>
                    <el-table-column label="角色" prop="role" min-width="100"></el-table-column>
                    <el-table-column
                        label="创建时间"
                        prop="createTime"
                        min-width="150"
                    ></el-table-column>
                    <el-table-column
                        label="最近登录时间"
                        prop="lastLoginTime"
                        min-width="150"
                    ></el-table-column>
                    <el-table-column
                        label="最近登录IP"
                        prop="lastLoginIp"
                        min-width="100"
                    ></el-table-column>
                    <el-table-column label="状态" min-width="100">
                        <template #default="scope">
                            <el-switch
                                v-model="scope.row.isDisable"
                                :active-value="0"
                                :inactive-value="1"
                                @change="handleStatusChange($event, scope.row.id)"
                            />
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="150" fixed="right">
                        <template #default="{ row }">
                            <router-link
                                v-perm="['system:admin:edit']"
                                class="m-r-10"
                                :to="{
                                    path: '/permission/admin/edit',
                                    query: {
                                        id: row.id
                                    }
                                }"
                            >
                                <el-button type="text">编辑</el-button>
                            </router-link>
                            <popup
                                v-perm="['system:admin:del']"
                                class="m-r-10 inline"
                                @confirm="handleDelete(row.id)"
                            >
                                <template #trigger>
                                    <el-button type="text">删除</el-button>
                                </template>
                            </popup>
                        </template>
                    </el-table-column>
                </el-table>
            </div>
            <div class="flex row-right">
                <pagination
                    v-model="pager"
                    layout="total, prev, pager, next, jumper"
                    @change="requestApi"
                />
            </div>
        </el-card>
    </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, Ref, ref } from 'vue'
import Pagination from '@/components/pagination/index.vue'
import Popup from '@/components/popup/index.vue'
import { apiAdminEdit, adminLists, apiAdminDelete, apiRoleLists, apiAdminStatus } from '@/api/auth'
import { usePages } from '@/core/hooks/pages'
import { ElMessage } from 'element-plus'

export default defineComponent({
    components: {
        Pagination,
        Popup
    },
    setup() {
        // 表单数据
        const formData = reactive({
            username: '',
            nickname: '',
            role: ''
        })
        const roleList: Ref<any[]> = ref([])
        const { pager, requestApi, resetParams, resetPage } = usePages({
            callback: adminLists,
            params: formData
        })
        const changeStatus = (data: any) => {
            apiAdminEdit({
                id: data.id,
                username: data.username,
                nickname: data.nickname,
                role: data.role,
                isDisable: data.isDisable,
                multipoint_login: data.multipoint_login
            }).finally(() => {
                requestApi()
            })
        }

        const handleStatusChange = async (event: Event, id: number) => {
            await apiAdminStatus({ isDisable: event, id })
            requestApi()
            ElMessage({ type: 'success', message: '操作成功' })
        }

        const handleDelete = (id: number) => {
            apiAdminDelete({ id }).then(() => {
                requestApi()
                ElMessage({ type: 'success', message: '删除成功' })
            })
        }

        const getRoleList = () => {
            apiRoleLists({
                page_type: 1
            }).then((res: any) => {
                roleList.value = res.lists
            })
        }
        onMounted(() => {
            requestApi()
            getRoleList()
        })
        return {
            formData,
            roleList,
            pager,
            requestApi,
            resetParams,
            resetPage,
            adminLists,
            changeStatus,
            handleDelete,
            handleStatusChange
        }
    }
})
</script>

<style lang="scss" scoped>
.ls-form {
    margin-bottom: -16px;
}
</style>
