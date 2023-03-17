package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.ISystemAuthPermService;
import com.mdd.admin.service.ISystemAuthRoleService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemRoleCreateValidate;
import com.mdd.admin.validate.system.SystemRoleUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthRoleVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.system.SystemAuthAdmin;
import com.mdd.common.entity.system.SystemAuthRole;
import com.mdd.common.mapper.system.SystemAuthAdminMapper;
import com.mdd.common.mapper.system.SystemAuthRoleMapper;
import com.mdd.common.util.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

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
     * 角色所有
     *
     * @author fzr
     * @return List<SystemAuthRoleVo>
     */
    @Override
    public List<SystemAuthRoleVo> all() {
        QueryWrapper<SystemAuthRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id,name,sort,is_disable,create_time,update_time");
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));
        List<SystemAuthRole> systemAuthRoles = systemAuthRoleMapper.selectList(queryWrapper);

        List<SystemAuthRoleVo> list = new ArrayList<>();
        for (SystemAuthRole systemAuthRole : systemAuthRoles) {
            SystemAuthRoleVo vo = new SystemAuthRoleVo();

            vo.setId(systemAuthRole.getId());
            vo.setName(systemAuthRole.getName());
            vo.setSort(systemAuthRole.getSort());
            vo.setIsDisable(systemAuthRole.getIsDisable());
            vo.setCreateTime(TimeUtils.timestampToDate(systemAuthRole.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(systemAuthRole.getUpdateTime()));
            vo.setMember(0);
            vo.setRemark("");
            vo.setMenus(Collections.EMPTY_LIST);
            list.add(vo);
        }

        return list;
    }

    /**
     * 角色列表
     *
     * @author fzr
     * @param pageValidate 参数
     * @return PageResult<SysRoleListVo>
     */
    @Override
    public PageResult<SystemAuthRoleVo> list(@Validated PageValidate pageValidate) {
        Integer page  = pageValidate.getPageNo();
        Integer limit = pageValidate.getPageSize();

        QueryWrapper<SystemAuthRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc(Arrays.asList("sort", "id"));

        IPage<SystemAuthRole> iPage = systemAuthRoleMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SystemAuthRoleVo> list = new ArrayList<>();
        for (SystemAuthRole systemAuthRole : iPage.getRecords()) {
            SystemAuthRoleVo vo = new SystemAuthRoleVo();
            BeanUtils.copyProperties(systemAuthRole, vo);

            List<Integer> ids = systemAuthAdminMapper.selectChildrenById(systemAuthRole.getId());
            Integer member = ids.size();

            vo.setMenus(new ArrayList<>());
            vo.setMember(member);
            vo.setCreateTime(TimeUtils.timestampToDate(systemAuthRole.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(systemAuthRole.getUpdateTime()));
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
    public SystemAuthRoleVo detail(Integer id) {
        SystemAuthRole systemAuthRole = systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .eq("id", id)
                .last("limit 1"));

        Assert.notNull(systemAuthRole, "角色已不存在!");

        List<Integer> roleIds = new LinkedList<>();
        roleIds.add(systemAuthRole.getId());

        SystemAuthRoleVo vo = new SystemAuthRoleVo();
        BeanUtils.copyProperties(systemAuthRole, vo);
        vo.setMember(0);
        vo.setMenus(iSystemAuthPermService.selectMenuIdsByRoleId(roleIds));
        vo.setCreateTime(TimeUtils.timestampToDate(systemAuthRole.getCreateTime()));
        vo.setUpdateTime(TimeUtils.timestampToDate(systemAuthRole.getUpdateTime()));

        return vo;
    }

    /**
     * 新增角色
     *
     * @author fzr
     * @param createValidate 参数
     */
    @Override
    @Transactional
    public void add(SystemRoleCreateValidate createValidate) {
        Assert.isNull(systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .select("id,name")
                .eq("name", createValidate.getName().trim())
                .last("limit 1")), "角色名称已存在!");

        SystemAuthRole model = new SystemAuthRole();
        model.setName(createValidate.getName().trim());
        model.setRemark(createValidate.getRemark());
        model.setSort(createValidate.getSort());
        model.setIsDisable(createValidate.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthRoleMapper.insert(model);
        iSystemAuthPermService.batchSaveByMenuIds(model.getId(), createValidate.getMenuIds());
    }

    /**
     * 编辑角色
     *
     * @author fzr
     * @param updateValidate 参数
     */
    @Override
    @Transactional
    public void edit(SystemRoleUpdateValidate updateValidate) {
        Assert.notNull(systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .select("id,name")
                .eq("id", updateValidate.getId())
                .last("limit 1")), "角色已不存在!");

        Assert.isNull(systemAuthRoleMapper.selectOne(new QueryWrapper<SystemAuthRole>()
                .select("id,name")
                .ne("id", updateValidate.getId())
                .eq("name", updateValidate.getName().trim())
                .last("limit 1")), "角色名称已存在!");

        SystemAuthRole model = new SystemAuthRole();
        model.setId(updateValidate.getId());
        model.setName(updateValidate.getName().trim());
        model.setRemark(updateValidate.getRemark());
        model.setSort(updateValidate.getSort());
        model.setIsDisable(updateValidate.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthRoleMapper.updateById(model);

        iSystemAuthPermService.batchDeleteByRoleId(updateValidate.getId());
        iSystemAuthPermService.batchSaveByMenuIds(updateValidate.getId(), updateValidate.getMenuIds());
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
    }

}
