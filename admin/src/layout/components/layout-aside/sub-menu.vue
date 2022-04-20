<template>
    <div v-if="!route.meta.hidden">
        <el-sub-menu v-if="hasChildren" :index="path">
            <template #title>
                <i class="iconfont m-r-10" :class="route.meta.icon"></i>
                <span>{{ route.meta.title }}</span>
            </template>
            <template #default>
                <sub-menu
                    v-for="(item, index) in route.children"
                    :key="index"
                    :route="item"
                    :path="resolvePath(item.path)"
                />
            </template>
        </el-sub-menu>
        <router-link
            v-else
            :to="{
                path: path,
                query: resolveQuery
            }"
        >
            <el-menu-item :index="path">
                <i class="iconfont m-r-10" :class="route.meta.icon"></i>
                <span>{{ route.meta.title }}</span>
            </el-menu-item>
        </router-link>
    </div>
</template>

<script lang="ts">
import { queryToObject } from '@/utils/util'
import { isQuery } from '@/utils/validate'
import { computed, defineComponent, toRefs } from 'vue'
import { RouteRecordRaw } from 'vue-router'
export default defineComponent({
    components: {},
    props: {
        route: {
            type: Object,
            default: () => ({})
        },
        path: {
            type: String
        }
    },
    setup(props) {
        const { path, route } = toRefs(props)
        // 是否有子路由
        const hasChildren = computed(() => {
            const children: RouteRecordRaw[] = route.value.children ?? []
            return !!children.filter(item => !item.meta?.hidden).length
        })
        // 解析路径，后台上传的并非完整路径
        const resolvePath = computed(() => (p?: string) => {
            return p !== undefined ? `${path.value}/${p}` : path.value
        })
        // 解析参数'{id:1}'|| id=1 => {id: 1}
        const resolveQuery = computed(() => {
            const query = route.value.query
            try {
                if (isQuery(query)) {
                    return queryToObject(query)
                } else {
                    return JSON.parse(query)
                }
            } catch (error) {

            }
        })

        return {
            hasChildren,
            resolvePath,
            resolveQuery
        }
    }
})
</script>
