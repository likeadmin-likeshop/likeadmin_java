<template>
    <div class="post">
        <el-card shadow="never">
            <el-form class="ls-form" :model="formData" label-width="80px" size="small" inline>
                <el-form-item label="岗位编码">
                    <el-input class="ls-input" />
                </el-form-item>

                <el-form-item label="岗位名称">
                    <el-input class="ls-input" />
                </el-form-item>

                <el-form-item label="岗位状态：">
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
                        <el-button type="primary" @click="resetPage">查询</el-button>
                        <el-button @click="resetParams">重置</el-button>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <el-card shadow="never" class="m-t-15">
            <router-link to="/organize/post/edit">
                <el-button v-perm="['system:post:add']" type="primary" size="small">
                    新增岗位
                </el-button>
            </router-link>

            <el-table size="small" row-key="id" class="m-t-15">
                <el-table-column label="岗位编码" prop="code" min-width="100"></el-table-column>
                <el-table-column label="岗位名称" prop="name" min-width="100"></el-table-column>
                <el-table-column label="岗位状态" prop="status_desc" min-width="100">
                    <template #default="{ row }">
                        <el-tag class="ml-2" :type="row.status ? '' : 'danger'">
                            {{ row.status_desc }}
                        </el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="排序" prop="sort" min-width="100"></el-table-column>
                <el-table-column
                    label="添加时间"
                    prop="create_time"
                    min-width="100"
                ></el-table-column>
                <el-table-column label="操作" min-width="100" fixed="right">
                    <template #default="{ row }">
                        <router-link
                            class="m-r-10"
                            :to="{
                                path: '/organize/post/edit',
                                query: {
                                    id: row.id
                                }
                            }"
                        >
                            <el-button type="text">编辑</el-button>
                        </router-link>
                        <popup class="m-r-10 inline" @confirm="handleDelete(row.id)">
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
