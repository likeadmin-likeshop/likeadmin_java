import wechatOa from '@/utils/wechat'
import request from '@/utils/request'

//发送短信
export function smsSend(data: any) {
    return request.post({ url: '/index/sendSms', data: data })
}

export function getConfig() {
    return request.get({ url: '/index/config' })
}

export function getPolicy(data: any) {
    return request.get({ url: '/index/policy', data: data })
}

export function uploadImage(file: any, token?: string) {
    return request.uploadFile({
        url: '/upload/image',
        filePath: file,
        name: 'file',
        header: {
            token
        },
        fileType: 'image'
    })
}

export function wxJsConfig(data: any) {
    return request.get({ url: '/wechat/jsConfig', data })
}
