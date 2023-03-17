import request from '@/utils/request'

// 登录
export function mobileLogin(data: Record<string, any>) {
    return request.post({ url: '/login/mobileLogin', data })
}

// 登录
export function accountLogin(data: Record<string, any>) {
    return request.post({ url: '/login/accountLogin', data })
}

//注册
export function register(data: Record<string, any>) {
    return request.post({ url: '/login/register', data })
}

//忘记密码
export function forgotPassword(data: Record<string, any>) {
    return request.post({ url: '/login/forgotPassword', data })
}

//向微信请求code的链接
export function getWxCodeUrl() {
    return request.get({ url: '/login/oaCodeUrl', data: { url: location.href } })
}

// 微信小程序登录

export function mnpLogin(data: Record<string, any>) {
    return request.post({ url: '/login/mnpLogin', data })
}

// 公众号登录
export function OALogin(data: Record<string, any>) {
    return request.post({ url: '/login/oaLogin', data })
}
