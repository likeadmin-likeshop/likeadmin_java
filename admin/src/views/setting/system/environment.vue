<!-- 系统环境 -->
<template>
    <div class="system-environment">
        <div class="flex">
            <el-card class="flex-1 m-r-15" shadow="never">
                <div class="lg">CPU</div>
                <div class="m-t-15">
                    <el-form :inline="true" :model="cpuFormData" label-width="110px" size="small">
                        <div class="flex">
                            <div class="flex-1">
                                <div class="f-s-32 m-b-10">{{ cpuFormData.cpuNum || '-' }}</div>
                                <div class="lighter">核心数</div>
                            </div>

                            <div class="flex-1">
                                <div class="f-s-32 m-b-10">{{ cpuFormData.used || '-' }}%</div>
                                <div class="lighter">用户使用率</div>
                            </div>

                            <div class="flex-1">
                                <div class="f-s-32 m-b-10">{{ cpuFormData.sys || '-' }}%</div>
                                <div class="lighter">系统使用率</div>
                            </div>

                            <div class="flex-1">
                                <div class="f-s-32 m-b-10">{{ cpuFormData.free || '-' }}%</div>
                                <div class="lighter">当前空闲率</div>
                            </div>
                        </div>
                    </el-form>
                </div>
            </el-card>

            <el-card class="flex-1" shadow="never">
                <div class="lg">内存</div>
                <div class="m-t-15">
                    <el-form :inline="true" :model="memFormData" label-width="110px" size="small">
                        <div class="flex">
                            <div class="flex-1">
                                <div class="f-s-32 m-b-10">{{ memFormData.total || '-' }}G</div>
                                <div class="lighter">总内存</div>
                            </div>

                            <div class="flex-1">
                                <div class="f-s-32 m-b-10">{{ memFormData.used || '-' }}G</div>
                                <div class="lighter">已用内存</div>
                            </div>

                            <div class="flex-1">
                                <div class="f-s-32 m-b-10">{{ memFormData.free || '-' }}G</div>
                                <div class="lighter">剩余内存</div>
                            </div>

                            <div class="flex-1">
                                <div class="f-s-32 m-b-10">{{ memFormData.usage || '-' }}%</div>
                                <div class="lighter">使用率</div>
                            </div>
                        </div>
                    </el-form>
                </div>
            </el-card>
        </div>

        <el-card shadow="never" class="m-t-15">
            <div class="lg">服务器信息</div>
            <div class="m-t-15">
                <el-table :data="info.serverFormData" size="medium">
                    <el-table-column prop="computerName" label="服务器名称"> </el-table-column>
                    <el-table-column prop="computerIp" label="服务器IP"> </el-table-column>
                    <el-table-column prop="osName" label="操作系统"> </el-table-column>
                    <el-table-column prop="osArch" label="系统架构"> </el-table-column>
                    <el-table-column prop="userDir" label="项目路径"> </el-table-column>
                </el-table>
            </div>
        </el-card>

        <el-card shadow="never" class="m-t-15">
            <div class="lg">Java虚拟机信息</div>
            <div class="m-t-15">
                <el-table :data="info.jvmFormData" size="medium">
                    <el-table-column prop="name" label="Java名称"> </el-table-column>
                    <el-table-column prop="startTime" label="启动时间"> </el-table-column>
                    <el-table-column prop="home" label="安装路径"> </el-table-column>
                    <el-table-column prop="inputArgs" label="运行参数"> </el-table-column>
                    <el-table-column prop="version" label="Java版本"> </el-table-column>
                    <el-table-column prop="runTime" label="运行时长"> </el-table-column>
                </el-table>
            </div>
        </el-card>

        <el-card shadow="never" class="m-t-15">
            <div class="lg">硬盘状态</div>
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
    disk: [], // 硬盘
    serverFormData: [], // 服务器信息
    jvmFormData: [] // Java虚拟机信息
})

const cpuFormData = ref<any>({}) // CPU
const memFormData = ref<any>({}) // 内存

const getSystemInfo = () => {
    apiSystemInfo()
        .then((res: any) => {
            console.log('res', res)
            cpuFormData.value = res.cpu
            memFormData.value = res.mem

            info.disk = res.disk
            info.serverFormData = [res.sys]
            info.jvmFormData = [res.jvm]
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
