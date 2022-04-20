/**
 * @description 判断字符串是否是id=1&name=2这种类型
 * @param { String } str 
 * @returns { Boolean }
 */
export function isQuery(str: string) {
    return /([^?&=]+)=([^?&=]*)/g.test(str)
}