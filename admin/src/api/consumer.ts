import request from '@/utils/request'

// 用户列表
export function getUserList(params: any) {
    return request.get({ url: '/user/list', params })
}

// 用户详情
export function getUserDetail(params: any) {
    return request.get({ url: '/user/detail', params })
}
