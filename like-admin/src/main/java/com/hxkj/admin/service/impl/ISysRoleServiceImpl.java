package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.hxkj.admin.service.ISysRoleService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.SysRoleParam;
import com.hxkj.admin.vo.system.SysRoleListVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SysRole;
import com.hxkj.common.mapper.system.SysRoleMapper;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ISysRoleServiceImpl extends MPJBaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    /**
     * 根据ID获取角色名称
     *
     * @author fzr
     * @param id 角色ID
     * @return String
     */
    @Override
    public String getRoleNameById(Integer id) {
        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name");
        queryWrapper.eq("id", id);
        queryWrapper.eq("is_delete", 0);
        queryWrapper.last("limit 1");

        SysRole sysRole = this.getOne(queryWrapper, false);
        if (sysRole == null) {
            return "";
        }
        return sysRole.getName();
    }

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 参数
     * @return PageResult<SysRoleListVo>
     */
    @Override
    public PageResult<SysRoleListVo> lists(@Validated PageParam pageParam) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SysRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysRole.class, info->
                !info.getColumn().equals("is_delete") &&
                !info.getColumn().equals("delete_time"))
           .eq("is_delete", 0)
           .orderByDesc(Arrays.asList("sort", "id"));

        IPage<SysRole> iPage = this.page(new Page<>(page, limit), queryWrapper);

        List<SysRoleListVo> roleVoArrayList = new ArrayList<>();
        for (SysRole sysRole : iPage.getRecords()) {
            SysRoleListVo vo = new SysRoleListVo();
            BeanUtils.copyProperties(sysRole, vo);

            vo.setCreateTime(TimeUtil.timeToDate(sysRole.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timeToDate(sysRole.getUpdateTime()));
            roleVoArrayList.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), roleVoArrayList);
    }

    /**
     * 角色详情
     *
     * @author fzr
     * @param id 主键参数
     * @return SysRole
     */
    @Override
    public SysRole detail(Integer id) {
        SysRole sysRole = this.getOne(new QueryWrapper<SysRole>()
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(sysRole, "角色已不存在!");

        return sysRole;
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param sysRoleParam 参数
     */
    @Override
    public void add(SysRoleParam sysRoleParam) {
        Assert.isNull(this.getOne(new QueryWrapper<SysRole>()
                .select("id,name")
                .eq("name", sysRoleParam.getName().trim())
                .eq("is_delete", 0)
                .last("limit 1")), "角色名称已存在!");

        SysRole model = new SysRole();
        model.setName(sysRoleParam.getName().trim());
        model.setRemark(sysRoleParam.getRemark());
        model.setMenuIds(sysRoleParam.getMenuIds());
        model.setIsDisable(sysRoleParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        this.save(model);
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param sysRoleParam 参数
     */
    @Override
    public void edit(SysRoleParam sysRoleParam) {
        Assert.notNull(this.getOne(new QueryWrapper<SysRole>()
                .select("id,name")
                .eq("id", sysRoleParam.getId())
                .eq("is_delete", 0)
                .last("limit 1")), "角色已不存在!");

        Assert.isNull(this.getOne(new QueryWrapper<SysRole>()
                .select("id,name")
                .ne("id", sysRoleParam.getId())
                .eq("name", sysRoleParam.getName().trim())
                .eq("is_delete", 0)
                .last("limit 1")), "角色名称已存在!");

        SysRole model = new SysRole();
        model.setId(sysRoleParam.getId());
        model.setName(sysRoleParam.getName().trim());
        model.setRemark(sysRoleParam.getRemark());
        model.setMenuIds(sysRoleParam.getMenuIds());
        model.setIsDisable(sysRoleParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        this.updateById(model);
    }

    /**
     * 删除角色
     *
     * @author fzr
     * @param id 主键参数
     */
    @Override
    public void del(Integer id) {
        SysRole sysRole = this.getOne(new QueryWrapper<SysRole>()
            .select("id,name")
            .eq("id", id)
            .eq("is_delete", 0)
            .last("limit 1"));

        Assert.notNull(sysRole, "角色已不存在!");

        sysRole.setId(id);
        sysRole.setIsDelete(true);
        sysRole.setDeleteTime(System.currentTimeMillis() / 1000);
        this.updateById(sysRole);
    }

}
