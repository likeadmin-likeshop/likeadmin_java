<script setup lang="ts">
import { onLaunch } from '@dcloudio/uni-app'
import { useAppStore } from './stores/app'
import { useUserStore } from './stores/user'
const { getConfig } = useAppStore()
const { getUser } = useUserStore()
console.log(useUserStore())
onLaunch(() => {
    getConfig()
    getUser()
})
</script>
<style lang="scss">
/* 注意要写在第一行，同时给style标签加入lang="scss"属性 */
</style>
