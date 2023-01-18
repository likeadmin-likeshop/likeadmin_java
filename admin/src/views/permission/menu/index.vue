<template>
    <div class="menu-lists">
        <el-card class="!border-none" shadow="never">
            <div>
                <el-button v-perms="['system:menu:add']" type="primary" @click="handleAdd()">
                    <template #icon>
                        <icon name="el-icon-Plus" />
                    </template>
                    新增
                </el-button>
                <el-button @click="handleExpand"> 展开/折叠 </el-button>
            </div>
            <el-table
                v-loading="loading"
                ref="tableRef"
                class="mt-4"
                size="large"
                :data="lists"
                row-key="id"
                :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
            >
                <el-table-column
                    label="菜单名称"
                    prop="menuName"
                    min-width="150"
                    show-overflow-tooltip
                />
                <el-table-column label="类型" prop="menuType" min-width="80">
                    <template #default="{ row }">
                        <div v-if="row.menuType == MenuEnum.CATALOGUE">目录</div>
                        <div v-else-if="row.menuType == MenuEnum.MENU">菜单</div>
                        <div v-else-if="row.menuType == MenuEnum.BUTTON">按钮</div>
                    </template>
                </el-table-column>
                <el-table-column label="图标" prop="menuIcon" min-width="80">
                    <template #default="{ row }">
                        <div class="flex">
                            <icon :name="row.menuIcon" :size="20" />
                        </div>
                    </template>
                </el-table-column>
                <el-table-column
                    label="权限标识"
                    prop="perms"
                    min-width="150"
                    show-overflow-tooltip
                />
                <el-table-column label="状态" prop="isDisable" min-width="100">
                    <template #default="{ row }">
                        <el-tag v-if="row.isDisable == 0">正常</el-tag>
                        <el-tag v-else type="danger">停用</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="排序" prop="menuSort" min-width="100" />
                <el-table-column
                    label="更新时间"
                    prop="updateTime"
                    min-width="180"
                ></el-table-column>
                <el-table-column label="操作" width="160" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-if="row.menuType !== MenuEnum.BUTTON"
                            v-perms="['system:menu:add']"
                            type="primary"
                            link
                            @click="handleAdd(row.id)"
                        >
                            新增
                        </el-button>
                        <el-button
                            v-perms="['system:menu:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['system:menu:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.id)"
                        >
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <edit-popup v-if="showEdit" ref="editRef" @success="getLists" @close="showEdit = false" />
    </div>
</template>
<script lang="ts" setup name="menu">
import { menuDelete, menuLists } from '@/api/perms/menu'
import type { ElTable } from 'element-plus'
import { MenuEnum } from '@/enums/appEnums'
import EditPopup from './edit.vue'
import feedback from '@/utils/feedback'
const tableRef = shallowRef<InstanceType<typeof ElTable>>()
const editRef = shallowRef<InstanceType<typeof EditPopup>>()
let isExpand = false
const loading = ref(false)
const showEdit = ref(false)
const lists = ref([])

const getLists = async () => {
    loading.value = true
    try {
        const data = await menuLists()
        lists.value = data
        loading.value = false
    } catch (error) {
        loading.value = false
    }
}

const handleAdd = async (id?: number) => {
    showEdit.value = true
    await nextTick()
    if (id) {
        editRef.value?.setFormData({
            pid: id
        })
    }
    editRef.value?.open('add')
}

const handleEdit = async (data: any) => {
    showEdit.value = true
    await nextTick()
    editRef.value?.open('edit')
    editRef.value?.getDetail(data)
}

const handleDelete = async (id: number) => {
    await feedback.confirm('确定要删除？')
    await menuDelete({ id })
    feedback.msgSuccess('删除成功')
    getLists()
}

const handleExpand = () => {
    isExpand = !isExpand
    toggleExpand(lists.value, isExpand)
}

const toggleExpand = (children: any[], unfold = true) => {
    for (const key in children) {
        tableRef.value?.toggleRowExpansion(children[key], unfold)
        if (children[key].children) {
            toggleExpand(children[key].children!, unfold)
        }
    }
}

getLists()
</script>
