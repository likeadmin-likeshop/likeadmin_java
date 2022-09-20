<template>
    <div>
        <el-card class="!border-none" shadow="never">
            <el-alert
                type="warning"
                title="温馨提示：平台配置在各个场景下的通知发送方式和内容模板"
                :closable="false"
                show-icon
            ></el-alert>
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <el-tabs v-model="tabsActive" @tab-change="getLists">
                <el-tab-pane
                    v-for="(item, index) in tabsMap"
                    :key="index"
                    :label="item.name"
                    :name="item.type"
                    lazy
                ></el-tab-pane>
            </el-tabs>
            <el-table size="large" :data="state.lists" v-loading="state.loading">
                <el-table-column label="通知场景" prop="name" min-width="120" />
                <el-table-column label="通知类型" prop="type" min-width="160" />
                <el-table-column label="短信通知" min-width="80">
                    <template #default="{ row }">
                        <el-tag v-if="row.smsStatus == 1">开启</el-tag>
                        <el-tag type="danger" v-else>关闭</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" min-width="80" fixed="right">
                    <template #default="{ row }">
                        <el-button v-perms="['setting:notice:detail']" type="primary" link>
                            <router-link
                                :to="{
                                    path: getRoutePath('setting:notice:detail'),
                                    query: {
                                        id: row.id
                                    }
                                }"
                            >
                                设置
                            </router-link>
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>
<script lang="ts" setup name="notice">
import { noticeLists } from '@/api/message'
import { getRoutePath } from '@/router'

enum NoticeEnums {
    USER = 1,
    PLATFORM = 2
}

const tabsActive = ref(NoticeEnums.USER)
const tabsMap = [
    {
        name: '通知用户',
        type: NoticeEnums.USER
    },
    {
        name: '通知平台',
        type: NoticeEnums.PLATFORM
    }
]

// 列表数据
const state = reactive({
    loading: false,
    lists: []
})

// 获取存储引擎列表数据
const getLists = async () => {
    try {
        state.loading = true
        state.lists = await noticeLists({
            recipient: tabsActive.value
        })
        state.loading = false
    } catch (error) {
        state.loading = false
    }
}

onActivated(() => {
    getLists()
})

getLists()
</script>
