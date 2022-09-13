import request from '@/utils/request'

export function getUserCenter() {
    return request.get({ url: '/user/center' })
}

// 个人信息
export function getUserInfo() {
    return request.get({ url: '/user/info' })
}

// 个人编辑
export function userEdit(data: any) {
    return request.post({ url: '/user/edit', data: data })
}

// 绑定手机
export function userBindMobile(data: any) {
    return request.post({ url: '/user/bindMobile', data: data })
}

// 微信电话
export function userMnpMobile(data: any) {
    return request.post({ url: '/user/mnpMobile', data: data })
}
export function userChangePwd(data: any) {
    return request.post({ url: '/user/changePwd', data: data })
}
