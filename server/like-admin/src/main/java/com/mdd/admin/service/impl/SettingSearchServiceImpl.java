package com.mdd.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.ISettingSearchService;
import com.mdd.admin.validate.setting.SettingSearchValidate;
import com.mdd.admin.vo.setting.SettingSearchDetailVo;
import com.mdd.admin.vo.setting.SettingSearchObjectVo;
import com.mdd.common.entity.setting.HotSearch;
import com.mdd.common.mapper.setting.HotSearchMapper;
import com.mdd.common.util.ConfigUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
     * @return SettingSearchDetailVo
     */
    @Override
    public SettingSearchDetailVo detail() {
        Integer isHotSearch = Integer.parseInt(ConfigUtils.get("search", "isHotSearch", "0"));
        List<HotSearch> list = hotSearchMapper.selectList(new QueryWrapper<HotSearch>().orderByDesc("sort"));

        SettingSearchDetailVo vo = new SettingSearchDetailVo();
        vo.setIsHotSearch(isHotSearch);
        vo.setList(list);
        return vo;
    }

    /**
     * 热门搜索新增
     *
     * @author fzr
     * @param searchValidate 参数
     */
    @Override
    public void save(SettingSearchValidate searchValidate) {
        String isHotSearch = String.valueOf(searchValidate.getIsHotSearch());
        ConfigUtils.set("search", "isHotSearch", isHotSearch);

        hotSearchMapper.delete(new QueryWrapper<HotSearch>().ge("id", 0));
        for (SettingSearchObjectVo vo : searchValidate.getList()) {
            HotSearch hotSearch = new HotSearch();
            hotSearch.setName(vo.getName());
            hotSearch.setSort(vo.getSort());
            hotSearchMapper.insert(hotSearch);
        }
    }

}
