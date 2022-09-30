package com.mdd.admin.service.channel;

import com.mdd.admin.validate.channel.ChannelMpParam;
import com.mdd.admin.vo.channel.ChannelMpVo;

import java.util.Map;

/**
 * 微信小程序渠道服务接口类
 */
public interface IChannelMpService {

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
     * @param channelMpParam
     */
    void save(ChannelMpParam channelMpParam);

}
