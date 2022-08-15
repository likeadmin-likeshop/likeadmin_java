<el-form-item label="${字段描述}" prop="${字段名}">
    <el-select
        v-model="queryParams.${字段名}"
        class="w-56"
        clearable
    >
        //选择字典数据时渲染
        <el-option label="全部" value="" />
        <el-option 
            v-for="(item, index) in dictData.${字典类型}"
            :key="index" 
            :label="item.name"
            :value="item.value"
        />
        //没有选字典数据时渲染
        <el-option label="请选择字典生成" value="" />
    </el-select>
</el-form-item>
