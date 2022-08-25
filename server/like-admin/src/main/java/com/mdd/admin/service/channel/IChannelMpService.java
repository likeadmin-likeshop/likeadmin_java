package com.mdd.admin.service.channel;

import java.util.Map;

/**
 * 微信小程序渠道服务接口类
 */
public interface IChannelMpService {

    /**
     * 微信小程序设置详情
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, Object> detail();

    /**
     * 微信小程序设置保存
     *
     * @author fzr
     */
    void save(Map<String, String> param);

}
