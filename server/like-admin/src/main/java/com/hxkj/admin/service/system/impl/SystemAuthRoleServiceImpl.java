package com.hxkj.admin.service.system.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxkj.admin.config.AdminConfig;
import com.hxkj.admin.service.system.ISystemAuthPermService;
import com.hxkj.admin.service.system.ISystemAuthRoleService;
import com.hxkj.admin.validate.common.PageParam;
import com.hxkj.admin.validate.system.SystemAuthRoleParam;
import com.hxkj.admin.vo.system.SystemRoleVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SystemAuthAdmin;
import com.hxkj.common.entity.system.SystemAuthRole;
import com.hxkj.common.mapper.system.SystemAuthAdminMapper;
import com.hxkj.common.mapper.system.SystemAuthRoleMapper;
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
public class SystemAuthRoleServiceImpl implements ISystemAuthRoleService {

    @Resource
    SystemAuthAdminMapper systemAuthAdminMapper;

    @Resource
    SystemAuthRoleMapper systemAuthRoleMapper;

    @Resource
    ISystemAuthPermService iSystemAuthPermService;

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageParam 参数
     * @return PageResult<SysRoleListVo>
     */
    @Override
    public PageResult<SystemRoleVo> list(@Validated PageParam pageParam) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SystemAuthRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        IPage<SystemAuthRole> iPage = systemAuthRoleMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SystemRoleVo> list = new ArrayList<>();
        for (SystemAuthRole systemAuthRole : iPage.getRecords()) {
            SystemRoleVo vo = new SystemRoleVo();
            BeanUtils.copyProperties(systemAuthRole, vo);

            Integer member = systemAuthAdminMapper.selectCount(new QueryWrapper<SystemAuthAdmin>()
                    .eq("is_delete", 0)
                    .eq("role", systemAuthRole.getId()));

            vo.setMenus(new ArrayList<>());
            vo.setMember(member);
            vo.setCreateTime(TimeUtil.timestampToDate(systemAuthRole.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(systemAuthRole.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
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
        SystemAuthRole systemAuthRole = systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .eq("id", id)
                .last("limit 1"));

        Assert.notNull(systemAuthRole, "角色已不存在!");

        Integer member = systemAuthAdminMapper.selectCount(new QueryWrapper<SystemAuthAdmin>()
                .eq("is_delete", 0)
                .eq("role", systemAuthRole.getId()));

        SystemRoleVo vo = new SystemRoleVo();
        BeanUtils.copyProperties(systemAuthRole, vo);
        vo.setMember(member);
        vo.setMenus(iSystemAuthPermService.selectMenuIdsByRoleId(systemAuthRole.getId()));
        vo.setCreateTime(TimeUtil.timestampToDate(systemAuthRole.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(systemAuthRole.getUpdateTime()));

        return vo;
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param systemAuthRoleParam 参数
     */
    @Override
    @Transactional
    public void add(SystemAuthRoleParam systemAuthRoleParam) {
        Assert.isNull(systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .select("id,name")
                .eq("name", systemAuthRoleParam.getName().trim())
                .last("limit 1")), "角色名称已存在!");

        SystemAuthRole model = new SystemAuthRole();
        model.setName(systemAuthRoleParam.getName().trim());
        model.setRemark(systemAuthRoleParam.getRemark());
        model.setIsDisable(systemAuthRoleParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthRoleMapper.insert(model);
        System.out.println(model.getId());
        System.out.println(systemAuthRoleParam.getMenuIds());
        iSystemAuthPermService.batchSaveByMenuIds(model.getId(), systemAuthRoleParam.getMenuIds());
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param systemAuthRoleParam 参数
     */
    @Override
    @Transactional
    public void edit(SystemAuthRoleParam systemAuthRoleParam) {
        Assert.notNull(systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .select("id,name")
                .eq("id", systemAuthRoleParam.getId())
                .last("limit 1")), "角色已不存在!");

        Assert.isNull(systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .select("id,name")
                .ne("id", systemAuthRoleParam.getId())
                .eq("name", systemAuthRoleParam.getName().trim())
                .last("limit 1")), "角色名称已存在!");

        SystemAuthRole model = new SystemAuthRole();
        model.setId(systemAuthRoleParam.getId());
        model.setName(systemAuthRoleParam.getName().trim());
        model.setRemark(systemAuthRoleParam.getRemark());
        model.setSort(systemAuthRoleParam.getSort());
        model.setIsDisable(systemAuthRoleParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthRoleMapper.updateById(model);

        iSystemAuthPermService.batchDeleteByRoleId(systemAuthRoleParam.getId());
        iSystemAuthPermService.batchSaveByMenuIds(systemAuthRoleParam.getId(), systemAuthRoleParam.getMenuIds());
        iSystemAuthPermService.cacheRoleMenusByRoleId(systemAuthRoleParam.getId());
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
                systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                    .select("id", "name")
                    .eq("id", id)
                    .last("limit 1")),
                "角色已不存在!");

        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select("id", "role", "nickname")
                .eq("role", id)
                .eq("is_delete", 0)),
                "角色已被管理员使用,请先移除");

        systemAuthRoleMapper.deleteById(id);
        iSystemAuthPermService.batchDeleteByRoleId(id);
        RedisUtil.hDel(AdminConfig.backstageRolesKey, String.valueOf(id));
    }

}
