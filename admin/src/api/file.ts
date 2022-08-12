import request from '@/utils/request'

export function fileCateAdd(params: Record<string, any>) {
    return request.post({ url: '/common/album/cateAdd', params })
}

export function fileCateEdit(params: Record<string, any>) {
    return request.post({ url: '/common/album/cateRename', params })
}

// 文件分类删除
export function fileCateDelete(params: Record<string, any>) {
    return request.post({ url: '/common/album/cateDel', params })
}

// 文件分类列表
export function fileCateLists(params: Record<string, any>) {
    return request.get({ url: '/common/album/cateList', params })
}

// 文件列表
export function fileList(params: Record<string, any>) {
    return request.get({ url: '/common/album/albumList', params })
}

// 文件删除
export function fileDelete(params: Record<string, any>) {
    return request.post({ url: '/common/album/albumDel', params })
}

// 文件移动
export function fileMove(params: Record<string, any>) {
    return request.post({ url: '/common/album/albumMove', params })
}

// 文件重命名
export function fileRename(params: { id: number; name: string }) {
    return request.post({ url: '/common/album/albumRename', params })
}
