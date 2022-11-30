package com.mdd.admin.service;

import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.CrontabDetailVo;
import com.mdd.admin.vo.CrontabListedVo;
import com.mdd.common.core.PageResult;

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

    CrontabDetailVo detail();

    void add();

    void edit();

    void del();

}
