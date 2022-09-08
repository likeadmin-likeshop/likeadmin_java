<template>
    <div class="user-info flex items-center px-[50rpx]">
        <navigator
            v-if="isLogin"
            class="flex items-center"
            hover-class="none"
            url="/pages/login/login"
        >
            <u-avatar :src="user.avatar" :size="120"></u-avatar>
            <div class="text-white text-3xl ml-[20rpx]">{{ user.nickname }}</div>
        </navigator>
        <navigator v-else class="flex items-center" hover-class="none" url="/pages/login/login">
            <u-avatar src="/static/images/user/default_avatar.png" :size="120"></u-avatar>
            <div class="text-white text-3xl ml-[20rpx]">未登录</div>
        </navigator>
    </div>
</template>
<script lang="ts" setup>
const props = defineProps({
    content: {
        type: Object,
        default: () => ({})
    },
    styles: {
        type: Object,
        default: () => ({})
    },
    user: {
        type: Object,
        default: () => ({})
    },
    isLogin: {
        type: Boolean
    }
})
</script>

<style lang="scss" scoped>
.user-info {
    background: url(/static/images/user/my_topbg.png);
    height: 115px;
    background-position: bottom;
    background-size: 100% auto;
}
</style>
