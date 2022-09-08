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
