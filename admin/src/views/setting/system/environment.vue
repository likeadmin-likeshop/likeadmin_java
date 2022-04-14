<!-- 系统环境 -->
<template>
    <div class="system-environment">
        <div class="flex">
            <el-card class="flex-1 m-r-15" shadow="never">
                <div>CPU</div>
                <div class="m-t-15">
                    <el-table :data="info.cpu" size="medium">
                        <el-table-column prop="cpuNum" label="核心数"> </el-table-column>
                        <el-table-column prop="used" label="用户使用率"> </el-table-column>
                        <el-table-column prop="sys" label="系统使用率"> </el-table-column>
                        <el-table-column prop="value" label="当前空闲率"> </el-table-column>
                    </el-table>
                </div>
            </el-card>

            <el-card class="flex-1" shadow="never">
                <div>内存</div>
                <div class="m-t-15">
                    <el-table :data="info.mem" size="medium">
                        <el-table-column prop="total" label="总内存"> </el-table-column>
                        <el-table-column prop="used" label="已用内存"> </el-table-column>
                        <el-table-column prop="free" label="剩余内存"> </el-table-column>
                        <el-table-column prop="usage" label="使用率"> </el-table-column>
                    </el-table>
                </div>
            </el-card>
        </div>

        <el-card shadow="never" class="m-t-15">
            <div>服务器信息</div>
            <div class="m-t-15">
                <el-table :data="info.server" size="medium">
                    <el-table-column prop="computerName" label="服务器名称"> </el-table-column>
                    <el-table-column prop="computerIp" label="服务器IP"> </el-table-column>
                    <el-table-column prop="osName" label="操作系统"> </el-table-column>
                    <el-table-column prop="osArch" label="系统架构"> </el-table-column>
                </el-table>
            </div>
        </el-card>

        <el-card shadow="never" class="m-t-15">
            <div>Java虚拟机信息</div>
            <div class="m-t-15">
                <el-table :data="info.server" size="medium">
                    <el-table-column prop="name" label="Java名称"> </el-table-column>
                    <el-table-column prop="startTime" label="启动时间"> </el-table-column>
                    <el-table-column prop="home" label="安装路径"> </el-table-column>
                    <!-- <el-table-column prop="remark" label="项目路径"> </el-table-column> -->
                    <el-table-column prop="inputArgs" label="运行参数"> </el-table-column>
                    <el-table-column prop="version" label="Java版本"> </el-table-column>
                    <el-table-column prop="runTime" label="运行时长"> </el-table-column>
                </el-table>
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

<script lang="ts">
import { defineComponent, reactive, onMounted } from 'vue'
import { apiSystemInfo } from '@/api/setting'
export default defineComponent({
    setup() {
        const info = reactive({
            cpu: [], // cpu
            mem: [], // 内存
            server: [], // 服务器信息
            auth: [], // 目录权限
            disk: [] // 硬盘
        })

        const getSystemInfo = () => {
            apiSystemInfo().then((res: any) => {
                console.log('res', res)
                info.cpu = res.cpu
                info.server = res.server
                info.auth = res.auth
                info.disk = res.disk
            })
        }

        onMounted(() => {
            getSystemInfo()
        })

        return {
            info,
            getSystemInfo
        }
    }
})
</script>

<style lang="scss" scoped></style>
