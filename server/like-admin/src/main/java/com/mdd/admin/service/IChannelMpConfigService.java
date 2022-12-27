package com.mdd.admin.service;

import com.mdd.admin.validate.channel.ChannelMpValidate;
import com.mdd.admin.vo.channel.ChannelMpVo;

/**
 * 微信小程序渠道服务接口类
 */
public interface IChannelMpConfigService {

    /**
     * 微信小程序设置详情
     *
     * @author fzr
     * @return ChannelMpVo
     */
    ChannelMpVo detail();

    /**
     * 微信小程序设置保存
     *
     * @author fzr
     * @param channelMpValidate 参数
     */
    void save(ChannelMpValidate channelMpValidate);

}
