<template>
    <div class="code-edit">
        <el-card class="!border-none" shadow="never">
            <el-page-header content="编辑数据表" @back="$router.back()" />
        </el-card>
        <el-card class="mt-4 !border-none" shadow="never">
            <el-form
                ref="formRef"
                class="ls-form"
                :model="formData"
                label-width="130px"
                :rules="rules"
            >
                <el-tabs v-model="activeName">
                    <el-tab-pane label="基础信息" name="base">
                        <el-form-item label="表名称" prop="base.tableName">
                            <div class="w-80">
                                <el-input
                                    v-model="formData.base.tableName"
                                    placeholder="请输入表名称"
                                    clearable
                                />
                            </div>
                        </el-form-item>
                        <el-form-item label="表描述" prop="base.tableComment">
                            <div class="w-80">
                                <el-input
                                    v-model="formData.base.tableComment"
                                    placeholder="请输入表描述"
                                    clearable
                                />
                            </div>
                        </el-form-item>
                        <el-form-item label="实体类名称" prop="base.entityName">
                            <div class="w-80">
                                <el-input
                                    v-model="formData.base.entityName"
                                    placeholder="请输入实体类名称"
                                    clearable
                                />
                            </div>
                        </el-form-item>

                        <el-form-item label="作者" prop="base.authorName">
                            <div class="w-80">
                                <el-input
                                    v-model="formData.base.authorName"
                                    placeholder="请输入作者"
                                    clearable
                                />
                            </div>
                        </el-form-item>
                        <el-form-item label="备注">
                            <div class="w-80">
                                <el-input
                                    v-model="formData.base.remarks"
                                    class="w-full"
                                    type="textarea"
                                    :autosize="{ minRows: 4, maxRows: 4 }"
                                    maxlength="200"
                                    show-word-limit
                                    clearable
                                />
                            </div>
                        </el-form-item>
                    </el-tab-pane>
                    <el-tab-pane label="字段管理" name="column">
                        <el-table :data="formData.column">
                            <el-table-column label="字段列名" prop="columnName" />
                            <el-table-column label="字段描述" prop="columnComment" min-width="120">
                                <template v-slot="{ row }">
                                    <el-input v-model="row.columnComment"></el-input>
                                </template>
                            </el-table-column>
                            <el-table-column label="物理类型" prop="columnType" min-width="100" />
                            <el-table-column label="Java类型" min-width="100">
                                <template v-slot="{ row }">
                                    <el-select v-model="row.javaType">
                                        <el-option label="Long" value="Long" />
                                        <el-option label="String" value="String" />
                                        <el-option label="Integer" value="Integer" />
                                        <el-option label="Double" value="Double" />
                                        <el-option label="BigDecimal" value="BigDecimal" />
                                        <el-option label="Date" value="Date" />
                                        <el-option label="Boolean" value="Boolean" />
                                    </el-select>
                                </template>
                            </el-table-column>
                            <el-table-column label="Java属性" min-width="100">
                                <template v-slot="{ row }">
                                    <el-input v-model="row.javaField" />
                                </template>
                            </el-table-column>
                            <el-table-column label="必填" width="80">
                                <template v-slot="{ row }">
                                    <el-checkbox
                                        v-model="row.isRequired"
                                        :true-label="1"
                                        :false-label="0"
                                    />
                                </template>
                            </el-table-column>
                            <el-table-column label="插入" width="80">
                                <template v-slot="{ row }">
                                    <el-checkbox
                                        v-model="row.isInsert"
                                        :true-label="1"
                                        :false-label="0"
                                    />
                                </template>
                            </el-table-column>
                            <el-table-column label="编辑" width="80">
                                <template v-slot="{ row }">
                                    <el-checkbox
                                        v-model="row.isEdit"
                                        :true-label="1"
                                        :false-label="0"
                                    />
                                </template>
                            </el-table-column>
                            <el-table-column label="列表" width="80">
                                <template v-slot="{ row }">
                                    <el-checkbox
                                        v-model="row.isList"
                                        :true-label="1"
                                        :false-label="0"
                                    />
                                </template>
                            </el-table-column>
                            <el-table-column label="查询" width="80">
                                <template v-slot="{ row }">
                                    <el-checkbox
                                        v-model="row.isQuery"
                                        :true-label="1"
                                        :false-label="0"
                                    />
                                </template>
                            </el-table-column>
                            <el-table-column label="查询方式">
                                <template v-slot="{ row }">
                                    <el-select v-model="row.queryType">
                                        <el-option label="=" value="EQ" />
                                        <el-option label="!=" value="NE" />
                                        <el-option label=">" value="GT" />
                                        <el-option label=">=" value="GTE" />
                                        <el-option label="<" value="LT" />
                                        <el-option label="<=" value="LTE" />
                                        <el-option label="LIKE" value="LIKE" />
                                        <el-option label="BETWEEN" value="BETWEEN" />
                                    </el-select>
                                </template>
                            </el-table-column>
                            <el-table-column label="显示类型" min-width="120">
                                <template v-slot="{ row }">
                                    <el-select v-model="row.htmlType">
                                        <el-option label="文本框" value="input" />
                                        <el-option label="数字框" value="number" />
                                        <el-option label="文本域" value="textarea" />
                                        <el-option label="下拉框" value="select" />
                                        <el-option label="单选框" value="radio" />
                                        <el-option label="复选框" value="checkbox" />
                                        <el-option label="日期控件" value="datetime" />
                                        <el-option label="图片选择控件" value="imageUpload" />
                                        <el-option label="富文本控件" value="editor" />
                                    </el-select>
                                </template>
                            </el-table-column>
                            <el-table-column label="字典类型" min-width="120">
                                <template v-slot="{ row }">
                                    <el-select
                                        v-model="row.dictType"
                                        clearable
                                        :disabled="
                                            !(
                                                row.htmlType == 'select' ||
                                                row.htmlType == 'radio' ||
                                                row.htmlType == 'checkbox'
                                            )
                                        "
                                        placeholder="字典类型"
                                    >
                                        <el-option
                                            v-for="(item, index) in optionsData.dictType"
                                            :key="index"
                                            :label="item.dictName"
                                            :value="item.dictType"
                                            :disabled="!item.dictStatus"
                                        />
                                    </el-select>
                                </template>
                            </el-table-column>
                        </el-table>
                    </el-tab-pane>
                    <el-tab-pane label="生成配置" name="config">
                        <el-form-item label="模板类型" prop="gen.genTpl" required>
                            <el-radio-group v-model="formData.gen.genTpl">
                                <el-radio :label="GenTpl.CRUD">单表（增删改查）</el-radio>
                                <el-radio :label="GenTpl.TREE">树表（增删改查）</el-radio>
                            </el-radio-group>
                        </el-form-item>
                        <el-form-item label="模块名" prop="gen.moduleName">
                            <div class="w-80">
                                <el-input
                                    v-model="formData.gen.moduleName"
                                    placeholder="请输入模块名"
                                    clearable
                                />
                                <div class="form-tips">生成文件所在模块名</div>
                            </div>
                        </el-form-item>
                        <el-form-item label="功能名称" prop="gen.functionName">
                            <div class="w-80">
                                <el-input
                                    v-model="formData.gen.functionName"
                                    placeholder="请输入功能名称"
                                    clearable
                                />
                            </div>
                        </el-form-item>
                        <el-form-item label="生成方式" prop="gen.genType">
                            <div>
                                <el-radio-group v-model="formData.gen.genType">
                                    <el-radio :label="GenType.ZIP">压缩包下载</el-radio>
                                    <el-radio :label="GenType.CUSTOM_PATH">自定义路径</el-radio>
                                </el-radio-group>
                                <div class="form-tips">压縮包下载方式暂不支持自动构建菜单权限</div>
                            </div>
                        </el-form-item>
                        <el-form-item label="菜单构建" prop="gen.menuStatus" required>
                            <div>
                                <el-radio-group v-model="formData.gen.menuStatus">
                                    <el-radio :label="1">自动构建</el-radio>
                                    <el-radio :label="0">手动添加</el-radio>
                                </el-radio-group>
                                <div class="form-tips">
                                    自动构建：自动执行生成菜单sql。 手动添加：自行添加菜单
                                </div>
                            </div>
                        </el-form-item>
                        <el-form-item label="父级菜单" prop="gen.menuPid">
                            <el-tree-select
                                class="w-80"
                                v-model="formData.gen.menuPid"
                                :data="optionsData.menu"
                                clearable
                                node-key="id"
                                :props="{
                                    label: 'menuName'
                                }"
                                default-expand-all
                                placeholder="请选择父级菜单"
                                check-strictly
                            />
                        </el-form-item>
                        <el-form-item label="菜单名称" prop="gen.menuName">
                            <div class="w-80">
                                <el-input
                                    v-model="formData.gen.menuName"
                                    placeholder="请输入菜单名称"
                                    clearable
                                />
                            </div>
                        </el-form-item>

                        <el-form-item
                            v-if="formData.gen.genType == GenType.CUSTOM_PATH"
                            label="自定义路径"
                            prop="gen.genPath"
                        >
                            <div class="w-80">
                                <el-input
                                    v-model="formData.gen.genPath"
                                    placeholder="请输入自定义路径"
                                    clearable
                                />
                            </div>
                        </el-form-item>
                        <template v-if="formData.gen.genTpl == GenTpl.TREE">
                            <el-form-item label="树主键字段" prop="gen.treePrimary">
                                <el-select
                                    class="w-80"
                                    v-model="formData.gen.treePrimary"
                                    clearable
                                >
                                    <el-option
                                        v-for="item in formData.column"
                                        :key="item.id"
                                        :value="item.columnName"
                                        :label="`${item.columnName}：${item.columnComment}`"
                                    />
                                </el-select>
                            </el-form-item>
                            <el-form-item label="树父级字段" prop="gen.treeParent">
                                <el-select class="w-80" v-model="formData.gen.treeParent" clearable>
                                    <el-option
                                        v-for="item in formData.column"
                                        :key="item.id"
                                        :value="item.columnName"
                                        :label="`${item.columnName}：${item.columnComment}`"
                                    />
                                </el-select>
                            </el-form-item>
                            <el-form-item label="树名称字段" prop="gen.treeName">
                                <el-select class="w-80" v-model="formData.gen.treeName" clearable>
                                    <el-option
                                        v-for="item in formData.column"
                                        :key="item.id"
                                        :value="item.columnName"
                                        :label="`${item.columnName}：${item.columnComment}`"
                                    />
                                </el-select>
                            </el-form-item>
                        </template>
                    </el-tab-pane>
                    <el-tab-pane label="关联配置" name="relation">
                        <el-form-item label="关联子表的表名" prop="gen.subTableName">
                            <el-select
                                class="w-80"
                                v-model="formData.gen.subTableName"
                                clearable
                                @change="handleTableChange"
                            >
                                <el-option
                                    v-for="item in optionsData.dataTable"
                                    :key="item.tableName"
                                    :value="item.tableName"
                                    :label="`${item.tableName}：${item.tableComment}`"
                                />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="子表关联的外键名 " prop="gen.subTableFk">
                            <el-select
                                class="w-80"
                                v-model="formData.gen.subTableFk"
                                clearable
                                :loading="columnLoading"
                            >
                                <el-option
                                    v-for="item in tableColumn"
                                    :key="item.id"
                                    :value="item.columnName"
                                    :label="`${item.columnName}：${item.columnComment}`"
                                />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="关联表主键 " prop="gen.subTableFr">
                            <el-select class="w-80" v-model="formData.gen.subTableFr" clearable>
                                <el-option
                                    v-for="item in formData.column"
                                    :key="item.id"
                                    :value="item.columnName"
                                    :label="`${item.columnName}：${item.columnComment}`"
                                />
                            </el-select>
                        </el-form-item>
                    </el-tab-pane>
                </el-tabs>
            </el-form>
        </el-card>
        <footer-btns>
            <el-button type="primary" @click="handleSave">保存</el-button>
        </footer-btns>
    </div>
</template>

<script lang="ts" setup name="tableEdit">
import { dataTableAll, generateEdit, tableDetail, dataTableToColumn } from '@/api/tools/code'
import { dictTypeAll } from '@/api/setting/dict'
import type { FormInstance } from 'element-plus'
import feedback from '@/utils/feedback'
import { menuLists } from '@/api/perms/menu'
import { useDictOptions } from '@/hooks/useDictOptions'
import useMultipleTabs from '@/hooks/useMultipleTabs'
enum GenTpl {
    CRUD = 'crud',
    TREE = 'tree'
}

enum GenType {
    ZIP,
    CUSTOM_PATH
}

const route = useRoute()
const router = useRouter()
const { removeTab } = useMultipleTabs()
const activeName = ref('column')
const formData = reactive({
    base: {
        id: '',
        tableName: '',
        tableComment: '',
        entityName: '',
        authorName: '',
        remarks: ''
    },
    column: [] as any[],
    gen: {
        functionName: '',
        genPath: '',
        genTpl: '',
        genType: 0,
        moduleName: '',
        subTableFk: '',
        subTableName: '',
        subTableFr: '',
        treeParent: '',
        treePrimary: '',
        treeName: '',
        menuName: '',
        menuStatus: 0,
        menuPid: 0
    }
})

const formRef = shallowRef<FormInstance>()
const rules = reactive({
    ['base.tableName']: [{ required: true, message: '请输入表名称', trigger: 'blur' }],
    ['base.tableComment']: [{ required: true, message: '请输入表描述', trigger: 'blur' }],
    ['base.entityName']: [{ required: true, message: '请输入实体类名称', trigger: 'blur' }],
    ['base.authorName']: [{ required: true, message: '请输入作者', trigger: 'blur' }],
    ['gen.moduleName']: [{ required: true, message: '请输入模块名', trigger: 'blur' }],
    ['gen.functionName']: [{ required: true, message: '请输入功能名称', trigger: 'blur' }],
    ['gen.treePrimary']: [{ required: true, message: '请选择树主键字段', trigger: 'blur' }],
    ['gen.treeParent']: [{ required: true, message: '请选择树父级字段', trigger: 'blur' }],
    ['gen.treeName']: [{ required: true, message: '请选择树名称字段', trigger: 'blur' }]
})

const getDetails = async () => {
    const data = await tableDetail({
        id: route.query.id
    })

    Object.keys(formData).forEach((key) => {
        //@ts-ignore
        formData[key] = data[key]
    })
    getTableColumn()
}

const { optionsData } = useDictOptions<{
    dictType: any[]
    menu: any[]
    dataTable: any[]
}>({
    dictType: {
        api: dictTypeAll
    },
    menu: {
        api: menuLists,
        transformData(data: any) {
            const menu = { id: 0, menuName: '顶级', children: [] }
            menu.children = data
            return [menu]
        }
    },
    dataTable: {
        api: dataTableAll
    }
})

const columnLoading = ref(false)
const tableColumn = ref<any[]>([])
const getTableColumn = async () => {
    columnLoading.value = true
    const res = await dataTableToColumn({ tableName: formData.gen.subTableName })
    columnLoading.value = false
    tableColumn.value = res
}

const handleTableChange = () => {
    formData.gen.subTableFk = ''
    getTableColumn()
}
const handleSave = async () => {
    try {
        await formRef.value?.validate()
        const { base, column, gen } = formData
        await generateEdit({ ...base, ...gen, column })
        feedback.msgSuccess('操作成功')
        removeTab()
        router.back()
    } catch (error: any) {
        for (const err in error) {
            const isInRules = Object.keys(rules).includes(err)
            isInRules && feedback.msgError(error[err][0]?.message)
        }
    }
}

getDetails()
</script>
