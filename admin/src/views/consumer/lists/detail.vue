<template>
    <div class="article-edit">
        <el-card class="!border-none" shadow="never">
            <el-page-header content="用户详情" @back="$router.back()" />
        </el-card>
        <el-card class="mt-4 !border-none" header="基本资料" shadow="never">
            <el-form
                ref="formRef"
                class="ls-form"
                :model="formData"
                label-width="120px"
                :rules="rules"
            >
                <div class="bg-page py-5 pl-20 mb-10">
                    <div class="mb-3 text-tx-regular">用户头像</div>
                    <el-avatar :size="58" />
                </div>
                <el-form-item label="用户编号："> 20000440556 </el-form-item>
                <el-form-item label="用户昵称："> 热心市民小张 </el-form-item>
                <el-form-item label="账号："> fwewrwre90 </el-form-item>
                <el-form-item label="真实姓名："> 张三 </el-form-item>
                <el-form-item label="性别："> 未知 </el-form-item>
                <el-form-item label="联系电话："> 13800138000 </el-form-item>
                <el-form-item label="注册来源："> 微信小程序 </el-form-item>
                <el-form-item label="注册时间："> 2022-02-15 12:12:12 </el-form-item>
                <el-form-item label="最近登录时间："> 2022-02-18 12:12:12 </el-form-item>
            </el-form>
        </el-card>
        <footer-btns>
            <el-button type="primary" @click="handleSave">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup>
import type { FormInstance } from 'element-plus'
import feedback from '@/utils/feedback'
import { useDictOptions } from '@/hooks/useDictOptions'
import { articleCateAll, articleDetail, articleEdit } from '@/api/article'

const route = useRoute()
const router = useRouter()
const formData = reactive({
    id: '',
    title: '',
    image: '',
    cid: '',
    intro: '',
    author: '',
    content: '',
    visit: 0,
    sort: 0,
    isShow: ''
})

const formRef = shallowRef<FormInstance>()
const rules = reactive({
    ['base.tableName']: [{ required: true, message: '请输入表名称', trigger: 'blur' }],
    ['base.tableComment']: [{ required: true, message: '请输入表描述', trigger: 'blur' }],
    ['base.entityName']: [{ required: true, message: '请输入实体类名称', trigger: 'blur' }],
    ['base.authorName']: [{ required: true, message: '请输入作者', trigger: 'blur' }],
    ['gen.moduleName']: [{ required: true, message: '请输入模块名', trigger: 'blur' }],
    ['gen.functionName']: [{ required: true, message: '请输入功能名称', trigger: 'blur' }],
    ['gen.treePrimary']: [{ required: true, message: '请选择树主键字段', trigger: 'blur' }],
    ['gen.treeParent']: [{ required: true, message: '请选择树父级字段', trigger: 'blur' }],
    ['gen.treeName']: [{ required: true, message: '请选择树名称字段', trigger: 'blur' }]
})

const getDetails = async () => {
    const data = await articleDetail({
        id: route.query.id
    })
    Object.keys(formData).forEach((key) => {
        //@ts-ignore
        formData[key] = data[key]
    })
}

const { optionsData } = useDictOptions<{
    articleCate: any[]
}>({
    articleCate: {
        api: articleCateAll
    }
})

const handleSave = async () => {
    await formRef.value?.validate()
    await articleEdit(formData)
    feedback.msgSuccess('操作成功')
    router.back()
}

getDetails()
</script>
