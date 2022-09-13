/**
 * @description
 * @param file
 */
export function streamFileDownload(file: any, fileName = '文件名称.zip') {
    const blob = new Blob([file], { type: 'application/octet-stream;charset=UTF-8' })
    const url = window.URL.createObjectURL(blob)
    const link = document.createElement('a')
    link.style.display = 'none'
    link.href = url
    link.setAttribute('download', fileName)
    document.body.appendChild(link)
    link.click()
    document.body.removeChild(link) // 下载完成移除元素
    window.URL.revokeObjectURL(url)
}
