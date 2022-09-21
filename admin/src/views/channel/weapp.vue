<template>
    <div>
        <el-card class="!border-none" shadow="never">
            <el-alert
                type="warning"
                title="温馨提示：填写微信小程序开发配置，请前往微信公众平台申请小程序并完成认证"
                :closable="false"
                show-icon
            />
        </el-card>
        <el-form ref="formRef" :model="formData" label-width="160px">
            <el-card class="!border-none mt-4" shadow="never">
                <div class="font-medium mb-7">微信小程序</div>
                <el-form-item label="小程序名称" prop="name">
                    <div class="w-80">
                        <el-input v-model="formData.name" placeholder="请输入小程序名称" />
                    </div>
                </el-form-item>
                <el-form-item label="原始ID" prop="primaryId">
                    <div class="w-80">
                        <el-input v-model="formData.primaryId" placeholder="请输入原始ID" />
                    </div>
                </el-form-item>
                <el-form-item label="小程序码" prop="qrCode">
                    <div>
                        <div>
                            <material-picker v-model="formData.qrCode" :limit="1" />
                        </div>
                        <div class="form-tips">建议尺寸：宽400px*高400px。jpg，jpeg，png格式</div>
                    </div>
                </el-form-item>
            </el-card>
            <el-card class="!border-none mt-4" shadow="never">
                <div class="font-medium mb-7">开发者ID</div>
                <el-form-item label="AppID" prop="appId">
                    <div class="w-80">
                        <el-input v-model="formData.appId" placeholder="请输入AppID" />
                    </div>
                </el-form-item>
                <el-form-item label="AppSecret" prop="appSecret">
                    <div>
                        <div class="w-80">
                            <el-input v-model="formData.appSecret" placeholder="请输入AppSecret" />
                        </div>
                    </div>
                </el-form-item>
                <el-form-item>
                    <div class="form-tips">
                        小程序账号登录微信公众平台，点击开发>开发设置->开发者ID，设置AppID和AppSecret
                    </div>
                </el-form-item>
            </el-card>
            <el-card class="!border-none mt-4" shadow="never">
                <div class="font-medium mb-7">服务器域名</div>
                <el-form-item label="request合法域名" prop="appId">
                    <div>
                        <div class="flex">
                            <div class="mr-4 w-80">
                                <el-input v-model="formData.requestDomain" disabled />
                            </div>
                            <el-button v-copy="formData.requestDomain">复制</el-button>
                        </div>
                        <div class="form-tips">
                            小程序账号登录微信公众平台，点击开发>开发设置->服务器域名，填写https协议域名
                        </div>
                    </div>
                </el-form-item>
                <el-form-item label="socket合法域名">
                    <div>
                        <div class="flex">
                            <div class="mr-4 w-80">
                                <el-input v-model="formData.socketDomain" disabled />
                            </div>
                            <el-button v-copy="formData.socketDomain">复制</el-button>
                        </div>
                        <div class="form-tips">
                            小程序账号登录微信公众平台，点击开发>开发设置->服务器域名，填写wss协议域名
                        </div>
                    </div>
                </el-form-item>
                <el-form-item label="uploadFile合法域名">
                    <div>
                        <div class="flex">
                            <div class="mr-4 w-80">
                                <el-input v-model="formData.uploadFileDomain" disabled />
                            </div>
                            <el-button v-copy="formData.uploadFileDomain">复制</el-button>
                        </div>
                        <div class="form-tips">
                            小程序账号登录微信公众平台，点击开发>开发设置->服务器域名，填写https协议域名
                        </div>
                    </div>
                </el-form-item>
                <el-form-item label="downloadFile合法域名">
                    <div>
                        <div class="flex">
                            <div class="mr-4 w-80">
                                <el-input v-model="formData.downloadFileDomain" disabled />
                            </div>
                            <el-button v-copy="formData.downloadFileDomain">复制</el-button>
                        </div>
                        <div class="form-tips">
                            小程序账号登录微信公众平台，点击开发>开发设置->服务器域名，填写https协议域名
                        </div>
                    </div>
                </el-form-item>
                <el-form-item label="udp合法域名">
                    <div>
                        <div class="flex">
                            <div class="mr-4 w-80">
                                <el-input v-model="formData.udpDomain" disabled />
                            </div>
                            <el-button v-copy="formData.udpDomain">复制</el-button>
                        </div>
                        <div class="form-tips">
                            小程序账号登录微信公众平台，点击开发>开发设置->服务器域名，填写udp协议域名
                        </div>
                    </div>
                </el-form-item>
            </el-card>
            <el-card class="!border-none mt-4" shadow="never">
                <div class="font-medium mb-7">业务域名</div>
                <el-form-item label="业务域名">
                    <div>
                        <div class="flex">
                            <div class="mr-4 w-80">
                                <el-input v-model="formData.businessDomain" disabled />
                            </div>
                            <el-button v-copy="formData.businessDomain">复制</el-button>
                        </div>
                        <div class="form-tips">
                            小程序账号登录微信公众平台，点击开发>开发设置->业务域名，填写业务域名
                        </div>
                    </div>
                </el-form-item>
            </el-card>
        </el-form>
        <footer-btns v-perms="['channel:mp:save']">
            <el-button type="primary" @click="handelSave">保存</el-button>
        </footer-btns>
    </div>
</template>
<script lang="ts" setup name="weappConfig">
import { getWeappConfig, setWeappConfig } from '@/api/channel/weapp'
import feedback from '@/utils/feedback'

const formData = reactive({
    name: '',
    primaryId: '',
    qrCode: '',
    appId: '',
    appSecret: '',
    businessDomain: '',
    downloadFileDomain: '',
    requestDomain: '',
    socketDomain: '',
    tcpDomain: '',
    udpDomain: '',
    uploadFileDomain: ''
})

const getDetail = async () => {
    const data = await getWeappConfig()
    for (const key in formData) {
        //@ts-ignore
        formData[key] = data[key]
    }
}

const handelSave = async () => {
    await setWeappConfig(formData)
    getDetail()
    feedback.msgSuccess('操作成功')
}

getDetail()
</script>
