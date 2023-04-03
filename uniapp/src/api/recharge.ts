import request from '@/utils/request'

//充值
export function recharge(data: any) {
    return request.post({ url: '/recharge/placeOrder', data }, { isAuth: true })
}

//充值记录
export function rechargeRecord(data: any) {
    return request.get({ url: '/recharge/record', data }, { isAuth: true })
}

// 充值配置
export function rechargeConfig() {
    return request.get({ url: '/recharge/config' }, { isAuth: true })
}
