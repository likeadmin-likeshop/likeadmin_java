<el-table-column label="${字段描述}" prop="${字段名}">
    <template #default="{ row }">
        <dict-value :options="dictData.${字典类型}" :value="row.${字段名}" />
    </template>
</el-table-column>
