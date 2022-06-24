<template>
    <div class="storage-setting">
        <!-- 导航头部 -->
        <el-card shadow="never">
            <el-page-header @back="$router.go(-1)" content="存储设置" />
        </el-card>

        <!-- 提示 -->
        <el-card shadow="never" class="m-t-15" v-if="alias !== storage.LOCAL">
            <el-alert
                v-if="alias == storage.QINIU"
                title="温馨提示：切换七牛云存储后，素材库需要重新上传至七牛云。"
                type="primary"
                :closable="false"
                show-icon
            />
            <el-alert
                v-if="alias == storage.ALIYUN"
                title="温馨提示：切换阿里云OSS后，素材库需要重新上传至阿里云OSS。"
                type="primary"
                :closable="false"
                show-icon
            />
            <el-alert
                v-if="alias == storage.QCLOUD"
                title="温馨提示：切换腾讯云OSS后，素材库需要重新上传至腾讯云OSS。"
                type="primary"
                :closable="false"
                show-icon
            />
        </el-card>

        <!-- 表单 -->
        <el-card shadow="never" class="m-t-15">
            <el-form
                ref="formRef"
                :model="form"
                :rules="formRules"
                label-width="240px"
                size="small"
                class="ls-form"
            >
                <!-- 存储设置 -->
                <div class="card-content m-t-24">
                    <el-form-item label="存储方式">
                        <div v-if="alias === storage.LOCAL">本地存储</div>
                        <div v-if="alias === storage.QINIU">七牛云存储</div>
                        <div v-if="alias === storage.ALIYUN">阿里云OSS</div>
                        <div v-if="alias === storage.QCLOUD">腾讯云OSS</div>
                        <div v-if="alias === storage.LOCAL" class="muted xs m-r-16">
                            本地存储方式不需要配置其他参数
                        </div>
                    </el-form-item>
                </div>
                <div v-if="alias !== 'local'">
                    <el-form-item label=" 存储空间名称(Bucket)" prop="bucket">
                        <el-input v-model="form.bucket" placeholder="请输入存储空间名称(Bucket)"></el-input>
                    </el-form-item>
                    <el-form-item label="ACCESS_KEY（AK）" prop="accessKey">
                        <el-input v-model="form.accessKey" placeholder="请输入ACCESS_KEY"></el-input>
                    </el-form-item>
                    <el-form-item label="SECRET_KEY（SK）" prop="secretKey">
                        <el-input v-model="form.secretKey" placeholder="请输入SECRET_KEY"></el-input>
                    </el-form-item>
                    <el-form-item label="空间域名（Domain）" prop="domain">
                        <!-- <el-select class="ls-select" v-model="form" placeholder="https://">
							<el-option label="https://" value="https://"></el-option>
							<el-option label="http://" value="http://"></el-option>
						</el-select> -->
                        <el-input v-model="form.domain" placeholder="请输入空间域名"></el-input>
                        <div class="muted xs m-r-16">
                            请补全http://或https://，例如https://static.cloud.com
                        </div>
                    </el-form-item>
                    <el-form-item v-if="alias == storage.QCLOUD" label="REGION" prop="region">
                        <el-input v-model="form.region" placeholder="请输入region"></el-input>
                    </el-form-item>
                </div>
                <el-form-item label="状态" prop="status">
                    <el-radio-group class="m-r-16" v-model="form.status">
                        <el-radio :label="0">停用</el-radio>
                        <el-radio :label="1">启用</el-radio>
                    </el-radio-group>
                </el-form-item>
            </el-form>
        </el-card>

        <!--  保存  -->
        <footer-btns>
            <el-button type="primary" size="small" @click="onSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script setup lang="ts">
    import { ref, onMounted } from 'vue'
    import { ElMessage, ElForm } from 'element-plus'
    import { storage } from '@/utils/type'
    import { useAdmin } from '@/core/hooks/app'
    import FooterBtns from '@/components/footer-btns/index.vue'
    import { apiStorageDetail, apiStorageEdit } from '@/api/setting'

    const { route, router } = useAdmin()

    // 存储方式
    let alias = ref<any>('') // 存储引擎名称：local-本地，qiniu-七牛云,aliyun-阿里云OSS,qcloud-腾讯云OS:

    // 设置表单
    let form = ref<object>({
        bucket: '',
        accessKey: '',
        secretKey: '',
        domain: '',
        region: '', // 腾讯云需要
        status: 0,
    })

    let formRules = ref({
        bucket: [
            {
                required: true,
                message: '请输入存储空间名称',
                trigger: 'blur',
            },
        ],
        accessKey: [
            {
                required: true,
                message: '请输入ACCESS_KEY',
                trigger: 'blur',
            },
        ],
        secretKey: [
            {
                required: true,
                message: '请输入SECRET_KEY',
                trigger: 'blur',
            },
        ],
        domain: [
            {
                required: true,
                message: '请输入空间域名',
                trigger: 'blur',
            },
        ],
        region: [
            {
                required: true,
                message: '请输入REGION',
                trigger: 'blur',
            },
        ],
    })

    const formRef: Ref<typeof ElForm | null> = ref(null)

    const onSubmit = () => {
        // 验证表单格式是否正确
        formRef.value?.validate((valid: boolean) => {
            if (!valid) {
                return
            }
            // 请求发送
            apiStorageEdit({
                ...form.value,
                alias: alias.value,
            })
                .then((res: any) => {
                    setTimeout(() => router.back(), 500)
                })
                .catch((err: any) => {
                    console.log('err', err)
                })
        })
    }

    // 获取详情
    const getStorageDetail = async () => {
        form.value = await apiStorageDetail({
            alias: alias.value,
        })
    }

    onMounted(async () => {
        if (route.query.alias) {
            console.log('route.query.alias', route.query.alias)
            alias.value = route.query.alias
            console.log('alias.value', alias.value)
        }

        await getStorageDetail()
    })
</script>

<style lang="scss" scoped></style>
