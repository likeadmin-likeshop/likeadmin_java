<template>
    <div class="edit-popup">
        <popup
            ref="popupRef"
            title="设置存储"
            :async="true"
            width="550px"
            @confirm="handleSubmit"
            @close="handleClose"
        >
            <el-form ref="formRef" :model="formData" label-width="120px" :rules="formRules">
                <el-form-item label="短信渠道">
                    <div>
                        {{ formData.name }}
                    </div>
                </el-form-item>
                <el-form-item label="短信签名" prop="sign">
                    <el-input v-model="formData.sign" placeholder="请输入短信签名" />
                </el-form-item>
                <el-form-item label="APP_KEY" prop="appKey" v-if="formData.alias == 'aliyun'">
                    <el-input v-model="formData.appKey" placeholder="请输入APP_KEY" />
                </el-form-item>
                <el-form-item label="APP_ID" prop="appId" v-if="formData.alias == 'tencent'">
                    <el-input v-model="formData.appId" placeholder="请输入APP_ID" />
                </el-form-item>
                <el-form-item label="SECRET_ID" prop="secretId" v-if="formData.alias == 'tencent'">
                    <el-input v-model="formData.secretId" placeholder="请输入SECRET_ID" />
                </el-form-item>
                <el-form-item label="SECRET_KEY" prop="secretKey">
                    <el-input v-model="formData.secretKey" placeholder="请输入SECRET_KEY" />
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-radio-group v-model="formData.status">
                        <el-radio :label="0">关闭</el-radio>
                        <el-radio :label="1">开启</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
        </popup>
    </div>
</template>
<script lang="ts" setup>
import { setSmsConfig, smsDetail } from '@/api/message'
import type { FormInstance } from 'element-plus'
import Popup from '@/components/popup/index.vue'
import feedback from '@/utils/feedback'
const emit = defineEmits(['success'])
const formRef = shallowRef<FormInstance>()
const popupRef = shallowRef<InstanceType<typeof Popup>>()
const formData = reactive({
    name: '',
    alias: '',
    sign: '',
    appKey: '',
    appId: '',
    secretKey: '',
    secretId: '',
    status: 0
})

const formRules = {
    sign: [
        {
            required: true,
            message: '请输入短信签名',
            trigger: 'blur'
        }
    ],
    appId: [
        {
            required: true,
            message: '请输入APP_ID',
            trigger: 'blur'
        }
    ],
    appKey: [
        {
            required: true,
            message: '请输入APP_KEY',
            trigger: 'blur'
        }
    ],
    secretKey: [
        {
            required: true,
            message: '请输入SECRET_KEY',
            trigger: 'blur'
        }
    ],
    secretId: [
        {
            required: true,
            message: '请输入SECRET_ID',
            trigger: 'blur'
        }
    ]
}

const handleSubmit = async () => {
    await formRef.value?.validate()
    await setSmsConfig(formData)
    feedback.msgSuccess('操作成功')
    popupRef.value?.close()
    emit('success')
}

const getDetail = async () => {
    const data = await smsDetail({
        alias: formData.alias
    })
    for (const key in data) {
        //@ts-ignore
        formData[key] = data[key]
    }
}

const open = (type: string) => {
    formData.alias = type
    popupRef.value?.open()
    getDetail()
}

const handleClose = () => {
    formRef.value?.resetFields()
}

defineExpose({
    open
})
</script>
