package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxkj.admin.config.AdminConfig;
import com.hxkj.admin.service.ISystemRoleMenuService;
import com.hxkj.admin.service.ISystemRoleService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.system.SystemRoleParam;
import com.hxkj.admin.vo.system.SystemRoleVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SystemAdmin;
import com.hxkj.common.entity.system.SystemRole;
import com.hxkj.common.mapper.system.SystemAdminMapper;
import com.hxkj.common.mapper.system.SystemRoleMapper;
import com.hxkj.common.utils.RedisUtil;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统角色服务实现类
 */
@Service
public class SystemRoleServiceImpl implements ISystemRoleService {

    @Resource
    SystemAdminMapper systemAdminMapper;

    @Resource
    SystemRoleMapper systemRoleMapper;

    @Resource
    ISystemRoleMenuService iSystemRoleMenuService;

    /**
     * 根据ID获取角色名称
     *
     * @author fzr
     * @param id 角色ID
     * @return String
     */
    @Override
    public String getRoleNameById(Integer id) {
        QueryWrapper<SystemRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "name")
                .eq("id", id)
                .last("limit 1");

        SystemRole systemRole = systemRoleMapper.selectOne(queryWrapper);
        if (systemRole == null) {
            return "";
        }
        return systemRole.getName();
    }

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 参数
     * @return PageResult<SysRoleListVo>
     */
    @Override
    public PageResult<SystemRoleVo> lists(@Validated PageParam pageParam) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SystemRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        IPage<SystemRole> iPage = systemRoleMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SystemRoleVo> roleVoArrayList = new ArrayList<>();
        for (SystemRole systemRole : iPage.getRecords()) {
            SystemRoleVo vo = new SystemRoleVo();
            BeanUtils.copyProperties(systemRole, vo);

            vo.setMenus(new ArrayList<>());
            vo.setCreateTime(TimeUtil.timestampToDate(systemRole.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(systemRole.getUpdateTime()));
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
    public SystemRoleVo detail(Integer id) {
        SystemRole systemRole = systemRoleMapper.selectOne(new QueryWrapper<SystemRole>()
                .eq("id", id)
                .last("limit 1"));

        Assert.notNull(systemRole, "角色已不存在!");

        SystemRoleVo vo = new SystemRoleVo();
        BeanUtils.copyProperties(systemRole, vo);

        vo.setMenus(iSystemRoleMenuService.selectMenuIdsByRoleId(systemRole.getId()));
        vo.setCreateTime(TimeUtil.timestampToDate(systemRole.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(systemRole.getUpdateTime()));

        return vo;
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param systemRoleParam 参数
     */
    @Override
    @Transactional
    public void add(SystemRoleParam systemRoleParam) {
        Assert.isNull(systemRoleMapper.selectOne(new QueryWrapper<SystemRole>()
                .select("id,name")
                .eq("name", systemRoleParam.getName().trim())
                .last("limit 1")), "角色名称已存在!");

        SystemRole model = new SystemRole();
        model.setName(systemRoleParam.getName().trim());
        model.setRemark(systemRoleParam.getRemark());
        model.setIsDisable(systemRoleParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemRoleMapper.insert(model);

        iSystemRoleMenuService.batchSaveByMenuIds(model.getId(), systemRoleParam.getMenuIds());
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param systemRoleParam 参数
     */
    @Override
    @Transactional
    public void edit(SystemRoleParam systemRoleParam) {
        Assert.notNull(systemRoleMapper.selectOne(new QueryWrapper<SystemRole>()
                .select("id,name")
                .eq("id", systemRoleParam.getId())
                .last("limit 1")), "角色已不存在!");

        Assert.isNull(systemRoleMapper.selectOne(new QueryWrapper<SystemRole>()
                .select("id,name")
                .ne("id", systemRoleParam.getId())
                .eq("name", systemRoleParam.getName().trim())
                .last("limit 1")), "角色名称已存在!");

        SystemRole model = new SystemRole();
        model.setId(systemRoleParam.getId());
        model.setName(systemRoleParam.getName().trim());
        model.setRemark(systemRoleParam.getRemark());
        model.setSort(systemRoleParam.getSort());
        model.setIsDisable(systemRoleParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemRoleMapper.updateById(model);

        iSystemRoleMenuService.batchDeleteByRoleId(systemRoleParam.getId());
        iSystemRoleMenuService.batchSaveByMenuIds(systemRoleParam.getId(), systemRoleParam.getMenuIds());
        iSystemRoleMenuService.cacheRoleMenusByRoleId(systemRoleParam.getId());
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
                systemRoleMapper.selectOne(new QueryWrapper<SystemRole>()
                    .select("id", "name")
                    .eq("id", id)
                    .last("limit 1")),
                "角色已不存在!");

        Assert.isNull(systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select("id", "role", "nickname")
                .eq("role", id)
                .eq("is_delete", 0)),
                "角色已被管理员使用,请先移除");

        systemRoleMapper.deleteById(id);
        iSystemRoleMenuService.batchDeleteByRoleId(id);
        RedisUtil.hDel(AdminConfig.backstageRolesKey, String.valueOf(id));
    }

}
