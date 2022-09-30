import { client } from '@/utils/client'
import request from '@/utils/request'

// 登录
export function login(data: Record<string, any>) {
    return request.post({ url: '/login/check', data: { ...data, client } })
}

//注册
export function register(data: Record<string, any>) {
    return request.post({ url: '/login/register', data: { ...data, client } })
}

//忘记密码
export function forgotPassword(data: Record<string, any>) {
    return request.post({ url: '/login/forgotPassword', data })
}

//向微信请求code的链接
export function getWxCodeUrl() {
    return request.get({ url: '/login/codeUrl', data: { url: location.href } })
}

export function OALogin(data: Record<string, any>) {
    return request.get({ url: '/login/oaLogin', data })
}
