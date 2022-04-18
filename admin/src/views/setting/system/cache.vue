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
                <div class="m-b-15">基本信息</div>

                <el-form :inline="true" :model="formData" label-width="110px" size="small">
                    <el-form-item label="Redis版本">
                        <div>{{ formData.redis_version || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="运行模式">
                        <div>{{ formData.redis_mode || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="端口">
                        <div>{{ formData.tcp_port || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="客户端数">
                        <div>{{ formData.connected_clients || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="运行时间(天)">
                        <div>{{ formData.uptime_in_days || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="使用内存">
                        <div>{{ formData.used_memory_human || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="使用CPU">
                        <div>{{ formData.used_cpu_user_children || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="内存配置">
                        <div>{{ formData.maxmemory_human || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="AOF是否开启">
                        <div>{{ formData.aof_enabled || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="RDB是否成功">
                        <div>{{ formData.rdb_last_bgsave_status || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="Key数量">
                        <div>{{ formData.dbSize || '-' }}</div>
                    </el-form-item>
                    <el-form-item label="网络入口/出口">
                        <div>
                            {{ formData.instantaneous_input_kbps || '-' }}
                            <span>/</span>
                            {{ formData.instantaneous_output_kbps || '-' }}
                        </div>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>

        <div class="m-t-15 flex">
            <!-- 命令统计 -->
            <el-card class="m-r-15 flex-1 test" shadow="never">
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
                    <div class="p-b-40">内存信息</div>
                    <div class="statistical-chart">
                        <v-chart class="chart" :option="statisticalData.memoryChartOption" />
                    </div>
                </div>
            </el-card>
        </div>
    </div>
</template>

<script setup lang="ts">
import { apiSystemCache } from '@/api/setting'
import { reactive, ref, onMounted } from 'vue'
import Popup from '@/components/Popup/index.vue'

// 列表数据
let cacheDate = ref<Array<object>>([
    {
        content: '系统缓存',
        desc: '系统运行过程中产生的各类缓存数据'
    }
])

const formData = ref<any>({})

const statisticalData = reactive({
    commandChartOption: {
        tooltip: {
            trigger: 'item'
            // formatter: '{b} : {d}%'
        },

        series: [
            {
                label: {
                    show: true
                },
                labelLine: {
                    show: true
                },
                type: 'pie',
                radius: '85%',
                color: [
                    '#0D47A1',
                    '#1565C0',
                    '#1976D2',
                    '#1E88E5',
                    '#2196F3',
                    '#42A5F5',
                    '#64B5F6',
                    '#90CAF9',
                    '#BBDEFB',
                    '#E3F2FD',
                    '#CAF0F8',
                    '#ADE8F4',
                    '#90E0EF',
                    '#48CAE4',
                    '#00B4D8',
                    '#0096C7',
                    '#0077B6',
                    '#023E8A',
                    '#03045E',
                    '#8ecae6',
                    '#98c1d9',
                    '#D9ED92',
                    '#B5E48C',
                    '#99D98C',
                    '#76C893',
                    '#52B69A',
                    '#34A0A4',
                    '#168AAD',
                    '#1A759F',
                    '#1E6091',
                    '#184E77',
                    '#457b9d'
                ],
                data: [
                    {
                        value: '',
                        name: ''
                    }
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
    },

    memoryChartOption: {
        tooltip: {
            formatter: '{a} <br/>{b} : {c}%'
        },
        series: [
            {
                name: 'Pressure',
                type: 'gauge',
                radius: '100%',
                detail: {
                    formatter: '{value}'
                },
                data: [
                    {
                        value: '',
                        name: '内存消耗'
                    }
                ]
            }
        ]
    }
})

const getSystemCache = async () => {
    apiSystemCache({})
        .then((res: any) => {
            console.log(res)
            formData.value = res.info
            formData.value.dbSize = res.dbSize || ''

            statisticalData.commandChartOption.series[0].data = res.commandStats
            statisticalData.memoryChartOption.series[0].data = res.info.used_memory_human
            console.log(
                statisticalData.memoryChartOption.series[0].data,
                '-------------------------+'
            )
        })
        .catch((err: any) => {
            console.log('err', err)
        })
}

onMounted(() => {
    getSystemCache()
})
</script>

<style lang="scss" scoped>
.statistical-chart {
    display: flex;
    justify-content: center;
    height: 240px;
}
</style>
