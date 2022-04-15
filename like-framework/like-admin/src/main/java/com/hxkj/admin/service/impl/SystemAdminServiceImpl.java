package com.hxkj.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxkj.admin.LikeAdminThreadLocal;
import com.hxkj.admin.config.AdminConfig;
import com.hxkj.admin.service.ISystemAdminService;
import com.hxkj.admin.service.ISystemRoleMenuService;
import com.hxkj.admin.service.ISystemRoleService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.system.SystemAdminParam;
import com.hxkj.admin.vo.system.SystemAdminVo;
import com.hxkj.admin.vo.system.SystemSelfVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SystemAdmin;
import com.hxkj.common.entity.system.SystemMenu;
import com.hxkj.common.entity.system.SystemRoleMenu;
import com.hxkj.common.mapper.system.SystemAdminMapper;
import com.hxkj.common.mapper.system.SystemMenuMapper;
import com.hxkj.common.mapper.system.SystemRoleMenuMapper;
import com.hxkj.common.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 系统管理员实现类
 */
@Service
public class SystemAdminServiceImpl implements ISystemAdminService {

    @Resource
    SystemAdminMapper systemAdminMapper;

    @Resource
    SystemMenuMapper systemMenuMapper;

    @Resource
    ISystemRoleService iSystemRoleService;

    @Resource
    ISystemRoleMenuService iSystemRoleMenuService;

    /**
     * 根据账号查找管理员
     *
     * @author fzr
     * @param username 主键ID
     * @return SysAdmin
     */
    @Override
    public SystemAdmin findByUsername(String username) {
        return systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .eq("username", username)
                .last("limit 1"));
    }

    /**
     * 管理员列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return PageResult<SysAdminListVo>
     */
    @Override
    public PageResult<SystemAdminVo> lists(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SystemAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SystemAdmin.class, info->
                !info.getColumn().equals("salt") &&
                !info.getColumn().equals("password") &&
                !info.getColumn().equals("is_delete") &&
                !info.getColumn().equals("delete_time"))
        .eq("is_delete", 0)
        .orderByDesc("sort");

        systemAdminMapper.setSearch(queryWrapper, params, new String[]{
                "like:username:str",
                "like:nickname:str",
                "=:role:int"
        });

        IPage<SystemAdmin> iPage = systemAdminMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SystemAdminVo> adminVoArrayList = new ArrayList<>();
        for (SystemAdmin sysAdmin : iPage.getRecords()) {
            SystemAdminVo vo = new SystemAdminVo();
            BeanUtils.copyProperties(sysAdmin, vo);

            if (sysAdmin.getId() == 1) {
                vo.setRole("超级管理员");
            } else {
                vo.setRole(iSystemRoleService.getRoleNameById(sysAdmin.getRole()));
            }

            vo.setAvatar(UrlUtil.toAbsoluteUrl(sysAdmin.getAvatar()));
            vo.setCreateTime(TimeUtil.timestampToDate(sysAdmin.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(sysAdmin.getUpdateTime()));
            vo.setLastLoginTime(TimeUtil.timestampToDate(sysAdmin.getLastLoginTime()));
            adminVoArrayList.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), adminVoArrayList);
    }

    /**
     * 当前管理员
     *
     * @author fzr
     * @return SystemSelfVo
     */
    @Override
    public SystemSelfVo self(Integer adminId) {
        // 管理员信息
        SystemAdmin sysAdmin = systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(SystemAdmin.class, info->
                        !info.getColumn().equals("salt") &&
                                !info.getColumn().equals("password") &&
                                !info.getColumn().equals("is_delete") &&
                                !info.getColumn().equals("delete_time"))
                .eq("is_delete", 0)
                .eq("id", adminId)
                .last("limit 1"));

        SystemAdminVo systemAdminVo = new SystemAdminVo();
        BeanUtils.copyProperties(sysAdmin, systemAdminVo);
        systemAdminVo.setRole(String.valueOf(sysAdmin.getRole()));
        systemAdminVo.setAvatar(UrlUtil.toAbsoluteUrl(sysAdmin.getAvatar()));
        systemAdminVo.setUpdateTime(TimeUtil.timestampToDate(sysAdmin.getUpdateTime()));
        systemAdminVo.setCreateTime(TimeUtil.timestampToDate(sysAdmin.getCreateTime()));
        systemAdminVo.setLastLoginTime(TimeUtil.timestampToDate(sysAdmin.getLastLoginTime()));

        // 角色权限
        List<String> auths = new LinkedList<>();
        if (adminId > 1) {
            List<Integer> menuIds = iSystemRoleMenuService.selectMenuIdsByRoleId(sysAdmin.getRole());
            List<SystemMenu> systemMenus = systemMenuMapper.selectList(new QueryWrapper<SystemMenu>()
                    .eq("is_disable", 0)
                    .in("id", menuIds)
                    .in("menu_type", Arrays.asList("C", "A"))
                    .orderByAsc(Arrays.asList("menu_sort", "id")));

            // 处理权限
            for (SystemMenu item : systemMenus) {
                if (StringUtil.isNotNull(item.getPerms()) && StringUtil.isNotEmpty(item.getPerms())) {
                    auths.add(item.getPerms().trim());
                }
            }
        } else {
            auths.add("*");
        }

        // 返回数据
        SystemSelfVo vo = new SystemSelfVo();
        vo.setUser(systemAdminVo);
        vo.setPermissions(auths);
        return vo;
    }

    /**
     * 管理员详细
     *
     * @author fzr
     * @param id 主键
     * @return SysAdmin
     */
    @Override
    public SystemAdminVo detail(Integer id) {
        SystemAdmin sysAdmin = systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(SystemAdmin.class, info->
                        !info.getColumn().equals("salt") &&
                        !info.getColumn().equals("password") &&
                        !info.getColumn().equals("is_delete") &&
                        !info.getColumn().equals("delete_time"))
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(sysAdmin, "账号已不存在！");

        SystemAdminVo vo = new SystemAdminVo();
        BeanUtils.copyProperties(sysAdmin, vo);

        vo.setRole(String.valueOf(sysAdmin.getRole()));
        vo.setAvatar(UrlUtil.toAbsoluteUrl(sysAdmin.getAvatar()));
        vo.setCreateTime(TimeUtil.timestampToDate(sysAdmin.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(sysAdmin.getUpdateTime()));
        vo.setLastLoginTime(TimeUtil.timestampToDate(sysAdmin.getLastLoginTime()));

        return vo;
    }

    /**
     * 管理员新增
     *
     * @author fzr
     * @param systemAdminParam 参数
     */
    @Override
    public void add(SystemAdminParam systemAdminParam) {
        String[] field = {"id", "username", "nickname"};
        Assert.isNull(systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("username", systemAdminParam.getUsername())
                .last("limit 1")), "账号已存在换一个吧！");

        Assert.isNull(systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("nickname", systemAdminParam.getNickname())
                .last("limit 1")), "昵称已存在换一个吧！");

        Assert.notNull(iSystemRoleService.detail(systemAdminParam.getRole()), "角色不存在!");

        String salt   = ToolsUtil.randomString(5);
        String pwd    = ToolsUtil.makeMd5(systemAdminParam.getPassword().trim() + salt);
        String avatar = UrlUtil.toRelativeUrl(systemAdminParam.getAvatar());

        SystemAdmin model = new SystemAdmin();
        model.setUsername(systemAdminParam.getUsername());
        model.setNickname(systemAdminParam.getNickname());
        model.setRole(systemAdminParam.getRole());
        model.setAvatar(avatar);
        model.setPassword(pwd);
        model.setSalt(salt);
        model.setSort(systemAdminParam.getSort());
        model.setIsMultipoint(systemAdminParam.getIsMultipoint());
        model.setIsDisable(systemAdminParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAdminMapper.insert(model);
    }

    /**
     * 管理员更新
     *
     * @author fzr
     * @param systemAdminParam 参数
     */
    @Override
    public void edit(SystemAdminParam systemAdminParam) {
        String[] field = {"id", "username", "nickname"};
        Assert.notNull(systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(field)
                .eq("id", systemAdminParam.getId())
                .eq("is_delete", 0)
                .last("limit 1")), "账号不存在了!");

        Assert.isNull(systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("username", systemAdminParam.getUsername())
                .ne("id", systemAdminParam.getId())
                .last("limit 1")), "账号已存在换一个吧！");

        Assert.isNull(systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("nickname", systemAdminParam.getNickname())
                .ne("id", systemAdminParam.getId())
                .last("limit 1")), "昵称已存在换一个吧！");

        Assert.notNull(iSystemRoleService.detail(systemAdminParam.getRole()), "角色不存在!");

        SystemAdmin model = new SystemAdmin();
        model.setId(systemAdminParam.getId());
        model.setNickname(systemAdminParam.getNickname());
        model.setUsername(systemAdminParam.getUsername());
        model.setAvatar( UrlUtil.toRelativeUrl(systemAdminParam.getAvatar()));
        model.setRole(systemAdminParam.getId() == 1 ? 0 : systemAdminParam.getRole());
        model.setSort(systemAdminParam.getSort());
        model.setIsMultipoint(systemAdminParam.getIsMultipoint());
        model.setIsDisable(systemAdminParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);

        if (systemAdminParam.getPassword() != null) {
            String salt   = ToolsUtil.randomString(5);
            String pwd    = ToolsUtil.makeMd5( systemAdminParam.getPassword().trim() + salt);
            model.setPassword(pwd);
            model.setSalt(salt);
        }

        systemAdminMapper.updateById(model);
        this.cacheAdminUserByUid(systemAdminParam.getId());
    }

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @param systemAdminParam 参数
     */
    @Override
    public void upInfo(SystemAdminParam systemAdminParam, Integer adminId) {
        String[] field = {"id", "username", "nickname"};
        SystemAdmin model = systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(field)
                .eq("id", adminId)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(model, "账号不存在了!");

        model.setNickname(systemAdminParam.getNickname());
        model.setAvatar( UrlUtil.toRelativeUrl(systemAdminParam.getAvatar()));
        model.setUpdateTime(System.currentTimeMillis() / 1000);

        if (systemAdminParam.getPassword() != null && !systemAdminParam.getPassword().equals("")) {
            String salt   = ToolsUtil.randomString(5);
            String pwd    = ToolsUtil.makeMd5( systemAdminParam.getPassword().trim() + salt);
            model.setPassword(pwd);
            model.setSalt(salt);
        }

        systemAdminMapper.updateById(model);
        this.cacheAdminUserByUid(adminId);

        if (systemAdminParam.getPassword() != null) {
            String token = Objects.requireNonNull(HttpUtil.obj()).getHeader("token");
            RedisUtil.del(AdminConfig.backstageTokenKey + token);
        }
    }

    /**
     * 管理员删除
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void del(Integer id) {
        String[] field = {"id", "username", "nickname"};
        Assert.notNull(systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(field)
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1")), "账号已不存在！");

        Assert.isFalse(id == 1, "系统管理员不允许删除");

        int adminId = Integer.parseInt(LikeAdminThreadLocal.getAdminId().toString());
        Assert.isFalse(id == adminId, "不能删除自己");

        SystemAdmin model = new SystemAdmin();
        model.setId(id);
        model.setIsDelete(1);
        model.setDeleteTime(System.currentTimeMillis() / 1000);
        systemAdminMapper.updateById(model);
        this.cacheAdminUserByUid(id);
    }

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @param id 主键参数
     */
    @Override
    public void disable(Integer id) {
        String[] field = {"id", "username", "nickname", "is_disable"};
        SystemAdmin systemAdmin = systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select(field)
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(systemAdmin, "账号已不存在！");

        Integer disable = systemAdmin.getIsDisable() == 1 ? 0 : 1;
        systemAdmin.setIsDisable(disable);
        systemAdmin.setUpdateTime(TimeUtil.timestamp());
        systemAdminMapper.updateById(systemAdmin);
    }

    /**
     * 缓存管理员
     */
    @Override
    public void cacheAdminUserByUid(Integer id) {
        SystemAdmin sysAdmin = systemAdminMapper.selectById(id);

        Map<String, Object> user = new LinkedHashMap<>();
        Map<String, Object> map  = new LinkedHashMap<>();

        user.put("id", sysAdmin.getId());
        user.put("role", sysAdmin.getRole());
        user.put("username", sysAdmin.getUsername());
        user.put("nickname", sysAdmin.getNickname());
        user.put("avatar", sysAdmin.getAvatar());
        user.put("isMultipoint", sysAdmin.getIsDisable());
        user.put("isDisable", sysAdmin.getIsDisable());
        user.put("isDelete", sysAdmin.getIsDelete());
        user.put("lastLoginIp", sysAdmin.getLastLoginIp());
        user.put("lastLoginTime", TimeUtil.timestampToDate(sysAdmin.getLastLoginTime()));
        user.put("createTime", TimeUtil.timestampToDate(sysAdmin.getCreateTime()));
        user.put("updateTime", TimeUtil.timestampToDate(sysAdmin.getUpdateTime()));
        map.put(String.valueOf(sysAdmin.getId()), JSON.toJSONString(user));
        RedisUtil.hmSet(AdminConfig.backstageManageKey, map);
    }

}
