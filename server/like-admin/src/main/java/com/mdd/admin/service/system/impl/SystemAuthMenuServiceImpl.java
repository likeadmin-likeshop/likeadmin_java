package com.mdd.admin.service.system.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.config.AdminConfig;
import com.mdd.admin.service.system.ISystemAuthMenuService;
import com.mdd.admin.service.system.ISystemAuthPermService;
import com.mdd.admin.validate.system.SystemAuthMenuParam;
import com.mdd.admin.vo.system.SystemAuthMenuVo;
import com.mdd.common.entity.system.SystemAuthMenu;
import com.mdd.common.mapper.system.SystemAuthMenuMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.RedisUtil;
import com.mdd.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统菜单服务实现类
 */
@Service
public class SystemAuthMenuServiceImpl implements ISystemAuthMenuService {

    @Resource
    SystemAuthMenuMapper systemAuthMenuMapper;

    @Resource
    ISystemAuthPermService iSystemAuthPermService;

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
        List<Integer> menuIds = iSystemAuthPermService.selectMenuIdsByRoleId(roleId);

        QueryWrapper<SystemAuthMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("menu_type", Arrays.asList("M", "C"));
        queryWrapper.eq("is_disable", 0);
        queryWrapper.orderByDesc("menu_sort");
        queryWrapper.orderByAsc("id");
        if (adminId != 1 && menuIds.size() > 0) {
            queryWrapper.in("id", menuIds);
        }

        List<SystemAuthMenu> systemAuthMenus = systemAuthMenuMapper.selectList(queryWrapper);

        List<SystemAuthMenuVo> lists = new ArrayList<>();
        for (SystemAuthMenu systemAuthMenu : systemAuthMenus) {
            SystemAuthMenuVo vo = new SystemAuthMenuVo();
            BeanUtils.copyProperties(systemAuthMenu, vo);

            vo.setUpdateTime(TimeUtil.timestampToDate(systemAuthMenu.getUpdateTime()));
            vo.setCreateTime(TimeUtil.timestampToDate(systemAuthMenu.getCreateTime()));
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
    public JSONArray list() {
        QueryWrapper<SystemAuthMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("menu_sort");
        queryWrapper.orderByAsc("id");

        List<SystemAuthMenu> systemAuthMenus = systemAuthMenuMapper.selectList(queryWrapper);

        List<SystemAuthMenuVo> lists = new ArrayList<>();
        for (SystemAuthMenu systemAuthMenu : systemAuthMenus) {
            SystemAuthMenuVo vo = new SystemAuthMenuVo();
            BeanUtils.copyProperties(systemAuthMenu, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(systemAuthMenu.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(systemAuthMenu.getUpdateTime()));
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
    public SystemAuthMenuVo detail(Integer id) {
        SystemAuthMenu systemAuthMenu = systemAuthMenuMapper.selectOne(new QueryWrapper<SystemAuthMenu>().eq("id", id));
        Assert.notNull(systemAuthMenu, "菜单已不存在!");

        SystemAuthMenuVo vo  = new SystemAuthMenuVo();
        BeanUtils.copyProperties(systemAuthMenu, vo);
        vo.setCreateTime(TimeUtil.timestampToDate(systemAuthMenu.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(systemAuthMenu.getUpdateTime()));

        return vo;
    }

    /**
     * 新增菜单
     *
     * @author fzr
     * @param systemAuthMenuParam 参数
     */
    @Override
    public void add(SystemAuthMenuParam systemAuthMenuParam) {
        SystemAuthMenu model = new SystemAuthMenu();
        model.setPid(systemAuthMenuParam.getPid());
        model.setMenuType(systemAuthMenuParam.getMenuType());
        model.setMenuName(systemAuthMenuParam.getMenuName());
        model.setMenuIcon(systemAuthMenuParam.getMenuIcon());
        model.setMenuSort(systemAuthMenuParam.getMenuSort());
        model.setPerms(systemAuthMenuParam.getPerms());
        model.setPaths(systemAuthMenuParam.getPaths());
        model.setComponent(systemAuthMenuParam.getComponent());
        model.setSelected(systemAuthMenuParam.getSelected());
        model.setParams(systemAuthMenuParam.getParams());
        model.setIsCache(systemAuthMenuParam.getIsCache());
        model.setIsShow(systemAuthMenuParam.getIsShow());
        model.setIsDisable(systemAuthMenuParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthMenuMapper.insert(model);

        RedisUtil.del(AdminConfig.backstageRolesKey);
    }

    /**
     * 编辑菜单
     *
     * @author fzr
     * @param systemAuthMenuParam 菜单
     */
    @Override
    public void edit(SystemAuthMenuParam systemAuthMenuParam) {
        SystemAuthMenu model = systemAuthMenuMapper.selectOne(new QueryWrapper<SystemAuthMenu>().eq("id", systemAuthMenuParam.getId()));
        Assert.notNull(model, "菜单已不存在!");

        model.setMenuType(systemAuthMenuParam.getMenuType());
        model.setMenuName(systemAuthMenuParam.getMenuName());
        model.setMenuIcon(systemAuthMenuParam.getMenuIcon());
        model.setMenuSort(systemAuthMenuParam.getMenuSort());
        model.setPaths(systemAuthMenuParam.getPaths());
        model.setPerms(systemAuthMenuParam.getPerms());
        model.setComponent(systemAuthMenuParam.getComponent());
        model.setPid(systemAuthMenuParam.getPid());
        model.setSelected(systemAuthMenuParam.getSelected());
        model.setParams(systemAuthMenuParam.getParams());
        model.setIsCache(systemAuthMenuParam.getIsCache());
        model.setIsShow(systemAuthMenuParam.getIsShow());
        model.setIsDisable(systemAuthMenuParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthMenuMapper.updateById(model);

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
        SystemAuthMenu model = systemAuthMenuMapper.selectOne(
                new QueryWrapper<SystemAuthMenu>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(model, "菜单已不存在!");

        Assert.isNull(systemAuthMenuMapper.selectOne(
                new QueryWrapper<SystemAuthMenu>()
                        .eq("pid", id)
                        .last("limit 1")),
                "请先删除子菜单再操作！");

        systemAuthMenuMapper.deleteById(id);
        iSystemAuthPermService.batchDeleteByMenuId(id);
        RedisUtil.del(AdminConfig.backstageRolesKey);
    }

}
