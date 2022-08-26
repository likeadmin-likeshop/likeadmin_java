package com.mdd.admin.service.channel;

import java.util.Map;

/**
 * 公众号渠道设置服务接口类
 */
public interface IChannelOaService {

    /**
     * 公众号设置详情
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, Object> detail();

    /**
     * 公众号设置保存
     *
     * @author fzr
     */
    void save(Map<String, String> param);

}
