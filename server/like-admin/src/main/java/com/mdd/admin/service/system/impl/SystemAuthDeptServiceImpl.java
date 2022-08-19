package com.mdd.admin.service.system.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.system.ISystemAuthDeptService;
import com.mdd.admin.validate.system.SystemAuthDeptParam;
import com.mdd.admin.vo.system.SystemAuthDeptVo;
import com.mdd.common.entity.system.SystemAuthAdmin;
import com.mdd.common.entity.system.SystemAuthDept;
import com.mdd.common.mapper.system.SystemAuthAdminMapper;
import com.mdd.common.mapper.system.SystemAuthDeptMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统部门服务实现类
 */
@Service
class SystemAuthDeptServiceImpl implements ISystemAuthDeptService {

    @Resource
    SystemAuthDeptMapper systemAuthDeptMapper;

    @Resource
    SystemAuthAdminMapper systemAuthAdminMapper;

    /**
     * 岗位所有
     *
     * @author fzr
     * @return List<SystemPostVo>
     */
    @Override
    public List<SystemAuthDeptVo> all() {
        List<SystemAuthDept> systemAuthDeptList = systemAuthDeptMapper.selectList(new QueryWrapper<SystemAuthDept>()
                .gt("pid", 0)
                .eq("is_delete", 0)
                .orderByDesc((Arrays.asList("id", "sort"))));

        List<SystemAuthDeptVo> adminVoArrayList = new ArrayList<>();
        for (SystemAuthDept systemAuthDept : systemAuthDeptList) {
            SystemAuthDeptVo vo = new SystemAuthDeptVo();
            BeanUtils.copyProperties(systemAuthDept, vo);

            vo.setUpdateTime(TimeUtil.timestampToDate(systemAuthDept.getUpdateTime()));
            vo.setCreateTime(TimeUtil.timestampToDate(systemAuthDept.getCreateTime()));
            adminVoArrayList.add(vo);
        }

        return adminVoArrayList;
    }

    /**
     *  部门列表
     *
     * @author fzr
     * @param params 搜索参数
     * @return JSONArray
     */
    @Override
    public JSONArray list(Map<String, String> params) {
        QueryWrapper<SystemAuthDept> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderByAsc(Arrays.asList("sort", "id"));
        queryWrapper.select(SystemAuthDept.class, info ->
                !info.getColumn().equals("is_delete") &&
                        !info.getColumn().equals("delete_time"));

        systemAuthDeptMapper.setSearch(queryWrapper, params, new String[]{
                "like:name:str",
                "=:isStop@is_stop:int"
        });

        List<SystemAuthDept> systemAuthDeptList = systemAuthDeptMapper.selectList(queryWrapper);

        List<SystemAuthDeptVo> lists = new ArrayList<>();
        for (SystemAuthDept systemAuthDept : systemAuthDeptList) {
            SystemAuthDeptVo vo = new SystemAuthDeptVo();
            BeanUtils.copyProperties(systemAuthDept, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(systemAuthDept.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(systemAuthDept.getUpdateTime()));
            lists.add(vo);
        }

        JSONArray jsonArray = JSONArray.parseArray(JSONArray.toJSONString(lists));
        return ArrayUtil.listToTree(jsonArray, "id", "pid", "children");
    }

    /**
     * 部门详情
     *
     * @author fzr
     * @param id 主键
     * @return SystemDeptVo
     */
    @Override
    public SystemAuthDeptVo detail(Integer id) {
        SystemAuthDept systemAuthDept = systemAuthDeptMapper.selectOne(
                new QueryWrapper<SystemAuthDept>()
                        .select(SystemAuthDept.class, info ->
                                !info.getColumn().equals("is_delete") &&
                                        !info.getColumn().equals("delete_time"))
                        .eq("id", id)
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(systemAuthDept, "部门已不存在!");

        SystemAuthDeptVo vo  = new SystemAuthDeptVo();
        BeanUtils.copyProperties(systemAuthDept, vo);
        vo.setCreateTime(TimeUtil.timestampToDate(systemAuthDept.getCreateTime()));
        vo.setUpdateTime(TimeUtil.timestampToDate(systemAuthDept.getUpdateTime()));

        return vo;
    }

    /**
     * 部门新增
     *
     * @author fzr
     * @param systemAuthDeptParam 参数
     */
    @Override
    public void add(SystemAuthDeptParam systemAuthDeptParam) {
        if (systemAuthDeptParam.getPid() == 0) {
            SystemAuthDept systemAuthDept = systemAuthDeptMapper.selectOne(
                    new QueryWrapper<SystemAuthDept>()
                            .select("id,pid,name")
                            .eq("pid", 0)
                            .eq("is_delete", 0)
                            .last("limit 1"));

            Assert.isNull(systemAuthDept, "顶级部门只允许有一个");
        }

        SystemAuthDept model = new SystemAuthDept();
        model.setPid(systemAuthDeptParam.getPid());
        model.setName(systemAuthDeptParam.getName());
        model.setDuty(systemAuthDeptParam.getDuty());
        model.setMobile(systemAuthDeptParam.getMobile());
        model.setSort(systemAuthDeptParam.getSort());
        model.setIsStop(systemAuthDeptParam.getIsStop());
        model.setIsDelete(0);
        model.setCreateTime(System.currentTimeMillis() / 1000);
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthDeptMapper.insert(model);
    }

    /**
     * 部门编辑
     *
     * @author fzr
     * @param systemAuthDeptParam 参数
     */
    @Override
    public void edit(SystemAuthDeptParam systemAuthDeptParam) {
        SystemAuthDept model = systemAuthDeptMapper.selectOne(
                new QueryWrapper<SystemAuthDept>()
                        .select(SystemAuthDept.class, info ->
                                !info.getColumn().equals("is_delete") &&
                                        !info.getColumn().equals("delete_time"))
                        .eq("id", systemAuthDeptParam.getId())
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(model, "部门不存在");
        Assert.isFalse((model.getPid() == 0 && systemAuthDeptParam.getPid() > 0), "顶级部门不能修改上级");
        Assert.isFalse(systemAuthDeptParam.getId().equals(systemAuthDeptParam.getPid()), "上级部门不能是自己");

        model.setPid(systemAuthDeptParam.getPid());
        model.setName(systemAuthDeptParam.getName());
        model.setDuty(systemAuthDeptParam.getDuty());
        model.setMobile(systemAuthDeptParam.getMobile());
        model.setSort(systemAuthDeptParam.getSort());
        model.setIsStop(systemAuthDeptParam.getIsStop());
        model.setUpdateTime(System.currentTimeMillis() / 1000);
        systemAuthDeptMapper.updateById(model);
    }

    /**
     * 部门删除
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    public void del(Integer id) {
        SystemAuthDept model = systemAuthDeptMapper.selectOne(
                new QueryWrapper<SystemAuthDept>()
                        .select("id,pid,name")
                        .eq("id", id)
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(model, "部门不存在");
        Assert.isFalse((model.getPid() == 0), "顶级部门不能删除");

        SystemAuthAdmin systemAuthAdmin = systemAuthAdminMapper.selectOne(new QueryWrapper<SystemAuthAdmin>()
                .select("id,nickname")
                .eq("dept_id", id)
                .eq("is_delete", 0)
                .last("limit 1"));

        Assert.isNull(systemAuthAdmin, "该部门已被管理员使用,请先移除");

        model.setIsDelete(1);
        model.setDeleteTime(System.currentTimeMillis() / 1000);
        systemAuthDeptMapper.updateById(model);
    }

}