import request from '@/utils/request'

// 角色列表
export function roleLists(params: any) {
    return request.get({ url: '/system/role/list', params })
}

// 角色列表
export function roleAll(params?: any) {
    return request.get({ url: '/system/role/all', params })
}

// 角色列表
export function roleDetail(params: any) {
    return request.get({ url: '/system/role/detail', params })
}

// 添加角色
export function roleAdd(params: any) {
    return request.post({ url: '/system/role/add', params })
}
// 编辑角色
export function roleEdit(params: any) {
    return request.post({ url: '/system/role/edit', params })
}
// 删除角色
export function roleDelete(params: any) {
    return request.post({ url: '/system/role/del', params })
}
