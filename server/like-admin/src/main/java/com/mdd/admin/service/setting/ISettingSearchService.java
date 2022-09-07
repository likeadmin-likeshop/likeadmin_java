package com.mdd.admin.service.setting;

import java.util.Map;

/**
 * 热门搜索服务接口类
 */
public interface ISettingSearchService {

    /**
     * 热门搜索详情
     *
     * @author fzr
     * @return Map<String, Object>
     */
    Map<String, Object> detail();

    /**
     * 热门搜索新增
     *
     * @author fzr
     * @param params 参数
     */
     void save(Map<String, Object> params);


}
