import { RouteRecordRaw, RouterView } from "vue-router"

export enum MenuType{
    Catalogue = 'M',
    Menu = 'C',
    Button = 'A'
}

// 匹配views里面所有的.vue文件，动态引入
const modules = import.meta.glob('/src/views/**/*.vue')


// 过滤路由所需要的数据
export function filterAsyncRoutes(routes: any[], firstRoute = true) {
    return routes.map((route => {
        const routeRecord = createRouteRecord(route, firstRoute)
        if (route.children != null && route.children && route.children.length) {
            routeRecord.children = filterAsyncRoutes(route.children, false)
        }
        return routeRecord
    }))
}

// 创建一条路由记录
export function createRouteRecord(route: any, firstRoute: boolean): RouteRecordRaw {
    //@ts-ignore
    const routeRecord: RouteRecordRaw = {
        path: firstRoute ? `/${route.paths}` : route.paths,
        name: route.paths,
        meta: {
            hidden: !route.isShow,
            keepAlive: !!route.isCache,
            title: route.menuName,
            perms: route.perms,
            query: route.params,
            icon: route.menuIcon,
            activePath: route.selected
        },
    }
    switch (route.menuType) {
        case MenuType.Catalogue:
            routeRecord.component = RouterView
            break
        case MenuType.Menu:
            routeRecord.component = loadRouteView(route.component)
            break
    }
    return routeRecord
}

console.log(modules)
// 动态加载组件
export function loadRouteView(component: string) {
    try {
        const key = Object.keys(modules).find((key) => {
            return key.includes(`${component}.vue`)
        })
        if (key) {
            return modules[key]
        } else {
            throw Error(`找不到组件${component}，请确保组件路径正确`)
        }
    } catch (error) {
        console.error(error)
        return RouterView
    }
 
}
