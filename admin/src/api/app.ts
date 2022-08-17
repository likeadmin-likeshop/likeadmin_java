import request from '@/utils/request'

// 配置
export function getConfig() {
    return request.get({ url: '/common/index/config' })
}

// 工作台主页
export function getWorkbench() {
    return request.get({ url: '/common/index/console' })
}
