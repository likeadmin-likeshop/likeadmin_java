package com.mdd.admin.service.channel;

import com.mdd.admin.validate.channel.ChannelH5Param;
import com.mdd.admin.vo.channel.ChannelH5Vo;

import java.util.Map;

/**
 * H5渠道设置服务接口类
 */
public interface IChannelH5Service {

    /**
     * H5设置详情
     *
     * @author fzr
     * @return ChannelH5Vo
     */
    ChannelH5Vo detail();

    /**
     * H5设置保存
     *
     * @author fzr
     * @param channelH5Param 参数
     */
    void save(ChannelH5Param channelH5Param);

}
