package com.mdd.front.service;

import com.mdd.common.mapper.DecoratePageMapper;
import com.mdd.common.mapper.DecorateTabbarMapper;
import com.mdd.common.mapper.article.ArticleMapper;
import com.mdd.common.mapper.setting.HotSearchMapper;

import javax.annotation.Resource;
import java.util.Map;

public interface IPcService {




    Map<String,Object> index();
    /**
     * 配置
     * @author cjh
     * @return Map<String, Object>
     */
    Map<String, Object> getConfig();
}
