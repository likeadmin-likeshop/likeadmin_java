<template>
    <div class="select-icon">
        <div>
            <span class="m-r-20">选择图标</span>

            <el-popover placement="bottom" :width="500" trigger="click">
                <template #reference>
                    <el-button style="margin-right: 16px">
                        <el-icon><arrow-down /> </el-icon>
                    </el-button>
                </template>

                <div>goerb</div>
            </el-popover>
        </div>
    </div>
</template>

<script lang="ts" setup></script>

<style lang="scss" scoped></style>
