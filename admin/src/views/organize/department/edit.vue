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
                        :options="leaderList"
                        :props="{
                            value: 'id',
                            label: 'name',
                            checkStrictly: true
                        }"
                        clearable
                    />
                </el-form-item>
                <!-- 部门名称 -->
                <el-form-item label="部门名称" prop="name">
                    <el-input placeholder="请输入部门名称"></el-input>
                </el-form-item>

                <!-- 负责人 -->
                <el-form-item label="负责人" prop="leader">
                    <el-input placeholder="请输入负责人姓名"></el-input>
                </el-form-item>

                <!-- 联系电话 -->
                <el-form-item label="联系电话" prop="mobile">
                    <el-input placeholder="请输入联系电话" type="tel"></el-input>
                </el-form-item>

                <!-- 岗位排序 -->
                <el-form-item label="排序" prop="sort">
                    <el-input placeholder="请输入排序" type="number"></el-input>
                    <div class="muted">默认为0， 数值越大越排前</div>
                </el-form-item>

                <!-- 管理员状态 -->
                <el-form-item label="部门状态">
                    <el-switch :active-value="1" :inactive-value="0" />
                </el-form-item>
            </el-form>
        </el-card>

        <footer-btns>
            <el-button type="primary" size="small" @click="onSubmit">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup>
import FooterBtns from '@/components/footer-btns/index.vue'
</script>

<style lang="scss" scoped></style>
