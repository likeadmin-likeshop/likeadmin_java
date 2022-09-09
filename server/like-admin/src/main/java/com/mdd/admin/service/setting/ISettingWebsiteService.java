package com.mdd.admin.service.setting;

import java.util.Map;

/**
 * 网站信息服务接口类
 */
public interface ISettingWebsiteService {

    /**
     * 获取网站信息
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, String> detail();

    /**
     * 保存网站信息
     *
     * @author fzr
     * @param params 参数
     */
    void save(Map<String, String> params);

}
