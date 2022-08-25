package com.mdd.admin.service.channel;

import java.util.Map;

/**
 * 微信开放平台设置服务接口类
 */
public interface IChannelWxService {

    /**
     * 微信开放平台设置详情
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, Object> detail();

    /**
     * 微信开放平台设置保存
     *
     * @author fzr
     */
    void save(Map<String, String> param);

}
