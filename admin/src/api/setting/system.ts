import request from '@/utils/request'

// 获取系统环境
export function systemInfo() {
    return request.get({ url: '/monitor/server' })
}

// 获取系统日志列表
export function systemLogLists(params: any) {
    return request.get({ url: '/system/log/operate', params })
}

// 系统缓存监控
export function systemCache() {
    return request.get({ url: '/monitor/cache' })
}

// 定时任务列表
export function crontabLists(params: any) {
    return request.get({ url: '/crontab/list', params })
}

// 添加定时任务
export function crontabAdd(params: any) {
    return request.post({ url: '/crontab/add', params })
}

// 定时任务详情
export function crontabDetail(params: any) {
    return request.get({ url: '/crontab/detail', params })
}

// 编辑定时任务
export function crontabEdit(params: any) {
    return request.post({ url: '/crontab/edit', params })
}

// 删除定时任务
export function crontabDel(params: any) {
    return request.post({ url: '/crontab/del', params })
}

// 获取登录日志列表
export function loginLogLists(params: any) {
    return request.get({ url: '/system/log/login', params })
}
