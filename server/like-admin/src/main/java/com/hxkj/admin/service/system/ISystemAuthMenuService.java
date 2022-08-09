package com.hxkj.admin.service.system;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.validate.system.SystemAuthMenuParam;
import com.hxkj.admin.vo.system.SystemAuthMenuVo;

/**
 * 系统菜单服务接口类
 */
public interface ISystemAuthMenuService {

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
    JSONArray list();

    /**
     * 菜单详情
     *
     * @author fzr
     * @param id 主键
     * @return SysMenu
     */
    SystemAuthMenuVo detail(Integer id);

    /**
     * 菜单新增
     *
     * @author fzr
     * @param systemAuthMenuParam 参数
     */
    void add(SystemAuthMenuParam systemAuthMenuParam);

    /**
     * 菜单编辑
     *
     * @author fzr
     * @param systemAuthMenuParam 参数
     */
    void edit(SystemAuthMenuParam systemAuthMenuParam);

    /**
     * 菜单删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
