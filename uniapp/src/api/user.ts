import request from '@/utils/request'

export function getUserCenter(header?: any) {
    return request.get({ url: '/user/center', header })
}

// 个人信息
export function getUserInfo() {
    return request.get({ url: '/user/info' }, { isAuth: true })
}

// 个人编辑
export function userEdit(data: any) {
    return request.post({ url: '/user/edit', data }, { isAuth: true })
}

// 绑定手机
export function userBindMobile(data: any, header?: any) {
    return request.post({ url: '/user/bindMobile', data, header }, { isAuth: true })
}

// 微信电话
export function userMnpMobile(data: any) {
    return request.post({ url: '/user/mnpMobile', data }, { isAuth: true })
}
export function userChangePwd(data: any) {
    return request.post({ url: '/user/changePwd', data }, { isAuth: true })
}

// 绑定小程序
export function mnpAuthBind(data: any) {
    return request.post({ url: '/user/bindMnp', data })
}

// 绑定公众号
export function oaAuthBind(data: any) {
    return request.post({ url: '/user/bindOa', data })
}

//更新微信小程序头像昵称
export function updateUser(data: Record<string, any>, header: any) {
    return request.post({ url: '/user/updateUser', data, header })
}

//余额明细
export function accountLog(data: any) {
    return request.get({ url: '/logs/userMoney', data })
}
