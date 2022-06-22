<template>
    <div class="admin">
        <el-card shadow="never">
            <el-page-header :content="id ? '编辑管理员' : '新增管理员'" @back="$router.back()" />
        </el-card>
        <el-card v-loading="loading" shadow="never" class="m-t-15">
            <el-form
                ref="formRefs"
                :rules="rules"
                class="ls-form"
                :model="formData"
                label-width="150px"
                size="small"
            >
                <!-- 账号输入框 -->
                <el-form-item label="账号：" prop="username">
                    <el-input v-model="formData.username" placeholder="请输入账号"></el-input>
                </el-form-item>
                <!-- 管理员头像 -->
                <el-form-item label="头像：">
                    <material-select v-model="formData.avatar" :limit="1"></material-select>
                    <div class="muted">建议尺寸：100*100px，支持jpg，jpeg，png格式</div>
                </el-form-item>

                <!-- 名称输入框 -->
                <el-form-item label="名称：" prop="nickname">
                    <el-input v-model="formData.nickname" placeholder="请输入名称"></el-input>
                </el-form-item>

                <!-- 归属部门选择框 -->
                <el-form-item label="归属部门" prop="deptId">
                    <!-- <el-select v-model="formData.deptId" placeholder="请选择归属部门">
						<el-option v-for="(item, index) in deptList" :key="index" :label="item.name" :value="item.id">
						</el-option>
					</el-select> -->
                    <el-cascader
                        v-model="formData.deptId"
                        :options="deptList"
                        :props="{
                            value: 'id',
                            label: 'name',
                            checkStrictly: true,
                        }"
                        clearable
                    />

                    <!-- 新增 -->
                    <router-link to="/organize/department" target="_blank">
                        <el-button type="text" style="margin: 0 10px">新增部门</el-button>
                    </router-link>
                    <el-button type="text">|</el-button>
                    <el-button type="text" @click="getDeptList">刷新</el-button>
                </el-form-item>

                <!-- 岗位选择框 -->
                <el-form-item label="岗位" prop="postId">
                    <el-select v-model="formData.postId" placeholder="请选择岗位">
                        <el-option
                            v-for="(item, index) in postList"
                            :key="index"
                            :label="item.name"
                            :value="item.id"
                        >
                        </el-option>
                    </el-select>

                    <!-- 新增 -->
                    <router-link to="/organize/post" target="_blank">
                        <el-button type="text" style="margin: 0 10px">新增岗位</el-button>
                    </router-link>
                    <el-button type="text">|</el-button>
                    <el-button type="text" @click="getPostList">刷新</el-button>
                </el-form-item>

                <!-- 角色选择框 -->
                <el-form-item label="角色：" prop="role">
                    <el-select
                        v-model="formData.role"
                        placeholder="请选择角色"
                        :disabled="id && formData.root"
                    >
                        <el-option
                            v-for="(item, index) in roleList"
                            :key="index"
                            :label="item.name"
                            :value="item.id + ''"
                        ></el-option>
                    </el-select>

                    <!-- 新增 -->
                    <router-link to="/permission/role" target="_blank">
                        <el-button type="text" style="margin: 0 10px">新增角色</el-button>
                    </router-link>
                    <el-button type="text">|</el-button>
                    <el-button type="text" @click="getRoleList">刷新</el-button>
                </el-form-item>

                <!-- 密码输入框 -->
                <el-form-item label="密码：" prop="password">
                    <el-input
                        v-model="formData.password"
                        show-password
                        type="password"
                        placeholder="请输入密码"
                    ></el-input>
                </el-form-item>

                <!-- 确认密码输入框 -->
                <el-form-item label="确认密码：" prop="password_confirm">
                    <el-input
                        v-model="formData.password_confirm"
                        show-password
                        type="password"
                        placeholder="请输入确认密码"
                    ></el-input>
                </el-form-item>

                <!-- 管理员状态 -->
                <el-form-item label="管理员状态">
                    <el-switch v-model="formData.isDisable" :active-value="0" :inactive-value="1" />
                </el-form-item>

                <!-- 多处登录 -->
                <el-form-item label="支持多处登录">
                    <el-switch v-model="formData.isMultipoint" :active-value="1" :inactive-value="0" />
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
    import MaterialSelect from '@/components/material-select/index.vue'
    import FooterBtns from '@/components/footer-btns/index.vue'
    import { apiRoleLists, apiAdminDetail, apiAdminAdd, apiAdminEdit } from '@/api/auth'
    import { apiDeptLists, apiPostLists } from '@/api/organize'
    import { ElForm } from 'element-plus'
    import { useAdmin } from '@/core/hooks/app'
    import { ElMessage } from 'element-plus'

    const formRefs: Ref<typeof ElForm | null> = ref(null)
    const { router, route } = useAdmin()
    const id = computed(() => route.query?.id)
    const loading = ref(false)
    // 表单数据
    const roleList: Ref<any[]> = ref([]) // 角色
    const deptList: Ref<any[]> = ref([]) // 部门
    const postList: Ref<any[]> = ref([]) // 岗位

    const { formData, rules } = toRefs(
        reactive({
            formData: {
                username: '',
                nickname: '',
                role: '',
                avatar: '',
                password: '',
                password_confirm: '',
                isDisable: 0,
                isMultipoint: 0,
                deptId: '',
                postId: '',
            },
            rules: {
                username: [
                    {
                        required: true,
                        message: '请输入账号',
                        trigger: ['blur'],
                    },
                ],
                nickname: [
                    {
                        required: true,
                        message: '请输入名称',
                        trigger: ['blur'],
                    },
                ],
                role: [
                    {
                        required: true,
                        message: '请选择角色',
                        trigger: ['blur'],
                    },
                ],
                password: [
                    {
                        required: true,
                        message: '请输入密码',
                        trigger: 'blur',
                        pattern: /(^[^\s]*$)/, // 不能输入空格
                    },
                    {
                        validator: (rule: object, value: string, callback: any) => {
                            !value ? callback(new Error('请输入密码')) : callback()
                        },
                        trigger: 'blur',
                    },
                ] as any[],
                password_confirm: [
                    {
                        required: true,
                        message: '请再次输入密码',
                        trigger: 'blur',
                        pattern: /(^[^\s]*$)/, // 不能输入空格
                    },
                    {
                        validator: (rule: object, value: string, callback: any) => {
                            if (formData.value.password) {
                                if (!value) callback(new Error('请再次输入密码'))
                                if (value !== formData.value.password)
                                    callback(new Error('两次输入密码不一致!'))
                            }
                            callback()
                        },
                        trigger: 'blur',
                    },
                ] as any[],
            },
        })
    )

    const getRoleList = () => {
        apiRoleLists({
            page_type: 0,
        }).then((res: any) => {
            roleList.value = res.lists

            // if (formData.value.id == 1) {
            //     roleList.value.push({
            //         id: 0,
            //         name: '超级管理员',
            //     })
            // }
            if (id.value && formData.value.root == 1) {
                roleList.value.push({
                    id: 0,
                    name: '系统管理员',
                })
            }
        })
    }

    // 获取部门联级列表
    const getDeptList = () => {
        apiDeptLists({}).then((res: any) => {
            // console.log(res.lists, 'res.lists')

            deptList.value = isDisabled(res)
        })
    }

    // 判断是否禁用， 添加禁用字段disabled
    const isDisabled = (treeArr: any) => {
        let newTree = treeArr.map((item: any) => {
            const children = item.children || []
            if (children.length) isDisabled(children)

            if (item.status == 0) {
                item.disabled = true
            } else {
                item.disabled = false
            }
            return item
        })
        return newTree
    }

    // 获取岗位列表
    const getPostList = () => {
        apiPostLists({
            page_type: 0,
        }).then((res: any) => {
            postList.value = res.lists
        })
    }

    const getAdminDetail = () => {
        if (!id.value) {
            rules.value.password
            rules.value.password_confirm
            return
        }
        loading.value = true
        apiAdminDetail({
            id: id.value,
        })
            .then((res: any) => {
                formData.value = res
            })
            .finally(() => {
                loading.value = false
            })
    }

    const onSubmit = () => {
        formRefs.value?.validate((valid: boolean) => {
            if (!valid) {
                return
            }

            // 因为组件绑定的pid是number，会匹配对应的联级数据
            // 而选中后，pid是变为数组
            // 所以pid是数组时（即新建部门或者编辑部门改变上级部门）
            if (Array.isArray(formData.value.deptId)) {
                formData.value.deptId = formData.value.deptId[formData.value.deptId.length - 1]
            }

            const promise = id.value
                ? apiAdminEdit({ ...formData.value, id: id.value })
                : apiAdminAdd(formData.value)
            promise.then(() => {
                setTimeout(() => router.go(-1), 500)
                ElMessage({ type: 'success', message: '保存成功' })
            })
        })
    }

    onMounted(() => {
        getAdminDetail()
        getRoleList()
        getDeptList()
        getPostList()
    })
</script>

<style lang="scss" scoped></style>
