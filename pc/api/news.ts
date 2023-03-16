/**
 * @description 获取文章分类
 * @return { Promise }
 */
export function getArticleCate() {
    return $request.get({ url: '/article/category' })
}

/**
 * @description 获取文章列表
 * @return { Promise }
 */
export function getArticleList(params) {
    return $request.get({ url: '/article/list', params })
}

/**
 * @description 获取资讯中心
 * @return { Promise }
 */
export function getArticleCenter() {
    return $request.get({ url: '/pc/articleCenter' })
}

/**
 * @description 文章详情
 * @return { Promise }
 */
export function getArticleDetail(params) {
    return $request.get({ url: '/pc/articleDetail', params })
}

/**
 * @description 加入收藏
 * @param { number } id
 * @return { Promise }
 */
export function addCollect(params) {
    return $request.post({ url: '/article/collectAdd', params })
}

/**
 * @description 取消收藏
 * @param { number } id
 * @return { Promise }
 */
export function cancelCollect(params) {
    return $request.post({ url: '/article/collectCancel', params })
}

/**
 * @description 获取收藏列表
 * @return { Promise }
 */
export function getCollect(params) {
    return $request.get({ url: '/article/collectList', params })
}
