import { ref } from "vue"
import feedback from '@/utils/feedback'

// 菜单数据
const menuList = ref<any>([])
const menuIndex = ref<number>(0)


export const useMenuOa = () => {

    const handleAddMenu = () => {
        menuList.value.push({
            name: '菜单名称',
            type: 1,
            jump_link: 1,
            url: '',
            app_id: '',
            pages: '',
            children: []
        })
    }

    return {
        menuList,
        menuIndex,
        handleAddMenu
    }
}