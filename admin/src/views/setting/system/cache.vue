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
                <div class="m-b-20 lg">基本信息</div>

                <el-form :inline="true" :model="formData" size="small">
                    <div class="flex basic-information">
                        <div class="flex-1">
                            <span class="m-r-40 nr">Redis版本</span>
                            <span>{{ formData.redis_version || '-' }}</span>
                        </div>

                        <div class="flex-1">
                            <span class="m-r-40 nr">运行模式</span>
                            <span>{{ formData.redis_mode || '-' }}</span>
                        </div>

                        <div class="flex-1">
                            <span class="m-r-40 nr">端口</span>
                            <span>{{ formData.tcp_port || '-' }}</span>
                        </div>

                        <div class="flex-1">
                            <span class="m-r-40 nr">客户端数</span>
                            <span>{{ formData.connected_clients || '-' }}</span>
                        </div>
                    </div>

                    <div class="flex basic-information">
                        <div class="flex-1">
                            <span class="m-r-40 nr">运行时间(天)</span>
                            <span>{{ formData.uptime_in_days || '-' }}</span>
                        </div>
                        <div class="flex-1">
                            <span class="m-r-40 nr">使用内存</span>
                            <span>{{ formData.used_memory_human || '-' }}</span>
                        </div>
                        <div class="flex-1">
                            <span class="m-r-40 nr">使用CPU</span>
                            <span>{{ formData.used_cpu_user_children || '-' }}</span>
                        </div>
                        <div class="flex-1">
                            <span class="m-r-40 nr">内存配置</span>
                            <span>{{ formData.maxmemory_human || '-' }}</span>
                        </div>
                    </div>

                    <div class="flex basic-information">
                        <div class="flex-1">
                            <span class="m-r-40 nr">AOF是否开启</span>
                            <span>{{ formData.aof_enabled == '0' ? '开启' : '关闭' || '-' }}</span>
                        </div>
                        <div class="flex-1">
                            <span class="m-r-40 nr">RDB是否成功</span>
                            <span>
                                {{
                                    formData.rdb_last_bgsave_status == 'ok' ? '成功' : '失败' || '-'
                                }}
                            </span>
                        </div>
                        <div class="flex-1">
                            <span class="m-r-40 nr">Key数量</span>
                            <span>{{ formData.dbSize || '-' }}</span>
                        </div>
                        <div class="flex-1">
                            <span class="m-r-40 nr">网络入口/出口</span>
                            <span>
                                {{ formData.instantaneous_input_kbps || '-' }}
                                <span>/</span>
                                {{ formData.instantaneous_output_kbps || '-' }}
                            </span>
                        </div>
                    </div>
                </el-form>
            </div>
        </el-card>

        <div class="m-t-15 flex">
            <!-- 命令统计 -->
            <el-card class="m-r-15 flex-1 test" shadow="never">
                <div>
                    <div class="p-b-60 lg">命令统计</div>
                    <div class="statistical-chart">
                        <v-chart class="chart" :option="statisticalData.commandChartOption" />
                    </div>
                </div>
            </el-card>

            <!-- 内存信息 -->
            <el-card class="flex-1" shadow="never">
                <div>
                    <div class="p-b-40 lg">内存信息</div>
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

            statisticalData.memoryChartOption.series[0].data[0].value = (
                res.info.used_memory /
                1024 /
                1024
            ).toFixed(2)
            statisticalData.memoryChartOption.series[0].detail.formatter = '{value}' + 'M'
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

.basic-information {
    padding-bottom: 20px;
    margin-top: 20px;
    border-bottom: 1px solid #dfdfdf;
}
</style>
