import request from '@/utils/request'

/**
 * @return { Promise }
 * @description 获取用户设置
 */
export function getUserSetup() {
    return request.get({ url: '/setting/user/detail' })
}

/**
 * @return { Promise }
 * @param { string } defaultAvatar 默认用户头像
 * @description 设置用户设置
 */
export function setUserSetup(params: { defaultAvatar: string }) {
    return request.post({ url: '/setting/user/save', params })
}

/**
 * @return { Promise }
 * @description 设置登录注册规则
 */
export function getLogin() {
    return request.get({ url: '/setting/login/detail' })
}

export interface LoginSetup {
    loginWay: number[] | any // 登录方式, 逗号隔开
    forceBindMobile: number // 强制绑定手机 0/1
    openAgreement: number // 是否开启协议 0/1
    openOtherAuth: number // 第三方登录 0/1
    autoLoginAuth: number[] | any // 第三方自动登录 逗号隔开
}
/**
 * @return { Promise }
 * @param { LoginSetup } LoginSetup
 * @description 设置登录注册规则
 */
export function setLogin(params: LoginSetup) {
    return request.post({ url: '/setting/login/save', params })
}
