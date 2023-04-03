package com.mdd.admin.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.IDecorateTabbarService;
import com.mdd.admin.validate.decorate.DecorateTabsValidate;
import com.mdd.admin.vo.decorate.DecorateTabsListsVo;
import com.mdd.admin.vo.decorate.DecorateTabbarVo;
import com.mdd.common.entity.decorate.DecorateTabbar;
import com.mdd.common.mapper.decorate.DecorateTabbarMapper;
import com.mdd.common.util.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 底部导航服务实现类
 */
@Service
public class DecorateTabbarServiceImpl implements IDecorateTabbarService {

    @Resource
    DecorateTabbarMapper decorateTabbarMapper;

    /**
     * 底部导航详情
     *
     * @author fzr
     * @return DecorateTabbarVo
     */
    @Override
    public DecorateTabbarVo detail() {

        List<DecorateTabbar> list = decorateTabbarMapper.selectList(
                new QueryWrapper<DecorateTabbar>()
                    .orderByAsc("id"));

        List<DecorateTabsListsVo> tabList = new LinkedList<>();
        for (DecorateTabbar tab: list) {
            DecorateTabsListsVo vo = new DecorateTabsListsVo();
            vo.setId(tab.getId());
            vo.setName(tab.getName());
            vo.setSelected(UrlUtils.toAbsoluteUrl(tab.getSelected()));
            vo.setUnselected(UrlUtils.toAbsoluteUrl(tab.getUnselected()));
            vo.setLink(JSON.parse(tab.getLink()));
            vo.setCreateTime(TimeUtils.timestampToDate(tab.getCreateTime()));
            vo.setUpdateTime(TimeUtils.timestampToDate(tab.getUpdateTime()));
            tabList.add(vo);
        }

        String tabbar = ConfigUtils.get("tabbar", "style", "{}");

        DecorateTabbarVo response = new DecorateTabbarVo();
        response.setStyle(MapUtils.jsonToMap(tabbar));
        response.setList(tabList);
        return response;
    }

    /**
     * 底部导航保存
     *
     * @author fzr
     * @param tabsValidate 参数
     */
    @Override
    @Transactional
    public void save(DecorateTabsValidate tabsValidate) {
        decorateTabbarMapper.delete(new QueryWrapper<DecorateTabbar>().gt("id", 0));

        for (DecorateTabsListsVo obj : tabsValidate.getList()) {
            DecorateTabbar model = new DecorateTabbar();
            model.setName(obj.getName());
            model.setLink(JSON.toJSONString(obj.getLink()));
            model.setSelected(UrlUtils.toRelativeUrl(obj.getSelected()));
            model.setUnselected(UrlUtils.toRelativeUrl(obj.getUnselected()));
            model.setCreateTime(System.currentTimeMillis() / 1000);
            model.setUpdateTime(System.currentTimeMillis() / 1000);
            decorateTabbarMapper.insert(model);
        }

        if (StringUtils.isNotNull(tabsValidate.getStyle())) {
            ConfigUtils.set("tabbar", "style", JSON.toJSONString(tabsValidate.getStyle()));
        }
    }

}
