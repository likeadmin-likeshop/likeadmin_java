import request from '@/utils/request'

// 菜单列表
export function menuLists(params?: Record<string, any>) {
    return request.get({ url: '/system/menu/list', params })
}

// 添加菜单
export function menuAdd(params: Record<string, any>) {
    return request.post({ url: '/system/menu/add', params })
}

// 编辑菜单
export function menuEdit(params: Record<string, any>) {
    return request.post({ url: '/system/menu/edit', params })
}

// 菜单删除
export function menuDelete(params: Record<string, any>) {
    return request.post({ url: '/system/menu/del', params })
}

// 菜单删除
export function menuDetail(params: Record<string, any>) {
    return request.get({ url: '/system/menu/detail', params })
}
