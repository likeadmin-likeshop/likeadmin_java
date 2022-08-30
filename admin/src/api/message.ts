import request from '@/utils/request'

// 通知设置列表
export function noticeLists(params: any) {
    return request.get({ url: '/setting/notice/list', params })
}

// 通知设置详情
export function noticeDetail(params: any) {
    return request.get({ url: '/setting/notice/detail', params })
}

// 通知设置保存
export function setNoticeConfig(params: any) {
    return request.post({ url: '/setting/notice/save', params })
}

// 短信设置列表
export function smsLists() {
    return request.get({ url: '/setting/sms/list' })
}

// 短信设置详情
export function smsDetail(params: any) {
    return request.get({ url: '/setting/sms/detail', params })
}

// 短信设置保存
export function setSmsConfig(params: any) {
    return request.post({ url: '/setting/sms/save', params })
}
