import request from '@/utils/request'

// 岗位列表
export function postLists(params?: any) {
    return request.get({ url: '/system/post/list', params })
}
// 岗位列表
export function postAll(params?: any) {
    return request.get({ url: '/system/post/all', params })
}

// 添加岗位
export function postAdd(params: any) {
    return request.post({ url: '/system/post/add', params })
}

// 编辑岗位
export function postEdit(params: any) {
    return request.post({ url: '/system/post/edit', params })
}

// 删除岗位
export function postDelete(params: any) {
    return request.post({ url: '/system/post/del', params })
}

// 岗位详情
export function postDetail(params: any) {
    return request.get({ url: '/system/post/detail', params })
}
