import request from '@/utils/request'

// 微信公众号配置保存
export function setOaConfig(params: any) {
    return request.post({ url: '/channel/oa/save', params })
}

// 微信公众号配置详情
export function getOaConfig() {
    return request.get({ url: '/channel/oa/detail' })
}

export interface Menu {
    name: string
    menuType?: number
    visitType?: string
    url?: string
    appId?: string
    pagePath?: string
    subButtons: Menu[] | any
}

/**
 * @return { Promise }
 * @description 获取菜单
 */
export function getOaMenu() {
    return request.get({ url: '/channel/oaMenu/detail' })
}

/**
 * @return { Promise }
 * @param { Menu } Menu
 * @description 菜单保存
 */
export function setOaMenuSave(params: Menu | any) {
    return request.post({ url: '/channel/oaMenu/save', params })
}

/**
 * @return { Promise }
 * @param { Menu } Menu
 * @description 菜单发布
 */
export function setOaMenuPublish(params: Menu | any) {
    return request.post({ url: '/channel/oaMenu/publish', params })
}

/**
 * @return { Promise }
 * @param { string } type
 * @description 获取回复列表
 */
export function getOaReplyList(params: { type: string }) {
    return request.get({ url: '/channel/oaReply/list', params })
}

/**
 * @return { Promise }
 * @param { number } id
 * @description 回复列表删除
 */
export function oaReplyDel(params: { id: number }) {
    return request.post({ url: '/channel/oaReply/del', params })
}

/**
 * @return { Promise }
 * @param { number } id
 * @description 回复状态修改
 */
export function changeOaReplyStatus(params: { id: number }) {
    return request.post({ url: '/channel/oaReply/status', params })
}

export interface Reply {
    content: string // 内容
    contentType: number // 内容类型: 1=文本
    keyword?: string // 关键词
    matchingType?: number // 匹配方式: [1=全匹配, 2=模糊匹配]
    name: string // 规则名称
    status: number // 状态: 1=开启, 0=关闭
    type: string // 类型: follow=关注, keyword=关键词, default=默认
    sort: number // 排序
}
/**
 * @return { Promise }
 * @description 回复添加
 */
export function oaReplyAdd(params: Reply) {
    return request.post({ url: '/channel/oaReply/add', params })
}

/**
 * @return { Promise }
 * @description 回复编辑
 */
export function oaReplyEdit(params: Reply) {
    return request.post({ url: '/channel/oaReply/edit', params })
}

/**
 * @return { Promise }
 * @param { string } type
 * @description 获取回复详情
 */
export function getOaReplyDetail(params: { id: number }) {
    return request.get({ url: '/channel/oaReply/detail', params })
}
