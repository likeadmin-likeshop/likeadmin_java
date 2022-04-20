import { Module } from 'vuex'
import { RouteRecordRaw } from 'vue-router'
import { apiConfigGetRoutes } from '@/api/auth'
import { filterAsyncRoutes } from '@/core/lib/router'

export interface PermissionModule {
    routes:  Array<RouteRecordRaw>
    sidebar: Array<RouteRecordRaw>
}

const permission: Module<PermissionModule, any> = {
    namespaced: true,
    state: {
        // 路由
        routes: [],
        // 左侧菜单
        sidebar: []
    },
    getters: {},
    mutations: {
        setSidebar(state, data) {
            state.sidebar = data
        },
    },
    actions: {
        generateRoutes({ commit }) {
            return new Promise((resolve, reject) => {
                apiConfigGetRoutes()
                    .then((data: any) => {
                        const rdata = JSON.parse(JSON.stringify(data))
                        const routes = filterAsyncRoutes(rdata)
                        commit('setSidebar', routes)
                        resolve(routes)
                    })
                    .catch(err => {
                        reject(err)
                    })
            })
        }
    }
}

export default permission
