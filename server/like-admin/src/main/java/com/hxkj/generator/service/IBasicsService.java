package com.hxkj.generator.service;

import java.util.Map;

/**
 * 基础配置服务接口类
 */
public interface IBasicsService {

    /**
     * 获取网站信息
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, String> getWebsite();

    /**
     * 获取版权信息
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, String> getCopyright();

    /**
     * 设置网站信息
     *
     * @author fzr
     * @param params 参数
     */
    void setWebsite(Map<String, String> params);

    /**
     * 设置版权信息
     *
     * @author fzr
     * @param params 参数
     */
    void setCopyright(Map<String, String> params);

}
