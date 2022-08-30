import request from '@/utils/request'

// 微信公众号配置保存
export function setOaConfig(params: any) {
    return request.post({ url: '/channel/oa/save', params })
}

// 微信公众号配置详情
export function getOaConfig() {
    return request.get({ url: '/channel/oa/detail' })
}
