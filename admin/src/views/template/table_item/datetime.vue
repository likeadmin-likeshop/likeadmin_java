<el-table-column label="${字段描述}" prop="${字段名}">
    <template #default="{ row }">
       <span>{{ timeFormat(row.${字段名}, 'yyyy-mm-dd hh:MM:ss') }}</span>
    </template>
</el-table-column>
