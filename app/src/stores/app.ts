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
            login: {},
            tabbar: [],
            style: {}
        }
    }),
    getters: {
        getWebsiteConfig: (state) => state.config.website,
        getLoginConfig: (state) => state.config.login,
        getTabbarConfig: (state) => state.config.tabbar,
        getStyleConfig: (state) => state.config.style
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
