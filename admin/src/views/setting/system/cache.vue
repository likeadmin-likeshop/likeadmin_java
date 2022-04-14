<!-- 系统缓存 -->
<template>
    <div class="cache">
        <el-card shadow="never">
            <el-alert
                class="xxl"
                title="温馨提示：管理系统运行过程中产生的缓存"
                type="primary"
                :closable="false"
                show-icon
            >
            </el-alert>
        </el-card>

        <el-card class="m-t-15" shadow="never">
            <div>
                <div>基本信息</div>
                <el-table class="m-t-20" :data="cacheDate">
                    <el-table-column label="Redis版本" prop=""></el-table-column>
                    <el-table-column label="运行模式" prop=""> </el-table-column>
                    <el-table-column label="端口" prop=""> </el-table-column>
                    <el-table-column label="客户端数" prop=""> </el-table-column>
                    <el-table-column label="运行时间(天)" prop=""> </el-table-column>
                    <el-table-column label="使用内存" prop=""> </el-table-column>
                    <el-table-column label="使用CPU" prop=""> </el-table-column>
                    <el-table-column label="内存配置" prop=""> </el-table-column>
                    <el-table-column label="AOF是否开启" prop=""> </el-table-column>
                    <el-table-column label="RDB是否成功" prop=""> </el-table-column>
                    <el-table-column label="Key数量" prop=""> </el-table-column>
                    <el-table-column label="网络入口/出口" prop=""> </el-table-column>
                </el-table>
            </div>
        </el-card>

        <div class="m-t-15 flex">
            <!-- 命令统计 -->
            <el-card class="m-r-15 flex-1" shadow="never">
                <div>
                    <div class="p-b-60">命令统计</div>
                    <div class="statistical-chart">
                        <v-chart class="chart" :option="statisticalData.commandChartOption" />
                    </div>
                </div>
            </el-card>

            <!-- 内存信息 -->
            <el-card class="flex-1" shadow="never">
                <div>
                    <div class="p-b-60">内存信息</div>
                    <div class="statistical-chart">
                        <div>12222222</div>
                    </div>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script setup lang="ts">
import { apiSystemCacheClear } from '@/api/setting'
import { reactive, ref } from 'vue'
import Popup from '@/components/Popup/index.vue'

// 列表数据
let cacheDate = ref<Array<object>>([
    {
        content: '系统缓存',
        desc: '系统运行过程中产生的各类缓存数据'
    }
])

const statisticalData = reactive({
    commandChartOption: {
        tooltip: {
            trigger: 'item'
            // formatter: '{b} : {d}%'
        },

        series: [
            {
                label: {
                    show: false
                },
                labelLine: {
                    show: false
                },
                type: 'pie',
                radius: '75%',
                color: ['#3D87ED', '#CBDAF7', '#246AE7', '#0042de'],
                data: [
                    { value: 1048, name: 'Search Engine' },
                    { value: 735, name: 'Direct' },
                    { value: 580, name: 'Email' },
                    { value: 484, name: 'Union Ads' },
                    { value: 300, name: 'Video Ads' }
                ],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    }
})

// 清理缓存
const handleClean = async () => {
    await apiSystemCacheClear()
}
</script>

<style lang="scss" scoped>
.statistical-chart {
    display: flex;
    justify-content: center;
}
</style>
