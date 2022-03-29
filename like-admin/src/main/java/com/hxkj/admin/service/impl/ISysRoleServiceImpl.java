package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.hxkj.admin.service.ISysRoleMenuService;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ISysRoleServiceImpl extends MPJBaseServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Resource
    ISysRoleMenuService iSysRoleMenuService;


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
        queryWrapper.select("id", "name")
                .eq("id", id)
                .last("limit 1");

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
           .orderByDesc(Arrays.asList("sort", "id"));

        IPage<SysRole> iPage = this.page(new Page<>(page, limit), queryWrapper);

        List<SysRoleListVo> roleVoArrayList = new ArrayList<>();
        for (SysRole sysRole : iPage.getRecords()) {
            SysRoleListVo vo = new SysRoleListVo();
            BeanUtils.copyProperties(sysRole, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(sysRole.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(sysRole.getUpdateTime()));
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
    @Transactional
    public void add(SysRoleParam sysRoleParam) {
        Assert.isNull(this.getOne(new QueryWrapper<SysRole>()
                .select("id,name")
                .eq("name", sysRoleParam.getName().trim())
                .last("limit 1")), "角色名称已存在!");

        SysRole model = new SysRole();
        model.setName(sysRoleParam.getName().trim());
        model.setRemark(sysRoleParam.getRemark());
        model.setIsDisable(sysRoleParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        this.save(model);

        iSysRoleMenuService.batchSaveByMenuIds(sysRoleParam.getId(), sysRoleParam.getMenuIds());
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param sysRoleParam 参数
     */
    @Override
    @Transactional
    public void edit(SysRoleParam sysRoleParam) {
        Assert.notNull(this.getOne(new QueryWrapper<SysRole>()
                .select("id,name")
                .eq("id", sysRoleParam.getId())
                .last("limit 1")), "角色已不存在!");

        Assert.isNull(this.getOne(new QueryWrapper<SysRole>()
                .select("id,name")
                .ne("id", sysRoleParam.getId())
                .eq("name", sysRoleParam.getName().trim())
                .last("limit 1")), "角色名称已存在!");

        SysRole model = new SysRole();
        model.setId(sysRoleParam.getId());
        model.setName(sysRoleParam.getName().trim());
        model.setRemark(sysRoleParam.getRemark());
        model.setIsDisable(sysRoleParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        this.updateById(model);

        iSysRoleMenuService.batchDeleteByRoleId(sysRoleParam.getId());
        iSysRoleMenuService.batchSaveByMenuIds(sysRoleParam.getId(), sysRoleParam.getMenuIds());
    }

    /**
     * 删除角色
     *
     * @author fzr
     * @param id 主键参数
     */
    @Override
    @Transactional
    public void del(Integer id) {
        Assert.notNull(
                this.getOne(new QueryWrapper<SysRole>()
                    .select("id,name")
                    .eq("id", id)
                    .last("limit 1")),
                "角色已不存在!");

        this.removeById(id);
        iSysRoleMenuService.batchDeleteByRoleId(id);
    }

}
