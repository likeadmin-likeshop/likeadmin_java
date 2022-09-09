import { defineStore } from 'pinia'
import { getConfig } from '@/api/app'

interface AppSate {
    config: Record<string, any>
}
export const useAppStore = defineStore({
    id: 'appStore',
    state: (): AppSate => ({
        config: {
            website: {}
        }
    }),
    getters: {},
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
