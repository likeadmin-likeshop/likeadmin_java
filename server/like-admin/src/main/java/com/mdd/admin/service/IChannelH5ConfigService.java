package com.mdd.admin.service;

import com.mdd.admin.validate.channel.ChannelH5Validate;
import com.mdd.admin.vo.channel.ChannelH5Vo;

/**
 * H5渠道设置服务接口类
 */
public interface IChannelH5ConfigService {

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
     * @param channelH5Validate 参数
     */
    void save(ChannelH5Validate channelH5Validate);

}
