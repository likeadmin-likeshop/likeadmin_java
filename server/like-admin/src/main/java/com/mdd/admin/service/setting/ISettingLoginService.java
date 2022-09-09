package com.mdd.admin.service.setting;

import java.util.Map;

/**
 * 登录设置服务接口类
 */
public interface ISettingLoginService {

    /**
     * 登录设置详情
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, Object> detail();

    /**
     * 登录设置保存
     *
     * @author fzr
     * @param params 参数
     */
    void save(Map<String, String> params);

}
