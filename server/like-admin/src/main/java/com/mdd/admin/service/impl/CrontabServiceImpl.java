package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.ICrontabService;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.CrontabDetailVo;
import com.mdd.admin.vo.CrontabListedVo;
import com.mdd.admin.vo.article.ArticleListedVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.Crontab;
import com.mdd.common.mapper.CrontabMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 计划任务服务实现类
 */
@Service
public class CrontabServiceImpl implements ICrontabService {

    @Resource
    CrontabMapper crontabMapper;

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
        }

        return PageResult.iPageHandle(iPage.getTotal(), iPage.getCurrent(), iPage.getSize(), list);
    }

    @Override
    public CrontabDetailVo detail() {
        return null;
    }

    @Override
    public void add() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void del() {

    }

}
