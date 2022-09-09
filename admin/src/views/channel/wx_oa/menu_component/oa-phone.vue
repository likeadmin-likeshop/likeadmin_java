<template>
    <!-- Phone -->
    <div class="oa-phone">
        <div class="oa-phone-content"></div>

        <div class="oa-phone-menu">
            <div class="flex items-center justify-center oa-phone-switch">
                <el-icon>
                    <Grid />
                </el-icon>
            </div>
        </div>
    </div>
</template>

<style lang="scss" scoped>
.oa-phone {
    width: 260px;
    height: 460px;
    border: 1px solid #E5E5EA;

    &-content {
        height: 420px;
        border-bottom: 1px solid #E5E5EA;
    }

    &-menu {
        height: 40px;

        .oa-phone-switch {
            width: 40px;
            height: 100%;
            border-right: 1px solid #E5E5EA;
        }
    }
}
</style>