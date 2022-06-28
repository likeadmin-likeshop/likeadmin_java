import request from '@/utils/request'
/** 部门 S **/
// 部门列表
export function apiDeptLists(params: any) {
    return request.get('/system/dept/list', { params })
}

// 添加部门
export function apiDeptAdd(params: any) {
    return request.post('/system/dept/add', params)
}

// 编辑部门
export function apiDeptEdit(params: any) {
    return request.post('/system/dept/edit', params)
}

// 删除部门
export function apiDeptDelete(params: any) {
    return request.post('/system/dept/del', params)
}

// 部门详情
export function apiDeptDetail(params: any) {
    return request.get('/system/dept/detail', { params })
}

// 所有部门
export function apiDeptAll(params: any) {
    return request.get('/system/dept/all', { params })
}
/** 部门 E **/

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
