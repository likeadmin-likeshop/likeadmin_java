import request from '@/utils/request'

/**
 * @description 获取文章分类
 * @return { Promise } 
 */
export function getArticleCate() {
    return request.get({ url: '/article/category' })
}

/**
 * @description 获取文章列表
 * @return { Promise } 
 */
export function getArticleList(data: Record<string, any>) {
    return request.get({ url: '/article/list', data: data })
}
