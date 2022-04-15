<!-- 系统环境 -->
<template>
    <div class="system-environment">
        <div class="flex">
            <el-card class="flex-1 m-r-15" shadow="never">
                <div>CPU</div>
                <div class="m-t-15">
                    <el-form :inline="true" :model="cpuFormData" label-width="110px" size="small">
                        <el-form-item label="核心数">
                            <div>{{ cpuFormData.cpuNum || '-' }}</div>
                        </el-form-item>
                        <el-form-item label="用户使用率">
                            <div>{{ cpuFormData.used || '-' }}%</div>
                        </el-form-item>
                        <el-form-item label="系统使用率">
                            <div>{{ cpuFormData.sys || '-' }}%</div>
                        </el-form-item>
                        <el-form-item label="当前空闲率">
                            <div>{{ cpuFormData.free || '-' }}%</div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>

            <el-card class="flex-1" shadow="never">
                <div>内存</div>
                <div class="m-t-15">
                    <el-form :inline="true" :model="memFormData" label-width="110px" size="small">
                        <el-form-item label="总内存">
                            <div>{{ memFormData.total || '-' }}G</div>
                        </el-form-item>
                        <el-form-item label="已用内存">
                            <div>{{ memFormData.used || '-' }}G</div>
                        </el-form-item>
                        <el-form-item label="剩余内存">
                            <div>{{ memFormData.free || '-' }}G</div>
                        </el-form-item>
                        <el-form-item label="使用率">
                            <div>{{ memFormData.usage || '-' }}%</div>
                        </el-form-item>
                    </el-form>
                </div>
            </el-card>
        </div>

        <el-card shadow="never" class="m-t-15">
            <div>服务器信息</div>
            <div class="m-t-15">
                <el-form :inline="true" :model="serverFormData" label-width="160px" size="small">
                    <el-form-item label="服务器名称">
                        <div>{{ serverFormData.computerName || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="服务器IP">
                        <div>{{ serverFormData.computerIp || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="操作系统">
                        <div>{{ serverFormData.osName || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="系统架构">
                        <div>{{ serverFormData.osArch || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="项目路径">
                        <div>{{ serverFormData.userDir || '-' }}</div>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>

        <el-card shadow="never" class="m-t-15">
            <div>Java虚拟机信息</div>
            <div class="m-t-15">
                <el-form :inline="true" :model="jvmFormData" label-width="120px" size="small">
                    <el-form-item label="Java名称">
                        <div>{{ jvmFormData.name || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="启动时间">
                        <div>{{ jvmFormData.startTime || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="安装路径">
                        <div>{{ jvmFormData.home || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="运行参数">
                        <div>{{ jvmFormData.inputArgs || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="Java版本">
                        <div>{{ jvmFormData.version || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="运行时长">
                        <div>{{ jvmFormData.runTime || '-' }}</div>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>

        <el-card shadow="never" class="m-t-15">
            <div>硬盘状态</div>
            <div class="m-t-15">
                <el-table :data="info.disk" size="medium">
                    <el-table-column prop="dirName" label="盘符路径"> </el-table-column>
                    <el-table-column prop="sysTypeName" label="文件系统"> </el-table-column>
                    <el-table-column prop="typeName" label="盘符类型"> </el-table-column>
                    <el-table-column prop="total" label="总大小"> </el-table-column>
                    <el-table-column prop="free" label="可以大小"> </el-table-column>
                    <el-table-column prop="used" label="已用大小"> </el-table-column>
                    <el-table-column prop="usage" label="已用百分比"> </el-table-column>
                </el-table>
            </div>
        </el-card>
    </div>
</template>

<script setup lang="ts">
import { defineComponent, reactive, onMounted, ref } from 'vue'
import { apiSystemInfo } from '@/api/setting'

const info = reactive({
    disk: [] // 硬盘
})

const cpuFormData = ref<any>({})
const memFormData = ref<any>({})
const serverFormData = ref<any>({})
const jvmFormData = ref<any>({})

const getSystemInfo = () => {
    apiSystemInfo()
        .then((res: any) => {
            console.log('res', res)
            cpuFormData.value = res.cpu
            memFormData.value = res.mem
            serverFormData.value = res.sys
            jvmFormData.value = res.jvm

            info.disk = res.disk
        })
        .catch((err: any) => {
            console.log('err', err)
        })
}

onMounted(() => {
    getSystemInfo()
})
</script>

<style lang="scss" scoped></style>
