<template>
    <view class="login">
        <view>
            <u-image src="" mode="widthFix" height="160" width="160" />
        </view>
        <view class="mt-4">这里是商城名称</view>
        <view class="w-full mt-[60rpx]">
            <u-form :model="formData" borderBottom>
                <u-form-item borderBottom>
                    <u-icon class="mr-2" :size="36" name="/static/images/icon_user.png" />
                    <u-input
                        v-model="formData.mobile"
                        :border="false"
                        placeholder="请输入账号/手机号码"
                    />
                </u-form-item>
                <u-form-item borderBottom>
                    <u-icon class="mr-2" :size="36" name="/static/images/icon_password.png" />
                    <u-input placeholder="请输入密码" :border="false" />
                    <view
                        class="border-l border-solid border-0 border-light pl-3 text-muted leading-4"
                    >
                        忘记密码？
                    </view>
                </u-form-item>
            </u-form>
        </view>
    </view>
</template>

<script setup lang="ts">
import { reactive } from 'vue'

const formData = reactive({
    mobile: ''
})
</script>

<style lang="scss">
page {
    height: 100%;
}
.login {
    @apply min-h-full flex flex-col items-center px-[40rpx] pt-[80rpx] box-border;
}
</style>
