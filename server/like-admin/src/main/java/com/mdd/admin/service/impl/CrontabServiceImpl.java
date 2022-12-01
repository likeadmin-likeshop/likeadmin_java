package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mdd.admin.service.ICrontabService;
import com.mdd.admin.validate.CrontabCreateValidate;
import com.mdd.admin.validate.CrontabUpdateValidate;
import com.mdd.admin.validate.commons.PageValidate;
import com.mdd.admin.vo.CrontabDetailVo;
import com.mdd.admin.vo.CrontabListedVo;
import com.mdd.common.core.PageResult;
import com.mdd.common.entity.Crontab;
import com.mdd.common.entity.server.Sys;
import com.mdd.common.mapper.CrontabMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    public void add(CrontabCreateValidate createValidate) {
        Crontab crontab = new Crontab();
        crontab.setName(createValidate.getName());
        crontab.setCommand(createValidate.getCommand());
        crontab.setRules(createValidate.getRules());
        crontab.setStatus(createValidate.getStatus());
        crontab.setRemark(createValidate.getRemark());
        crontab.setCreateTime(System.currentTimeMillis() / 1000);
        crontab.setUpdateTime(System.currentTimeMillis() / 1000);
        crontabMapper.insert(crontab);
    }

    /**
     * 计划任务编辑
     *
     * @author fzr
     * @param updateValidate 参数
     */
    @Override
    public void edit(CrontabUpdateValidate updateValidate) {
        Crontab crontab = crontabMapper.selectOne(
                new QueryWrapper<Crontab>()
                        .eq("id", updateValidate.getId())
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(crontab, "数据不存在!");

        crontab.setName(updateValidate.getName());
        crontab.setCommand(updateValidate.getCommand());
        crontab.setRules(updateValidate.getRules());
        crontab.setStatus(updateValidate.getStatus());
        crontab.setRemark(updateValidate.getRemark());
        crontab.setUpdateTime(System.currentTimeMillis() / 1000);
        crontabMapper.updateById(crontab);
    }

    @Override
    public void del(Integer id) {
        Crontab crontab = crontabMapper.selectOne(
                new QueryWrapper<Crontab>()
                        .eq("id", id)
                        .eq("is_delete", 0)
                        .last("limit 1"));

        Assert.notNull(crontab, "数据不存在!");

        crontab.setIsDelete(1);
        crontab.setDeleteTime(System.currentTimeMillis() / 1000);
        crontabMapper.updateById(crontab);
    }

}
