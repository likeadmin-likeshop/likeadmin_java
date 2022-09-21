<!-- 系统环境 -->
<template>
    <div class="system-environment" v-loading="loading">
        <div class="lg:flex">
            <el-card class="!border-none flex-1 mb-4 lg:mr-4" shadow="never">
                <div>CPU</div>
                <div class="mt-4">
                    <div class="flex flex-wrap">
                        <div class="sm:flex-1 w-1/2 mb-4">
                            <div class="text-4xl mb-3">{{ info.cpu.cpuNum }}</div>
                            <div class="text-tx-regular">核心数</div>
                        </div>

                        <div class="sm:flex-1 w-1/2 mb-4">
                            <div class="text-4xl mb-3">
                                {{ info.cpu.used ? `${info.cpu.used}%` : '-' }}
                            </div>
                            <div class="text-tx-regular">用户使用率</div>
                        </div>

                        <div class="sm:flex-1 w-1/2 mb-4">
                            <div class="text-4xl mb-3">
                                {{ info.cpu.sys ? `${info.cpu.sys}%` : '-' }}
                            </div>
                            <div class="text-tx-regular">系统使用率</div>
                        </div>

                        <div class="sm:flex-1 w-1/2 mb-4">
                            <div class="text-4xl mb-3">
                                {{ info.cpu.free ? `${info.cpu.free}%` : '-' }}
                            </div>
                            <div class="text-tx-regular">当前空闲率</div>
                        </div>
                    </div>
                </div>
            </el-card>
            <el-card class="!border-none flex-1 mb-4" shadow="never">
                <div>内存</div>
                <div class="mt-4">
                    <div class="flex flex-wrap">
                        <div class="sm:flex-1 w-1/2 mb-4">
                            <div class="text-4xl mb-3">{{ info.mem.total }}</div>
                            <div class="text-tx-regular">总内存</div>
                        </div>

                        <div class="sm:flex-1 w-1/2 mb-4">
                            <div class="text-4xl mb-3">
                                {{ info.mem.used ? `${info.mem.used}%` : '-' }}
                            </div>
                            <div class="text-tx-regular">已用内存</div>
                        </div>

                        <div class="sm:flex-1 w-1/2 mb-4">
                            <div class="text-4xl mb-3">
                                {{ info.mem.free ? `${info.mem.free}%` : '-' }}
                            </div>
                            <div class="text-tx-regular">剩余内存</div>
                        </div>

                        <div class="sm:flex-1 w-1/2 mb-4">
                            <div class="text-4xl mb-3">
                                {{ info.mem.usage ? `${info.mem.usage}%` : '-' }}
                            </div>
                            <div class="text-tx-regular">使用率</div>
                        </div>
                    </div>
                </div>
            </el-card>
        </div>
        <el-card class="!border-none" shadow="never">
            <div>服务器信息</div>
            <div class="mt-4">
                <el-table :data="[info.sys]" size="large">
                    <el-table-column prop="computerName" label="服务器名称" min-width="150" />
                    <el-table-column prop="computerIp" label="服务器IP" min-width="120" />
                    <el-table-column prop="osName" label="操作系统" min-width="100" />
                    <el-table-column prop="osArch" label="系统架构" min-width="100" />
                    <el-table-column prop="userDir" label="项目路径" min-width="250" />
                </el-table>
            </div>
        </el-card>

        <el-card shadow="never" class="!border-none mt-4">
            <div>Java虚拟机信息</div>
            <div class="mt-4">
                <el-table :data="[info.jvm]" size="large">
                    <el-table-column prop="name" label="Java名称" min-width="120" />
                    <el-table-column prop="startTime" label="启动时间" min-width="120" />
                    <el-table-column prop="home" label="安装路径" min-width="120" />
                    <el-table-column prop="inputArgs" label="运行参数" min-width="120" />
                    <el-table-column prop="version" label="Java版本" min-width="120" />
                    <el-table-column prop="runTime" label="运行时长" min-width="120" />
                </el-table>
            </div>
        </el-card>

        <el-card shadow="never" class="!border-none mt-4">
            <div>硬盘状态</div>
            <div class="mt-4">
                <el-table :data="info.disk" size="large">
                    <el-table-column prop="dirName" label="盘符路径" min-width="100" />
                    <el-table-column prop="sysTypeName" label="文件系统" min-width="100" />
                    <el-table-column prop="typeName" label="盘符类型" min-width="100" />
                    <el-table-column prop="total" label="总大小" min-width="100" />
                    <el-table-column prop="free" label="可用大小" min-width="100" />
                    <el-table-column prop="used" label="已用大小" min-width="100" />
                    <el-table-column prop="usage" label="已用百分比" min-width="100">
                        <template #default="{ row }"> {{ row.usage }}% </template>
                    </el-table-column>
                </el-table>
            </div>
        </el-card>
    </div>
</template>

<script lang="ts" setup name="environment">
import { systemInfo } from '@/api/setting/system'
const loading = ref(false)
const info = ref({
    cpu: {} as any,
    disk: [],
    jvm: {},
    mem: {} as any,
    sys: {}
})

const getSystemInfo = async () => {
    try {
        loading.value = true
        const data = await systemInfo()
        info.value = data

        loading.value = false
    } catch (error) {
        loading.value = false
    }
}

getSystemInfo()
</script>

<style lang="scss" scoped></style>
