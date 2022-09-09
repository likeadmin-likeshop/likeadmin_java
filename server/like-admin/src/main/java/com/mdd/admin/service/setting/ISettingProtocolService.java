package com.mdd.admin.service.setting;

import java.util.Map;

/**
 * 政策协议服务接口类
 */
public interface ISettingProtocolService {

    /**
     * 获取政策协议信息
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, Map<String, String>> detail();

    /**
     * 保存政策协议信息
     *
     * @author fzr
     * @param params 参数
     */
    void save(Map<String, Object> params);

}
