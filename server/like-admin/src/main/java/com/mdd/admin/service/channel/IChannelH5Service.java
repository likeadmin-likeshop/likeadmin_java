package com.mdd.admin.service.channel;

import java.util.Map;

/**
 * H5渠道设置服务接口类
 */
public interface IChannelH5Service {

    /**
     * H5设置详情
     *
     * @author fzr
     * @return Map<String, String>
     */
    Map<String, Object> detail();

    /**
     * H5设置保存
     *
     * @author fzr
     */
    void save(Map<String, String> param);

}
