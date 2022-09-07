import { getClient } from '@/utils/client'
import { defineStore } from 'pinia'

export const useAppStore = defineStore({
    id: 'appStore',
    // convert to a function
    state: () => ({
        client: getClient()
    }),
    getters: {},
    actions: {}
})
