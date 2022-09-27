package com.mdd.admin.service.channel;

import com.mdd.admin.validate.channel.ChannelWxParam;
import com.mdd.admin.vo.channel.ChannelWxVo;

import java.util.Map;

/**
 * 微信开放平台设置服务接口类
 */
public interface IChannelWxService {

    /**
     * 微信开放平台设置详情
     *
     * @author fzr
     * @return ChannelWxVo
     */
    ChannelWxVo detail();

    /**
     * 微信开放平台设置保存
     *
     * @author fzr
     * @param channelWxParam 参数
     */
    void save(ChannelWxParam channelWxParam);

}
