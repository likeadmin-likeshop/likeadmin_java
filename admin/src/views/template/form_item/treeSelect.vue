<el-form-item label="${字段描述}" prop="${字段名}">
    <el-tree-select
        class="flex-1"
        v-model="formData.${字段名}"
        :data="treeList"
        clearable
        node-key="${树主键}"
        :props="{
            label: ${显示的字段名如name}
        }"
        :default-expand-all="true"
        placeholder="请选择${字段描述}"
        check-strictly
    />
</el-form-item>
