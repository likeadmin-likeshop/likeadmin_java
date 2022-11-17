package com.mdd.admin.vo.channel;

import lombok.Data;

import java.io.Serializable;

/**
 * 小程序渠道Vo
 */
@Data
public class ChannelMpVo implements Serializable {

    private static final long serialVersionUID = 1L;

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
