<template>
    <div>
        <el-card class="!border-none" shadow="never">
            <el-page-header :content="$route.meta.title" @back="$router.back()" />
        </el-card>
        <el-form
            ref="formRef"
            :model="formData"
            label-width="120px"
            :rules="rules"
            v-loading="loading"
        >
            <el-card class="!border-none mt-4" shadow="never">
                <div class="font-medium mb-7">通知名称</div>
                <el-form-item label="通知名称" prop="name"> {{ formData.name }} </el-form-item>
                <el-form-item label="通知类型" prop="name"> {{ formData.type }} </el-form-item>
                <el-form-item label="通知业务" prop="name"> {{ formData.remarks }} </el-form-item>
            </el-card>
            <el-card class="!border-none mt-4" shadow="never">
                <div class="font-medium mb-7">短信通知</div>
                <el-form-item label="开启状态" prop="smsNotice.status" required>
                    <el-radio-group v-model="formData.smsNotice.status">
                        <el-radio label="0">关闭</el-radio>
                        <el-radio label="1">开启</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="模板ID" prop="smsNotice.templateId">
                    <div class="w-80">
                        <el-input
                            v-model="formData.smsNotice.templateId"
                            placeholder="请输入模板ID"
                        />
                    </div>
                </el-form-item>
                <el-form-item label="短信内容" prop="smsNotice.content">
                    <div class="flex-1">
                        <div class="w-full max-w-[320px]">
                            <el-input
                                type="textarea"
                                :autosize="{ minRows: 6, maxRows: 6 }"
                                v-model="formData.smsNotice.content"
                            />
                        </div>
                        <div class="form-tips">
                            <div v-for="(item, index) in formData.smsNotice.tips" :key="index">
                                {{ item }}
                            </div>
                        </div>
                    </div>
                </el-form-item>
            </el-card>
        </el-form>
        <footer-btns>
            <el-button type="primary" @click="handleSave">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup name="noticeEdit">
import type { FormInstance } from 'element-plus'
import feedback from '@/utils/feedback'
import { noticeDetail, setNoticeConfig } from '@/api/message'
import useMultipleTabs from '@/hooks/useMultipleTabs'

const route = useRoute()
const router = useRouter()

const loading = ref(false)
const formData = reactive({
    id: '',
    name: '',
    type: '',
    remarks: '',
    smsNotice: {
        status: 0,
        templateId: '',
        content: '',
        tips: []
    }
})

const rules = {
    'smsNotice.templateId': [
        {
            required: true,
            message: '请输入模板ID',
            trigger: 'blur'
        }
    ],
    'smsNotice.content': [
        {
            required: true,
            message: '请输入短信内容',
            trigger: 'blur'
        }
    ]
}
const { removeTab } = useMultipleTabs()
const formRef = shallowRef<FormInstance>()

const getDetails = async () => {
    loading.value = true
    const data = await noticeDetail({
        id: route.query.id
    })
    Object.keys(data).forEach((key) => {
        //@ts-ignore
        formData[key] = data[key]
    })
    loading.value = false
}

const handleSave = async () => {
    await formRef.value?.validate()
    await setNoticeConfig(formData)
    feedback.msgSuccess('操作成功')
    removeTab()
    router.back()
}

route.query.id && getDetails()
</script>
