<script lang="ts" setup>
import { useMenuOa } from "./useMenuOa";

const { menuList, menuIndex, handleAddMenu } = useMenuOa();
const curMenu = computed(() => menuList.value[menuIndex.value] || {});
console.log(curMenu);
const rules = [];
</script>

<template>
    <!-- Attr -->
    <div class="flex-1 oa-attr">
        <div class="text-base oa-attr-title">菜单配置</div>

        <del-wrap class="w-2/4">
            <div
                class="flex items-center w-full p-4 mt-4 rounded bg-fill-light"
            >
                <el-form ref="ruleFormRef" :model="curMenu" label-width="120px">
                    <!-- 菜单名称 -->
                    <el-form-item label="菜单名称" prop="name">
                        <el-input v-model="curMenu.name" />
                    </el-form-item>

                    <!-- 菜单类型 -->
                    <el-form-item label="主菜单类型" prop="name">
                        <el-radio-group v-model="curMenu.type">
                            <el-radio :label="1">不配置子菜单</el-radio>
                            <el-radio :label="2">配置子菜单</el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <!-- 跳转链接 -->
                    <el-form-item label="跳转链接" prop="name">
                        <el-radio-group v-model="curMenu.jump_link">
                            <el-radio :label="1">网页</el-radio>
                            <el-radio :label="2">小程序</el-radio>
                        </el-radio-group>
                    </el-form-item>

                    <!-- 网址 -->
                    <el-form-item label="网址" prop="name">
                        <el-input v-model="curMenu.url" />
                    </el-form-item>

                    <!-- AppId -->
                    <el-form-item label="AppId" prop="name">
                        <el-input v-model="curMenu.app_id" />
                    </el-form-item>

                    <!-- 路径 -->
                    <el-form-item label="路径" prop="name">
                        <el-input v-model="curMenu.pages" />
                    </el-form-item>
                </el-form>
            </div>
        </del-wrap>
    </div>
</template>

<style lang="scss" scoped>
.oa-attr {
}
</style>
