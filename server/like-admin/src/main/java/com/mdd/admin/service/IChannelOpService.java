package com.mdd.admin.service;

import com.mdd.admin.validate.channel.ChannelOpValidate;
import com.mdd.admin.vo.channel.ChannelOpVo;

/**
 * 微信开放渠道设置接口服务类
 */
public interface IChannelOpService {

    /**
     * 开放平台设置详情
     *
     * @author fzr
     * @return ChannelOpVo
     */
    ChannelOpVo detail();

    /**
     * 开放平台设置保存
     *
     * @author fzr
     * @param opValidate 参数
     */
    void save(ChannelOpValidate opValidate);

}
