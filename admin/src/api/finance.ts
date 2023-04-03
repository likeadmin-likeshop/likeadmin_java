import request from '@/utils/request'

// 余额明细
export function accountLog(params?: any) {
    return request.get({ url: '/finance/wallet/list', params })
}

// 充值记录
export function rechargeLists(params?: any) {
    return request.get({ url: '/finance/recharger/list', params }, { ignoreCancelToken: true })
}

//退款
export function refund(params?: any) {
    return request.post({ url: '/finance/recharger/refund', params })
}

//重新退款
export function refundAgain(params?: any) {
    return request.post({ url: '/finance/recharger/refundAgain', params })
}

//退款记录
export function refundRecord(params?: any) {
    return request.get({ url: '/finance/refund/list', params })
}

//退款日志
export function refundLog(params?: any) {
    return request.get({ url: '/finance/refund/log', params })
}
