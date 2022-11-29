package com.mdd.admin.service;

import com.alibaba.fastjson2.JSONArray;
import com.mdd.admin.validate.system.SystemMenuCreateValidate;
import com.mdd.admin.validate.system.SystemMenuUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthMenuVo;

import java.util.List;

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
    JSONArray selectMenuByRoleId(List<Integer> roleId);

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
     * @param createValidate 参数
     */
    void add(SystemMenuCreateValidate createValidate);

    /**
     * 菜单编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(SystemMenuUpdateValidate updateValidate);

    /**
     * 菜单删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id);

}
