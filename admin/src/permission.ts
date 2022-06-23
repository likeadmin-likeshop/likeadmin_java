/**
 * 权限控制
 */

import NProgress from 'nprogress'
import store from './store'
import router, { indexName } from './router'
import 'nprogress/nprogress.css'

// NProgress配置
NProgress.configure({ showSpinner: false })

const loginPath = '/login'
const defaultPath = '/'
// 免登录白名单
const whiteList = ['/login']

router.beforeEach(async (to, from, next) => {
    NProgress.start()
    // 开始 Progress Bar
    to.meta?.title && (document.title = to.meta.title as string)
    const token = store.getters.token
    if (token) {
        // 获取用户信息
        if (store.getters.permission.length === 0) {
            try {
                await store.dispatch('user/getUser')
                const routes = await store.dispatch('permission/generateRoutes')
                routes.forEach((route: any) => {
                    if(!route.children) {
                        router.addRoute(indexName, route) 
                        return 
                    }
                    router.addRoute(route) // 动态添加可访问路由表
                })
                console.log(router.getRoutes())
                if (to.path === '/login') {
                    next({ path: '/' })
                } else {
                    next({ ...to, replace: true }) //确保addRoutes已完成
                }
            } catch {
                await store.dispatch('user/logout')
                next({ path: loginPath, query: { redirect: to.fullPath } })
                NProgress.done()
            }
        } else {
            next()
        }

    } else if (whiteList.includes(to.path as string)) {
        // 在免登录白名单，直接进入
        next()
    } else {
        next({ path: loginPath, query: { redirect: to.fullPath } })
    }
})

router.afterEach(async (to, from) => {
    NProgress.done()
})
