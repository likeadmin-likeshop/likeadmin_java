import request from '@/utils/request'

export function getRechargeConfig() {
    return request.get({ url: '/marketing/recharge/detail' })
}

// 设置
export function setRechargeConfig(params: any) {
    return request.post({ url: '/marketing/recharge/save', params })
}
