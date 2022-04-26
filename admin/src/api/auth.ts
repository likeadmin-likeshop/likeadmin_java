import request from '@/utils/request'
import { terminal } from '@/config/app'

export function adminLists(params: any) {
    return request.get('/system/admin/list', { params })
}

// 管理员添加
export function apiAdminAdd(params: any) {
    return request.post('/system/admin/add', params)
}

export function apiAdminEdit(params: any) {
    return request.post('/system/admin/edit', params)
}

// 管理员删除
export function apiAdminDelete(params: { id: number }) {
    return request.post('/system/admin/del', params)
}

// 管理员详情
export function apiAdminDetail(params: any) {
    return request.get('/system/admin/detail', { params })
}

// 管理员状态
export function apiAdminStatus(params: any) {
    return request.post('/system/admin/disable', params)
}

/* 角色 */
// 角色列表
export function apiRoleLists(params: any) {
    return request.get('/system/role/list', { params })
}
// 添加角色
export function apiRoleAdd(params: any) {
    return request.post('/system/role/add', { ...params })
}
// 编辑角色
export function apiRoleEdit(params: any) {
    return request.post('/system/role/edit', { ...params })
}
// 删除角色
export function apiRoleDel(params: any) {
    return request.post('/system/role/del', { ...params })
}
// 角色详情
export function apiRoleDetail(params: any) {
    return request.get('/system/role/detail', { params })
}

// 角色权限菜单
export function apiConfigGetMenu() {
    return request.get('/system/menu/list')
}

// 菜单路由
export function apiConfigGetRoutes() {
    return request.get('/system/menu/route')
}

/* 菜单 */
// 菜单详情
export function apiMenuDetail(params: any) {
    return request.get('/system/menu/detail', { params })
}

// 新增菜单
export function apiMenuAdd(params: any) {
    return request.post('/system/menu/add', params)
}

// 编辑菜单
export function apiMenuEdit(params: any) {
    return request.post('/system/menu/edit', params)
}

// 删除菜单
export function apiMenuDelete(params: { id: number }) {
    return request.post('/system/menu/del', params)
}
