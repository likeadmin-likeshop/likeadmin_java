<template>
    <div>
        <el-card class="!border-none" shadow="never">
            <el-page-header content="用户详情" @back="$router.back()" />
        </el-card>
        <el-card class="mt-4 !border-none" header="基本资料" shadow="never">
            <el-form ref="formRef" class="ls-form" :model="formData" label-width="120px">
                <div class="bg-page flex py-5 mb-10 items-center">
                    <div class="basis-40 flex flex-col justify-center items-center">
                        <div class="mb-2 text-tx-regular">用户头像</div>
                        <el-avatar :src="formData.avatar" :size="58" />
                    </div>
                    <div class="basis-40 flex flex-col justify-center items-center">
                        <div class="text-tx-regular">账户余额</div>
                        <div class="mt-2 flex items-center">
                            ¥{{ formData.user_money }}
                            <el-button
                                v-perms="['user.user/adjustMoney']"
                                type="primary"
                                link
                                @click="handleAdjust(formData.user_money)"
                            >
                                调整
                            </el-button>
                        </div>
                    </div>
                </div>
                <el-form-item label="用户编号："> {{ formData.sn }} </el-form-item>
                <el-form-item label="用户昵称：">
                    {{ formData.nickname }}
                </el-form-item>
                <el-form-item label="账号：">
                    {{ formData.username }}
                    <popover-input
                        class="ml-[10px]"
                        :limit="32"
                        @confirm="handleEdit($event, 'username')"
                    >
                        <el-button type="primary" link v-perms="['user:edit']">
                            <icon name="el-icon-EditPen" />
                        </el-button>
                    </popover-input>
                </el-form-item>
                <el-form-item label="真实姓名：">
                    {{ formData.realName || '-' }}
                    <popover-input
                        class="ml-[10px]"
                        :limit="32"
                        @confirm="handleEdit($event, 'realName')"
                    >
                        <el-button type="primary" link v-perms="['user:edit']">
                            <icon name="el-icon-EditPen" />
                        </el-button>
                    </popover-input>
                </el-form-item>
                <el-form-item label="性别：">
                    {{ formData.sex }}
                    <popover-input
                        class="ml-[10px]"
                        type="select"
                        :options="[
                            {
                                label: '未知',
                                value: 0
                            },
                            {
                                label: '男',
                                value: 1
                            },
                            {
                                label: '女',
                                value: 2
                            }
                        ]"
                        @confirm="handleEdit($event, 'sex')"
                    >
                        <el-button type="primary" link v-perms="['user:edit']">
                            <icon name="el-icon-EditPen" />
                        </el-button>
                    </popover-input>
                </el-form-item>
                <el-form-item label="联系电话：">
                    {{ formData.mobile || '-' }}
                    <popover-input
                        class="ml-[10px]"
                        type="number"
                        @confirm="handleEdit($event, 'mobile')"
                    >
                        <el-button type="primary" link v-perms="['user:edit']">
                            <icon name="el-icon-EditPen" />
                        </el-button>
                    </popover-input>
                </el-form-item>
                <el-form-item label="注册来源："> {{ formData.channel }} </el-form-item>
                <el-form-item label="注册时间："> {{ formData.createTime }} </el-form-item>
                <el-form-item label="最近登录时间："> {{ formData.lastLoginTime }} </el-form-item>
            </el-form>
        </el-card>
        <account-adjust
            v-model:show="adjustState.show"
            :value="adjustState.value"
            @confirm="handleConfirmAdjust"
        />
    </div>
</template>

<script lang="ts" setup name="consumerDetail">
import type { FormInstance } from 'element-plus'
import { getUserDetail, userEdit } from '@/api/consumer'
import feedback from '@/utils/feedback'
import { isEmpty } from '@/utils/util'
import AccountAdjust from '../components/account-adjust.vue'
const route = useRoute()
const formData = reactive({
    avatar: '',
    channel: '',
    createTime: '',
    lastLoginIp: '',
    lastLoginTime: '',
    mobile: '',
    nickname: '',
    realName: 0,
    sex: 0,
    sn: '',
    username: ''
})

const formRef = shallowRef<FormInstance>()
const adjustState = reactive({
    show: false,
    value: ''
})
const getDetails = async () => {
    const data = await getUserDetail({
        id: route.query.id
    })
    Object.keys(formData).forEach((key) => {
        //@ts-ignore
        formData[key] = data[key]
    })
}

const handleEdit = async (value: string, field: string) => {
    if (isEmpty(value)) return
    await userEdit({
        id: route.query.id,
        field,
        value
    })
    feedback.msgSuccess('编辑成功')
    getDetails()
}
const handleAdjust = (value: string) => {
    adjustState.show = true
    adjustState.value = value
}
const handleConfirmAdjust = async (value: any) => {
    await adjustMoney({ user_id: route.query.id, ...value })
    adjustState.show = false
    getDetails()
}

getDetails()
</script>
