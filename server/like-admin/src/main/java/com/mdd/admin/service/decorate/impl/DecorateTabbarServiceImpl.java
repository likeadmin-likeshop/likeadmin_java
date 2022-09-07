package com.mdd.admin.service.decorate.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.decorate.IDecorateTabbarService;
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
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail() {
        Map<String, Object> response = new LinkedHashMap<>();
        List<DecorateTabbar> list = decorateTabbarMapper.selectList(
                new QueryWrapper<DecorateTabbar>()
                    .orderByAsc("id"));

        List<Map<String, Object>> tabList = new LinkedList<>();
        for (DecorateTabbar tab: list) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", tab.getId());
            map.put("name", tab.getName());
            map.put("selected", UrlUtil.toAbsoluteUrl(tab.getSelected()));
            map.put("unselected", UrlUtil.toAbsoluteUrl(tab.getUnselected()));
            map.put("link", tab.getLink());
            map.put("createTime", TimeUtil.timestampToDate(tab.getCreateTime()));
            map.put("updateTime", TimeUtil.timestampToDate(tab.getUpdateTime()));
            tabList.add(map);
        }

        String tabbar = ConfigUtil.get("tabbar", "style", "{}");
        response.put("style", ToolsUtil.jsonToMap(tabbar));
        response.put("list", tabList);
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
            System.out.println(item);
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
