package com.mdd.admin.service;

import com.mdd.admin.validate.crontab.CrontabCreateValidate;
import com.mdd.admin.validate.crontab.CrontabUpdateValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.CrontabDetailVo;
import com.mdd.admin.vo.CrontabListedVo;
import com.mdd.common.core.PageResult;
import org.quartz.SchedulerException;

/**
 * 计划任务服务接口类
 */
public interface ICrontabService {

    /**
     * 计划任务列表
     *
     * @author fzr
     * @param pageValidate 分页参数
     * @return PageResult<CrontabListedVo>
     */
    PageResult<CrontabListedVo> list(PageValidate pageValidate);

    /**
     * 计划任务详情
     *
     * @author fzr
     * @param id 主键
     * @return CrontabDetailVo
     */
    CrontabDetailVo detail(Integer id);

    /**
     * 计划任务新增
     *
     * @author fzr
     * @param createValidate 参数
     */
    void add(CrontabCreateValidate createValidate) throws SchedulerException;

    /**
     * 计划任务编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    void edit(CrontabUpdateValidate updateValidate) throws SchedulerException;

    /**
     * 计划任务删除
     *
     * @author fzr
     * @param id 主键
     */
    void del(Integer id) throws SchedulerException;

}
