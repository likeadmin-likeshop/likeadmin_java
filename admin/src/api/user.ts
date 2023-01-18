import config from '@/config'
import request from '@/utils/request'

// 登录
export function login(params: Record<string, any>) {
    return request.post({ url: '/system/login', params: { ...params, terminal: config.terminal } })
}

// 登录
export function loginCaptcha() {
    return request.get({ url: '/system/captcha' })
}

// 退出登录
export function logout() {
    return request.post({ url: '/system/logout' })
}

// 用户信息
export function getUserInfo() {
    return request.get({ url: '/system/admin/self' })
}

// 菜单路由
export function getMenu() {
    return request.get({ url: '/system/menu/route' })
}

// 编辑管理员信息
export function setUserInfo(params: any) {
    return request.post({ url: '/system/admin/upInfo', params })
}
