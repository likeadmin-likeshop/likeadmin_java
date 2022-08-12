<el-form-item label="${字段描述}" prop="${字段名}">
    <el-checkbox-group v-model="formData.${字段名}" placeholder="请选择${字段描述}">
        <el-checkbox 
            v-for="(item, index) in dictData.${字典类型}"
            :key="index" 
            :label="item.value"
            :disabled="!item.status"
        >
            {{ item.name }}
        </el-checkbox>
    </el-checkbox-group>
</el-form-item>
