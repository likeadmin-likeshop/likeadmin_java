import request from '@/utils/request'

// 获取备案信息
export function getCopyright() {
    return request.get({ url: '/setting/copyright/detail' })
}
// 设置备案信息
export function setCopyright(params: any) {
    return request.post({ url: '/setting/copyright/save', params })
}
// 获取网站信息
export function getWebsite() {
    return request.get({ url: '/setting/website/detail' })
}
// 设置网站信息
export function setWebsite(params: any) {
    return request.post({ url: '/setting/website/save', params })
}

// 获取政策协议
export function getProtocol() {
    return request.get({ url: '/setting/protocol/detail' })
}
// 设置政策协议
export function setProtocol(params: any) {
    return request.post({ url: '/setting/protocol/save', params })
}
