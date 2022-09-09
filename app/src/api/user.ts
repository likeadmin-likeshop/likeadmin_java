import request from '@/utils/request'

export function getUserCenter() {
    return request.get({ url: '/user/center' })
}

export function getUserInfo() {
    return request.get({ url: '/user/info' })
}
