<template>
    <view class="user-set">
        <navigator :url="`/pages/user_data/user_data`">
            <view class="item flex bg-white mt-[20rpx]">
                <u-avatar :src="userInfo.avatar" shape="square"></u-avatar>
                <view class="ml-[20rpx] flex flex-1 justify-between">
                    <view>
                        <view class="mb-[15rpx] text-xl font-medium">{{ userInfo.nickname }}</view>
                        <view class="text-content text-xs">账号：{{ userInfo.username }}</view>
                    </view>
                    <u-icon name="arrow-right" color="#666"></u-icon>
                </view>
            </view>
        </navigator>
        <view
            class="item bg-white mt-[20rpx] btn-border flex flex-1 justify-between"
            @click="show = true"
        >
            <view class="">登录密码</view>
            <u-icon name="arrow-right" color="#666"></u-icon>
        </view>
        <view class="item bg-white btn-border flex flex-1 justify-between">
            <view class="">绑定微信</view>
            <view class="flex justify-between">
                <view class="text-muted mr-[20rpx]">
                    {{ userInfo.isBindMnp ? '已绑定' : '未绑定' }}
                </view>
                <u-icon name="arrow-right" color="#666"></u-icon>
            </view>
        </view>

        <navigator :url="`/pages/agreement/agreement?type=${AgreementEnum.PRIVACY}`">
            <view class="item bg-white mt-[20rpx] btn-border flex flex-1 justify-between">
                <view class="">隐私政策</view>
                <u-icon name="arrow-right" color="#666"></u-icon>
            </view>
        </navigator>
        <navigator :url="`/pages/agreement/agreement?type=${AgreementEnum.SERVICE}`">
            <view class="item bg-white btn-border flex flex-1 justify-between">
                <view class="">服务协议</view>
                <u-icon name="arrow-right" color="#666"></u-icon>
            </view>
        </navigator>
        <view class="item bg-white btn-border flex flex-1 justify-between">
            <view class="">关于我们</view>
            <view class="flex justify-between">
                <view class="text-muted mr-[20rpx]">
                    {{ appStore.config.version }}
                </view>
                <u-icon name="arrow-right" color="#666"></u-icon>
            </view>
        </view>
        <u-action-sheet :list="list" v-model="show" @click="handleClick"></u-action-sheet>
    </view>
</template>

<script setup lang="ts">
import { getUserInfo } from '@/api/user'
import { onShow } from '@dcloudio/uni-app'
import { reactive, ref } from 'vue'
import { useAppStore } from '@/stores/app'
import { AgreementEnum } from '@/enums/agreementEnums'

const appStore = useAppStore()
const userInfo = ref({})
const list = ref([
    {
        text: '修改密码'
    },
    {
        text: '忘记密码'
    }
])
const show = ref(false)
const getUser = async () => {
    const res = await getUserInfo()
    console.log(res, 'res')

    userInfo.value = res
}

const handleClick = (index: number) => {
    switch (index) {
        case 0:
            uni.navigateTo({ url: '/pages/change_password/change_password' })
            break
        case 1:
            uni.navigateTo({ url: '/pages/forget_pwd/forget_pwd' })
            break
    }
}

onShow(() => {
    getUser()
})
</script>

<style lang="scss" scoped>
.user-set {
    .item {
        padding: 30rpx;
    }

    .btn-border {
        border-bottom: 1rpx solid $u-form-item-border-color;
    }
}
</style>
