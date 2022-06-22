import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import Layout from '@/layout/index.vue'
/**
 * Note: 路由配置项
 *
 * name:'router-name'               // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * 
 * meta : {
    keepAlive: true                  // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'                  // 设置该路由在侧边栏的名字
    icon: 'svg-name'                // 设置该路由的图标
    activeMenu: '/system/user'      // 当路由设置了该属性，则会高亮相对应的侧边栏。
    query: '{"id": 1}'             // 访问路由的默认传递参数
    hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 
  }
 */

// 公共路由
export const constantRoutes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: 'workbench',
        name: 'index',
        component: Layout,
    },

    {
        path: '/permission',
        component: Layout,
        children: [
            {
                path: 'admin/edit',
                component: () => import('@/views/permission/admin/edit.vue'),
                meta: { title: '编辑管理员', activeMenu: '/permission/admin' },
            },
            {
                path: 'menu/edit',
                component: () => import('@/views/permission/menu/edit.vue'),
                meta: { title: '编辑菜单', activeMenu: '/permission/menu' },
            },
            {
                path: 'role/edit',
                component: () => import('@/views/permission/role/edit.vue'),
                meta: { title: '编辑角色', activeMenu: '/permission/role' },
            },
        ],
    },
    {
        path: '/organize',
        component: Layout,
        children: [
            {
                path: 'department/edit',
                component: () => import('@/views/organize/department/edit.vue'),
                meta: { title: '编辑部门', activeMenu: '/organize/department' },
            },
            {
                path: 'post/edit',
                component: () => import('@/views/organize/post/edit.vue'),
                meta: { title: '编辑岗位', activeMenu: '/organize/post' },
            },
        ],
    },
    {
        path: '/setting',
        component: Layout,
        children: [
            {
                path: 'storage/edit',
                component: () => import('@/views/setting/storage/edit.vue'),
                meta: { title: '存储设置', activeMenu: '/setting/storage' },
            },
        ],
    },
    {
        path: '/login',
        component: () => import('@/views/account/login.vue'),
    },
    {
        path: '/500',
        component: () => import('@/views/error/500.vue'),
    },
    {
        path: '/:pathMatch(.*)*',
        component: () => import('@/views/error/404.vue'),
    },
]

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: constantRoutes,
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return { top: 0 }
        }
    },
})

export default router
