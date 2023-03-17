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

/**
 * @description 获取文章详情
 * @param { number } id
 * @return { Promise }
 */
export function getArticleDetail(data: { id: number }) {
    return request.get({ url: '/article/detail', data: data })
}

/**
 * @description 加入收藏
 * @param { number } articleId
 * @return { Promise }
 */
export function addCollect(data: { articleId: number }) {
    return request.post({ url: '/article/collectAdd', data: data }, { isAuth: true })
}

/**
 * @description 取消收藏
 * @param { number } id
 * @return { Promise }
 */
export function cancelCollect(data: { articleId: number }) {
    return request.post({ url: '/article/collectCancel', data: data }, { isAuth: true })
}

/**
 * @description 获取收藏列表
 * @return { Promise }
 */
export function getCollect() {
    return request.get({ url: '/article/collectList' })
}
