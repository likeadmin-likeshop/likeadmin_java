<template>
    <div class="article-edit">
        <el-card class="!border-none" shadow="never">
            <el-page-header :content="$route.meta.title" @back="$router.back()" />
        </el-card>
        <el-card class="mt-4 !border-none" shadow="never">
            <el-form
                ref="formRef"
                class="ls-form"
                :model="formData"
                label-width="96px"
                :rules="rules"
            >
                <el-form-item label="任务名称" prop="name">
                    <div class="w-80">
                        <el-input
                            v-model="formData.name"
                            placeholder="请输入任务名称"
                            maxlength="30"
                            clearable
                        />
                    </div>
                </el-form-item>
                <el-form-item label="任务分组" prop="types">
                    <el-select
                        class="w-80"
                        v-model="formData.types"
                        clearable
                        placeholder="请选择任务分组"
                    >
                        <el-option label="默认" value="default" />
                        <el-option label="系统" value="system" />
                    </el-select>
                </el-form-item>
                <el-form-item label="调用方法" prop="command">
                    <div class="w-80">
                        <el-input
                            v-model="formData.command"
                            placeholder="请输入调用目标字符串"
                            clearable
                        />
                    </div>
                </el-form-item>

                <el-form-item label="cron表达式" prop="rules">
                    <div class="w-80">
                        <el-input v-model="formData.rules" placeholder="请输入cron执行表达式" />
                    </div>
                </el-form-item>

                <el-form-item label="备注" prop="remark">
                    <div class="w-80">
                        <el-input
                            v-model="formData.remark"
                            type="textarea"
                            :autosize="{ minRows: 3, maxRows: 6 }"
                            :maxlength="200"
                            show-word-limit
                            clearable
                        />
                    </div>
                </el-form-item>
                <el-form-item label="执行策略" prop="strategy">
                    <el-radio-group v-model="formData.strategy">
                        <el-radio :label="1"> 立即执行 </el-radio>
                        <el-radio :label="2"> 执行一次 </el-radio>
                        <el-radio :label="3"> 放弃执行 </el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="是否并发" prop="concurrent">
                    <el-radio-group v-model="formData.concurrent">
                        <el-radio :label="1"> 允许 </el-radio>
                        <el-radio :label="0"> 禁止 </el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="状态">
                    <el-switch v-model="formData.status" :active-value="1" :inactive-value="2" />
                </el-form-item>
            </el-form>
        </el-card>
        <footer-btns>
            <el-button type="primary" @click="handleSave">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup name="scheduledTaskEdit">
import type { FormInstance } from 'element-plus'
import { crontabAdd, crontabEdit, crontabDetail } from '@/api/setting/system'
import useMultipleTabs from '@/hooks/useMultipleTabs'
import feedback from '@/utils/feedback'

const route = useRoute()
const router = useRouter()
const formData = reactive({
    id: '',
    name: '',
    types: '',
    command: '',
    rules: '',
    status: 1,
    strategy: 1,
    concurrent: 0,
    remark: ''
})

const { removeTab } = useMultipleTabs()
const formRef = shallowRef<FormInstance>()
const rules = reactive({
    name: [{ required: true, message: '请输入任务名称' }],
    command: [{ required: true, message: '请输入调用目标字符串' }],
    rules: [{ required: true, message: '请输入cron执行表达式' }]
})

const getDetails = async () => {
    const data = await crontabDetail({
        id: route.query.id
    })
    Object.keys(formData).forEach((key) => {
        //@ts-ignore
        formData[key] = data[key]
    })
}

const handleSave = async () => {
    await formRef.value?.validate()
    if (route.query.id) {
        await crontabEdit(formData)
    } else {
        await crontabAdd(formData)
    }
    feedback.msgSuccess('操作成功')
    removeTab()
    router.back()
}
onMounted(async () => {
    if (!route.query.id) {
        return
    }
    await getDetails()
})
</script>
