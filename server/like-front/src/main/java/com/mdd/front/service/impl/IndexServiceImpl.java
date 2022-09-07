package com.mdd.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.mdd.common.entity.decorate.DecoratePage;
import com.mdd.common.entity.decorate.DecorateTabbar;
import com.mdd.common.entity.setting.HotSearch;
import com.mdd.common.mapper.decorate.DecoratePageMapper;
import com.mdd.common.mapper.decorate.DecorateTabbarMapper;
import com.mdd.common.mapper.setting.HotSearchMapper;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.ToolsUtil;
import com.mdd.common.utils.UrlUtil;
import com.mdd.front.service.IIndexService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 首页服务实现类
 */
@Service
public class IndexServiceImpl implements IIndexService {

    @Resource
    DecoratePageMapper decoratePageMapper;

    @Resource
    DecorateTabbarMapper decorateTabbarMapper;

    @Resource
    HotSearchMapper hotSearchMapper;

    /**
     * 首页
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> index() {
        Map<String, Object> response = new LinkedHashMap<>();
        DecoratePage decoratePage = decoratePageMapper.selectOne(
                new QueryWrapper<DecoratePage>()
                    .eq("id", 1)
                    .last("limit 1"));

        response.put("domain", UrlUtil.domain());
        response.put("pages", decoratePage.getPageData());
        return response;
    }

    /**
     * 装修
     *
     * @author fzr
     * @param id 主键
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> decorate(Integer id) {
        DecoratePage decoratePage = decoratePageMapper.selectOne(
                new QueryWrapper<DecoratePage>()
                        .eq("id", id)
                        .last("limit 1"));

        Assert.notNull(decoratePage, "数据不存在!");

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("type", decoratePage.getPageType());
        response.put("name", decoratePage.getPageName());
        response.put("pages", decoratePage.getPageData());
        return response;
    }

    /**
     * 配置
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> config() {
        Map<String, Object> response = new LinkedHashMap<>();

        List<Map<String, String>> tabs = new LinkedList<>();
        List<DecorateTabbar> decorateTabbars = decorateTabbarMapper.selectList(new QueryWrapper<DecorateTabbar>().orderByAsc("id"));
        for (DecorateTabbar tab: decorateTabbars) {
            Map<String, String> map = new LinkedHashMap<>();
            map.put("name", tab.getName());
            map.put("selected", UrlUtil.toAbsoluteUrl(tab.getSelected()));
            map.put("unselected", UrlUtil.toAbsoluteUrl(tab.getUnselected()));
            map.put("link", tab.getLink());
            tabs.add(map);
        }

        String tabbarStyle = ConfigUtil.get("tabbar", "style", "{}");
        response.put("domain", UrlUtil.domain());
        response.put("style", ToolsUtil.jsonToMap(tabbarStyle));
        response.put("tabbar", tabs);
        return response;
    }

    /**
     * 政策
     *
     * @author fzr
     * @param type 类型 service=服务协议,privacy=隐私协议
     * @return Map<String, Object>
     */
    @Override
    public Map<String, String> policy(String type) {
        Map<String, String> map = ConfigUtil.getMap("protocol", type);
        if (map == null) {
            Map<String, String> m = new LinkedHashMap<>();
            m.put("name", "");
            m.put("content", "");
            return m;
        }
        return map;
    }

    /**
     * 热搜
     *
     * @author fzr
     * @return List<String>
     */
    @Override
    public List<String> search() {
        String isHotSearch = ConfigUtil.get("search", "isHotSearch", "0");

        List<String> list = new LinkedList<>();
        if (Integer.parseInt(isHotSearch) == 1) {
            List<HotSearch> hotSearches = hotSearchMapper.selectList(
                    new QueryWrapper<HotSearch>()
                        .orderByDesc(Arrays.asList("sort", "id")));

            for (HotSearch hotSearch : hotSearches) {
                list.add(hotSearch.getName());
            }
        }

        return list;
    }

}
