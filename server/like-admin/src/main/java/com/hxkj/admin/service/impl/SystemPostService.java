package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxkj.admin.service.ISystemPostService;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.validate.system.SystemPostParam;
import com.hxkj.admin.vo.system.SystemPostVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SystemAdmin;
import com.hxkj.common.entity.system.SystemPost;
import com.hxkj.common.mapper.system.SystemAdminMapper;
import com.hxkj.common.mapper.system.SystemPostMapper;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SystemPostService implements ISystemPostService {

    @Resource
    SystemPostMapper systemPostMapper;

    @Resource
    SystemAdminMapper systemAdminMapper;

    /**
     * 岗位所有
     *
     * @author fzr
     * @return List<SystemPostVo>
     */
    @Override
    public List<SystemPostVo> all() {
        List<SystemPost> systemPostList = systemPostMapper.selectList(new QueryWrapper<SystemPost>()
                .eq("is_delete", 0)
                .orderByDesc((Arrays.asList("id", "sort"))));

        List<SystemPostVo> adminVoArrayList = new ArrayList<>();
        for (SystemPost systemPost : systemPostList) {
            SystemPostVo vo = new SystemPostVo();
            BeanUtils.copyProperties(systemPost, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(systemPost.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(systemPost.getUpdateTime()));
            adminVoArrayList.add(vo);
        }

        return adminVoArrayList;
    }

    /**
     * 岗位列表
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<SystemPostVo>
     */
    @Override
    public PageResult<SystemPostVo> list(PageParam pageParam, Map<String, String> params) {
        Integer page  = pageParam.getPageNo();
        Integer limit = pageParam.getPageSize();

        QueryWrapper<SystemPost> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(SystemPost.class, info->
                    !info.getColumn().equals("is_delete") &&
                    !info.getColumn().equals("delete_time"))
                .eq("is_delete", 0)
                .orderByDesc(Arrays.asList("id", "sort"));

        systemPostMapper.setSearch(queryWrapper, params, new String[]{
                "like:code:str",
                "like:name:str",
                "=:isStop:int"
        });

        IPage<SystemPost> iPage = systemPostMapper.selectPage(new Page<>(page, limit), queryWrapper);

        List<SystemPostVo> list = new ArrayList<>();
        for (SystemPost systemPost : iPage.getRecords()) {
            SystemPostVo vo = new SystemPostVo();
            BeanUtils.copyProperties(systemPost, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(systemPost.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(systemPost.getUpdateTime()));
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
    public SystemPostVo detail(Integer id) {
        SystemPost systemPost = systemPostMapper.selectOne(new QueryWrapper<SystemPost>()
                .select(SystemPost.class, info ->
                    !info.getColumn().equals("is_delete") &&
                   !info.getColumn().equals("delete_time"))
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(systemPost, "岗位不存在");

        SystemPostVo vo = new SystemPostVo();
        BeanUtils.copyProperties(systemPost, vo);
        vo.setCreateTime(TimeUtil.timestampToDate(systemPost.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(systemPost.getUpdateTime()));

        return vo;
    }

    /**
     * 岗位新增
     *
     * @author fzr
     * @param systemPostParam 参数
     */
    @Override
    public void add(SystemPostParam systemPostParam) {
        Assert.isNull(systemPostMapper.selectOne(new QueryWrapper<SystemPost>()
                .select("id,code,name")
                .nested(
                        wq->wq.eq("code", systemPostParam.getCode())
                        .or()
                        .eq("name", systemPostParam.getName())
                )
                .eq("is_delete", 0)
                .last("limit 1")), "该岗位已存在");

        SystemPost model = new SystemPost();
        model.setCode(systemPostParam.getCode());
        model.setName(systemPostParam.getName());
        model.setSort(systemPostParam.getSort());
        model.setRemarks(systemPostParam.getRemarks());
        model.setIsStop(systemPostParam.getIsStop());
        model.setIsDelete(0);
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemPostMapper.insert(model);
    }

    /**
     * 岗位编辑
     *
     * @author fzr
     * @param systemPostParam 参数
     */
    @Override
    public void edit(SystemPostParam systemPostParam) {
        SystemPost model = systemPostMapper.selectOne(new QueryWrapper<SystemPost>()
                .select(SystemPost.class, info ->
                        !info.getColumn().equals("is_delete") &&
                        !info.getColumn().equals("delete_time"))
                .eq("id", systemPostParam.getId())
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(model, "岗位不存在");

        Assert.isNull(systemPostMapper.selectOne(new QueryWrapper<SystemPost>()
                .select("id,code,name")
                .ne("id", systemPostParam.getId())
                .nested(
                        wq->wq.eq("code", systemPostParam.getCode())
                        .or()
                        .eq("name", systemPostParam.getName())
                )
                .eq("is_delete", 0)
                .last("limit 1")), "该岗位已存在");

        model.setCode(systemPostParam.getCode());
        model.setName(systemPostParam.getName());
        model.setSort(systemPostParam.getSort());
        model.setRemarks(systemPostParam.getRemarks());
        model.setIsStop(systemPostParam.getIsStop());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemPostMapper.updateById(model);
    }

    /**
     * 岗位删除
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void del(Integer id) {
        SystemPost model = systemPostMapper.selectOne(new QueryWrapper<SystemPost>()
                .select("id,code,name")
                .eq("id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.notNull(model, "岗位不存在");

        SystemAdmin systemAdmin = systemAdminMapper.selectOne(new QueryWrapper<SystemAdmin>()
                .select("id,nickname")
                .eq("post_id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.isNull(systemAdmin, "该岗位已被“"+systemAdmin.getNickname()+"”管理员使用,请先移除");

        model.setIsDelete(1);
        model.setDeleteTime(System.currentTimeMillis() / 1000);
        systemPostMapper.updateById(model);
    }

}
