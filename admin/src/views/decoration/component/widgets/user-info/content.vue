<template>
    <div class="user-info flex items-center px-[25px]">
        <img src="./images/default_avatar.png" class="w-[60px] h-[60px]" alt="" />
        <div class="text-white text-[18px] ml-[10px]">未登录</div>
    </div>
</template>
<script lang="ts" setup></script>

<style lang="scss" scoped>
.user-info {
    background: url(./images/my_topbg.png);
    height: 115px;
    background-position: bottom;
    background-size: 100% auto;
    .search-con {
        height: 100%;
        height: 36px;
        border-radius: 36px;
        background: #f4f4f4;
        color: #999999;
    }
}
</style>
