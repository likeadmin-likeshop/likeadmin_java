<el-form-item label="${字段描述}" prop="${字段名}">
    <el-select
        v-model="formData.${字段名}"
        class="w-56"
        clearable
    >
        <el-option label="全部" value="" />
        <el-option 
            v-for="(item, index) in dictData.${字典类型}"
            :key="index" 
            :label="item.name"
            :value="item.value"
        />
    </el-select>
</el-form-item>
