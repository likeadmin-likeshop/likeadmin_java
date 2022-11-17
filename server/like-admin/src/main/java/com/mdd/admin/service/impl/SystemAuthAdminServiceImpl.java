package com.mdd.admin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.admin.config.AdminConfig;
import com.mdd.admin.service.ISystemAuthAdminService;
import com.mdd.admin.service.ISystemAuthPermService;
import com.mdd.admin.service.ISystemAuthRoleService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemAdminCreateValidate;
import com.mdd.admin.validate.system.SystemAdminSearchValidate;
import com.mdd.admin.validate.system.SystemAdminUpInfoValidate;
import com.mdd.admin.validate.system.SystemAdminUpdateValidate;
import com.mdd.admin.vo.system.*;
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
     * 管理员列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<SystemAuthAdminListedVo>
     */
    @Override
    public PageResult<SystemAuthAdminListedVo> list(PageValidate pageValidate, SystemAdminSearchValidate searchValidate) {
        Integer page  = pageValidate.getPageNo();
        Integer limit = pageValidate.getPageSize();

        MPJQueryWrapper<SystemAuthAdmin> mpjQueryWrapper = new MPJQueryWrapper<>();
        mpjQueryWrapper.select("t.id,t.username,t.nickname,t.avatar," +
                "sd.name as dept,sr.name as role,t.is_multipoint,t.is_disable," +
                "t.last_login_ip,t.last_login_time,t.create_time,t.update_time")
            .eq("t.is_delete", 0)
            .leftJoin("?_system_auth_role sr ON sr.id=t.role".replace("?_", GlobalConfig.tablePrefix))
            .leftJoin("?_system_auth_dept sd ON sd.id=t.dept_id".replace("?_", GlobalConfig.tablePrefix))
            .orderByDesc(Arrays.asList("t.id", "t.sort"));


        systemAuthAdminMapper.setSearch(mpjQueryWrapper, searchValidate, new String[]{
                "like:username:str",
                "like:nickname:str",
                "=:role:int"
        });

        IPage<SystemAuthAdminListedVo> iPage = systemAuthAdminMapper.selectJoinPage(
                new Page<>(page, limit),
                SystemAuthAdminListedVo.class,
                mpjQueryWrapper);

        for (SystemAuthAdminListedVo vo : iPage.getRecords()) {
            if (vo.getId().equals(1)) {
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
     * @param adminId 管理员ID
     * @return SystemAuthAdminSelvesVo
     */
    @Override
    public SystemAuthAdminSelvesVo self(Integer adminId) {
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

        SystemAuthAdminInformVo systemAuthAdminInformVo = new SystemAuthAdminInformVo();
        BeanUtils.copyProperties(sysAdmin, systemAuthAdminInformVo);
        systemAuthAdminInformVo.setAvatar(UrlUtil.toAbsoluteUrl(sysAdmin.getAvatar()));
        systemAuthAdminInformVo.setUpdateTime(TimeUtil.timestampToDate(sysAdmin.getUpdateTime()));
        systemAuthAdminInformVo.setCreateTime(TimeUtil.timestampToDate(sysAdmin.getCreateTime()));
        systemAuthAdminInformVo.setLastLoginTime(TimeUtil.timestampToDate(sysAdmin.getLastLoginTime()));

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
        SystemAuthAdminSelvesVo vo = new SystemAuthAdminSelvesVo();
        vo.setUser(systemAuthAdminInformVo);
        vo.setPermissions(auths);
        return vo;
    }

    /**
     * 管理员详细
     *
     * @author fzr
     * @param id 主键
     * @return SystemAuthAdminDetailVo
     */
    @Override
    public SystemAuthAdminDetailVo detail(Integer id) {
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

        SystemAuthAdminDetailVo vo = new SystemAuthAdminDetailVo();
        BeanUtils.copyProperties(sysAdmin, vo);
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
     * @param createValidate 参数
     */
    @Override
    public void add(SystemAdminCreateValidate createValidate) {
        String[] field = {"id", "username", "nickname"};
        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("username", createValidate.getUsername())
                .last("limit 1")), "账号已存在换一个吧！");

        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("nickname", createValidate.getNickname())
                .last("limit 1")), "昵称已存在换一个吧！");

        SystemAuthRoleVo roleVo = iSystemAuthRoleService.detail(createValidate.getRole());
        Assert.notNull(roleVo, "角色不存在!");
        Assert.isTrue(roleVo.getIsDisable() <= 0, "当前角色已被禁用!");

        String salt = ToolsUtil.randomString(5);
        String pwd  = ToolsUtil.makeMd5(createValidate.getPassword().trim() + salt);

        String createAvatar  = createValidate.getAvatar();
        String defaultAvatar = "/api/static/backend_avatar.png";
        String avatar = StringUtil.isNotEmpty(createValidate.getAvatar()) ? UrlUtil.toRelativeUrl(createAvatar) : defaultAvatar;

        SystemAuthAdmin model = new SystemAuthAdmin();
        model.setDeptId(createValidate.getDeptId());
        model.setPostId(createValidate.getPostId());
        model.setUsername(createValidate.getUsername());
        model.setNickname(createValidate.getNickname());
        model.setRole(createValidate.getRole());
        model.setAvatar(avatar);
        model.setPassword(pwd);
        model.setSalt(salt);
        model.setSort(createValidate.getSort());
        model.setIsMultipoint(createValidate.getIsMultipoint());
        model.setIsDisable(createValidate.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthAdminMapper.insert(model);
    }

    /**
     * 管理员更新
     *
     * @author fzr
     * @param updateValidate 参数
     * @param adminId 管理员ID
     */
    @Override
    public void edit(SystemAdminUpdateValidate updateValidate, Integer adminId) {
        if (adminId.equals(1) && updateValidate.getId().equals(1)) {
            throw new OperateException("您无权限编辑系统管理员!");
        }

        String[] field = {"id", "username", "nickname"};
        Assert.notNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("id", updateValidate.getId())
                .eq("is_delete", 0)
                .last("limit 1")), "账号不存在了!");

        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("username", updateValidate.getUsername())
                .ne("id", updateValidate.getId())
                .last("limit 1")), "账号已存在换一个吧!");

        Assert.isNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("nickname", updateValidate.getNickname())
                .ne("id", updateValidate.getId())
                .last("limit 1")), "昵称已存在换一个吧!");

        if (updateValidate.getRole() > 0 && updateValidate.getId() != 1) {
            Assert.notNull(iSystemAuthRoleService.detail(updateValidate.getRole()), "角色不存在!");
        }

        SystemAuthAdmin model = new SystemAuthAdmin();
        model.setId(updateValidate.getId());
        model.setDeptId(updateValidate.getDeptId());
        model.setPostId(updateValidate.getPostId());
        model.setNickname(updateValidate.getNickname());
        model.setAvatar(UrlUtil.toRelativeUrl(updateValidate.getAvatar()));
        model.setRole(updateValidate.getId() == 1 ? 0 : updateValidate.getRole());
        model.setSort(updateValidate.getSort());
        model.setIsMultipoint(updateValidate.getIsMultipoint());
        model.setIsDisable(updateValidate.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);

        if (!updateValidate.getId().equals(1)) {
            model.setUsername(updateValidate.getUsername());
        }

        if (StringUtil.isNotNull(updateValidate.getPassword())) {
            String salt   = ToolsUtil.randomString(5);
            String pwd    = ToolsUtil.makeMd5( updateValidate.getPassword().trim() + salt);
            model.setPassword(pwd);
            model.setSalt(salt);
        }

        systemAuthAdminMapper.updateById(model);
        this.cacheAdminUserByUid(updateValidate.getId());

        if (StringUtil.isNotNull(updateValidate.getPassword())) {
            StpUtil.kickout(updateValidate.getId());
        }
    }

    /**
     * 当前管理员更新
     *
     * @author fzr
     * @param upInfoValidate 参数
     */
    @Override
    public void upInfo(SystemAdminUpInfoValidate upInfoValidate, Integer adminId) {
        SystemAuthAdmin model = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select("id,username,nickname,password,salt")
                .eq("id", adminId)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(model, "账号不存在了!");

        String createAvatar  = upInfoValidate.getAvatar();
        String defaultAvatar = "/api/static/backend_avatar.png";
        String avatar = StringUtil.isNotEmpty(upInfoValidate.getAvatar()) ? UrlUtil.toRelativeUrl(createAvatar) : defaultAvatar;

        model.setAvatar(avatar);
        model.setNickname(upInfoValidate.getNickname());
        model.setUpdateTime(System.currentTimeMillis() / 1000);

        if (StringUtil.isNotNull(upInfoValidate.getPassword())) {
            String currPassword = ToolsUtil.makeMd5(upInfoValidate.getCurrPassword() + model.getSalt());
            Assert.isFalse(!currPassword.equals(model.getPassword()), "当前密码不正确!");
            String salt   = ToolsUtil.randomString(5);
            String pwd    = ToolsUtil.makeMd5( upInfoValidate.getPassword().trim() + salt);
            model.setPassword(pwd);
            model.setSalt(salt);
        }

        systemAuthAdminMapper.updateById(model);
        this.cacheAdminUserByUid(adminId);

        if (StringUtil.isNotNull(upInfoValidate.getPassword())) {
            StpUtil.kickout(adminId);
        }
    }

    /**
     * 管理员删除
     *
     * @author fzr
     * @param id 主键
     * @param adminId 管理员ID
     */
    @Override
    public void del(Integer id, Integer adminId) {
        String[] field = {"id", "username", "nickname"};
        Assert.notNull(systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select(field)
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1")), "账号已不存在!");

        Assert.isFalse(id.equals(1), "系统管理员不允许删除!");
        Assert.isFalse(id.equals(adminId) , "不能删除自己!");

        SystemAuthAdmin model = new SystemAuthAdmin();
        model.setId(id);
        model.setIsDelete(1);
        model.setDeleteTime(System.currentTimeMillis() / 1000);
        systemAuthAdminMapper.updateById(model);
        this.cacheAdminUserByUid(id);

        StpUtil.kickout(id);
    }

    /**
     * 管理员状态切换
     *
     * @author fzr
     * @param id 主键参数
     * @param adminId 管理员ID
     */
    @Override
    public void disable(Integer id, Integer adminId) {
        SystemAuthAdmin systemAuthAdmin = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select("id,username,nickname,is_disable")
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(systemAuthAdmin, "账号已不存在!");
        Assert.isFalse(id.equals(adminId) , "不能禁用自己!");

        Integer disable = systemAuthAdmin.getIsDisable() == 1 ? 0 : 1;
        systemAuthAdmin.setIsDisable(disable);
        systemAuthAdmin.setUpdateTime(TimeUtil.timestamp());
        systemAuthAdminMapper.updateById(systemAuthAdmin);
        this.cacheAdminUserByUid(id);

        if (disable.equals(1)) {
            StpUtil.kickout(id);
        }
    }

    /**
     * 缓存管理员
     */
    @Override
    public void cacheAdminUserByUid(Integer id) {
        SystemAuthAdmin sysAdmin = systemAuthAdminMapper.selectOne(
                new QueryWrapper<SystemAuthAdmin>()
                    .select("id,role,username,nickname,is_multipoint,is_disable,is_delete")
                    .eq("id", id)
                    .last("limit 1"));

        Map<String, Object> user = new LinkedHashMap<>();
        user.put("id", sysAdmin.getId());
        user.put("roleId", sysAdmin.getRole());
        user.put("username", sysAdmin.getUsername());
        user.put("nickname", sysAdmin.getNickname());
        user.put("isMultipoint", sysAdmin.getIsMultipoint());
        user.put("isDisable", sysAdmin.getIsDisable());
        user.put("isDelete", sysAdmin.getIsDelete());

        Map<String, Object> map  = new LinkedHashMap<>();
        map.put(String.valueOf(sysAdmin.getId()), JSON.toJSONString(user));

        RedisUtil.hmSet(AdminConfig.backstageManageKey, map);
    }

}
