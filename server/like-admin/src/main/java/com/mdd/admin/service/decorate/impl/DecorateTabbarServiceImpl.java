package com.mdd.admin.service.decorate.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mdd.admin.service.decorate.IDecorateTabbarService;
import com.mdd.common.entity.decorate.DecorateTabbar;
import com.mdd.common.mapper.decorate.DecorateTabbarMapper;
import com.mdd.common.utils.TimeUtil;
import com.mdd.common.utils.UrlUtil;
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
     * @return List<Map<String, Object>>
     */
    @Override
    public List<Map<String, Object>> detail() {
        List<Map<String, Object>> response = new LinkedList<>();
        List<DecorateTabbar> list = decorateTabbarMapper.selectList(
                new QueryWrapper<DecorateTabbar>()
                    .orderByAsc("id"));

        for (DecorateTabbar tab: list) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("id", tab.getId());
            map.put("name", tab.getName());
            map.put("selected", UrlUtil.toAbsoluteUrl(tab.getSelected()));
            map.put("unselected", UrlUtil.toAbsoluteUrl(tab.getUnselected()));
            map.put("link", tab.getLink());
            map.put("createTime", TimeUtil.timestampToDate(tab.getCreateTime()));
            map.put("updateTime", TimeUtil.timestampToDate(tab.getUpdateTime()));
            response.add(map);
        }
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
    public void save(List<Map<String, String>> params) {
        decorateTabbarMapper.delete(new QueryWrapper<DecorateTabbar>().gt("id", 0));

        for (Map<String, String> item: params) {
            DecorateTabbar model = new DecorateTabbar();
            model.setName(item.get("name"));
            model.setSelected(UrlUtil.toRelativeUrl(item.get("selected")));
            model.setUnselected(UrlUtil.toRelativeUrl(item.get("unselected")));
            model.setLink(item.get("link"));
            model.setCreateTime(System.currentTimeMillis() / 1000);
            model.setUpdateTime(System.currentTimeMillis() / 1000);
            decorateTabbarMapper.insert(model);
        }
    }

}
