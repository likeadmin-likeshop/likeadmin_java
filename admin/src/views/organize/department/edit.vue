<template>
    <div class="department-edit">
        <el-card shadow="never">
            <el-page-header :content="id ? '编辑部门' : '新增部门'" @back="$router.back()" />
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
                <!-- 上级部门 -->
                <el-form-item label="上级部门" prop="pid">
                    <!-- <el-select v-model="formData.pid" placeholder="请选择上级部门" :disabled="formData.pid === 0 && id">
					<el-option v-for="(item, index) in leaderList" :key="index" :label="item.name" :value="item.id">
					</el-option>
				</el-select> -->
                    <el-cascader
                        v-model="formData.pid"
                        :options="leaderList"
                        :props="{
                            value: 'id',
                            label: 'name',
                            checkStrictly: true,
                        }"
                        clearable
                        :disabled="formData.pid === 0 && id"
                    />
                </el-form-item>
                <!-- 部门名称 -->
                <el-form-item label="部门名称" prop="name">
                    <el-input v-model="formData.name" placeholder="请输入部门名称"></el-input>
                </el-form-item>

                <!-- 负责人 -->
                <el-form-item label="负责人" prop="duty">
                    <el-input v-model="formData.duty" placeholder="请输入负责人姓名"></el-input>
                </el-form-item>

                <!-- 联系电话 -->
                <el-form-item label="联系电话" prop="mobile">
                    <el-input v-model="formData.mobile" placeholder="请输入联系电话" type="tel"></el-input>
                </el-form-item>

                <!-- 岗位排序 -->
                <el-form-item label="排序" prop="sort">
                    <el-input v-model="formData.sort" placeholder="请输入排序" type="number"></el-input>
                    <div class="muted">默认为0， 数值越大越排前</div>
                </el-form-item>

                <!-- 管理员状态 -->
                <el-form-item label="部门状态">
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
    import { ElForm } from 'element-plus'
    import { useAdmin } from '@/core/hooks/app'
    import FooterBtns from '@/components/footer-btns/index.vue'
    import { ElMessage } from 'element-plus'
    import { apiDeptAdd, apiDeptEdit, apiDeptDetail, apiDeptLists } from '@/api/organize'

    const { router, route } = useAdmin()
    const id = computed(() => route.query?.id)
    const formRefs: Ref<typeof ElForm | null> = ref(null)

    // 表单数据
    const leaderList: Ref<any[]> = ref([])
    const { formData, rules } = toRefs(
        reactive({
            formData: {
                pid: '',
                name: '',
                duty: '',
                mobile: '',
                sort: 0,
                isStop: 0,
            },
            rules: {
                pid: [
                    {
                        required: true,
                        message: '请选择上级部门',
                        trigger: ['change'],
                    },
                ],
                name: [
                    {
                        required: true,
                        message: '请输入部门名称',
                        trigger: ['blur'],
                    },
                ],
            },
        })
    )

    // 获取部门联级列表
    const getList = () => {
        apiDeptLists({}).then((res: any) => {
            console.log(res, 'res')

            leaderList.value = isDisabled(res)
            // leaderList.value = res
        })
    }

    // 判断是否禁用
    // 编辑时，过滤掉当前部门, 过滤禁用
    // 添加时，过滤禁用
    const isDisabled = (treeArr: any) => {
        let newTree = treeArr.map((item: any) => {
            const children = item.children || []
            if (children.length) isDisabled(children)

            if (item.id == id.value || item.isStop == 1) {
                item.disabled = true
            } else {
                item.disabled = false
            }
            return item
        })
        return newTree
    }

    // 获取详情
    const getDetail = () => {
        if (!id.value) {
            return
        }

        apiDeptDetail({
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
            // 因为组件绑定的pid是number，会匹配对应的联级数据
            // 而选中后，pid是变为数组
            // 所以pid是数组时（即新建部门或者编辑部门改变上级部门）
            if (Array.isArray(formData.value.pid)) {
                formData.value.pid = formData.value.pid[formData.value.pid.length - 1]
            }

            const promise = id.value
                ? apiDeptEdit({
                      ...formData.value,
                      id: id.value,
                  })
                : apiDeptAdd(formData.value)
            promise.then(() => {
                setTimeout(() => router.go(-1), 500)
                ElMessage({ type: 'success', message: '保存成功' })
            })
        })
    }

    onMounted(() => {
        getDetail()
        getList()
    })
</script>

<style lang="scss" scoped></style>
