package com.mdd.admin.service.decorate.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.admin.service.decorate.IDecoratePageService;
import com.mdd.admin.validate.decorate.DecoratePageParam;
import com.mdd.common.entity.decorate.DecoratePage;
import com.mdd.common.mapper.decorate.DecoratePageMapper;
import com.mdd.common.utils.ToolsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DecoratePageServiceImpl implements IDecoratePageService {

    @Resource
    DecoratePageMapper decoratePageMapper;

    /**
     * 页面装修详情
     *
     * @author fzr
     * @param id 主键
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail(Integer id) {
        DecoratePage decoratePage = decoratePageMapper.selectById(id);
        Assert.notNull(decoratePage, "数据不存在!");

        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", decoratePage.getId());
        map.put("pageType", decoratePage.getPageType());
        map.put("pageData", decoratePage.getPageData());
        return map;
    }

    /**
     * 页面装修保存
     *
     * @author fzr
     * @param decoratePageParam 参数
     */
    @Override
    public void save(DecoratePageParam decoratePageParam) {
        DecoratePage decoratePage = decoratePageMapper.selectById(decoratePageParam.getId());
        Assert.notNull(decoratePage, "数据不存在!");

        decoratePage.setPageData(decoratePageParam.getPageData());
        decoratePage.setUpdateTime(System.currentTimeMillis() / 1000);
        decoratePageMapper.updateById(decoratePage);
    }

}
