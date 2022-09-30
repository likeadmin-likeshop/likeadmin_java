package com.mdd.admin.service.decorate.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.decorate.IDecorateTabbarService;
import com.mdd.admin.vo.decorate.DecorateTabObjVo;
import com.mdd.admin.vo.decorate.DecorateTabbarVo;
import com.mdd.common.entity.decorate.DecorateTabbar;
import com.mdd.common.mapper.decorate.DecorateTabbarMapper;
import com.mdd.common.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        List<DecorateTabObjVo> tabList = new LinkedList<>();
        for (DecorateTabbar tab: list) {
            DecorateTabObjVo vo = new DecorateTabObjVo();
            vo.setId(tab.getId());
            vo.setName(tab.getName());
            vo.setSelected(UrlUtil.toAbsoluteUrl(tab.getSelected()));
            vo.setUnselected(UrlUtil.toAbsoluteUrl(tab.getUnselected()));
            vo.setLink(tab.getLink());
            vo.setCreateTime(TimeUtil.timestampToDate(tab.getCreateTime()));
            vo.setUpdateTime(TimeUtil.timestampToDate(tab.getUpdateTime()));
            tabList.add(vo);
        }

        String tabbar = ConfigUtil.get("tabbar", "style", "{}");

        DecorateTabbarVo response = new DecorateTabbarVo();
        response.setStyle(ToolsUtil.jsonToMap(tabbar));
        response.setList(tabList);
        return response;
    }

    /**
     * 底部导航保存
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    @Transactional
    public void save(Map<String, Object> params) {
        decorateTabbarMapper.delete(new QueryWrapper<DecorateTabbar>().gt("id", 0));

        for (String obj : ArrayUtil.objectToListAsStr(params.get("list"))) {
            Map<String, String> item = ToolsUtil.jsonToMap(obj);
            DecorateTabbar model = new DecorateTabbar();
            model.setName(item.get("name"));
            model.setSelected(UrlUtil.toRelativeUrl(item.get("selected")));
            model.setUnselected(UrlUtil.toRelativeUrl(item.get("unselected")));
            model.setLink(item.get("link"));
            model.setCreateTime(System.currentTimeMillis() / 1000);
            model.setUpdateTime(System.currentTimeMillis() / 1000);
            decorateTabbarMapper.insert(model);
        }

        if (StringUtil.isNotNull(params.get("style"))) {
            ConfigUtil.set("tabbar", "style", JSON.toJSONString(params.get("style")));
        }

    }

}
