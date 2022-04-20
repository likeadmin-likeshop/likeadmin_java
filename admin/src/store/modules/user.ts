import { Module } from 'vuex'
import cache from '@/utils/cache'
import { TOKEN } from '@/config/cachekey'
import { apiLogin, apiLogout, apiUserInfo } from '@/api/user'
export interface UserModule {
    token: string
    user: object
    permissions: any[]
}
const user: Module<UserModule, any> = {
    namespaced: true,
    state: {
        token: cache.get(TOKEN) || '',
        user: {},
        permissions: []
    },
    mutations: {
        setToken(state, data) {
            state.token = data
            cache.set(TOKEN, data)
        },
        setUser(state, data) {
            state.user = data
        },
        setPermission(state, permissions) {
            state.permissions = permissions
        }
    },
    actions: {
        // 登录
        login({ commit }, data) {
            const { account, password } = data

            return new Promise((resolve, reject) => {
                apiLogin({
                    username: account,
                    password: password
                })
                    .then((data: any) => {
                        commit('setToken', data.token)
                        resolve(data)
                    })
                    .catch(error => {
                        reject(error)
                    })
            })
        },
        // 退出登录
        logout({ commit }) {
            return new Promise((resolve, reject) => {
                apiLogout()
                    .then(data => {
                        commit('setToken', '')
                        commit('setUser', {})
                        commit('setPermission', [])
                        cache.remove(TOKEN)
                        resolve(data)
                    })
                    .catch(error => {
                        reject(error)
                    })
            })
        },
        // 获取管理员信息
        getUser({ commit }) {
            return new Promise((resolve, reject) => {
                apiUserInfo()
                    .then((data: any) => {
                        commit('setUser', data)
                        commit('setPermission', data.permissions)
                        resolve(data)
                    })
                    .catch(error => {
                        reject(error)
                    })
            })
        }
    }
}

export default user
