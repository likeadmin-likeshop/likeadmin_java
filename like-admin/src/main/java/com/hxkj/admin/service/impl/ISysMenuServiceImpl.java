package com.hxkj.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.hxkj.admin.config.SystemConfig;
import com.hxkj.admin.service.ISysMenuService;
import com.hxkj.admin.validate.SysMenuParam;
import com.hxkj.admin.vo.system.SysMenuVo;
import com.hxkj.common.entity.system.SysMenu;
import com.hxkj.common.mapper.system.SysMenuMapper;
import com.hxkj.common.utils.ArrayUtil;
import com.hxkj.common.utils.RedisUtil;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ISysMenuServiceImpl extends MPJBaseServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    /**
     * 菜单列表
     *
     * @author fzr
     * @return JSONArray
     */
    @Override
    public JSONArray lists() {
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("menu_sort", "id"));

        List<SysMenu> sysMenus = this.list( queryWrapper);

        List<SysMenuVo> lists = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            SysMenuVo vo = new SysMenuVo();
            BeanUtils.copyProperties(sysMenu, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(sysMenu.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(sysMenu.getUpdateTime()));
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
    public SysMenuVo detail(Integer id) {
        SysMenu sysMenu = this.getOne(new QueryWrapper<SysMenu>().eq("id", id));
        Assert.notNull(sysMenu, "菜单已不存在!");

        SysMenuVo vo  = new SysMenuVo();
        BeanUtils.copyProperties(sysMenu, vo);
        vo.setCreateTime(TimeUtil.timestampToDate(sysMenu.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(sysMenu.getUpdateTime()));

        return vo;
    }

    /**
     * 新增菜单
     *
     * @author fzr
     * @param sysMenuParam 参数
     */
    @Override
    public void add(SysMenuParam sysMenuParam) {
        SysMenu model = new SysMenu();
        model.setPid(sysMenuParam.getPid());
        model.setMenuType(sysMenuParam.getMenuType());
        model.setMenuName(sysMenuParam.getMenuName());
        model.setMenuIcon(sysMenuParam.getMenuIcon());
        model.setMenuSort(sysMenuParam.getMenuSort());
        model.setPerms(sysMenuParam.getPerms());
        model.setIsDisable(sysMenuParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        this.save(model);
    }

    /**
     * 编辑菜单
     *
     * @author fzr
     * @param sysMenuParam 菜单
     */
    @Override
    public void edit(SysMenuParam sysMenuParam) {
        SysMenu model = this.getOne(new QueryWrapper<SysMenu>().eq("id", sysMenuParam.getId()));
        Assert.notNull(model, "菜单已不存在!");

        model.setMenuType(sysMenuParam.getMenuType());
        model.setMenuName(sysMenuParam.getMenuName());
        model.setMenuIcon(sysMenuParam.getMenuIcon());
        model.setMenuSort(sysMenuParam.getMenuSort());
        model.setPerms(sysMenuParam.getPerms());
        model.setPid(sysMenuParam.getPid());
        model.setIsDisable(sysMenuParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        this.updateById(model);

        RedisUtil.del(SystemConfig.backstageRolesKey);
    }

    /**
     * 删除菜单
     *
     * @author fzr
     * @param id 主键参数
     */
    @Override
    public void del(Integer id) {
        SysMenu model = this.getOne(new QueryWrapper<SysMenu>().eq("id", id));
        Assert.notNull(model, "菜单已不存在!");

        this.removeById(id);

        RedisUtil.del(SystemConfig.backstageRolesKey);
    }

}
