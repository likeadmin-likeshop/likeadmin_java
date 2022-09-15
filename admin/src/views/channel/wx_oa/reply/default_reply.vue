<template>
    <div>
        <el-card class="!border-none" shadow="never">
            <el-alert
                type="warning"
                title="温馨提示：1.粉丝在公众号发送内容时，系统无法匹配情况下发送启用的默认文本回复；2.同时只能启用一个默认回复。"
                :closable="false"
                show-icon
            />
        </el-card>
        <el-card class="!border-none mt-4" shadow="never">
            <div>
                <el-button
                    class="mb-4"
                    v-perms="['article:cate:add']"
                    type="primary"
                    @click="handleAdd()"
                >
                    <template #icon>
                        <icon name="el-icon-Plus" />
                    </template>
                    新增默认回复
                </el-button>
            </div>
            <el-table size="large" :data="lists">
                <el-table-column label="规则名称" prop="name" min-width="120" />
                <el-table-column label="回复类型" min-width="120">
                    <template #default="{ row }">
                        {{ getContentType(1) }}
                    </template>
                </el-table-column>
                <el-table-column
                    label="回复内容"
                    prop="content"
                    min-width="120"
                />
                <el-table-column label="状态" min-width="120">
                    <template #default="{ row }">
                        <el-switch
                            v-perms="['article:cate:change']"
                            v-model="row.status"
                            :active-value="1"
                            :inactive-value="0"
                            @change="changeStatus(row.id)"
                        />
                    </template>
                </el-table-column>
                <el-table-column label="排序" prop="sort" min-width="120" />
                <el-table-column label="操作" width="120" fixed="right">
                    <template #default="{ row }">
                        <el-button
                            v-perms="['article:cate:edit']"
                            type="primary"
                            link
                            @click="handleEdit(row)"
                        >
                            编辑
                        </el-button>
                        <el-button
                            v-perms="['article:cate:del']"
                            type="danger"
                            link
                            @click="handleDelete(row.id)"
                        >
                            删除
                        </el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
        <edit-popup
            v-if="showEdit"
            ref="editRef"
            @success="getLists"
            @close="showEdit = false"
        />
    </div>
</template>
<script lang="ts" setup>
import {
    oaReplyDel,
    getOaReplyList,
    changeOaReplyStatus,
} from "@/api/channel/wx_oa";
import feedback from "@/utils/feedback";
import EditPopup from "./edit.vue";
const editRef = shallowRef<InstanceType<typeof EditPopup>>();
const showEdit = ref(false);
const lists = ref();

const getContentType = computed(() => {
    return (val: number) => {
        switch (val) {
            case 1:
                return "文本";
        }
    };
});

const getLists = async () => {
    lists.value = await getOaReplyList({ type: "default" });
};

const handleAdd = async () => {
    showEdit.value = true;
    await nextTick();
    editRef.value?.open("add", "default");
};

const handleEdit = async (data: any) => {
    showEdit.value = true;
    await nextTick();
    editRef.value?.open("edit", "default");
    editRef.value?.getDetail(data);
};

const handleDelete = async (id: number) => {
    await feedback.confirm("确定要删除？");
    await oaReplyDel({ id });
    feedback.msgSuccess("删除成功");
    getLists();
};

const changeStatus = async (id: number) => {
    try {
        await changeOaReplyStatus({ id });
        feedback.msgSuccess("修改成功");
        getLists();
    } catch (error) {
        getLists();
    }
};

getLists();
</script>
