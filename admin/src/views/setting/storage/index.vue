<template>
    <div class="storage">
        <el-card shadow="never">
            <el-alert
                class="xxl"
                title="温馨提示：1.切换存储方式后，需要将资源文件传输至新的存储端；2.请勿随意切换存储方式，可能导致图片无法查看"
                type="primary"
                :closable="false"
                show-icon
            >
            </el-alert>
        </el-card>

        <el-card class="m-t-15" shadow="never">
            <el-table :data="storageLists" class="m-t-20" size="small">
                <el-table-column label="储存方式" prop="alias"></el-table-column>

                <el-table-column label="储存位置" prop="describe"> </el-table-column>

                <el-table-column label="状态">
                    <template #default="{ row }">
                        <el-switch
                            v-model="row.status"
                            :active-value="1"
                            :inactive-value="0"
                            @change="handleChange(row)"
                        />
                    </template>
                </el-table-column>

                <el-table-column label="操作" fixed="right">
                    <template #default="scope">
                        <router-link
                            class="m-r-10"
                            :to="{
                                path: '/setting/storage/edit',
                                query: {
                                    engine: scope.row.engine,
                                },
                            }"
                        >
                            <el-button type="text">设置</el-button>
                        </router-link>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script setup lang="ts">
    import { ref, onMounted } from 'vue'
    import { ElMessage } from 'element-plus'
    import { apiStorageLists, apiStorageChange } from '@/api/setting'

    // 列表数据
    let storageLists = ref<any>([])

    // 获取存储列表数据
    const getStorageLists = async () => {
        storageLists.value = await apiStorageLists()
    }

    // 切换存储
    const handleChange = async (event: any) => {
        await apiStorageChange({
            alias: event.alias,
            status: event.status,
        }).then(() => {
            ElMessage({ type: 'success', message: '切换成功' })
        })
        getStorageLists()
    }

    onMounted(() => {
        getStorageLists()
    })
</script>

<style lang="scss" scoped></style>
