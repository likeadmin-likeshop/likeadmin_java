import request from '@/utils/request'
import { terminal } from '@/config/app'

// 登录
export function apiLogin(params: { account: string; password: string }) {
    return request.post('/system/login', { ...params, terminal })
}

// 退出登录
export function apiLogout() {
    return request.post('/system/logout')
}

// 用户信息
export function apiUserInfo() {
    return request.get('/system/admin/self')
}
