<template>
    <div class="edit-popup">
        <popup
            ref="popupRef"
            :title="popupTitle"
            :async="true"
            width="550px"
            @confirm="handleSubmit"
            @close="handleClose"
        >
            <el-form ref="formRef" :model="formData" label-width="80px" :rules="formRules">
                <el-form-item label="菜单类型" prop="menuType" required>
                    <el-radio-group v-model="formData.menuType">
                        <el-radio :label="MenuEnum.CATALOGUE">目录</el-radio>
                        <el-radio :label="MenuEnum.MENU">菜单</el-radio>
                        <el-radio :label="MenuEnum.BUTTON">按钮</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="父级菜单" prop="pid">
                    <el-tree-select
                        class="flex-1"
                        v-model="formData.pid"
                        :data="menuOptions"
                        clearable
                        node-key="id"
                        :props="{
                            label: 'menuName'
                        }"
                        :default-expand-all="true"
                        placeholder="请选择父级菜单"
                        check-strictly
                    />
                </el-form-item>
                <el-form-item label="菜单名称" prop="menuName">
                    <el-input v-model="formData.menuName" placeholder="请输入菜单名称" clearable />
                </el-form-item>
                <el-form-item
                    v-if="formData.menuType != MenuEnum.BUTTON"
                    label="菜单图标"
                    prop="menuIcon"
                >
                    <icon-picker class="flex-1" v-model="formData.menuIcon" />
                </el-form-item>

                <el-form-item
                    v-if="formData.menuType != MenuEnum.BUTTON"
                    label="路由路径"
                    prop="paths"
                >
                    <div class="flex-1">
                        <el-input v-model="formData.paths" placeholder="请输入路由路径" clearable />
                        <div class="form-tips">
                            访问的路由地址，如：`admin`，如外网地址需内链访问则以`http(s)://`开头
                        </div>
                    </div>
                </el-form-item>
                <el-form-item
                    v-if="formData.menuType == MenuEnum.MENU"
                    label="组件路径"
                    prop="component"
                >
                    <div class="flex-1">
                        <el-autocomplete
                            class="w-full"
                            v-model="formData.component"
                            :fetch-suggestions="querySearch"
                            clearable
                            placeholder="请输入组件路径"
                        />
                        <!-- <el-input v-model="formData.component" placeholder="请输入组件路径" /> -->
                        <div class="form-tips">
                            访问的组件路径，如：`permission/admin/index`，默认在`views`目录下
                        </div>
                    </div>
                </el-form-item>
                <el-form-item
                    label="选中菜单"
                    prop="selected"
                    v-if="formData.menuType == MenuEnum.MENU"
                >
                    <div class="flex-1">
                        <el-input
                            v-model="formData.selected"
                            placeholder="请输入路由路径"
                            clearable
                        />
                        <div class="form-tips">
                            访问详情页面，编辑页面时，菜单高亮显示，如`/consumer/lists`
                        </div>
                    </div>
                </el-form-item>
                <el-form-item
                    v-if="formData.menuType != MenuEnum.CATALOGUE"
                    label="权限字符"
                    prop="perms"
                >
                    <div class="flex-1">
                        <el-input v-model="formData.perms" placeholder="请输入权限字符" clearable />
                        <div class="form-tips">
                            将作为server端API验权使用，如`system:admin:list`，请谨慎修改
                        </div>
                    </div>
                </el-form-item>
                <el-form-item
                    v-if="formData.menuType == MenuEnum.MENU"
                    label="路由参数"
                    prop="params"
                >
                    <div>
                        <div class="flex-1">
                            <el-input
                                v-model="formData.params"
                                placeholder="请输入路由参数"
                                clearable
                            />
                        </div>
                        <div class="form-tips">
                            访问路由的默认传递参数，如：`{"id": 1, "name":
                            "admin"}`或`id=1&name=admin`
                        </div>
                    </div>
                </el-form-item>
                <el-form-item
                    v-if="formData.menuType == MenuEnum.MENU"
                    label="是否缓存"
                    prop="isCache"
                    required
                >
                    <div>
                        <el-radio-group v-model="formData.isCache">
                            <el-radio :label="1">缓存</el-radio>
                            <el-radio :label="0">不缓存</el-radio>
                        </el-radio-group>
                        <div class="form-tips">选择缓存则会被`keep-alive`缓存</div>
                    </div>
                </el-form-item>
                <el-form-item
                    v-if="formData.menuType != MenuEnum.BUTTON"
                    label="是否显示"
                    prop="isShow"
                    required
                >
                    <div>
                        <el-radio-group v-model="formData.isShow">
                            <el-radio :label="1">显示</el-radio>
                            <el-radio :label="0">隐藏</el-radio>
                        </el-radio-group>
                        <div class="form-tips">
                            选择隐藏则路由将不会出现在侧边栏，但仍然可以访问
                        </div>
                    </div>
                </el-form-item>
                <el-form-item
                    v-if="formData.menuType != MenuEnum.BUTTON"
                    label="菜单状态"
                    prop="isDisable"
                    required
                >
                    <div>
                        <el-radio-group v-model="formData.isDisable">
                            <el-radio :label="0">正常</el-radio>
                            <el-radio :label="1">停用</el-radio>
                        </el-radio-group>
                        <div class="form-tips">选择停用则路由将不会出现在侧边栏，也不能被访问</div>
                    </div>
                </el-form-item>
                <el-form-item label="菜单排序" prop="menuSort">
                    <div>
                        <el-input-number v-model="formData.menuSort" :max="9999" />
                        <div class="form-tips">数值越大越排前</div>
                    </div>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import { menuLists, menuEdit, menuAdd, menuDetail } from '@/api/perms/menu'
import { getModulesKey } from '@/router'
import { MenuEnum } from '@/enums/appEnums'
import Popup from '@/components/popup/index.vue'
import feedback from '@/utils/feedback'
import { arrayToTree, treeToArray } from '@/utils/util'

const emit = defineEmits(['success', 'close'])
const formRef = shallowRef<FormInstance>()
const popupRef = shallowRef<InstanceType<typeof Popup>>()
const mode = ref('add')
const popupTitle = computed(() => {
    return mode.value == 'edit' ? '编辑菜单' : '新增菜单'
})

const componentsOptions = ref(getModulesKey())
const querySearch = (queryString: string, cb: any) => {
    const results = queryString
        ? componentsOptions.value.filter((item) =>
              item.toLowerCase().includes(queryString.toLowerCase())
          )
        : componentsOptions.value
    cb(results.map((item) => ({ value: item })))
}

const formData = reactive({
    id: '',
    //父级id
    pid: 0,
    //类型
    menuType: MenuEnum.CATALOGUE,
    //图标
    menuIcon: '',
    //名称
    menuName: '',
    //排序号
    menuSort: 0,
    // 路由路径
    paths: '',
    //权限链接
    perms: '',
    //前端组件
    component: '',
    //选中路径
    selected: '',
    //路由参数
    params: '',
    //是否缓存 0=否， 1=是
    isCache: 0,
    //是否显示 0=否， 1=是
    isShow: 1,
    //是否禁用 0=否， 1=是
    isDisable: 0
})

const formRules = {
    pid: [
        {
            required: true,
            message: '请选择父级菜单',
            trigger: ['blur', 'change']
        }
    ],
    menuName: [
        {
            required: true,
            message: '请输入菜单名称',
            trigger: 'blur'
        }
    ],
    paths: [
        {
            required: true,
            message: '请输入路由地址',
            trigger: 'blur'
        }
    ],
    component: [
        {
            required: true,
            message: '请输入组件地址',
            trigger: 'blur'
        }
    ]
}
const menuOptions = ref<any[]>([])

const getMenu = async () => {
    const data: any = await menuLists()
    const menu: any = { id: 0, menuName: '顶级', children: [] }
    menu.children = arrayToTree(
        treeToArray(data).filter((item) => item.menuType != MenuEnum.BUTTON)
    )
    menuOptions.value.push(menu)
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    mode.value == 'edit' ? await menuEdit(formData) : await menuAdd(formData)
    popupRef.value?.close()
    feedback.msgSuccess('操作成功')
    emit('success')
}

const open = (type = 'add') => {
    mode.value = type
    popupRef.value?.open()
}

const setFormData = (data: Record<any, any>) => {
    for (const key in formData) {
        if (data[key] != null && data[key] != undefined) {
            //@ts-ignore
            formData[key] = data[key]
        }
    }
}

const getDetail = async (row: Record<string, any>) => {
    const data = await menuDetail({
        id: row.id
    })
    setFormData(data)
}

const handleClose = () => {
    emit('close')
}

getMenu()

defineExpose({
    open,
    setFormData,
    getDetail
})
</script>
