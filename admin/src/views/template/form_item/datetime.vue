<el-form-item label="${字段描述}" prop="${字段名}">
    <el-date-picker
        v-model="form.${字段名}"
        type="date"
        clearable
        value-format="YYYY-MM-DD"
        placeholder="请选择${字段描述}"
    />
</el-form-item>
