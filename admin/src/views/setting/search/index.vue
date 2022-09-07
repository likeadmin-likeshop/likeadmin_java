<template>
    <div class="hot-search">
        <el-card class="!border-none" shadow="never">
            <el-form ref="formRef" :model="formData" label-width="100px">
                <el-form-item label="功能状态" style="margin-bottom: 0;">
                    <div>
                        <el-radio-group v-model="formData.isHotSearch">
                            <el-radio :label="1">开启</el-radio>
                            <el-radio :label="0">关闭</el-radio>
                        </el-radio-group>

                        <div class="form-tips">默认开始，关闭则前端不显示该功能</div>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <el-card class="!border-none mt-4" shadow="never">
            <div class="flex">
                <div class="flex-1 w-3/5">
                    <el-button type="primary" class="mb-4" @click="handleAdd">添加</el-button>
                    <el-table size="large" :data="formData.list">
                        <el-table-column label="ID" prop="id" width="120">
                            <template #default="{ $index }">
                                {{ $index }}
                            </template>
                        </el-table-column>
                        <el-table-column label="关键词" prop="describe" min-width="160">
                            <template #default="{ row }">
                                <el-input v-model="row.name" />
                            </template>
                        </el-table-column>
                        <el-table-column label="排序" prop="describe" min-width="160">
                            <template #default="{ row }">
                                <el-input v-model="row.sort" />
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" min-width="80" fixed="right">
                            <template #default="{ row, $index }">
                                <el-button v-perms="['setting:storage:edit']" type="danger" link
                                    @click="handleDel($index)">
                                    删除
                                </el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>

                <div class="w-2/5 hot-search-phone">
                    <span class="mb-4">- 热搜预览图 -</span>
                    <div class="hot-search-phone-content">
                        <!-- 搜索框 -->
                        <div class="search-com">
                            <div class="search-con flex items-center px-[15px]">
                                <icon name="el-icon-Search" :size="17" />
                                <span class="ml-[5px]">请输入关键词搜索</span>
                            </div>
                        </div>
                        <!-- 热门搜索 -->
                        <div class="hot-search-title">热门搜索</div>
                        <div class="hot-search-text">
                            <span v-for="text in list">{{ text.name }}</span>
                        </div>
                    </div>
                </div>
            </div>
        </el-card>

        <footer-btns v-perms="['setting:website:save']">
            <el-button type="primary" @click="handleSave">保存</el-button>
        </footer-btns>
    </div>
</template>

<script setup lang="ts">
import { getSearch, setSearch } from "@/api/setting/search";
import type { Search } from "@/api/setting/search";
import feedback from "@/utils/feedback";

const formData = reactive<Search>({
    isHotSearch: 1,
    list: [
        {
            name: '搜索引擎',
            sort: 2
        },
        {
            name: '卧槽',
            sort: 1
        }
    ]
})

const list = computed(() => {
    return formData.list.sort((v1, v2) => v1.sort - v2.sort)
})

// 获取登录注册数据
const getData = async () => {
    try {
        const data = await getSearch();
        for (const key in formData) {
            //@ts-ignore
            formData[key] = data[key];
        }
    } catch (error) {
        console.log('获取=>', error)
    }
};

const handleAdd = () => {
    formData.list.push({
        name: '关键字',
        sort: 0
    })
}

const handleDel = (index: number) => {
    formData.list.splice(index, 1)
}

const handleSave = async () => {
    try {
        await setSearch(formData)
        feedback.msgSuccess("操作成功");
        getData()
    } catch (error) {
        console.log('保存=>', error)
    }
}

getData()
</script>

<style lang="scss" scoped>
.hot-search {
    .hot-search-phone {
        margin-left: 20px;
        @apply flex flex-col items-center;

        &-content {
            width: 280px;
            height: 494px;
            padding: 12px 12px;
            border-radius: 10px;
            border: 1px solid #E6E6E6;

            .search-com {
                .search-con {
                    height: 100%;
                    height: 36px;
                    border-radius: 36px;
                    background: #f4f4f4;
                    color: #999999;
                }
            }

            .hot-search-title {
                padding: 10px 0;
                font-size: 13px;
            }

            .hot-search-text {
                span {
                    font-size: 12px;
                    border-radius: 100px;
                    padding: 5px 10px;
                    margin: 0 6px 6px 0;
                    display: inline-block;
                    background-color: #E6E6E6;
                }
            }
        }
    }
}
</style>