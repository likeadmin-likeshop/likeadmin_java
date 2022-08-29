import request from '@/utils/request'

// oa渠道配置保存
export function setOaConfig(params: any) {
    return request.post({ url: '/channel/oa/save', params })
}

// oa渠道配置详情
export function getOaConfig() {
    return request.get({ url: '/channel/oa/detail' })
}
