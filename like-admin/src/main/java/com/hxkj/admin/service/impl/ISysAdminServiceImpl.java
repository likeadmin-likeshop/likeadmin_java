package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.hxkj.admin.service.ISysAdminService;
import com.hxkj.admin.service.ISysRoleService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.SysAdminParam;
import com.hxkj.admin.vo.system.SysAdminListVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SysAdmin;
import com.hxkj.common.mapper.system.SysAdminMapper;
import com.hxkj.common.utils.TimeUtil;
import com.hxkj.common.utils.ToolsUtil;
import com.hxkj.common.utils.UrlUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ISysAdminServiceImpl extends MPJBaseServiceImpl<SysAdminMapper, SysAdmin> implements ISysAdminService {

    @Resource
    ISysRoleService iSysRoleService;

    /**
     * 获取管理员列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @return PageResult<SysAdminListVo>
     */
    @Override
    public PageResult<SysAdminListVo> lists(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SysAdmin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SysAdmin.class, info->
                !info.getColumn().equals("salt") &&
                !info.getColumn().equals("password") &&
                !info.getColumn().equals("is_delete") &&
                !info.getColumn().equals("delete_time"))
        .eq("is_delete", 0)
        .orderByDesc("sort");

        this.setSearch(queryWrapper, params, new String[]{
                "eq:username"
        });

//        String[] a = {"str:username:=", ""};

        IPage<SysAdmin> iPage = this.page(new Page<>(page, limit), queryWrapper);

        List<SysAdminListVo> adminVoArrayList = new ArrayList<>();
        for (SysAdmin sysAdmin : iPage.getRecords()) {
            SysAdminListVo vo = new SysAdminListVo();
            BeanUtils.copyProperties(sysAdmin, vo);

            vo.setRole(iSysRoleService.getRoleNameById(sysAdmin.getRole()));
            vo.setCreateTime(TimeUtil.timeToDate(sysAdmin.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timeToDate(sysAdmin.getUpdateTime()));
            vo.setLastLoginTime(TimeUtil.timeToDate(sysAdmin.getLastLoginTime()));
            adminVoArrayList.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), adminVoArrayList);
    }

    /**
     * 获取管理员详细
     *
     * @author fzr
     * @param id 主键
     * @return SysAdmin
     */
    @Override
    public SysAdmin detail(Integer id) {
        SysAdmin sysAdmin = this.getOne(new QueryWrapper<SysAdmin>()
                .select(SysAdmin.class, info->
                        !info.getColumn().equals("salt") &&
                        !info.getColumn().equals("password") &&
                        !info.getColumn().equals("is_delete") &&
                        !info.getColumn().equals("delete_time"))
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(sysAdmin, "账号已不存在！");

        return sysAdmin;
    }

    /**
     * 新增管理员
     *
     * @author fzr
     * @param sysAdminParam 参数
     */
    @Override
    public void add(SysAdminParam sysAdminParam) {
        String[] field = {"id", "username", "nickname"};
        Assert.isNull(this.getOne(new QueryWrapper<SysAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("username", sysAdminParam.getUsername())
                .last("limit 1")), "账号已存在换一个吧！");

        Assert.isNull(this.getOne(new QueryWrapper<SysAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("nickname", sysAdminParam.getNickname())
                .last("limit 1")), "昵称已存在换一个吧！");

        String salt   = ToolsUtil.randomString(5);
        String pwd    = ToolsUtil.makeMd5(sysAdminParam.getPassword().trim() + salt);
        String avatar = UrlUtil.toRelativeUrl(sysAdminParam.getAvatar());

        SysAdmin model = new SysAdmin();
        model.setUsername(sysAdminParam.getUsername());
        model.setNickname(sysAdminParam.getNickname());
        model.setRole(sysAdminParam.getRole());
        model.setAvatar(avatar);
        model.setPassword(pwd);
        model.setSalt(salt);
        model.setSort(sysAdminParam.getSort());
        model.setIsDisable(sysAdminParam.getIsDisable());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        this.save(model);
    }

    /**
     * 更新管理员
     *
     * @author fzr
     * @param sysAdminParam 参数
     */
    @Override
    public void edit(SysAdminParam sysAdminParam) {
        String[] field = {"id", "username", "nickname"};
        Assert.isNull(this.getOne(new QueryWrapper<SysAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("username", sysAdminParam.getUsername())
                .ne("id", sysAdminParam.getId())
                .last("limit 1")), "账号已存在换一个吧！");

        Assert.isNull(this.getOne(new QueryWrapper<SysAdmin>()
                .select(field)
                .eq("is_delete", 0)
                .eq("nickname", sysAdminParam.getNickname())
                .ne("id", sysAdminParam.getId())
                .last("limit 1")), "昵称已存在换一个吧！");

        String avatar = UrlUtil.toRelativeUrl(sysAdminParam.getAvatar());
        String salt   = ToolsUtil.randomString(5);
        String pwd    = ToolsUtil.makeMd5(sysAdminParam.getPassword().trim() + salt);

        SysAdmin model = new SysAdmin();
        model.setId(sysAdminParam.getId());
        model.setNickname(sysAdminParam.getNickname());
        model.setUsername(sysAdminParam.getUsername());
        model.setRole(sysAdminParam.getRole());
        model.setAvatar(avatar);
        model.setPassword(pwd);
        model.setSalt(salt);
        model.setSort(sysAdminParam.getSort());
        model.setIsDisable(sysAdminParam.getIsDisable());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        this.updateById(model);
    }

    /**
     * 删除管理员
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void del(Integer id) {
        String[] field = {"id", "username", "nickname"};
        Assert.notNull(this.getOne(new QueryWrapper<SysAdmin>()
                .select(field)
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1")), "账号已不存在！");

        SysAdmin model = new SysAdmin();
        model.setId(id);
        model.setIsDelete(1);
        model.setDeleteTime(System.currentTimeMillis() / 1000);
        this.updateById(model);
    }

}
