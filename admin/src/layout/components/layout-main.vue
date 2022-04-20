<template>
    <div class="layout-main">
        <el-scrollbar>
            <div class="p-15">
                <router-view />
            </div>
        </el-scrollbar>
    </div>
</template>

<script lang="ts">
import { useAdmin } from '@/core/hooks/app'
import { computed, defineComponent } from 'vue'
export default defineComponent({
    name: 'layout-main',
    setup() {
        const { route } = useAdmin()
        const keepAlive = computed(() => {
            return route.meta.keepAlive
        })
        return {
            keepAlive
        }
    }
})
</script>

<style lang="scss" scoped>
.layout-main {
    flex: 1;
    min-height: 0;
}
</style>
