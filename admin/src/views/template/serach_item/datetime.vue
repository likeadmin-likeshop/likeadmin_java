<el-form-item label="${字段描述}" prop="${字段名}">
    <daterange-picker 
        v-model:startTime="queryParams.startTime"
        v-model:endTime="queryParams.endTime"
    />
</el-form-item>
