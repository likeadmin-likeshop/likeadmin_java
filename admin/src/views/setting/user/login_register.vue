<!-- 网站信息 -->
<template>
    <div class="login-register">
        <el-form ref="formRef" :rules="rules" :model="formData" label-width="120px">
            <el-card shadow="never" class="!border-none">
                <div class="font-medium mb-7">通用设置</div>

                <el-form-item label="登录方式" prop="methods">
                    <div>
                        <el-checkbox v-model="formData.methods" true-label="1" false-label="2" label="登录" />
                        <el-checkbox v-model="formData.methods" true-label="1" false-label="2" label="注册" />

                        <div class="form-tips">系统通用登录方式，至少选择一项</div>
                    </div>
                </el-form-item>

                <el-form-item label="强制绑定手机" prop="favicon" required>
                    <div>
                        <el-switch v-model="formData.isBindMobile" />
                        <span class="mt-1 ml-2">{{
                                formData.isBindMobile ? "开启" : "关闭"
                        }}</span>

                        <div class="form-tips">
                            1、如果开启，则新用户在注册完成之后要强制绑定手机号<br />
                            2、老用户登录时如果检测到没有绑定手机，则要重新绑定手机号
                        </div>
                    </div>
                </el-form-item>

                <el-form-item label="政策协议" prop="favicon" required>
                    <div>
                        <el-switch v-model="formData.isBindMobile" />
                        <span class="mt-1 ml-2">{{
                                formData.isBindMobile ? "开启" : "关闭"
                        }}</span>

                        <div class="form-tips">
                            登录/注册会员时，是否显示服务协议和隐私政策
                        </div>
                    </div>
                </el-form-item>
            </el-card>

            <el-card shadow="never" class="!border-none mt-4">
                <div class="font-medium mb-7">第三方设置</div>

                <el-form-item label="第三方登录" prop="methods">
                    <div>
                        <el-switch v-model="formData.isBindMobile" />
                        <span class="mt-1 ml-2">{{
                                formData.isBindMobile ? "开启" : "关闭"
                        }}</span>

                        <div class="form-tips">登录时支持第三方登录，新用户授权即自动注册账号</div>

                        <div>
                            <el-checkbox v-model="formData.methods" true-label="1" false-label="2" label="登录" />
                            <el-checkbox v-model="formData.methods" true-label="1" false-label="2" label="注册" />
                        </div>
                    </div>
                </el-form-item>

                <el-form-item label="微信开放平台" prop="favicon" required>
                    <div>
                        <a href="https://baidu.com" target="_blank">
                            <el-button type="primary" link class="underline">前往微信开放平台</el-button>
                        </a>

                        <div class="form-tips">
                            1、在各渠道使用微信授权登录时，强烈建议配置微信开放平台<br />
                            2、微信开放平台关联公众号、小程序和APP后，可实现各端用户账号统一，识别买家唯一微信身份<br />
                            3、没有配置微信开放平台，同一微信号会生成多个用户，配置微信开放平台后已生成的用户账号无法合并
                        </div>
                    </div>
                </el-form-item>
            </el-card>
        </el-form>

        <footer-btns v-perms="['setting:website:save']">
            <el-button type="primary" @click="handleSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup>
import { getWebsite, setWebsite } from "@/api/setting/website";
import feedback from "@/utils/feedback";
import type { FormInstance } from "element-plus";
const formRef = ref<FormInstance>();

// 表单数据
const formData = reactive({
    methods: 1, // 登录方式
    isBindMobile: 1, // 是否强制绑定手机号码
    favicon: "", // 网站图标
    logo: "", // 网站logo
    backdrop: "", // 登录页广告图
});

// 表单验证
const rules = {
    name: [
        {
            required: true,
            message: "请输入网站名称",
            trigger: ["blur"],
        },
    ],
};

// 获取备案信息
const getData = async () => {
    const data = await getWebsite();
    for (const key in formData) {
        //@ts-ignore
        formData[key] = data[key];
    }
};

// 设置备案信息
const handleSubmit = async () => {
    await formRef.value?.validate();
    await setWebsite(formData);
    feedback.msgSuccess("操作成功");
    getData();
};

getData();
</script>

<style lang="scss" scoped>
</style>
