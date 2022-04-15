import { RouteRecordRaw, RouterView } from 'vue-router'

const routes: RouteRecordRaw = {
    path: '/permission',
    redirect: '/permission/admin',
    component: RouterView,
    meta: { title: '权限管理', icon: 'icon-quanxian' },
    children: [
        {
            path: '/permission/admin',
            component: () => import('@/views/permission/admin/index.vue'),
            meta: { title: '管理员', permission: ['view'] }
        },
        {
            path: '/permission/admin/edit',
            component: () => import('@/views/permission/admin/edit.vue'),
            meta: {
                title: '管理员',
                parent: '/permission/admin',
                hidden: true
            }
        },
        {
            path: '/permission/role',
            component: () => import('@/views/permission/role/index.vue'),
            meta: { title: '角色', permission: ['view'] }
        },
        {
            path: '/permission/role/edit',
            component: () => import('@/views/permission/role/edit.vue'),
            meta: {
                title: '角色',
                parent: '/permission/role',
                hidden: true
            }
        },
        {
            path: '/permission/menu',
            component: () => import('@/views/permission/menu/index.vue'),
            meta: { title: '菜单', permission: ['view'] }
        },
        {
            path: '/permission/menu/edit',
            component: () => import('@/views/permission/menu/edit.vue'),
            meta: {
                title: '菜单',
                parent: '/permission/menu',
                hidden: true
            }
        }
    ]
}

export default routes
