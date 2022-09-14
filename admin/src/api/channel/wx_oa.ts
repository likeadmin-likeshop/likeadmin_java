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
    name: string,
    menuType?: number,
    visitType?: string,
    url?: string,
    appId?: string
    pagePath?: string,
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