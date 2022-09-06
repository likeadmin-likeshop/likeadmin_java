const widgets: Record<string, any> = import.meta.glob('./**/*', { eager: true })
interface Widget {
    attr: any
    content: any
    options: any
}
const exportWidgets: Record<string, Widget> = {}
Object.keys(widgets).forEach((key) => {
    const widgetName = key.replace(/^\.\/([\w-]+).*/gi, '$1')
    const widgetContent = key.replace(/(.*\/)*([^.]+).*/gi, '$2') as keyof Widget
    exportWidgets[widgetName] = exportWidgets[widgetName] ?? {}
    exportWidgets[widgetName][widgetContent] = widgets[key]?.default
})

export default exportWidgets
