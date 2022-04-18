package com.hxkj.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.admin.config.AdminConfig;
import com.hxkj.admin.service.ISystemMenuService;
import com.hxkj.admin.service.ISystemRoleMenuService;
import com.hxkj.admin.validate.system.SystemMenuParam;
import com.hxkj.admin.vo.system.SystemMenuVo;
import com.hxkj.common.entity.system.SystemMenu;
import com.hxkj.common.mapper.system.SystemMenuMapper;
import com.hxkj.common.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {

    @Resource
    SystemMenuMapper systemMenuMapper;

    @Resource
    ISystemRoleMenuService iSystemRoleMenuService;

    /**
     * 根据角色ID获取菜单
     *
     * @author fzr
     * @param roleId 角色ID
     * @return JSONArray
     */
    @Override
    public JSONArray selectMenuByRoleId(Integer roleId) {
        Integer adminId = LikeAdminThreadLocal.getAdminId();
        List<Integer> menuIds = iSystemRoleMenuService.selectMenuIdsByRoleId(roleId);

        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("menu_type", Arrays.asList("M", "C"));
        queryWrapper.eq("is_show", 1);
        queryWrapper.orderByAsc(Arrays.asList("menu_sort", "id"));
        if (adminId != 1 && menuIds.size() > 0) {
            queryWrapper.in("id", menuIds);
        }

        List<SystemMenu> systemMenus = systemMenuMapper.selectList(queryWrapper);

        List<SystemMenuVo> lists = new ArrayList<>();
        for (SystemMenu systemMenu : systemMenus) {
            SystemMenuVo vo = new SystemMenuVo();
            BeanUtils.copyProperties(systemMenu, vo);

            vo.setUpdateTime(TimeUtil.timestampToDate(systemMenu.getUpdateTime()));
            vo.setCreateTime(TimeUtil.timestampToDate(systemMenu.getCreateTime()));
            lists.add(vo);
        }

        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(lists));
        return ArrayUtil.listToTree(jsonArray, "id", "pid", "children");
    }

    /**
     * 菜单列表
     *
     * @author fzr
     * @return JSONArray
     */
    @Override
    public JSONArray lists() {
        QueryWrapper<SystemMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc(Arrays.asList("menu_sort", "id"));

        List<SystemMenu> systemMenus = systemMenuMapper.selectList(queryWrapper);

        List<SystemMenuVo> lists = new ArrayList<>();
        for (SystemMenu systemMenu : systemMenus) {
            SystemMenuVo vo = new SystemMenuVo();
            BeanUtils.copyProperties(systemMenu, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(systemMenu.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(systemMenu.getUpdateTime()));
            lists.add(vo);
        }

        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(lists));
        return ArrayUtil.listToTree(jsonArray, "id", "pid", "children");
    }

    /**
     * 菜单详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysMenu
     */
    @Override
    public SystemMenuVo detail(Integer id) {
        SystemMenu systemMenu = systemMenuMapper.selectOne(new QueryWrapper<SystemMenu>().eq("id", id));
        Assert.notNull(systemMenu, "菜单已不存在!");

        SystemMenuVo vo  = new SystemMenuVo();
        BeanUtils.copyProperties(systemMenu, vo);
        vo.setCreateTime(TimeUtil.timestampToDate(systemMenu.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(systemMenu.getUpdateTime()));

        return vo;
    }

    /**
     * 新增菜单
     *
     * @author fzr
     * @param systemMenuParam 参数
     */
    @Override
    public void add(SystemMenuParam systemMenuParam) {
        SystemMenu model = new SystemMenu();
        model.setPid(systemMenuParam.getPid());
        model.setMenuType(systemMenuParam.getMenuType());
        model.setMenuName(systemMenuParam.getMenuName());
        model.setMenuIcon(systemMenuParam.getMenuIcon());
        model.setMenuSort(systemMenuParam.getMenuSort());
        model.setPerms(systemMenuParam.getPerms());
        model.setPaths(systemMenuParam.getPaths());
        model.setComponent(systemMenuParam.getComponent());
        model.setSelected(systemMenuParam.getSelected());
        model.setParams(systemMenuParam.getParams());
        model.setIsCache(systemMenuParam.getIsCache());
        model.setIsShow(systemMenuParam.getIsShow());
        model.setIsDisable(systemMenuParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemMenuMapper.insert(model);
    }

    /**
     * 编辑菜单
     *
     * @author fzr
     * @param systemMenuParam 菜单
     */
    @Override
    public void edit(SystemMenuParam systemMenuParam) {
        SystemMenu model = systemMenuMapper.selectOne(new QueryWrapper<SystemMenu>().eq("id", systemMenuParam.getId()));
        Assert.notNull(model, "菜单已不存在!");

        model.setMenuType(systemMenuParam.getMenuType());
        model.setMenuName(systemMenuParam.getMenuName());
        model.setMenuIcon(systemMenuParam.getMenuIcon());
        model.setMenuSort(systemMenuParam.getMenuSort());
        model.setPaths(systemMenuParam.getPaths());
        model.setPerms(systemMenuParam.getPerms());
        model.setComponent(systemMenuParam.getComponent());
        model.setPid(systemMenuParam.getPid());
        model.setSelected(systemMenuParam.getSelected());
        model.setParams(systemMenuParam.getParams());
        model.setIsCache(systemMenuParam.getIsCache());
        model.setIsShow(systemMenuParam.getIsShow());
        model.setIsDisable(systemMenuParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemMenuMapper.updateById(model);

        RedisUtil.del(AdminConfig.backstageRolesKey);
    }

    /**
     * 删除菜单
     *
     * @author fzr
     * @param id 主键参数
     */
    @Override
    public void del(Integer id) {
        SystemMenu model = systemMenuMapper.selectOne(new QueryWrapper<SystemMenu>().eq("id", id));
        Assert.notNull(model, "菜单已不存在!");

        systemMenuMapper.deleteById(id);

        iSystemRoleMenuService.batchDeleteByMenuId(id);
        RedisUtil.del(AdminConfig.backstageRolesKey);
    }

}
