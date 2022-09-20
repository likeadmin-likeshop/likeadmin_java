<!-- 个人资料 -->
<template>
    <div class="user-setting">
        <el-card class="!border-none" shadow="never">
            <el-form
                ref="formRef"
                class="ls-form"
                :model="formData"
                :rules="rules"
                label-width="100px"
            >
                <el-form-item label="头像：" prop="avatar">
                    <material-picker v-model="formData.avatar" :limit="1" />
                </el-form-item>

                <el-form-item label="账号：" prop="username">
                    <div class="w-80">
                        <el-input v-model="formData.username" disabled />
                    </div>
                </el-form-item>

                <el-form-item label="名称：" prop="nickname">
                    <div class="w-80">
                        <el-input v-model="formData.nickname" placeholder="请输入名称" />
                    </div>
                </el-form-item>

                <el-form-item label="当前密码：" prop="currPassword">
                    <div class="w-80">
                        <el-input
                            v-model.trim="formData.currPassword"
                            placeholder="修改密码时必填, 不修改密码时留空"
                            type="password"
                            show-password
                        />
                    </div>
                </el-form-item>

                <el-form-item label="新的密码：" prop="password">
                    <div class="w-80">
                        <el-input
                            v-model.trim="formData.password"
                            placeholder="修改密码时必填, 不修改密码时留空"
                            type="password"
                            show-password
                        />
                    </div>
                </el-form-item>

                <el-form-item label="确定密码：" prop="passwordConfirm">
                    <div class="w-80">
                        <el-input
                            v-model.trim="formData.passwordConfirm"
                            placeholder="修改密码时必填, 不修改密码时留空"
                            type="password"
                            show-password
                        />
                    </div>
                </el-form-item>
            </el-form>
        </el-card>
        <footer-btns>
            <el-button type="primary" @click="handleSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script setup lang="ts" name="userSetting">
import { setUserInfo } from '@/api/user'
import useUserStore from '@/stores/modules/user'
import feedback from '@/utils/feedback'
import type { FormInstance } from 'element-plus'
const formRef = ref<FormInstance>()
const userStore = useUserStore()
// 表单数据
const formData = reactive({
    avatar: '', // 头像
    username: '', // 账号
    nickname: '', // 名称
    currPassword: '', // 当前密码
    password: '', // 新的密码
    passwordConfirm: '' // 确定密码
})

// 表单校验规则
const rules = reactive<object>({
    avatar: [
        {
            required: true,
            message: '头像不能为空',
            trigger: ['change']
        }
    ],
    nickname: [
        {
            required: true,
            message: '请输入名称',
            trigger: ['blur']
        }
    ],
    currPassword: [
        {
            validator: (rule: object, value: string, callback: any) => {
                if (formData.password) {
                    if (!value) callback(new Error('请输入当前密码'))
                }
                callback()
            },
            trigger: 'blur'
        }
    ],
    password: [
        {
            validator: (rule: object, value: string, callback: any) => {
                if (formData.currPassword) {
                    if (!value) callback(new Error('请输入新的密码'))
                }
                callback()
            },
            trigger: 'blur'
        }
    ],
    passwordConfirm: [
        {
            validator: (rule: object, value: string, callback: any) => {
                if (formData.password) {
                    if (!value) callback(new Error('请再次输入密码'))
                    if (value !== formData.password) callback(new Error('两次输入密码不一致!'))
                }
                callback()
            },
            trigger: 'blur'
        }
    ]
})

// 获取个人设置
const getUser = async () => {
    const userInfo = userStore.userInfo
    for (const key in formData) {
        //@ts-ignore
        formData[key] = userInfo[key]
    }
}

// 设置个人设置
const setUser = async () => {
    await setUserInfo(formData)
    feedback.msgSuccess('保存成功')
    userStore.getUserInfo()
}

// 提交数据
const handleSubmit = async () => {
    await formRef.value?.validate()
    setUser()
}

getUser()
</script>

<style lang="scss" scoped></style>
