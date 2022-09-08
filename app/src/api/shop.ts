import request from '@/utils/request'

//首页数据
export function getIndex() {
    return request.get({ url: '/index' })
}

// 装修页面
export function getDecorate(data: any) {
    return request.get({ url: '/decorate', data })
}
