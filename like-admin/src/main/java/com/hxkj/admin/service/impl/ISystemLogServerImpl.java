package com.hxkj.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.hxkj.admin.service.ISystemLogServer;
import com.hxkj.admin.validate.PageParam;
import com.hxkj.admin.vo.system.LogLoginVo;
import com.hxkj.admin.vo.system.LogOperateVo;
import com.hxkj.common.core.PageResult;
import com.hxkj.common.entity.system.SystemLogLogin;
import com.hxkj.common.entity.system.SystemLogOperate;
import com.hxkj.common.mapper.system.SystemLogLoginMapper;
import com.hxkj.common.mapper.system.SystemLogOperateMapper;
import com.hxkj.common.utils.StringUtil;
import com.hxkj.common.utils.TimeUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ISystemLogServerImpl implements ISystemLogServer {

    @Resource
    SystemLogOperateMapper logOperateMapper;

    @Resource
    SystemLogLoginMapper logLoginMapper;

    /**
     * 系统操作日志
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<LogOperateVo>
     */
    @Override
    public PageResult<LogOperateVo> operate(PageParam pageParam, Map<String, String> params) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

        MPJQueryWrapper<SystemLogOperate> mpjQueryWrapper = new MPJQueryWrapper<SystemLogOperate>()
                .selectAll(SystemLogOperate.class)
                .select("sa.username,sa.nickname")
                .leftJoin("ls_system_admin sa ON sa.id=t.admin_id")
                .orderByDesc("id");

        IPage<LogOperateVo> iPage = logOperateMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                LogOperateVo.class,
                mpjQueryWrapper);

        logOperateMapper.setSearch(mpjQueryWrapper, params, new String[]{
                "like:title:str",
                "like:username:str",
                "=:type:int",
                "=:status:int",
                "datetime:startTime-endTime@create_time:str"
        });

        for (LogOperateVo vo : iPage.getRecords()) {
            vo.setTaskTime(vo.getTaskTime());
            vo.setStartTime(TimeUtil.timestampToDate(vo.getStartTime()));
            vo.setEndTime(TimeUtil.timestampToDate(vo.getEndTime()));
            vo.setCreateTime(TimeUtil.timestampToDate(vo.getCreateTime()));
            vo.setError(StringUtil.isNull(vo.getError()) ? "" : vo.getError());
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), iPage.getRecords());
    }

    /**
     * 系统登录日志
     *
     * @author fzr
     * @param pageParam 分页参数
     * @param params 搜索参数
     * @return PageResult<LogLoginVo>
     */
    @Override
    public PageResult<LogLoginVo> login(PageParam pageParam, Map<String, String> params) {
        Integer pageNo   = pageParam.getPageNo();
        Integer pageSize = pageParam.getPageSize();

        QueryWrapper<SystemLogLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        logLoginMapper.setSearch(queryWrapper, params, new String[]{
                "like:username:str",
                "=:status:int",
                "datetime:startTime-endTime@create_time:str"
        });

        IPage<SystemLogLogin> iPage = logLoginMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<LogLoginVo> voList = new LinkedList<>();
        for (SystemLogLogin item : iPage.getRecords()) {
            LogLoginVo vo = new LogLoginVo();
            BeanUtils.copyProperties(item, vo);

            vo.setCreateTime(TimeUtil.timestampToDate(item.getCreateTime()));
            voList.add(vo);
        }



        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), voList);
    }

}
