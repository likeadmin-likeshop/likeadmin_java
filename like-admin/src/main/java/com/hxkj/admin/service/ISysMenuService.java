package com.hxkj.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.validate.SysMenuParam;
import com.hxkj.admin.vo.system.SysMenuVo;
import com.hxkj.common.basics.BaseService;
import com.hxkj.common.entity.system.SysMenu;

/**
 * 系统菜单服务
 */
public interface ISysMenuService extends BaseService<SysMenu> {

    /**
     * 菜单列表
     *
     * @return JSONArray
     */
    JSONArray lists();

    /**
     * 菜单详情
     *
     * @author fzr
     * @param id 主键
     * @return SysMenu
     */
    SysMenuVo detail(Integer id);

    /**
     * 新增菜单
     *
     * @author fzr
     * @param sysMenuParam 参数
     */
    void add(SysMenuParam sysMenuParam);

    /**
     * 编辑菜单
     *
     * @author fzr
     * @param sysMenuParam 参数
     */
    void edit(SysMenuParam sysMenuParam);

    /**
     * 删除菜单
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
