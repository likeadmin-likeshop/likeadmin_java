package com.mdd.admin.service.system.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.admin.LikeAdminThreadLocal;
import com.mdd.admin.config.AdminConfig;
import com.mdd.admin.service.system.ISystemAuthAdminService;
import com.mdd.admin.service.system.ISystemAuthPermService;
import com.mdd.admin.service.system.ISystemAuthRoleService;
import com.mdd.admin.validate.common.PageParam;
import com.mdd.admin.validate.system.SystemAuthAdminParam;
import com.mdd.admin.vo.system.SystemAuthAdminVo;
import com.mdd.admin.vo.system.SystemAuthRoleVo;
import com.mdd.admin.vo.system.SystemAuthSelfVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.system.SystemAuthAdmin;
import com.mdd.common.entity.system.SystemAuthMenu;
import com.mdd.common.exception.OperateException;
import com.mdd.common.mapper.system.SystemAuthAdminMapper;
import com.mdd.common.mapper.system.SystemAuthMenuMapper;
import com.mdd.common.utils.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 系统管理员服务实现类
 */
@Service
public class SystemAuthAdminServiceImpl implements ISystemAuthAdminService {

    @Resource
    SystemAuthAdminMapper systemAuthAdminMapper;

    @Resource
    SystemAuthMenuMapper systemAuthMenuMapper;

    @Resource
    ISystemAuthRoleService iSystemAuthRoleService;

    @Resource
    ISystemAuthPermService iSystemAuthPermService;

    /**
     * 根据账号查找管理员
     *
     * @author fzr
     * @param username 主键ID
     * @return SysAdmin
     */
    @Override
    public SystemAuthAdmin findByUsername(String username) {
        return systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
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
    public PageResult<SystemAuthAdminVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        MPJQueryWrapper<SystemAuthAdmin> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.select("t.id,t.dept_id,t.post_id,t.username,t.nickname,t.avatar," +
                "sd.name as dept,sr.name as role,t.is_multipoint,t.is_disable," +
                "t.last_login_ip,t.last_login_time,t.create_time,t.update_time")
            .eq("t.is_delete", 0)
            .leftJoin("?_system_auth_role sr ON sr.id=t.role".replace("?_", GlobalConfig.tablePrefix))
            .leftJoin("?_system_auth_dept sd ON sd.id=t.dept_id".replace("?_", GlobalConfig.tablePrefix))
            .orderByDesc(Arrays.asList("t.id", "t.sort"));

        systemAuthAdminMapper.setSearch(mpjQueryWrapper, params, new String[]{
                "like:username:str",
                "like:nickname:str",
                "=:role:int"
        });

        IPage<SystemAuthAdminVo> iPage = systemAuthAdminMapper.selectJoinPage(
                new Page<>(page, limit),
                SystemAuthAdminVo.class,
                mpjQueryWrapper);

        for (SystemAuthAdminVo vo : iPage.getRecords()) {
            if (vo.getId() == 1) {
                vo.setRole("系统管理员");
            }

            if (vo.getDept() == null) {
                vo.setDept("");
            }

            vo.setAvatar(UrlUtil.toAbsoluteUrl(vo.getAvatar()));
            vo.setCreateTime(TimeUtil.timestampToDate(vo.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(vo.getUpdateTime()));
            vo.setLastLoginTime(TimeUtil.timestampToDate(vo.getLastLoginTime()));
        }

        return PageResult.iPageHandle(iPage);
    }

    /**
     * 当前管理员
     *
     * @author fzr
     * @return SystemSelfVo
     */
    @Override
    public SystemAuthSelfVo self(Integer adminId) {
        // 管理员信息
        SystemAuthAdmin sysAdmin = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(SystemAuthAdmin.class, info->
                    !info.getColumn().equals("salt") &&
                    !info.getColumn().equals("password") &&
                    !info.getColumn().equals("is_delete") &&
                    !info.getColumn().equals("delete_time"))
                .eq("is_delete", 0)
                .eq("id", adminId)
                .last("limit 1"));

        SystemAuthAdminVo systemAuthAdminVo = new SystemAuthAdminVo();
        BeanUtils.copyProperties(sysAdmin, systemAuthAdminVo);
        systemAuthAdminVo.setDept(String.valueOf(sysAdmin.getDeptId()));
        systemAuthAdminVo.setRole(String.valueOf(sysAdmin.getRole()));
        systemAuthAdminVo.setAvatar(UrlUtil.toAbsoluteUrl(sysAdmin.getAvatar()));
        systemAuthAdminVo.setUpdateTime(TimeUtil.timestampToDate(sysAdmin.getUpdateTime()));
        systemAuthAdminVo.setCreateTime(TimeUtil.timestampToDate(sysAdmin.getCreateTime()));
        systemAuthAdminVo.setLastLoginTime(TimeUtil.timestampToDate(sysAdmin.getLastLoginTime()));

        // 角色权限
        List<String> auths = new LinkedList<>();
        if (adminId > 1) {
            List<Integer> menuIds = iSystemAuthPermService.selectMenuIdsByRoleId(sysAdmin.getRole());
            if (menuIds.size() > 0) {
                List<SystemAuthMenu> systemAuthMenus = systemAuthMenuMapper.selectList(new QueryWrapper<SystemAuthMenu>()
                        .eq("is_disable", 0)
                        .in("id", menuIds)
                        .in("menu_type", Arrays.asList("C", "A"))
                        .orderByAsc(Arrays.asList("menu_sort", "id")));

                // 处理权限
                for (SystemAuthMenu item : systemAuthMenus) {
                    if (StringUtil.isNotNull(item.getPerms()) && StringUtil.isNotEmpty(item.getPerms())) {
                        auths.add(item.getPerms().trim());
                    }
                }
            }
            // 没有权限
            if (auths.size() <= 0) {
                auths.add("");
            }
        } else {
            // 所有权限
            auths.add("*");
        }

        // 返回数据
        SystemAuthSelfVo vo = new SystemAuthSelfVo();
        vo.setUser(systemAuthAdminVo);
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
    public SystemAuthAdminVo detail(Integer id) {
        SystemAuthAdmin sysAdmin = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(SystemAuthAdmin.class, info->
                    !info.getColumn().equals("salt") &&
                    !info.getColumn().equals("password") &&
                    !info.getColumn().equals("is_delete") &&
                    !info.getColumn().equals("delete_time"))
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(sysAdmin, "账号已不存在！");

        SystemAuthAdminVo vo = new SystemAuthAdminVo();
        BeanUtils.copyProperties(sysAdmin, vo);

        vo.setDept(String.valueOf(vo.getDeptId()));
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
     * @param systemAuthAdminParam 参数
     */
    @Override
    public void add(SystemAuthAdminParam systemAuthAdminParam) {
        String[] field = {"id", "username", "nickname"};
        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("username", systemAuthAdminParam.getUsername())
                .last("limit 1")), "账号已存在换一个吧！");

        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("nickname", systemAuthAdminParam.getNickname())
                .last("limit 1")), "昵称已存在换一个吧！");

        SystemAuthRoleVo roleVo = iSystemAuthRoleService.detail(systemAuthAdminParam.getRole());
        Assert.notNull(roleVo, "角色不存在!");
        Assert.isTrue(roleVo.getIsDisable() <= 0, "当前角色已被禁用!");

        String salt   = ToolsUtil.randomString(5);
        String pwd    = ToolsUtil.makeMd5(systemAuthAdminParam.getPassword().trim() + salt);
        String avatar = StringUtil.isNotEmpty(systemAuthAdminParam.getAvatar()) ?
                UrlUtil.toRelativeUrl(systemAuthAdminParam.getAvatar()) :
                "/api/static/backend_avatar.png";

        SystemAuthAdmin model = new SystemAuthAdmin();
        model.setDeptId(systemAuthAdminParam.getDeptId());
        model.setPostId(systemAuthAdminParam.getPostId());
        model.setUsername(systemAuthAdminParam.getUsername());
        model.setNickname(systemAuthAdminParam.getNickname());
        model.setRole(systemAuthAdminParam.getRole());
        model.setAvatar(avatar);
        model.setPassword(pwd);
        model.setSalt(salt);
        model.setSort(systemAuthAdminParam.getSort());
        model.setIsMultipoint(systemAuthAdminParam.getIsMultipoint());
        model.setIsDisable(systemAuthAdminParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthAdminMapper.insert(model);
    }

    /**
     * 管理员更新
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     */
    @Override
    public void edit(SystemAuthAdminParam systemAuthAdminParam) {
        String[] field = {"id", "username", "nickname"};
        Assert.notNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("id", systemAuthAdminParam.getId())
                .eq("is_delete", 0)
                .last("limit 1")), "账号不存在了!");

        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("username", systemAuthAdminParam.getUsername())
                .ne("id", systemAuthAdminParam.getId())
                .last("limit 1")), "账号已存在换一个吧！");

        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("nickname", systemAuthAdminParam.getNickname())
                .ne("id", systemAuthAdminParam.getId())
                .last("limit 1")), "昵称已存在换一个吧！");

        if (systemAuthAdminParam.getRole() > 0 && systemAuthAdminParam.getId() != 1) {
            Assert.notNull(iSystemAuthRoleService.detail(systemAuthAdminParam.getRole()), "角色不存在!");
        }

        SystemAuthAdmin model = new SystemAuthAdmin();
        model.setId(systemAuthAdminParam.getId());
        model.setDeptId(systemAuthAdminParam.getDeptId());
        model.setPostId(systemAuthAdminParam.getPostId());
        model.setNickname(systemAuthAdminParam.getNickname());
        model.setUsername(systemAuthAdminParam.getUsername());
        model.setAvatar(UrlUtil.toRelativeUrl(systemAuthAdminParam.getAvatar()));
        model.setRole(systemAuthAdminParam.getId() == 1 ? 0 : systemAuthAdminParam.getRole());
        model.setSort(systemAuthAdminParam.getSort());
        model.setIsMultipoint(systemAuthAdminParam.getIsMultipoint());
        model.setIsDisable(systemAuthAdminParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);

        if (systemAuthAdminParam.getPassword() != null && !systemAuthAdminParam.getPassword().equals("")) {
            if (systemAuthAdminParam.getPassword().length() < 6 || systemAuthAdminParam.getPassword().length() > 20) {
                throw new OperateException("密码必须在6~20位");
            }
            String salt   = ToolsUtil.randomString(5);
            String pwd    = ToolsUtil.makeMd5( systemAuthAdminParam.getPassword().trim() + salt);
            model.setPassword(pwd);
            model.setSalt(salt);
        }

        systemAuthAdminMapper.updateById(model);
        this.cacheAdminUserByUid(systemAuthAdminParam.getId());

        Integer id = LikeAdminThreadLocal.getAdminId();
        if (systemAuthAdminParam.getPassword() != null && systemAuthAdminParam.getId().equals(id)) {
            String token = Objects.requireNonNull(RequestUtil.handler()).getHeader("token");
            RedisUtil.del(AdminConfig.backstageTokenKey + token);

            Set<Object> ts = RedisUtil.sGet(AdminConfig.backstageTokenSet + id);
            for (Object t: ts) {
                RedisUtil.del(AdminConfig.backstageTokenKey+t.toString());
            }
            RedisUtil.del(AdminConfig.backstageTokenSet + id);
            RedisUtil.sSet(AdminConfig.backstageTokenSet + id, token);
        }
    }

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @param systemAuthAdminParam 参数
     */
    @Override
    public void upInfo(SystemAuthAdminParam systemAuthAdminParam, Integer adminId) {
        SystemAuthAdmin model = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select("id,username,nickname,password,salt")
                .eq("id", adminId)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(model, "账号不存在了!");

        String avatar = StringUtil.isNotEmpty(systemAuthAdminParam.getAvatar()) ?
                UrlUtil.toRelativeUrl(systemAuthAdminParam.getAvatar()) :
                "/api/static/backend_avatar.jpg";

        model.setAvatar(avatar);
        model.setNickname(systemAuthAdminParam.getNickname());
        model.setUpdateTime(System.currentTimeMillis() / 1000);

        if (systemAuthAdminParam.getPassword() != null && !systemAuthAdminParam.getPassword().equals("")) {
            String currPassword = ToolsUtil.makeMd5(systemAuthAdminParam.getCurrPassword() + model.getSalt());
            if (!currPassword.equals(model.getPassword())) {
                throw new OperateException("当前密码不正确!");
            }

            if (systemAuthAdminParam.getPassword().length() > 20 || systemAuthAdminParam.getPassword().length() < 6) {
                throw new OperateException("密码必须在6~20位!");
            }

            String salt   = ToolsUtil.randomString(5);
            String pwd    = ToolsUtil.makeMd5( systemAuthAdminParam.getPassword().trim() + salt);
            model.setPassword(pwd);
            model.setSalt(salt);
        }

        systemAuthAdminMapper.updateById(model);
        this.cacheAdminUserByUid(adminId);

        if (systemAuthAdminParam.getPassword() != null) {

            String token = Objects.requireNonNull(RequestUtil.handler()).getHeader("token");
            RedisUtil.del(AdminConfig.backstageTokenKey + token);

            int uid = model.getId();
            Set<Object> ts = RedisUtil.sGet(AdminConfig.backstageTokenSet + uid);
            for (Object t: ts) {
                RedisUtil.del(AdminConfig.backstageTokenKey+t.toString());
            }
            RedisUtil.del(AdminConfig.backstageTokenSet + uid);
            RedisUtil.sSet(AdminConfig.backstageTokenSet + model.getId(), token);
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
        Assert.notNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1")), "账号已不存在!");

        Assert.isFalse(id == 1, "系统管理员不允许删除!");

        int adminId = Integer.parseInt(LikeAdminThreadLocal.getAdminId().toString());
        Assert.isFalse(id == adminId, "不能删除自己!");

        SystemAuthAdmin model = new SystemAuthAdmin();
        model.setId(id);
        model.setIsDelete(1);
        model.setDeleteTime(System.currentTimeMillis() / 1000);
        systemAuthAdminMapper.updateById(model);
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
        SystemAuthAdmin systemAuthAdmin = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(systemAuthAdmin, "账号已不存在!");

        int adminId = Integer.parseInt(LikeAdminThreadLocal.getAdminId().toString());
        Assert.isFalse(id == adminId, "不能禁用自己!");

        Integer disable = systemAuthAdmin.getIsDisable() == 1 ? 0 : 1;
        systemAuthAdmin.setIsDisable(disable);
        systemAuthAdmin.setUpdateTime(TimeUtil.timestamp());
        systemAuthAdminMapper.updateById(systemAuthAdmin);
    }

    /**
     * 缓存管理员
     */
    @Override
    public void cacheAdminUserByUid(Integer id) {
        SystemAuthAdmin sysAdmin = systemAuthAdminMapper.selectById(id);

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
