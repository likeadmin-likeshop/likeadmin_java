<el-form-item label="${字段描述}" prop="${字段名}">
    <el-radio-group v-model="formData.${字段名}" placeholder="请选择${字段描述}">
        <el-radio 
            v-for="(item, index) in dictData.${字典类型}"
            :key="index" 
            :label="item.value"
            :disabled="!item.status"
        >
            {{ item.name }}
        </el-radio>
        //没有选字典数据时渲染
        <el-radio label="0">请选择字典生成</el-radio>
    </el-radio-group>
</el-form-item>
