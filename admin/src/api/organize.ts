import request from '@/utils/request'
/** 岗位 S **/
// 岗位列表
export function apiPostLists(params: any) {
    return request.get('/system/post/list', { params })
}

// 添加岗位
export function apiPostAdd(params: any) {
    return request.post('/system/post/add', params)
}

// 编辑岗位
export function apiPostEdit(params: any) {
    return request.post('/system/post/edit', params)
}

// 删除岗位
export function apiPostDelete(params: any) {
    return request.post('/system/post/del', params)
}

// 岗位详情
export function apiPostDetail(params: any) {
    return request.get('/system/post/detail', { params })
}

// 所有岗位
export function apiPostAll(params: any) {
    return request.get('/system/post/all', { params })
}
/** 岗位 E **/
