<template>
    <div class="menu-edit">
        <el-card shadow="never">
            <el-page-header :content="id ? '编辑菜单' : '新增菜单'" @back="$router.back()" />
        </el-card>

        <el-card class="m-t-15" shadow="never">
            <div class="m-t-15">
                <el-form
                    ref="formRefs"
                    :model="formData"
                    label-width="120px"
                    size="small"
                    style="max-width: 460px"
                >
                    <el-form-item label="菜单类型">
                        <el-radio-group v-model="formData.menuType">
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
                            v-model="formData.pid"
                            style="width: 340px"
                            :options="menuList"
                            :props="{
                                checkStrictly: true,
                                emitPath: false,
                                label: 'menuName',
                                value: 'id'
                            }"
                            clearable
                        ></el-cascader>
                    </el-form-item>

                    <div v-if="(formData.menuType == menuDataType.BUTTON) == ''">
                        <!-- <el-form-item label="请选择图标">
                            <el-input
                                v-model="formData.menuIcon"
                                show-word-limit
                                placeholder="请输入图标"
                            ></el-input>
                        </el-form-item> -->

                        <el-form-item label="请选择图标">
                            <div class="flex">
                                <!-- <el-input readonly>
                                    <template #prefix>
                                        <el-icon class="el-input__icon">
                                            <search class="f-s-20" />
                                        </el-icon>
                                    </template>
                                </el-input> -->

                                <select-icon></select-icon>
                            </div>
                        </el-form-item>
                    </div>

                    <el-form-item label="菜单名称">
                        <el-input
                            v-model="formData.menuName"
                            show-word-limit
                            placeholder="请输入名称"
                        ></el-input>
                    </el-form-item>

                    <div v-if="formData.menuType == menuDataType.BUTTON">
                        <el-form-item label="菜单权限字符">
                            <el-input
                                v-model="formData.perms"
                                show-word-limit
                                placeholder="请输入"
                            ></el-input>
                        </el-form-item>
                    </div>

                    <div v-if="(formData.menuType == menuDataType.BUTTON) == ''">
                        <el-form-item label="路由地址">
                            <el-input
                                v-model="formData.paths"
                                show-word-limit
                                placeholder="请输入"
                            ></el-input>
                        </el-form-item>
                    </div>

                    <div v-if="formData.menuType == menuDataType.MENU">
                        <el-form-item label="组件路径">
                            <el-input
                                v-model="formData.component"
                                show-word-limit
                                placeholder="请输入"
                            ></el-input>
                        </el-form-item>

                        <el-form-item label="菜单权限字符">
                            <el-input
                                v-model="formData.perms"
                                show-word-limit
                                placeholder="请输入"
                            ></el-input>
                        </el-form-item>

                        <el-form-item label="路由参数">
                            <el-input
                                v-model="formData.params"
                                show-word-limit
                                placeholder="请输入"
                            ></el-input>
                        </el-form-item>

                        <el-form-item label="选中菜单">
                            <el-input
                                v-model="formData.selected"
                                show-word-limit
                                placeholder="请输入选中菜单路径"
                            ></el-input>
                        </el-form-item>
                    </div>

                    <el-form-item label="菜单排序">
                        <el-input
                            v-model="formData.menuSort"
                            show-word-limit
                            type="number"
                            placeholder="请输入排序"
                        ></el-input>
                    </el-form-item>

                    <div v-if="formData.menuType == menuDataType.MENU">
                        <el-form-item label="是否缓存">
                            <el-radio-group v-model="formData.isCache">
                                <el-radio :label="1">缓存</el-radio>
                                <el-radio :label="0">不缓存</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </div>

                    <div v-if="(formData.menuType == menuDataType.BUTTON) == ''">
                        <el-form-item label="显示状态">
                            <el-radio-group v-model="formData.isShow">
                                <el-radio :label="1">显示</el-radio>
                                <el-radio :label="0">隐藏</el-radio>
                            </el-radio-group>
                        </el-form-item>

                        <el-form-item label="菜单状态">
                            <el-radio-group v-model="formData.isDisable">
                                <el-radio :label="0">正常</el-radio>
                                <el-radio :label="1">停用</el-radio>
                            </el-radio-group>
                        </el-form-item>
                    </div>
                </el-form>
            </div>
        </el-card>
        <footer-btns>
            <el-button type="primary" size="small" @click="onSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup>
import { useAdmin } from '@/core/hooks/app'
import { onMounted, reactive, ref } from 'vue'
import FooterBtns from '@/components/footer-btns/index.vue'
import type { ElForm, ElMessage } from 'element-plus'
import { apiConfigGetMenu, apiMenuDetail, apiMenuAdd, apiMenuEdit, apiMenuDelete } from '@/api/auth'
import SelectIcon from './select-icon/index.vue'

const menuDataType = {
    CATALOG: 'M', // 目录
    MENU: 'C', // 菜单
    BUTTON: 'A' // 按钮
}

const { router, route } = useAdmin()
const id: any = route.query.id

const formRefs = ref<InstanceType<typeof ElForm>>()

// 父级菜单选择
const menuList = ref<any>([])

// 表单数据
const formData = ref({
    pid: '', // 上级ID
    menuType: 'M', // 菜单类型
    menuName: '', // 菜单名称
    menuIcon: '', // 菜单图标
    menuSort: '', // 菜单排序
    perms: '', // 菜单权限字符
    paths: '', // 路由路径
    component: '', // 前端组件
    selected: '', // 选中路径
    params: '', // 路由参数
    isShow: 1, // 是否显示：0=否， 1=是
    isCache: 0, // 是否缓存：0=否， 1=是
    isDisable: 0 // 是否禁用: 0=否， 1=是
})

// 获取详情
const getMenuDetail = async (id: number) => {
    ;(formData.value as {}) = await apiMenuDetail({ id })
    getFatherMenu()
}

// 获取父级菜单选择
const getFatherMenu = async () => {
    const menus = (await apiConfigGetMenu()) || []
    menuList.value = [{ id: 0, menuName: '顶级' }, ...menus]
    console.log(menuList.value, '---------------menuaList.value')
}

// 添加菜单
const handleMenuAdd = async () => {
    await apiMenuAdd({ ...formData.value })
    router.back()
}

// 编辑菜单
const handleMenuEdit = async () => {
    await apiMenuEdit({ ...formData.value })
    router.back()
}

// 保存
const onSubmit = () => {
    formRefs.value?.validate()?.then(valid => {
        if (!valid) {
            return
        }

        if (!id) handleMenuAdd()
        else handleMenuEdit()
    })
}

// 切换
const changeType = (val: string) => {
    menuDataType.value = val
}

onMounted(() => {
    if (id) getMenuDetail(id)
    getFatherMenu()
})
</script>

<style lang="scss" scoped></style>
