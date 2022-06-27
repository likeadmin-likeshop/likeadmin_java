<template>
    <div class="post-edit">
        <el-card shadow="never">
            <el-page-header :content="id ? '编辑岗位' : '新增岗位'" @back="$router.back()" />
        </el-card>

        <el-card shadow="never" class="m-t-15">
            <el-form
                ref="formRefs"
                :rules="rules"
                class="ls-form"
                :model="formData"
                label-width="150px"
                size="small"
            >
                <!-- 岗位名称 -->
                <el-form-item label="岗位名称" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入岗位名称"></el-input>
                </el-form-item>

                <!-- 岗位编码 -->
                <el-form-item label="岗位编码" prop="code">
                    <el-input v-model="formData.code" placeholder="请输入岗位编码"></el-input>
                </el-form-item>

                <!-- 岗位排序 -->
                <el-form-item label="排序" prop="sort">
                    <el-input v-model="formData.sort" placeholder="请输入排序" type="number"></el-input>
                    <div class="muted">默认为0， 数值越大越排前</div>
                </el-form-item>

                <!-- 备注 -->
                <el-form-item label="备注" prop="remarks">
                    <el-input
                        v-model="formData.remarks"
                        placeholder="请输入备注"
                        type="textarea"
                        :autosize="{ minRows: 4, maxRows: 6 }"
                    ></el-input>
                </el-form-item>

                <!-- 管理员状态 -->
                <el-form-item label="岗位状态">
                    <el-switch v-model="formData.isStop" :active-value="0" :inactive-value="1" />
                </el-form-item>
            </el-form>
        </el-card>

        <footer-btns>
            <el-button type="primary" size="small" @click="onSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup>
    import { computed, defineComponent, onMounted, reactive, Ref, ref, toRefs } from 'vue'
    import FooterBtns from '@/components/footer-btns/index.vue'
    import { apiPostAdd, apiPostEdit, apiPostDetail } from '@/api/organize'
    import { ElForm, ElMessage } from 'element-plus'
    import { useAdmin } from '@/core/hooks/app'

    const { router, route } = useAdmin()
    const id = computed(() => route.query?.id)
    const formRefs: Ref<typeof ElForm | null> = ref(null)

    // 表单数据
    const { formData, rules } = toRefs(
        reactive({
            formData: {
                name: '',
                code: '',
                sort: 0,
                remarks: '',
                isStop: 0,
            },
            rules: {
                code: [
                    {
                        required: true,
                        message: '请输入岗位编码',
                        trigger: ['blur'],
                    },
                ],
                name: [
                    {
                        required: true,
                        message: '请输入岗位名称',
                        trigger: ['blur'],
                    },
                ],
            },
        })
    )

    // 获取详情
    const getDetail = () => {
        if (!id.value) {
            return
        }

        apiPostDetail({
            id: id.value,
        }).then((res: any) => {
            formData.value = res
        })
    }

    // 提交
    const onSubmit = () => {
        formRefs.value?.validate((valid: boolean) => {
            if (!valid) {
                return
            }
            const promise = id.value
                ? apiPostEdit({
                      ...formData.value,
                      id: id.value,
                  })
                : apiPostAdd(formData.value)
            promise.then(() => {
                setTimeout(() => router.go(-1), 500)
                ElMessage({ type: 'success', message: '保存成功' })
            })
        })
    }

    onMounted(() => {
        getDetail()
    })
</script>

<style lang="scss" scoped>
    .post-edit {
        :deep(.el-textarea) {
            width: 280px;
        }
    }
</style>
