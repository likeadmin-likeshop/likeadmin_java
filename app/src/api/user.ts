import request from '@/utils/request'

export function getUserCenter() {
    return request.get({ url: '/user/center' })
}
