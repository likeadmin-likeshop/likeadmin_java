<!-- 备案信息 -->
<template>
    <div class="website-filing">
        <el-card shadow="never" class="!border-none">
            <div class="mb-5">站点底部版权备案信息设置</div>
            <el-form ref="form" class="ls-form" label-width="100px">
                <del-wrap
                    v-for="(item, index) in formData"
                    class="mb-4"
                    :key="index"
                    :show-close="formData.length > 1"
                    @close="handleDelete(index)"
                >
                    <div class="bg-fill-lighter py-4">
                        <el-form-item label="显示内容" prop="name">
                            <div class="w-80">
                                <div>
                                    <el-input
                                        v-model="item.name"
                                        placeholder="请输入内容 ，例如： ICP备案号"
                                    />
                                </div>
                            </div>
                        </el-form-item>
                        <el-form-item label="跳转链接" prop="link">
                            <div class="w-80">
                                <div>
                                    <el-input
                                        v-model="item.link"
                                        placeholder="请输入链接，例如：http://www.beian.gov.cn"
                                    />
                                </div>
                                <div class="form-tips">跳转链接不设置，则不跳转</div>
                            </div>
                        </el-form-item>
                    </div>
                </del-wrap>
                <div>
                    <el-button type="primary" @click="handleAdd">
                        <icon name="el-icon-Plus" />
                        添加
                    </el-button>
                </div>
            </el-form>
        </el-card>

        <footer-btns v-perms="['setting:copyright:save']">
            <el-button type="primary" @click="handleSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup name="webFilling">
import { getCopyright, setCopyright } from '@/api/setting/website'
import feedback from '@/utils/feedback'
// 表单数据
const formData = ref([
    {
        name: '',
        link: ''
    }
])

// 获取备案信息
const getData = async () => {
    const data = await getCopyright()
    if (!data.length) return
    formData.value = data
}

const handleAdd = () => {
    formData.value.push({
        name: '',
        link: ''
    })
}

const handleDelete = (index: number) => {
    if (formData.value.length <= 1) {
        return feedback.msgError('至少保留一个')
    }
    formData.value.splice(index, 1)
}

// 设置备案信息
const handleSubmit = async () => {
    await setCopyright({
        list: formData.value
    })
    feedback.msgSuccess('操作成功')
    getData()
}

getData()
</script>
