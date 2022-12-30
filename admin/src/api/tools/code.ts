import request from '@/utils/request'

// 代码生成已选数据表列表接口
export function generateTable(params: any) {
    return request.get({ url: '/gen/list', params })
}

// 数据表列表接口
export function dataTable(params: any) {
    return request.get({ url: '/gen/db', params })
}

// 数据表所有列表接口
export function dataTableAll() {
    return request.get({ url: '/gen/dbAll' })
}

//表名查字段
export function dataTableToColumn(params: any) {
    return request.get({ url: '/gen/dbColumn', params })
}
//选择要生成代码的数据表
export function selectTable(params: any) {
    return request.post(
        { url: '/gen/importTable', params },
        {
            isParamsToData: false
        }
    )
}

// 已选择的数据表详情
export function tableDetail(params: any) {
    return request.get({ url: '/gen/detail', params })
}

//同步字段
export function syncColumn(params: any) {
    return request.post(
        { url: '/gen/syncTable', params },
        {
            isParamsToData: false
        }
    )
}

//删除已选择的数据表
export function generateDelete(params: any) {
    return request.post({ url: '/gen/delTable', params })
}

//编辑已选表字段
export function generateEdit(params: any) {
    return request.post({ url: '/gen/editTable', params })
}

//预览代码
export function generatePreview(params: any) {
    return request.get({ url: '/gen/previewCode', params })
}

//生成代码
export function generateCode(params: any) {
    return request.get({ url: '/gen/genCode', params })
}

//下载代码
export function downloadCode(params: any) {
    return request.get(
        { responseType: 'blob', url: '/gen/downloadCode', params },
        {
            isTransformResponse: false
        }
    )
}
