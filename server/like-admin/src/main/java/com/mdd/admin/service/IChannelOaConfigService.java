package com.mdd.admin.service;

import com.mdd.admin.validate.channel.ChannelOaValidate;
import com.mdd.admin.vo.channel.ChannelOaVo;


/**
 * 公众号渠道设置服务接口类
 */
public interface IChannelOaConfigService {

    /**
     * 公众号设置详情
     *
     * @author fzr
     * @return ChannelOaVo
     */
    ChannelOaVo detail();

    /**
     * 公众号设置保存
     *
     * @author fzr
     * @param channelOaValidate 参数
     */
    void save(ChannelOaValidate channelOaValidate);

}
