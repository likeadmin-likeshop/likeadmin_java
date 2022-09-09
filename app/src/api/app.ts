import request from '@/utils/request'

//发送短信
export function smsSend(data: any) {
    return request.post({ url: '/sms/send', data: data })
}

export function getConfig() {
    return request.get({ url: '/config' })
}

export function getPolicy(data: any) {
    return request.get({ url: '/policy', data: data })
}
