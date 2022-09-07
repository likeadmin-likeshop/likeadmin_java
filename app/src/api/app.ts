import request from '@/utils/request'

//注册
export function smsSend(data: Record<string, any>) {
    return request.post({ url: '/sms/send', data: data })
}
