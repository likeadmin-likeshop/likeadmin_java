package com.hxkj.admin.service;

import com.alibaba.fastjson.JSONArray;
import com.hxkj.admin.validate.system.SystemMenuParam;
import com.hxkj.admin.vo.system.SystemAuthVo;
import com.hxkj.admin.vo.system.SystemMenuVo;

import java.util.List;

/**
 * 系统菜单服务接口类
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
    JSONArray list();

    /**
     * 菜单详情
     *
     * @author fzr
     * @param id 主键
     * @return SysMenu
     */
    SystemMenuVo detail(Integer id);

    /**
     * 菜单新增
     *
     * @author fzr
     * @param systemMenuParam 参数
     */
    void add(SystemMenuParam systemMenuParam);

    /**
     * 菜单编辑
     *
     * @author fzr
     * @param systemMenuParam 参数
     */
    void edit(SystemMenuParam systemMenuParam);

    /**
     * 菜单删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
