<template>
    <div class="department">
        <el-card shadow="never">
            <el-form class="ls-form" :model="formData" label-width="80px" size="small" inline>
                <el-form-item label="部门名称">
                    <el-input v-model="formData.name" class="ls-input" />
                </el-form-item>

                <el-form-item label="部门状态：">
                    <el-select v-model="formData.isStop" placeholder="全部">
                        <el-option label="全部" value=""></el-option>
                        <el-option label="正常" :value="0"></el-option>
                        <el-option label="停用" :value="1"></el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <div class="m-l-20">
                        <el-button type="primary" @click="getDeptLists">查询</el-button>
                        <el-button @click="reGetLists">重置</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <el-card class="m-t-15" shadow="never">
            <router-link to="/organize/department/edit">
                <el-button type="primary" size="small">新增部门</el-button>
            </router-link>
            <el-button type="" size="small" style="margin-left: 16px" @click="openOrFold()">
                全部展开/折叠
            </el-button>

            <el-table :data="lists" class="m-t-15" size="small" row-key="id" :expand-row-keys="openIdArr">
                <el-table-column label="部门名称" prop="name" min-width="100"></el-table-column>

                <el-table-column label="部门状态" prop="isStop" min-width="100">
                    <template #default="{ row }">
                        <el-tag class="ml-2" :type="row.isStop ? 'danger' : ''">
                            {{ row.isStop == 0 ? '正常' : '停用' }}
                        </el-tag>
                    </template>
                </el-table-column>

                <el-table-column label="排序" prop="sort" min-width="100"></el-table-column>

                <el-table-column label="添加时间" prop="createTime" min-width="100"></el-table-column>

                <el-table-column label="操作" width="100" fixed="right">
                    <template #default="{ row }">
                        <router-link
                            class="m-r-10"
                            :to="{
                                path: '/organize/department/edit',
                                query: {
                                    id: row.id,
                                },
                            }"
                        >
                            <el-button type="text">编辑</el-button>
                        </router-link>

                        <popup class="m-r-10 inline" @confirm="handleDelete(row.id)" v-show="row.pid">
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
    import { flatten } from '@/utils/util'
    import { apiDeptLists, apiDeptDelete } from '@/api/organize'

    // 表单数据
    const formData = reactive({
        name: '',
        isStop: '',
    })

    // 展开列表数组
    let openIdArr = ref<any>([])

    // 列表数据
    let lists = ref<any>([])

    // 获取列表
    const getDeptLists = async () => {
        lists.value = await apiDeptLists({
            ...formData,
        })
    }

    // 重置列表
    const reGetLists = () => {
        formData.name = ''
        formData.isStop = ''
        getDeptLists()
    }

    // 删除
    const handleDelete = (id: number) => {
        apiDeptDelete({ id }).then(() => {
            getDeptLists()
        })
    }

    // 展开/折叠列表
    const openOrFold = () => {
        // console.log('触发')
        if (openIdArr.value.length) {
            openIdArr.value = []
        } else {
            let allArr = flatten(lists.value, [], 'children')
            openIdArr.value = allArr.map((item) => item.id + '')
        }
    }

    onMounted(async () => {
        await getDeptLists()

        openOrFold()
    })
</script>

<style lang="scss" scoped></style>
