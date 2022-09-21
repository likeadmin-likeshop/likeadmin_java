<template>
    <div class="role-lists">
        <el-card class="!border-none" shadow="never">
            <div>
                <el-button v-perms="['system:role:add']" type="primary" @click="handleAdd">
                    <template #icon>
                        <icon name="el-icon-Plus" />
                    </template>
                    新增
                </el-button>
            </div>
            <div class="mt-4">
                <div>
                    <el-table :data="pager.lists" size="large" v-loading="pager.loading">
                        <el-table-column prop="id" label="ID" min-width="100" />
                        <el-table-column prop="name" label="名称" min-width="150" />
                        <el-table-column
                            prop="remark"
                            label="备注"
                            min-width="150"
                            show-overflow-tooltip
                        />
                        <el-table-column prop="sort" label="排序" min-width="100" />
                        <el-table-column prop="member" label="管理员人数" min-width="120" />
                        <el-table-column prop="createTime" label="创建时间" min-width="180" />
                        <el-table-column label="操作" width="190" fixed="right">
                            <template #default="{ row }">
                                <el-button
                                    v-perms="['system:role:edit']"
                                    link
                                    type="primary"
                                    @click="handleEdit(row)"
                                >
                                    编辑
                                </el-button>
                                <el-button
                                    v-perms="['system:role:edit']"
                                    link
                                    type="primary"
                                    @click="handleAuth(row)"
                                >
                                    权限设置
                                </el-button>
                                <el-button
                                    v-perms="['system:role:del']"
                                    link
                                    type="danger"
                                    @click="handleDelete(row.id)"
                                >
                                    删除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
                <div class="flex justify-end mt-4">
                    <pagination v-model="pager" @change="getLists" />
                </div>
            </div>
        </el-card>
        <edit-popup v-if="showEdit" ref="editRef" @success="getLists" @close="showEdit = false" />
        <auth-popup v-if="showAuth" ref="authRef" @success="getLists" @close="showAuth = false" />
    </div>
</template>

<script lang="ts" setup name="role">
import { roleLists, roleDelete } from '@/api/perms/role'
import { usePaging } from '@/hooks/usePaging'
import feedback from '@/utils/feedback'
import EditPopup from './edit.vue'
import AuthPopup from './auth.vue'
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
const authRef = shallowRef<InstanceType<typeof AuthPopup>>()
const showEdit = ref(false)
const showAuth = ref(false)
const { pager, getLists } = usePaging({
    fetchFun: roleLists
})
const handleAdd = async () => {
    showEdit.value = true
    await nextTick()
    editRef.value?.open('add')
}

const handleEdit = async (data: any) => {
    showEdit.value = true
    await nextTick()
    editRef.value?.open('edit')
    editRef.value?.setFormData(data)
}

const handleAuth = async (data: any) => {
    showAuth.value = true
    await nextTick()
    authRef.value?.open()
    authRef.value?.setFormData(data)
}

// 删除角色
const handleDelete = async (id: number) => {
    await feedback.confirm('确定要删除？')
    await roleDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

getLists()
</script>
