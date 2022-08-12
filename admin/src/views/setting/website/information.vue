<!-- 网站信息 -->
<template>
    <div class="website-information">
        <el-card shadow="never" class="!border-none">
            <el-form
                ref="formRef"
                :rules="rules"
                class="ls-form"
                :model="formData"
                label-width="120px"
            >
                <el-form-item label="网站名称" prop="name">
                    <div class="w-80">
                        <el-input
                            v-model="formData.name"
                            placeholder="请输入网站名称"
                            maxlength="30"
                            show-word-limit
                        ></el-input>
                    </div>
                </el-form-item>
                <el-form-item label="网站图标" prop="favicon" required>
                    <div>
                        <material-picker v-model="formData.favicon" :limit="1" />
                        <div class="form-tips">建议尺寸：100*100像素，支持jpg，jpeg，png格式</div>
                    </div>
                </el-form-item>
                <el-form-item label="网站logo" prop="logo" required>
                    <div>
                        <material-picker v-model="formData.logo" :limit="1" />
                        <div class="form-tips">建议尺寸：200*200像素，支持jpg，jpeg，png格式</div>
                    </div>
                </el-form-item>
                <el-form-item label="登录页广告图" prop="backdrop" required>
                    <div>
                        <material-picker v-model="formData.backdrop" :limit="1" />
                        <div class="form-tips">建议尺寸：400*400像素，支持jpg，jpeg，png格式</div>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <footer-btns v-perms="['setting.web.web_setting/setWebsite']">
            <el-button type="primary" @click="handleSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup>
import { getWebsite, setWebsite } from '@/api/setting/website'
import type { FormInstance } from 'element-plus'
const formRef = ref<FormInstance>()

// 表单数据
const formData = reactive({
    name: '', // 网站名称
    favicon: '', // 网站图标
    logo: '', // 网站logo
    backdrop: '' // 登录页广告图
})

// 表单验证
const rules = {
    name: [
        {
            required: true,
            message: '请输入网站名称',
            trigger: ['blur']
        }
    ]
}

// 获取备案信息
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
    getData()
}

getData()
</script>

<style lang="scss" scoped></style>
