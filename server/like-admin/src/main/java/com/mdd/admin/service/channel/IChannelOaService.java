package com.mdd.admin.service.channel;

import com.mdd.admin.validate.channel.ChannelOaParam;
import com.mdd.admin.vo.channel.ChannelOaVo;


/**
 * 公众号渠道设置服务接口类
 */
public interface IChannelOaService {

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
     * @param channelOaParam 参数
     */
    void save(ChannelOaParam channelOaParam);

}
