package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.mdd.admin.service.ISystemLogsServer;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.validate.system.SystemSearchLoginsValidate;
import com.mdd.admin.validate.system.SystemSearchOperateValidate;
import com.mdd.admin.vo.system.SystemLogLoginVo;
import com.mdd.admin.vo.system.SystemLogOperateVo;
import com.mdd.common.config.GlobalConfig;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.system.SystemLogLogin;
import com.mdd.common.entity.system.SystemLogOperate;
import com.mdd.common.mapper.system.SystemLogLoginMapper;
import com.mdd.common.mapper.system.SystemLogOperateMapper;
import com.mdd.common.util.StringUtils;
import com.mdd.common.util.TimeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 系统日志服务实现类
 */
@Service
public class SystemLogsServerImpl implements ISystemLogsServer {

    @Resource
    SystemLogOperateMapper logOperateMapper;

    @Resource
    SystemLogLoginMapper logLoginMapper;

    /**
     * 系统操作日志
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<LogOperateVo>
     */
    @Override
    public PageResult<SystemLogOperateVo> operate(PageValidate pageValidate, SystemSearchOperateValidate searchValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        MPJQueryWrapper<SystemLogOperate> mpjQueryWrapper = new MPJQueryWrapper<SystemLogOperate>()
                .selectAll(SystemLogOperate.class)
                .select("sa.username,sa.nickname")
                .leftJoin("?_system_auth_admin sa ON sa.id=t.admin_id".replace("?_", GlobalConfig.tablePrefix))
                .orderByDesc("id");

        logOperateMapper.setSearch(mpjQueryWrapper, searchValidate, new String[]{
                "like:title:str",
                "like:username@sa.username:str",
                "like:ip:str",
                "=:type:str",
                "=:status:int",
                "=:url:str",
                "datetime:startTime-endTime@t.create_time:str"
        });

        IPage<SystemLogOperateVo> iPage = logOperateMapper.selectJoinPage(
                new Page<>(pageNo, pageSize),
                SystemLogOperateVo.class,
                mpjQueryWrapper);

        for (SystemLogOperateVo vo : iPage.getRecords()) {
            vo.setTaskTime(vo.getTaskTime());
            vo.setStartTime(TimeUtils.timestampToDate(vo.getStartTime()));
            vo.setEndTime(TimeUtils.timestampToDate(vo.getEndTime()));
            vo.setCreateTime(TimeUtils.timestampToDate(vo.getCreateTime()));
            vo.setError(StringUtils.isNull(vo.getError()) ? "" : vo.getError());
        }

        return PageResult.iPageHandle(iPage);
    }

    /**
     * 系统登录日志
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @param searchValidate 搜索参数
     * @return PageResult<LogLoginVo>
     */
    @Override
    public PageResult<SystemLogLoginVo> login(PageValidate pageValidate, SystemSearchLoginsValidate searchValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        QueryWrapper<SystemLogLogin> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        logLoginMapper.setSearch(queryWrapper, searchValidate, new String[]{
                "like:username:str",
                "=:status:int",
                "datetime:startTime-endTime@create_time:str"
        });

        IPage<SystemLogLogin> iPage = logLoginMapper.selectPage(new Page<>(pageNo, pageSize), queryWrapper);

        List<SystemLogLoginVo> list = new LinkedList<>();
        for (SystemLogLogin item : iPage.getRecords()) {
            SystemLogLoginVo vo = new SystemLogLoginVo();
            BeanUtils.copyProperties(item, vo);

            vo.setCreateTime(TimeUtils.timestampToDate(item.getCreateTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

}
