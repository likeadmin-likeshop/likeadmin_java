<template>
    <div>
        <el-form label-width="70px">
            <el-form-item label="是否启用">
                <el-radio-group v-model="content.enabled">
                    <el-radio :label="1">开启</el-radio>
                    <el-radio :label="0">停用</el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item label="菜单图片">
                <div class="flex-1">
                    <div class="form-tips">最多可添加10个，建议图片尺寸：100px*100px</div>
                    <del-wrap
                        v-for="(item, index) in content.data"
                        :key="index"
                        @close="handleDelete(index)"
                    >
                        <div class="bg-fill-light flex items-center w-full p-4 mt-4">
                            <material-picker v-model="item.image" upload-class="bg-body">
                                <template #upload>
                                    <div class="upload-btn w-[60px] h-[60px]">
                                        <icon name="el-icon-Plus" :size="20" />
                                    </div>
                                </template>
                            </material-picker>
                            <div>
                                <el-form-item label="图片名称">
                                    <el-input v-model="item.name" placeholder="请输入名称" />
                                </el-form-item>
                                <el-form-item class="mt-[18px]" label="图片链接">
                                    <link-picker v-model="item.link" />
                                </el-form-item>
                            </div>
                        </div>
                    </del-wrap>
                </div>
            </el-form-item>
            <el-form-item v-if="content.data?.length < limit">
                <el-button type="primary" @click="handleAdd">添加菜单</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script lang="ts" setup>
import feedback from '@/utils/feedback'
import type { PropType } from 'vue'
import type options from './options'
type OptionsType = ReturnType<typeof options>
const limit = 10
const props = defineProps({
    content: {
        type: Object as PropType<OptionsType['content']>,
        default: () => ({})
    },
    styles: {
        type: Object as PropType<OptionsType['styles']>,
        default: () => ({})
    }
})

const handleAdd = () => {
    if (props.content.data?.length < limit) {
        props.content.data.push({
            image: '',
            name: '导航',
            link: {}
        })
    } else {
        feedback.msgError(`最多添加${limit}个`)
    }
}
const handleDelete = (index: number) => {
    props.content.data.splice(index, 1)
}
</script>

<style lang="scss" scoped></style>
