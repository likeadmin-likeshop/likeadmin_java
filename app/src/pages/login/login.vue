<template>
    <view
        class="bg-white login min-h-full flex flex-col items-center px-[40rpx] pt-[80rpx] box-border"
    >
        <view>
            <u-image src="" mode="widthFix" height="160" width="160" />
        </view>
        <view class="mt-4">这里是商城名称</view>
        <view class="w-full mt-[60rpx]">
            <u-form borderBottom>
                <template v-if="scene == LoginTypeEnum.ACCOUNT">
                    <u-form-item borderBottom>
                        <u-icon class="mr-2" :size="36" name="/static/images/icon_user.png" />
                        <u-input
                            class="flex-1"
                            v-model="formData.username"
                            :border="false"
                            placeholder="请输入账号/手机号码"
                        />
                    </u-form-item>
                    <u-form-item borderBottom>
                        <u-icon class="mr-2" :size="36" name="/static/images/icon_password.png" />
                        <u-input
                            class="flex-1"
                            v-model="formData.password"
                            type="password"
                            placeholder="请输入密码"
                            :border="false"
                        />
                        <view
                            class="border-l border-solid border-0 border-light pl-3 text-muted leading-4 ml-3"
                        >
                            忘记密码？
                        </view>
                    </u-form-item>
                </template>
                <template v-if="scene == LoginTypeEnum.MOBILE">
                    <u-form-item borderBottom>
                        <u-icon class="mr-2" :size="36" name="/static/images/icon_mobile.png" />
                        <u-input
                            class="flex-1"
                            v-model="formData.mobile"
                            :border="false"
                            placeholder="请输入手机号码"
                        />
                    </u-form-item>
                    <u-form-item borderBottom>
                        <u-icon class="mr-2" :size="36" name="/static/images/icon_code.png" />
                        <u-input
                            class="flex-1"
                            v-model="formData.code"
                            placeholder="请输入验证码"
                            :border="false"
                        />
                        <view
                            class="border-l border-solid border-0 border-light pl-3 text-muted leading-4 ml-3 w-[180rpx]"
                            @click="sendSms"
                        >
                            <u-verification-code
                                ref="uCodeRef"
                                :seconds="60"
                                @change="codeChange"
                                change-text="x秒"
                            />
                            {{ codeTips }}
                        </view>
                    </u-form-item>
                </template>
            </u-form>
            <view class="mt-[60rpx]">
                <u-checkbox v-model="isCheckAgreement" shape="circle">
                    <view class="text-xs flex">
                        已阅读并同意
                        <navigator class="text-primary">《服务协议》</navigator>
                        和<navigator class="text-primary">《隐私协议》</navigator>
                    </view>
                </u-checkbox>
            </view>
            <view class="mt-[40rpx]">
                <u-button type="primary" shape="circle" @click="accountLogin(scene)">
                    登 录
                </u-button>
            </view>

            <view class="text-content flex justify-between mt-[40rpx]">
                <view v-if="scene == LoginTypeEnum.MOBILE" @click="scene = LoginTypeEnum.ACCOUNT">
                    账号密码登录
                </view>
                <view v-if="scene == LoginTypeEnum.ACCOUNT" @click="scene = LoginTypeEnum.MOBILE">
                    短信验证码登录
                </view>
                <navigator url="/pages/register/register">注册账号</navigator>
            </view>
            <view class="mt-[80rpx]">
                <u-divider>第三方登录</u-divider>
                <div class="flex justify-center mt-[40rpx]">
                    <div class="flex flex-col items-center" @click="wxLogin">
                        <u-icon name="/static/images/icon_wx.png" size="80" />
                        <div class="text-sm mt-[10px]">微信登录</div>
                    </div>
                </div>
            </view>
        </view>
    </view>
</template>

<script setup lang="ts">
import { login } from '@/api/account'
import { smsSend } from '@/api/app'
import { SMSEnum } from '@/enums/appEnums'
import { reactive, ref, shallowRef } from 'vue'
enum LoginTypeEnum {
    MOBILE = 'mobile',
    ACCOUNT = 'account',
    MNP = 'mnp'
}
const uCodeRef = shallowRef()
const scene = ref(LoginTypeEnum.ACCOUNT)
const codeTips = ref('')
const isCheckAgreement = ref(false)
const formData = reactive({
    username: '',
    password: '',
    code: '',
    mobile: ''
})

const codeChange = (text: string) => {
    codeTips.value = text
}

const sendSms = async () => {
    if (!formData.mobile) return uni.$u.toast('请输入手机号码')
    if (uCodeRef.value?.canGetCode) {
        await smsSend({
            scene: SMSEnum.LOGIN,
            mobile: formData.mobile
        })
        uni.$u.toast('发送成功')
        uCodeRef.value?.start()
    }
}
const accountLogin = async (scene: LoginTypeEnum, code?: string) => {
    if (!isCheckAgreement.value) return uni.$u.toast('请勾选已阅读并同意《服务协议》和《隐私协议》')
    if (scene == LoginTypeEnum.ACCOUNT) {
        if (!formData.username) return uni.$u.toast('请输入账号/手机号码')
        if (!formData.password) return uni.$u.toast('请输入密码')
    }
    if (scene == LoginTypeEnum.MOBILE) {
        if (!formData.mobile) return uni.$u.toast('请输入手机号码')
        if (!formData.code) return uni.$u.toast('请输入验证码')
    }
    const params = {
        ...formData,
        scene
    }
    if (code) params.code = code
    await login(params)
    uni.$u.toast('登录成功')
    uni.navigateBack()
}

const wxLogin = async () => {
    const data: any = await uni.login({
        provider: 'weixin'
    })
    console.log(data)
    accountLogin(LoginTypeEnum.MNP, data.code)
}
</script>

<style lang="scss">
page {
    height: 100%;
}
</style>
