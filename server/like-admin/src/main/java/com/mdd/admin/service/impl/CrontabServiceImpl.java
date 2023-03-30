package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.config.quartz.QuartzUtils;
import com.mdd.admin.service.ICrontabService;
import com.mdd.admin.validate.crontab.CrontabCreateValidate;
import com.mdd.admin.validate.crontab.CrontabUpdateValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.CrontabDetailVo;
import com.mdd.admin.vo.CrontabListedVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.Crontab;
import com.mdd.common.mapper.CrontabMapper;
import com.mdd.common.util.TimeUtils;
import org.quartz.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 计划任务服务实现类
 */
@Service
public class CrontabServiceImpl implements ICrontabService {

    @Resource
    Scheduler scheduler;

    @Resource
    CrontabMapper crontabMapper;

    /**
     * 项目启动初始化任务
     *
     * @author fzr
     * @throws SchedulerException 异常
     */
    @PostConstruct
    public void init() throws SchedulerException {
        scheduler.clear();
        List<Crontab> jobs = crontabMapper.selectList(new QueryWrapper<Crontab>().eq("is_delete", 0));
        for (Crontab crontab : jobs) {
            QuartzUtils.createScheduleJob(scheduler, crontab);
        }
    }

    /**
     * 计划任务列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<CrontabListedVo>
     */
    @Override
    public PageResult<CrontabListedVo> list(PageValidate pageValidate) {
        Integer pageNo   = pageValidate.getPageNo();
        Integer pageSize = pageValidate.getPageSize();

        IPage<Crontab> iPage = crontabMapper.selectPage(new Page<>(pageNo, pageSize),
                new QueryWrapper<Crontab>()
                    .eq("is_delete", 0)
                    .orderByDesc("id"));

        List<CrontabListedVo> list = new LinkedList<>();
        for (Crontab crontab : iPage.getRecords()) {
            CrontabListedVo vo = new CrontabListedVo();
            BeanUtils.copyProperties(crontab, vo);

            vo.setStartTime(crontab.getStartTime()<=0?"-": TimeUtils.timestampToDate(crontab.getStartTime()));
            vo.setEndTime(crontab.getEndTime()<=0?"-": TimeUtils.timestampToDate(crontab.getEndTime()));
            list.add(vo);
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    /**
     * 计划任务详情
     *
     * @author fzr
     * @param id 主键
     * @return CrontabDetailVo
     */
    @Override
    public CrontabDetailVo detail(Integer id) {
        Crontab crontab = crontabMapper.selectOne(
                new QueryWrapper<Crontab>()
                        .eq("id", id)
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(crontab, "数据不存在!");

        CrontabDetailVo vo = new CrontabDetailVo();
        BeanUtils.copyProperties(crontab, vo);
        return vo;
    }

    /**
     * 计划任务新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    @Override
    @Transactional
    public void add(CrontabCreateValidate createValidate) throws SchedulerException {
        Crontab crontab = new Crontab();
        crontab.setName(createValidate.getName());
        crontab.setTypes(createValidate.getTypes());
        crontab.setCommand(createValidate.getCommand());
        crontab.setRules(createValidate.getRules());
        crontab.setStatus(createValidate.getStatus());
        crontab.setRemark(createValidate.getRemark());
        crontab.setStrategy(createValidate.getStrategy());
        crontab.setConcurrent(createValidate.getConcurrent());
        crontab.setCreateTime(System.currentTimeMillis() / 1000);
        crontab.setUpdateTime(System.currentTimeMillis() / 1000);
        crontabMapper.insert(crontab);

        QuartzUtils.createScheduleJob(scheduler, crontab);
    }

    /**
     * 计划任务编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    @Override
    @Transactional
    public void edit(CrontabUpdateValidate updateValidate) throws SchedulerException {
        Crontab crontab = crontabMapper.selectOne(
                new QueryWrapper<Crontab>()
                        .eq("id", updateValidate.getId())
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(crontab, "数据不存在!");

        crontab.setName(updateValidate.getName());
        crontab.setCommand(updateValidate.getCommand());
        crontab.setTypes(updateValidate.getTypes());
        crontab.setRules(updateValidate.getRules());
        crontab.setStatus(updateValidate.getStatus());
        crontab.setRemark(updateValidate.getRemark());
        crontab.setStrategy(updateValidate.getStrategy());
        crontab.setConcurrent(updateValidate.getConcurrent());
        crontab.setUpdateTime(System.currentTimeMillis() / 1000);
        crontabMapper.updateById(crontab);

        QuartzUtils.createScheduleJob(scheduler, crontab);
    }

    /**
     * 计划任务删除
     *
     * @author fzr
     * @param id 主键
     */
    @Override
    @Transactional
    public void del(Integer id) throws SchedulerException {
        Crontab crontab = crontabMapper.selectOne(
                new QueryWrapper<Crontab>()
                        .eq("id", id)
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(crontab, "数据不存在!");

        crontab.setIsDelete(1);
        crontab.setDeleteTime(System.currentTimeMillis() / 1000);
        crontabMapper.updateById(crontab);

        scheduler.deleteJob(QuartzUtils.getJobKey(crontab.getId(), crontab.getTypes()));
    }

}
