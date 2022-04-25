<!-- 网站信息 -->
<template>
    <div class="website-information">
        <el-card shadow="never" class="m-t-15">
            <el-form
                ref="formRef"
                :rules="rules"
                class="ls-form"
                :model="formData"
                label-width="150px"
                size="small"
            >
                <el-form-item label="网站名称" prop="name">
                    <el-input
                        v-model="formData.name"
                        placeholder="请输入网站名称"
                        maxlength="12"
                        show-word-limit
                    ></el-input>
                </el-form-item>
                <el-form-item label="网站图标" prop="favicon">
                    <material-select v-model="formData.favicon" :limit="1" />
                    <div class="flex">
                        <div class="muted xs m-r-16">
                            建议尺寸：100*100像素，支持jpg，jpeg，png格式
                        </div>
                        <el-popover placement="right" width="200" trigger="hover">
                            <el-image
                                src="https://img2.baidu.com/it/u=3357699356,1912406716&fm=26&fmt=auto&gp=0.jpg"
                            />
                            <el-button slot="reference" type="text">查看示例</el-button>
                        </el-popover>
                    </div>
                </el-form-item>
                <el-form-item label="网站LOGO" prop="logo">
                    <material-select v-model="formData.logo" :limit="1" />
                    <div class="flex">
                        <div class="muted xs m-r-16">
                            建议尺寸：100*100像素，支持jpg，jpeg，png格式
                        </div>
                        <el-popover placement="right" width="200" trigger="hover">
                            <el-image
                                src="https://img2.baidu.com/it/u=3357699356,1912406716&fm=26&fmt=auto&gp=0.jpg"
                            />
                            <el-button slot="reference" type="text">查看示例</el-button>
                        </el-popover>
                    </div>
                </el-form-item>
                <el-form-item label="登录页广告图" prop="backdrop">
                    <material-select v-model="formData.backdrop" :limit="1" />
                    <div class="flex">
                        <div class="muted xs m-r-16">
                            建议尺寸：100*100像素，支持jpg，jpeg，png格式
                        </div>
                        <el-popover placement="right" width="200" trigger="hover">
                            <el-image
                                src="https://img2.baidu.com/it/u=3357699356,1912406716&fm=26&fmt=auto&gp=0.jpg"
                            />
                            <el-button slot="reference" type="text">查看示例</el-button>
                        </el-popover>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <footer-btns>
            <el-button
                v-perm="['setting:setWebsite']"
                type="primary"
                size="small"
                @click="setWebsite"
                >保存</el-button
            >
        </footer-btns>
    </div>
</template>

<script lang="ts">
import { defineComponent, reactive, onMounted, Ref, ref } from 'vue'
import { ElInput, ElForm, ElMessage } from 'element-plus'
import MaterialSelect from '@/components/material-select/index.vue'
import FooterBtns from '@/components/footer-btns/index.vue'
import { apiGetWebsite, apiSetWebsite } from '@/api/setting'

export default defineComponent({
    components: {
        MaterialSelect,
        FooterBtns
    },
    setup() {
        const formRef: Ref<typeof ElForm | null> = ref(null)

        // 表单数据
        const formData = reactive({
            name: '', // 网站名称
            favicon: '', // 网站图标
            logo: '', // 网站logo
            backdrop: '' // 登录页广告图
        })

        // 表单验证
        const rules = {
            name: [{ required: true, message: '请输入网站名称', trigger: ['blur'] }],
            favicon: [{ required: true, message: '上传网站图标', trigger: ['blur'] }],
            logo: [{ required: true, message: '上传网站logo', trigger: ['blur'] }],
            backdrop: [{ required: true, message: '上传登录页广告图', trigger: ['blur'] }]
        }

        // 获取备案信息
        const getWebsite = () => {
            apiGetWebsite().then((res: any) => {
                console.log('res', res)
                formData.name = res.name
                formData.favicon = res.favicon
                formData.logo = res.logo
                formData.backdrop = res.backdrop
            })
        }

        // 设置备案信息
        const setWebsite = () => {
            formRef.value?.validate((valid: boolean) => {
                if (!valid) {
                    return
                }

                console.log('fasdfasd')
                apiSetWebsite({
                    name: formData.name,
                    favicon: formData.favicon,
                    logo: formData.logo,
                    backdrop: formData.backdrop
                })
                    .then((res: any) => {
                        console.log('res', res)
                        ElMessage({ type: 'success', message: '保存成功' })
                        getWebsite()
                    })
                    .catch((err: any) => {
                        console.log('err', err)
                    })
            })
        }

        onMounted(() => {
            getWebsite()
        })

        return {
            formData,
            rules,
            getWebsite,
            setWebsite,
            formRef
        }
    }
})
</script>

<style lang="scss" scoped></style>
