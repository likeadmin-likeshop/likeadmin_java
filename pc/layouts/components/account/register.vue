<template>
    <div class="login">
        <div class="flex justify-between">
            <span class="text-4xl">注册账号</span>
            <ElButton
                type="primary"
                link
                @click="setPopupType(PopupTypeEnum.LOGIN)"
            >
                返回登录
            </ElButton>
        </div>
        <ElForm
            ref="formRef"
            class="mt-[35px]"
            size="large"
            :model="formData"
            :rules="formRules"
        >
            <ElFormItem prop="username">
                <ElInput
                    v-model="formData.username"
                    placeholder="请输入创建的账号"
                />
            </ElFormItem>
            <ElFormItem prop="password">
                <ElInput
                    v-model="formData.password"
                    type="password"
                    show-password
                    placeholder="请输入6-20位数字+字母或符号组合"
                />
            </ElFormItem>
            <ElFormItem prop="passwordConfirm">
                <ElInput
                    v-model="formData.passwordConfirm"
                    type="password"
                    show-password
                    placeholder="请再次输入密码"
                />
            </ElFormItem>
            <ElFormItem class="mt-[60px]">
                <ElButton
                    class="w-full"
                    type="primary"
                    :loading="isLock"
                    @click="handleConfirmLock"
                >
                    注册
                </ElButton>
            </ElFormItem>
        </ElForm>
    </div>
</template>
<script lang="ts" setup>
import {
    ElForm,
    ElFormItem,
    ElInput,
    ElButton,
    FormInstance,
    FormRules
} from 'element-plus'
import { register } from '~~/api/account'
import feedback from '~~/utils/feedback'
import { useAccount, PopupTypeEnum } from './useAccount'
const { setPopupType } = useAccount()
const formRef = shallowRef<FormInstance>()
const formRules: FormRules = {
    username: [
        {
            required: true,
            message: '请输入创建的账号',
            trigger: ['change', 'blur']
        },
        {
            min: 3,
            max: 12,
            message: '账号长度应为3-12',
            trigger: ['change', 'blur']
        }
    ],
    password: [
        {
            required: true,
            message: '请输入6-20位数字+字母或符号组合',
            trigger: ['change', 'blur']
        },
        {
            min: 6,
            max: 20,
            message: '密码长度应为6-20',
            trigger: ['change', 'blur']
        }
    ],
    passwordConfirm: [
        {
            validator(rule: any, value: any, callback: any) {
                if (value === '') {
                    callback(new Error('请再次输入密码'))
                } else if (value !== formData.password) {
                    callback(new Error('两次输入的密码不一致'))
                } else {
                    callback()
                }
            },
            trigger: ['change', 'blur']
        }
    ]
}
const formData = reactive({
    username: '',
    password: '',
    passwordConfirm: ''
})

const handleConfirm = async () => {
    await formRef.value?.validate()
    await register(formData)
    feedback.msgSuccess('注册成功')
    setPopupType(PopupTypeEnum.LOGIN)
}
const { lockFn: handleConfirmLock, isLock } = useLockFn(handleConfirm)
</script>

<style lang="scss" scoped></style>
