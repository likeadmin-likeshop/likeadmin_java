/**
 * perm 操作权限处理(用于复制文本)
 * 指令用法：
 *  <el-button v-perm="['system:admin:edit']">编辑</el-button>
 * copyValue为需要复制的值
 */

import store from "@/store"

export default {
    mounted: (el: HTMLElement, binding: any) => {
        const { value } = binding
        const permissions = store.getters && store.getters.permission
        const all_permission = "*";
        if (Array.isArray(value)) {
            if (value.length > 0) {
                const hasPermission = permissions.some((key: string) => {
                    return all_permission == key || value.includes(key)
                })

                if (!hasPermission) {
                    el.parentNode && el.parentNode.removeChild(el)
                }
            }
        } else {
            throw new Error(`like v-perm="['system:admin:edit']`)
        }
    }
}
