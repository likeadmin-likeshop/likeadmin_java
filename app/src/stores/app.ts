import { defineStore } from 'pinia'
import { getConfig } from '@/api/app'

interface AppSate {
    config: Record<string, any>
}
export const useAppStore = defineStore({
    id: 'appStore',
    state: (): AppSate => ({
        config: {
            website: {},
            login: {}
        }
    }),
    getters: {
        getWebsiteConfig(state) {
            return state.config.website
        },
        getLoginConfig(state) {
            return state.config.login
        }
    },
    actions: {
        getImageUrl(url: string) {
            return url ? `${this.config.domain}${url}` : ''
        },
        async getConfig() {
            const data = await getConfig()
            this.config = data
        }
    }
})
