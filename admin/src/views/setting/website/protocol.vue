<template>
    <div class="xl:flex">
        <el-card class="!border-none flex-1 xl:mr-4 mb-4" shadow="never">
            <template #header>
                <span class="font-medium">服务协议</span>
            </template>
            <el-form :model="formData" label-width="80px">
                <el-form-item label="协议名称">
                    <el-input v-model="formData.service.name" />
                </el-form-item>
            </el-form>

            <editor class="mb-10" v-model="formData.service.content" height="500"></editor>
        </el-card>
        <el-card class="!border-none flex-1 mb-4" shadow="never">
            <template #header>
                <span class="font-medium">隐私协议</span>
            </template>
            <el-form :model="formData" label-width="80px">
                <el-form-item label="协议名称">
                    <el-input v-model="formData.privacy.name" />
                </el-form-item>
            </el-form>

            <editor class="mb-10" v-model="formData.privacy.content" height="500"></editor>
        </el-card>
    </div>
    <footer-btns v-perms="['setting:protocol:save']">
        <el-button type="primary" @click="handelSave">保存</el-button>
    </footer-btns>
</template>

<script setup lang="ts" naem="webProtocol">
import { getProtocol, setProtocol } from '@/api/setting/website'
import feedback from '@/utils/feedback'

const formData = ref({
    privacy: {
        content: '',
        name: ''
    },
    service: {
        content: '',
        name: ''
    }
})
const getProtocolDetail = async () => {
    formData.value = await getProtocol()
}
const handelSave = async () => {
    await setProtocol(formData.value)
    feedback.msgSuccess('操作成功')
    getProtocolDetail()
}
getProtocolDetail()
</script>
