<el-table-column label="${字段描述}" prop="${字段名}" min-width="60">
    <template #default="{ row }">
        <image-contain width="50px" height="50px" :src="row.${字段名}" />
    </template>
</el-table-column>
