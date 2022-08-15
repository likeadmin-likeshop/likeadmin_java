import request from '@/utils/request'

// 岗位列表
export function postLists(params?: Record<string, any>) {
    return request.get({ url: '/system/post/list', params })
}

// 岗位新增
export function postAdd(params: Record<string, any>) {
    return request.post({ url: '/system/post/add', params })
}

// 岗位编辑
export function postEdit(params: Record<string, any>) {
    return request.post({ url: '/system/post/edit', params })
}

// 岗位删除
export function postDelete(params: Record<string, any>) {
    return request.post({ url: '/system/post/del', params })
}
