import request from '@/utils/request'

/**
 * @return { Promise }
 * @description 获取热门搜索数据
 */
export function getSearch() {
    return request.get({ url: '/setting/search/detail' })
}

export interface List {
    name: string // 搜索关键字
    sort: number // 热门搜索排序
}

export interface Search {
    isHotSearch: number // 是否开启搜索0/1
    list: List[]
}
/**
 * @return { Promise }
 * @param { Search } Search
 * @description 设置热门搜索
 */
export function setSearch(params: Search) {
    return request.post({ url: '/setting/search/save', params })
}
