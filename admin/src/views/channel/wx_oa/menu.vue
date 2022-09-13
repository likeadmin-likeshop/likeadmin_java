<template>
    <div class="menu-oa">
        <el-card class="!border-none" shadow="never">
            <el-alert
                type="warning"
                title="配置微信公众号菜单，点击确认，保存菜单并发布至微信公众号"
                :closable="false"
                show-icon
            />
        </el-card>

        <el-card class="!border-none mt-4" shadow="never">
            <div class="flex flex-1">
                <!-- Phone -->
                <oa-phone></oa-phone>

                <!-- Attr -->
                <oa-attr></oa-attr>
            </div>
        </el-card>

        <footer-btns v-perms="['channel:h5:save']">
            <el-button type="primary" @click="">保存</el-button>
        </footer-btns>
    </div>
</template>

<script setup lang="ts">
import OaPhone from "./menu_com/oa-phone.vue";
import OaAttr from "./menu_com/oa-attr.vue";
</script>

<style lang="scss" scoped>
.menu-oa {
}
</style>
