package com.mdd.admin.service.impl;

import com.mdd.admin.service.IChannelOpService;
import com.mdd.admin.validate.channel.ChannelOpValidate;
import com.mdd.admin.vo.channel.ChannelOpVo;
import com.mdd.common.util.ConfigUtils;
import org.springframework.stereotype.Service;

/**
 * 开放平台设置服务类
 */
@Service
public class ChannelOpServiceImpl implements IChannelOpService {

    /**
     * 开放平台设置详情
     *
     * @author fzr
     * @return ChannelOpVo
     */
    @Override
    public ChannelOpVo detail() {
        String appId = ConfigUtils.get("op_channel", "appId", "");
        String appSecret = ConfigUtils.get("op_channel", "appSecret", "");

        ChannelOpVo vo = new ChannelOpVo();
        vo.setAppId(appId);
        vo.setAppSecret(appSecret);
        return vo;
    }

    /**
     * 开放平台设置保存
     *
     * @author fzr
     * @param opValidate 参数
     */
    @Override
    public void save(ChannelOpValidate opValidate) {
        ConfigUtils.set("op_channel", "appId", opValidate.getAppId());
        ConfigUtils.set("op_channel", "appSecret", opValidate.getAppId());
    }

}
