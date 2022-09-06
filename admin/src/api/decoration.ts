import request from '@/utils/request'

// 页面装修详情
export function getDecoratePages(params: any) {
    return request.get({ url: '/decorate/pages/detail', params })
}

// 页面装修保存
export function setDecoratePages(params: any) {
    return request.post({ url: '/decorate/pages/save', params })
}
