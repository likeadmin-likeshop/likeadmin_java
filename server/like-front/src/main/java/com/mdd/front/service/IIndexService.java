package com.mdd.front.service;

import java.util.Map;

/**
 * 首页服务接口类
 */
public interface IIndexService {

    /**
     * 首页
     *
     * @author fzr
     * @return Map<String, Object>
     */
    Map<String, Object> index();

    /**
     * 装修
     *
     * @author fzr
     * @return Map<String, Object>
     */
    Map<String, Object> decorate(Integer id);

    /**
     * 配置
     *
     * @author fzr
     * @return Map<String, Object>
     */
    Map<String, Object> config();

}
