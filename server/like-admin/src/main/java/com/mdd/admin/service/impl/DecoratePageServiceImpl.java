package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.IDecoratePageService;
import com.mdd.admin.validate.decorate.DecoratePageValidate;
import com.mdd.admin.vo.decorate.DecoratePageVo;
import com.mdd.common.entity.decorate.DecoratePage;
import com.mdd.common.mapper.decorate.DecoratePageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DecoratePageServiceImpl implements IDecoratePageService {

    @Resource
    DecoratePageMapper decoratePageMapper;

    /**
     * 页面装修详情
     *
     * @author fzr
     * @param id 主键
     * @return DecoratePageVo
     */
    @Override
    public DecoratePageVo detail(Integer id) {
        DecoratePage decoratePage = decoratePageMapper.selectById(id);
        Assert.notNull(decoratePage, "数据不存在!");

        DecoratePageVo vo = new DecoratePageVo();
        vo.setId(decoratePage.getId());
        vo.setPageType(decoratePage.getPageType());
        vo.setPageData(decoratePage.getPageData());
        return vo;
    }

    /**
     * 页面装修保存
     *
     * @author fzr
     * @param decoratePageValidate 参数
     */
    @Override
    public void save(DecoratePageValidate decoratePageValidate) {
        DecoratePage decoratePage = decoratePageMapper.selectById(decoratePageValidate.getId());
        Assert.notNull(decoratePage, "数据不存在!");

        decoratePage.setPageData(decoratePageValidate.getPageData());
        decoratePage.setUpdateTime(System.currentTimeMillis() / 1000);
        decoratePageMapper.updateById(decoratePage);
    }

}
