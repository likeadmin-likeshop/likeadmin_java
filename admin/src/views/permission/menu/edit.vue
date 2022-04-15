<template>
    <div class="menu-edit">
        <el-card shadow="never">
            <el-page-header :content="id ? '编辑菜单' : '新增菜单'" @back="$router.back()" />
        </el-card>

        <el-card class="m-t-15" shadow="never">
            <!-- <el-radio-group v-model="radio" class="m-l-60">
                    <el-radio
                        :label="menuDataType.CATALOG"
                        @change="changeType(menuDataType.CATALOG)"
                    >
                        目录
                    </el-radio>
                    <el-radio :label="menuDataType.MENU" @change="changeType(menuDataType.MENU)">
                        菜单
                    </el-radio>
                    <el-radio
                        :label="menuDataType.BUTTON"
                        @change="changeType(menuDataType.BUTTON)"
                    >
                        按钮
                    </el-radio>
                </el-radio-group> -->

            <div class="m-t-15">
                <el-form
                    ref="form"
                    :model="formData"
                    label-width="120px"
                    size="small"
                    style="max-width: 460px"
                >
                    <el-form-item label="菜单类型">
                        <el-radio-group v-model="radio">
                            <el-radio
                                :label="menuDataType.CATALOG"
                                @change="changeType(menuDataType.CATALOG)"
                            >
                                目录
                            </el-radio>
                            <el-radio
                                :label="menuDataType.MENU"
                                @change="changeType(menuDataType.MENU)"
                            >
                                菜单
                            </el-radio>
                            <el-radio
                                :label="menuDataType.BUTTON"
                                @change="changeType(menuDataType.BUTTON)"
                            >
                                按钮
                            </el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <el-form-item label="父级菜单">
                        <el-cascader
                            style="width: 340px"
                            :options="menuList"
                            :props="{
                                checkStrictly: true,
                                emitPath: false,
                                label: 'title',
                                value: 'id'
                            }"
                            clearable
                        ></el-cascader>
                    </el-form-item>

                    <el-form-item label="菜单图标">
                        <el-input show-word-limit placeholder="请输入名称"></el-input>
                    </el-form-item>

                    <el-form-item label="菜单名称">
                        <el-input show-word-limit placeholder="请输入名称"></el-input>
                    </el-form-item>

                    <el-form-item label="路由地址">
                        <el-input show-word-limit placeholder="请输入名称"></el-input>
                    </el-form-item>

                    <div v-if="radio == menuDataType.CATALOG">
                        <el-form-item label="排序">
                            <el-input
                                show-word-limit
                                type="number"
                                placeholder="请输入名称"
                            ></el-input>
                        </el-form-item>
                    </div>

                    <div v-else-if="radio == menuDataType.MENU">
                        <el-form-item label="排序">
                            <el-input
                                show-word-limit
                                type="number"
                                placeholder="请输入名称"
                            ></el-input>
                        </el-form-item>
                        <el-form-item label="ssss">
                            <el-input
                                show-word-limit
                                type="number"
                                placeholder="请输入名称"
                            ></el-input>
                        </el-form-item>
                    </div>

                    <div v-else></div>
                </el-form>
            </div>
        </el-card>
    </div>
</template>

<script lang="ts" setup>
import { useAdmin } from '@/core/hooks/app'
import { onMounted, reactive, ref } from 'vue'
import { apiConfigGetMenu, apiMenuDetail, apiMenuAdd, apiMenuEdit, apiMenuDelete } from '@/api/auth'

const menuDataType = {
    CATALOG: 'M', // 目录
    MENU: 'C', // 菜单
    BUTTON: 'A' // 按钮
}

const { router, route } = useAdmin()
const id: any = route.query.id

const radio = ref(menuDataType.CATALOG)

// 切换
const changeType = (val: string) => {
    menuDataType.value = val
}

// 父级菜单选择
const menuList = ref<any>([])
</script>

<style lang="scss" scoped></style>
