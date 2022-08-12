<el-form-item label="${字段描述}" prop="${字段名}">
    <el-select
        v-model="formData.${字段名}"
        class="flex-1"
        placeholder="请选择${字段描述}"
        clearable
    >
        <el-option 
            v-for="(item, index) in dictData.${字典类型}"
            :key="index" 
            :label="item.name"
            :value="item.value"
            :disabled="!item.status"
        />
    </el-select>
</el-form-item>
