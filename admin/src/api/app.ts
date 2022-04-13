import request from '@/utils/request'

export function apiFileCateAdd(params: any) {
    return request.post('/album/cateAdd', params)
}

export function apiFileCateEdit(params: { id: number; name: string }) {
    return request.post('/file/editCate', params)
}

// 文件分类删除
export function apiFileCateDelete(params: { id: number }) {
    return request.post('/album/albumDel', params)
}

// 文件分类列表
export function apiFileCateLists(params: any) {
    return request.get('/album/cateList', { params })
}

// 文件列表
export function apiFileList(params: any) {
    return request.get('/album/albumList', { params })
}

// 文件删除
export function apiFileDelete(params: { ids: any[] }) {
    return request.post('/album/albumDel', params)
}

// 文件移动
export function apiFileMove(params: { ids: any[]; cid: number }) {
    return request.post('/album/albumMove', params)
}

// 文件重命名
export function apiFileRename(params: { id: number; name: string }) {
    return request.post('/album/albumRename', params)
}

// 配置
export function apiConfig() {
    return request.get('/index/config')
}
