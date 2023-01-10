import { getClient } from '~~/utils/env'

// 登录
export function login(params: any) {
    return $request.post({
        url: '/login/check',
        params: { ...params, client: getClient() }
    })
}
// // 登录
export function logout() {
    return $request.post({ url: '/login/logout' })
}

//注册
export function register(params: any) {
    return $request.post({
        url: '/login/register',
        params: { ...params, client: getClient() }
    })
}

//向微信请求code的链接
export function getWxCodeUrl() {
    return $request.get({
        url: '/login/getScanCode',
        params: {
            url: location.href
        }
    })
}

export function wxLogin(params: any) {
    return $request.post({ url: '/login/scanLogin', params })
}
