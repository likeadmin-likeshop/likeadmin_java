<!-- 备案信息 -->
<template>
    <div class="website-filing">
        <el-card shadow="never" class="">
            <el-form
                ref="form"
                :rules="rules"
                class="ls-form"
                :model="formData"
                label-width="150px"
                size="small"
            >
                <!-- 版权信息输入框 -->
                <el-form-item label="版权信息" prop="privilege">
                    <el-input v-model="formData.privilege" placeholder="请输入版权信息"></el-input>
                    <div class="muted xs m-r-16">例如填写，Copyright © 2019-2020 公司名称</div>
                </el-form-item>

                <!-- ICP备案号输入框 -->
                <el-form-item label="ICP备案号" prop="icpNumber">
                    <el-input v-model="formData.icpNumber" placeholder="请输入ICP备案号"></el-input>
                </el-form-item>

                <!-- ICP备案号链接输入框 -->
                <el-form-item label="ICP备案号链接" prop="icpLink">
                    <el-input
                        v-model="formData.icpLink"
                        placeholder="请输入ICP备案号链接"
                    ></el-input>
                    <div class="muted xs m-r-16">
                        例如填写域名信息备案系统链接，http://beian.miit.gov.cn
                    </div>
                </el-form-item>

                <!--公安备案号输入框 -->
                <el-form-item label="公安备案号" prop="gaNumber">
                    <el-input v-model="formData.gaNumber" placeholder="请输入公安备案号"></el-input>
                </el-form-item>

                <!-- 公安备案号链接输入框 -->
                <el-form-item label="公安备案号链接" prop="gaLink">
                    <el-input
                        v-model="formData.gaLink"
                        placeholder="请输入公安备案号链接"
                    ></el-input>
                    <div class="muted xs m-r-16">
                        例如填写公安信息备案系统链接，http://www.beian.gov.cn
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <footer-btns>
            <el-button v-perm="['setting:setCopyright']" type="primary" size="small" @click="setCopyright">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts">
import { defineComponent, reactive, onMounted } from 'vue'
import { apiGetCopyright, apiSetCopyright } from '@/api/setting'
import FooterBtns from '@/components/footer-btns/index.vue'
export default defineComponent({
    components: {
        FooterBtns
    },
    setup() {
        // 表单数据
        const formData = reactive({
            privilege: '', // 版权信息
            icpNumber: '', // icp备案号
            icpLink: '', // icp备案链接
            gaNumber: '', // 公安备案号
            gaLink: '' // 公安备案链接
        })

        // 表单验证
        const rules = {
            // privilege: [
            //     {
            //         required: true,
            //         message: '请输入版权信息',
            //         trigger: ['blur'],
            //     },
            // ],
        }

        // 获取备案信息
        const getCopyright = () => {
            apiGetCopyright().then((res: any) => {
                console.log('res', res)
                formData.privilege = res.privilege
                formData.icpNumber = res.icpNumber
                formData.icpLink = res.icpLink
                formData.gaNumber = res.gaNumber
                formData.gaLink = res.gaLink
            })
        }

        // 设置备案信息
        const setCopyright = () => {
            console.log(formData.privilege, '==')

            apiSetCopyright({
                privilege: formData.privilege,
                icpNumber: formData.icpNumber,
                icpLink: formData.icpLink,
                gaNumber: formData.gaNumber,
                gaLink: formData.gaLink
            })
                .then((res: any) => {
                    console.log('res', res)
                    getCopyright()
                })
                .catch((err: any) => {
                    console.log('err', err)
                })
        }

        onMounted(() => {
            getCopyright()
        })

        return {
            formData,
            rules,
            getCopyright,
            setCopyright
        }
    }
})
</script>

<style lang="scss" scoped></style>
