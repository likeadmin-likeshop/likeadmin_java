package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.ISystemAuthPostService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemPostCreateValidate;
import com.mdd.admin.validate.system.SystemPostSearchValidate;
import com.mdd.admin.validate.system.SystemPostUpdateValidate;
import com.mdd.admin.vo.system.SystemAuthPostVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.system.SystemAuthAdmin;
import com.mdd.common.entity.system.SystemAuthPost;
import com.mdd.common.mapper.system.SystemAuthAdminMapper;
import com.mdd.common.mapper.system.SystemAuthPostMapper;
import com.mdd.common.util.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统岗位服务实现类
 */
@Service
public class SystemAuthPostServiceImpl implements ISystemAuthPostService {

    @Resource
    SystemAuthPostMapper systemAuthPostMapper;

    @Resource
    SystemAuthAdminMapper systemAuthAdminMapper;

    /**
     * 岗位所有
     *
     * @author fzr
     * @return List<SystemPostVo>
     */
    @Override
    public List<SystemAuthPostVo> all() {
        List<SystemAuthPost> systemAuthPostList = systemAuthPostMapper.selectList(new QueryWrapper<SystemAuthPost>()
                .eq("is_delete", 0)
                .orderByDesc((Arrays.asList("sort", "id"))));

        List<SystemAuthPostVo> adminVoArrayList = new ArrayList<>();
        for (SystemAuthPost systemAuthPost : systemAuthPostList) {
            SystemAuthPostVo vo = new SystemAuthPostVo();
            BeanUtils.copyProperties(systemAuthPost, vo);

            vo.setCreateTime(TimeUtils.timestampToDate(systemAuthPost.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(systemAuthPost.getUpdateTime()));
            adminVoArrayList.add(vo);
        }

        return adminVoArrayList;
    }

    /**
     * 岗位列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<SystemPostVo>
     */
    @Override
    public PageResult<SystemAuthPostVo> list(PageValidate pageValidate, SystemPostSearchValidate searchValidate) {
        Integer page  = pageValidate.getPageNo();
        Integer limit = pageValidate.getPageSize();

        QueryWrapper<SystemAuthPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SystemAuthPost.class, info->
                    !info.getColumn().equals("is_delete") &&
                    !info.getColumn().equals("delete_time"))
                .eq("is_delete", 0)
                .orderByDesc(Arrays.asList("sort", "id"));

        systemAuthPostMapper.setSearch(queryWrapper, searchValidate, new String[]{
                "like:code:str",
                "like:name:str",
                "=:isStop@is_stop:int"
        });

        IPage<SystemAuthPost> iPage = systemAuthPostMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SystemAuthPostVo> list = new ArrayList<>();
        for (SystemAuthPost systemAuthPost : iPage.getRecords()) {
            SystemAuthPostVo vo = new SystemAuthPostVo();
            BeanUtils.copyProperties(systemAuthPost, vo);

            vo.setCreateTime(TimeUtils.timestampToDate(systemAuthPost.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(systemAuthPost.getUpdateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 岗位详情
     *
     * @author fzr
     * @param id 主键
     * @return SystemPostVo
     */
    @Override
    public SystemAuthPostVo detail(Integer id) {
        SystemAuthPost systemAuthPost = systemAuthPostMapper.selectOne(new QueryWrapper<SystemAuthPost>()
                .select(SystemAuthPost.class, info ->
                    !info.getColumn().equals("is_delete") &&
                   !info.getColumn().equals("delete_time"))
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(systemAuthPost, "岗位不存在");

        SystemAuthPostVo vo = new SystemAuthPostVo();
        BeanUtils.copyProperties(systemAuthPost, vo);
        vo.setCreateTime(TimeUtils.timestampToDate(systemAuthPost.getCreateTime()));
        vo.setUpdateTime(TimeUtils.timestampToDate(systemAuthPost.getUpdateTime()));

        return vo;
    }

    /**
     * 岗位新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    @Override
    public void add(SystemPostCreateValidate createValidate) {
        Assert.isNull(systemAuthPostMapper.selectOne(new QueryWrapper<SystemAuthPost>()
                .select("id,code,name")
                .nested(
                        wq->wq.eq("code", createValidate.getCode())
                        .or()
                        .eq("name", createValidate.getName())
                )
                .eq("is_delete", 0)
                .last("limit 1")), "该岗位已存在");

        SystemAuthPost model = new SystemAuthPost();
        model.setCode(createValidate.getCode());
        model.setName(createValidate.getName());
        model.setSort(createValidate.getSort());
        model.setRemarks(createValidate.getRemarks());
        model.setIsStop(createValidate.getIsStop());
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthPostMapper.insert(model);
    }

    /**
     * 岗位编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    @Override
    public void edit(SystemPostUpdateValidate updateValidate) {
        SystemAuthPost model = systemAuthPostMapper.selectOne(new QueryWrapper<SystemAuthPost>()
                .select(SystemAuthPost.class, info ->
                        !info.getColumn().equals("is_delete") &&
                        !info.getColumn().equals("delete_time"))
                .eq("id", updateValidate.getId())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(model, "岗位不存在");

        Assert.isNull(systemAuthPostMapper.selectOne(new QueryWrapper<SystemAuthPost>()
                .select("id,code,name")
                .ne("id", updateValidate.getId())
                .nested(
                        wq->wq.eq("code", updateValidate.getCode())
                        .or()
                        .eq("name", updateValidate.getName())
                )
                .eq("is_delete", 0)
                .last("limit 1")), "该岗位已存在");

        model.setCode(updateValidate.getCode());
        model.setName(updateValidate.getName());
        model.setSort(updateValidate.getSort());
        model.setRemarks(updateValidate.getRemarks());
        model.setIsStop(updateValidate.getIsStop());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthPostMapper.updateById(model);
    }

    /**
     * 岗位删除
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void del(Integer id) {
        SystemAuthPost model = systemAuthPostMapper.selectOne(new QueryWrapper<SystemAuthPost>()
                .select("id,code,name")
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(model, "岗位不存在");

        SystemAuthAdmin systemAuthAdmin = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select("id,nickname")
                .eq("post_id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.isNull(systemAuthAdmin, "该岗位已被管理员使用,请先移除");

        model.setIsDelete(1);
        model.setDeleteTime(System.currentTimeMillis() / 1000);
        systemAuthPostMapper.updateById(model);
    }

}
