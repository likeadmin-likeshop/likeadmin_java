<script lang="ts" setup>
import { useMenuOa } from './useMenuOa'
import oaMenuForm from './oa-menu-form.vue'
import oaMenuFormEdit from './oa-menu-form-edit.vue'

const menuRef = shallowRef()

const {
    menuList,
    menuIndex,
    handleAddSubMenu,
    handleEditSubMenu,
    handleDelMenu,
    handleDelSubMenu
} = useMenuOa(menuRef)
</script>

<template>
    <!-- Attr -->
    <template v-for="(attrItem, attrIndex) in menuList" :key="attrIndex">
        <div class="flex-1 oa-attr" v-show="attrIndex === menuIndex">
            <div class="text-base oa-attr-title">菜单配置</div>

            <del-wrap class="w-3/4" @close="handleDelMenu(menuIndex)">
                <div class="flex items-center w-full p-4 mt-4 rounded bg-fill-light">
                    <oa-menu-form
                        ref="menuRef"
                        modular="master"
                        v-model:name="attrItem.name"
                        v-model:menuType="attrItem.menuType"
                        v-model:visitType="attrItem.visitType"
                        v-model:url="attrItem.url"
                        v-model:appId="attrItem.appId"
                        v-model:pagePath="attrItem.pagePath"
                    >
                        <div class="flex-1">
                            <!-- 编辑子菜单 -->
                            <ul>
                                <li
                                    class="flex"
                                    v-for="(subItem, subIndex) in attrItem.subButtons"
                                    :key="subIndex"
                                    style="padding: 8px"
                                >
                                    <span class="mr-auto">{{ subItem.name }}</span>
                                    <!-- 编辑子菜单 -->
                                    <oa-menu-form-edit
                                        modular="edit"
                                        :subItem="subItem"
                                        @edit="handleEditSubMenu($event, subIndex)"
                                    >
                                        <el-icon><EditPen /></el-icon>
                                    </oa-menu-form-edit>

                                    <!-- 删除子菜单 -->
                                    <popup @confirm="handleDelSubMenu(menuIndex, subIndex)">
                                        是否删除当前子菜单？
                                        <template #trigger>
                                            <el-icon class="ml-5"><Delete /></el-icon>
                                        </template>
                                    </popup>
                                </li>
                            </ul>

                            <!-- 新增子菜单 -->
                            <oa-menu-form-edit modular="add" @add="handleAddSubMenu">
                                <el-button
                                    type="primary"
                                    link
                                    :disabled="attrItem.subButtons.length >= 5"
                                    >添加子菜单({{ attrItem.subButtons.length }}/5)</el-button
                                >
                            </oa-menu-form-edit>
                        </div>
                    </oa-menu-form>
                </div>
            </del-wrap>
        </div>
    </template>
</template>

<style lang="scss" scoped>
.oa-attr {
}
</style>
