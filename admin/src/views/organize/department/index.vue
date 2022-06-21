<template>
    <div class="department">
        <el-card shadow="never">
            <el-form class="ls-form" :model="formData" label-width="80px" size="small" inline>
                <el-form-item label="部门名称">
                    <el-input class="ls-input" />
                </el-form-item>

                <el-form-item label="部门状态：">
                    <el-select placeholder="全部">
                        <el-option
                            v-for="item in statusList"
                            :key="item.val"
                            :label="item.name"
                            :value="item.val"
                        >
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <div class="m-l-20">
                        <el-button type="primary" @click="getLists">查询</el-button>
                        <el-button @click="reGetLists">重置</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <el-card class="m-t-15" shadow="never">
            <router-link to="/organize/department/edit">
                <el-button type="primary" size="small">新增部门</el-button>
            </router-link>
            <el-button type="" size="small" style="margin-left: 16px" @click="openOrFold()">
                全部展开/折叠
            </el-button>

            <el-table class="m-t-15" size="small" row-key="id" :expand-row-keys="openIdArr">
                <el-table-column label="部门名称" prop="name" min-width="100"></el-table-column>

                <el-table-column label="部门状态" prop="status_desc" min-width="100">
                    <template #default="{ row }">
                        <el-tag class="ml-2" :type="row.status ? '' : 'danger'"
                            >{{ row.status_desc }}
                        </el-tag>
                    </template>
                </el-table-column>

                <el-table-column label="排序" prop="sort" min-width="100"></el-table-column>

                <el-table-column
                    label="添加时间"
                    prop="create_time"
                    min-width="100"
                ></el-table-column>

                <el-table-column label="操作" width="100" fixed="right">
                    <template #default="{ row }">
                        <router-link
                            class="m-r-10"
                            :to="{
                                path: '/organize/department/edit',
                                query: {
                                    id: row.id
                                }
                            }"
                        >
                            <el-button type="text">编辑</el-button>
                        </router-link>

                        <popup
                            class="m-r-10 inline"
                            @confirm="handleDelete(row.id)"
                            v-show="row.pid"
                        >
                            <template #trigger>
                                <el-button type="text">删除</el-button>
                            </template>
                        </popup>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script lang="ts" setup></script>

<style lang="scss" scoped></style>
