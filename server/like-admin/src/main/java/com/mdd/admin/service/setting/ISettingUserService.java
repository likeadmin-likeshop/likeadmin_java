package com.mdd.admin.service.setting;

import java.util.Map;

/**
 * 用户设置服务接口类
 */
public interface ISettingUserService {

    /**
     * 用户设置详情
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, Object> detail();

    /**
     * 用户设置保存
     *
     * @author fzr
     * @param params 参数
     */
    void save(Map<String, String> params);

}
