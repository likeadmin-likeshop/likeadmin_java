<!-- 网站信息 -->
<template>
    <div class="user-setup">
        <el-card shadow="never" class="!border-none">
            <div class="font-medium mb-7">基本设置</div>
            <el-form ref="formRef" :model="formData" label-width="120px">
                <el-form-item label="用户默认头像">
                    <div>
                        <material-picker v-model="formData.defaultAvatar" :limit="1" />
                    </div>
                </el-form-item>
                <el-form-item>
                    <div>
                        <div class="form-tips">
                            用户注册时给的默认头像，建议尺寸：400*400像素，支持jpg，jpeg，png格式
                        </div>
                    </div>
                </el-form-item>
            </el-form>
        </el-card>

        <footer-btns v-perms="['setting:user:save']">
            <el-button type="primary" @click="handleSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup name="userSetup">
import { getUserSetup, setUserSetup } from '@/api/setting/user'
import feedback from '@/utils/feedback'
// import type { FormInstance } from 'element-plus'

// 表单数据
const formData = reactive({
    defaultAvatar: '' // 用户默认头像
})

// 获取用户设置数据
const getData = async () => {
    try {
        const data = await getUserSetup()
        for (const key in formData) {
            //@ts-ignore
            formData[key] = data[key]
        }
    } catch (error) {
        console.log('获取=>', error)
    }
}

// 保存用户设置数据
const handleSubmit = async () => {
    try {
        await setUserSetup(formData)
        feedback.msgSuccess('操作成功')
        getData()
    } catch (error) {
        console.log('保存=>', error)
    }
}

getData()
</script>

<style lang="scss" scoped></style>
