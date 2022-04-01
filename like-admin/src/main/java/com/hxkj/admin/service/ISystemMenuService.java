package com.hxkj.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.validate.system.SystemMenuParam;
import com.hxkj.admin.vo.system.SystemMenuVo;

/**
 * 系统菜单服务
 */
public interface ISystemMenuService {

    /**
     * 根据角色获取菜单
     *
     * @author fzr
     * @return JSONArray
     */
    JSONArray selectMenuByRoleId(Integer roleId);

    /**
     * 菜单列表
     *
     * @author fzr
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
    SystemMenuVo detail(Integer id);

    /**
     * 新增菜单
     *
     * @author fzr
     * @param systemMenuParam 参数
     */
    void add(SystemMenuParam systemMenuParam);

    /**
     * 编辑菜单
     *
     * @author fzr
     * @param systemMenuParam 参数
     */
    void edit(SystemMenuParam systemMenuParam);

    /**
     * 删除菜单
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
