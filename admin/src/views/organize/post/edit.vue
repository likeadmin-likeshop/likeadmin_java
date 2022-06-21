<template>
    <div class="post-edit">
        <el-card shadow="never">
            <el-page-header :content="id ? '编辑岗位' : '新增岗位'" @back="$router.back()" />
        </el-card>

        <el-card v-loading="loading" shadow="never" class="m-t-15">
            <el-form
                ref="formRefs"
                :rules="rules"
                class="ls-form"
                :model="formData"
                label-width="150px"
                size="small"
            >
                <el-form-item label="岗位名称" prop="name">
                    <el-input placeholder="请输入岗位名称"></el-input>
                </el-form-item>

                <el-form-item label="岗位编码" prop="code">
                    <el-input placeholder="请输入岗位编码"></el-input>
                </el-form-item>

                <el-form-item label="排序" prop="sort">
                    <el-input placeholder="请输入排序" type="number"></el-input>
                    <div class="muted">默认为0， 数值越大越排前</div>
                </el-form-item>

                <el-form-item label="备注" prop="remark">
                    <el-input
                        placeholder="请输入备注"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    ></el-input>
                </el-form-item>

                <el-form-item label="岗位状态">
                    <el-switch :active-value="1" :inactive-value="0" />
                </el-form-item>
            </el-form>
        </el-card>

        <footer-btns>
            <el-button type="primary" size="small" @click="onSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup>
import FooterBtns from '@/components/footer-btns/index.vue'
</script>

<style lang="scss" scoped>
.post-edit {
    :deep(.el-textarea) {
        width: 280px;
    }
}
</style>
