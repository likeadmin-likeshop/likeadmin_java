<el-form-item label="${字段描述}" prop="${字段名}">
    <el-input
        v-model="formData.${字段名}"
        class="flex-1"
        type="textarea"
        :rows="4"
        placeholder="请输入${字段描述}"
        clearable
    />
</el-form-item>
