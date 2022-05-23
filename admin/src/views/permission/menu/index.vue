<template>
    <div class="menu">
        <el-card shadow="never">
            <router-link to="/permission/menu/edit">
                <el-button v-perm="['system:menu:add']" type="primary" size="small"
                    >添加菜单</el-button
                >
            </router-link>

            <el-table
                :data="menuTableData"
                class="m-t-24"
                row-key="id"
                default-expand-all
                :tree-props="{ children: 'children' }"
                size="mini"
            >
                <el-table-column prop="menuName" label="名称" width="180"> </el-table-column>
                <el-table-column prop="menuType" label="菜单类型">
                    <template #default="scope">
                        <span v-if="scope.row.menuType == menuDataType.CATALOG">{{ '目录' }}</span>
                        <span v-else-if="scope.row.menuType == menuDataType.MENU">
                            {{ '菜单' }}
                        </span>
                        <span v-else>{{ '按钮' }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="menuIcon" label="图标"> </el-table-column>
                <el-table-column prop="component" label="组件路径"> </el-table-column>
                <el-table-column prop="menuSort" label="排序"> </el-table-column>
                <el-table-column prop="perms" label="权限标识"> </el-table-column>
                <el-table-column prop="paths" label="路由地址"> </el-table-column>
                <el-table-column prop="isDisable" label="状态">
                    <template #default="scope">
                        <span>{{ scope.row.isDisable == 0 ? '启用' : '关闭' }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="createTime" label="创建时间" width="160"> </el-table-column>
                <el-table-column label="操作">
                    <template #default="scope">
                        <router-link
                            v-perm="['system:menu:edit']"
                            class="m-r-10"
                            :to="{
                                path: '/permission/menu/edit',
                                query: {
                                    id: scope.row.id
                                }
                            }"
                        >
                            <el-button type="text" size="mini">编辑</el-button>
                        </router-link>

                        <popup
                            v-perm="['system:menu:del']"
                            class="m-r-10 inline"
                            @confirm="handleDelete(scope.row.id)"
                        >
                            <template #trigger>
                                <el-button type="text" size="mini">删除</el-button>
                            </template>
                        </popup>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script lang="ts" setup>
import Popup from '@/components/popup/index.vue'
import { usePages } from '@/core/hooks/pages'
import { onMounted, reactive, ref } from 'vue'
import { apiConfigGetMenu, apiMenuDetail, apiMenuAdd, apiMenuEdit, apiMenuDelete } from '@/api/auth'

const menuDataType = {
    CATALOG: 'M', // 目录
    MENU: 'C', // 菜单
    BUTTON: 'A' // 按钮
}

const menuTableData = ref([])

// 获取菜单列表
const getMenuTableData = () => {
    apiConfigGetMenu().then(data => {
        menuTableData.value = data
    })
}

// 删除菜单
const handleDelete = async (id: number) => {
    await apiMenuDelete({ id })
    getMenuTableData()
}

onMounted(() => {
    getMenuTableData()
})
</script>

<style lang="scss" scoped></style>
