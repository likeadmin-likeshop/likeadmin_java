<!-- 网站信息 -->
<template>
    <div class="user-setup">
        <el-card shadow="never" class="!border-none">
            <div class="font-medium mb-7">服务器域名</div>
            <el-form ref="formRef" :model="formData" label-width="120px">
                <el-form-item label="用户默认头像">
                    <div>
                        <material-picker v-model="formData.default_avatar" :limit="1" />
                    </div>
                </el-form-item>
                <el-form-item>
                    <div>
                        <div class="form-tips">用户注册时给的默认头像，建议尺寸：400*400像素，支持jpg，jpeg，png格式</div>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <footer-btns v-perms="['setting:website:save']">
            <el-button type="primary" @click="handleSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup>
import { getWebsite, setWebsite } from '@/api/setting/website'
import feedback from '@/utils/feedback'
import type { FormInstance } from 'element-plus'
const formRef = ref<FormInstance>()

// 表单数据
const formData = reactive({
    default_avatar: '', // 用户默认头像
})

// 获取用户设置信息
const getData = async () => {
    const data = await getWebsite()
    for (const key in formData) {
        //@ts-ignore
        formData[key] = data[key]
    }
}

// 设置备案信息
const handleSubmit = async () => {
    await formRef.value?.validate()
    await setWebsite(formData)
    feedback.msgSuccess('操作成功')
    getData()
}

getData()
</script>

<style lang="scss" scoped>
</style>
