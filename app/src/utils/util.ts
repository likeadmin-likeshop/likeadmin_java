 import { getToken } from './auth'

/**
 * @description 获取元素节点信息（在组件中的元素必须要传ctx）
 * @param  { String } selector 选择器 '.app' | '#app'
 * @param  { Boolean } all 是否多选
 * @param  { ctx } context 当前组件实例
 */
export const getRect = (selector: string, all = false, context?: any) => {
    return new Promise((resolve, reject) => {
        let qurey = uni.createSelectorQuery()
        if (context) {
            qurey = uni.createSelectorQuery().in(context)
        }
        qurey[all ? 'selectAll' : 'select'](selector)
            .boundingClientRect(function (rect) {
                if (all && Array.isArray(rect) && rect.length) {
                    return resolve(rect)
                }
                if (!all && rect) {
                    return resolve(rect)
                }
                reject('找不到元素')
            })
            .exec()
    })
}

/**
 * @description 上传图片
 * @param  { String } path 选择的本地地址
 */
export function uploadFile(path: any) {
	return new Promise((resolve, reject) => {
		const token = getToken()
		uni.uploadFile({
			url: `${import.meta.env.VITE_APP_BASE_URL}/api/Upload/image`,
			filePath: path,
			name: 'file',
			header: {
				token,
			},
			fileType: 'image',
			success: res => {
				console.log('uploadFile res ==> ', res)
				let data = JSON.parse(res.data);
				if (data.code == 1) {
					resolve(data.data);
				} else {
					reject()
				}
			},
			fail: (err) => {
				console.log(err)
				reject()
			}
		});
	});
}

export const getWxCode = (): Promise<void> => {
    return new Promise((resolve, reject) => {
        uni.login({
            desc: '获取用户信息，完善用户资料',
            success: (res: Event) => resolve(res.code)
        })
    })
}
