package com.mdd.front.service;

import java.util.Map;

public interface IPcService {

    /**
     * 配置
     * @author cjh
     * @return Map<String, Object>
     */
    Map<String, Object> index();

    /**
     * 配置
     * @author cjh
     * @return Map<String, Object>
     */
    Map<String, Object> getConfig();
}
