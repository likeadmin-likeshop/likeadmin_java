package com.mdd.admin.service.setting.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.setting.ISettingSearchService;
import com.mdd.common.entity.setting.HotSearch;
import com.mdd.common.mapper.setting.HotSearchMapper;
import com.mdd.common.utils.ArrayUtil;
import com.mdd.common.utils.ConfigUtil;
import com.mdd.common.utils.ToolsUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 热门搜索服务实现类
 */
@Service
public class SettingSearchServiceImpl implements ISettingSearchService {

    @Resource
    HotSearchMapper hotSearchMapper;

    /**
     * 热门搜索详情
     *
     * @author fzr
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> detail() {
        Map<String, Object> response = new LinkedHashMap<>();

        Integer isHotSearch = Integer.parseInt(ConfigUtil.get("search", "isHotSearch", "0"));
        response.put("isHotSearch", isHotSearch);

        List<HotSearch> list = hotSearchMapper.selectList(new QueryWrapper<HotSearch>().orderByDesc("sort"));
        response.put("list", list);

        return response;
    }

    /**
     * 热门搜索新增
     *
     * @author fzr
     * @param params 参数
     */
    @Override
    public void save(Map<String, Object> params) {
        String isHotSearch = params.getOrDefault("isHotSearch", "0").toString();
        ConfigUtil.set("search", "isHotSearch", isHotSearch);

        hotSearchMapper.delete(new QueryWrapper<HotSearch>().ge("id", 0));
        for (String obj : ArrayUtil.objectToListAsStr(params.get("list"))) {
            Map<String, String> item = ToolsUtil.jsonToMap(obj);
            HotSearch hotSearch = new HotSearch();
            hotSearch.setName(item.getOrDefault("name", ""));
            hotSearch.setSort(Integer.parseInt(item.getOrDefault("sort", "0")));
            hotSearchMapper.insert(hotSearch);
        }
    }

}
