package com.mdd.admin.vo.channel;

import lombok.Data;

/**
 * 小程序渠道Vo
 */
@Data
public class ChannelMpVo {

    private String name;
    private String primaryId;
    private String appId;
    private String appSecret;
    private String qrCode;

    private String requestDomain;
    private String socketDomain;
    private String uploadFileDomain;
    private String downloadFileDomain;
    private String udpDomain;
    private String tcpDomain;
    private String businessDomain;

}
