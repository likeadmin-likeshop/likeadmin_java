import request from '@/utils/request'

//首页数据
export function getIndex() {
    return request.get({ url: '/index' })
}

// 装修页面
export function getDecorate(data: any) {
    return request.get({ url: '/decorate', data })
}

/**
 * @description 热门搜索
 * @return { Promise } 
 */
export function getHotSearch() {
    return request.get({ url: '/hotSearch' })
}

/**
 * @description 搜索
 * @param { string } keyword 关键词
 * @return { Promise } 
 */
export function getSearch(data: { keyword: string, pageNo: number, pageSize: number }) {
    return request.get({ url: '/search', data })
}
