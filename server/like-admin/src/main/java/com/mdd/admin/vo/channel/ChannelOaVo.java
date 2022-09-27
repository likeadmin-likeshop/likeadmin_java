package com.mdd.admin.vo.channel;

import lombok.Data;

/**
 * 公众号渠道Vo
 */
@Data
public class ChannelOaVo {

    private String name;
    private String primaryId;
    private String appId;
    private String appSecret;
    private String qrCode;
    private String url;
    private String token;
    private String encodingAesKey ;
    private Integer encryptionType;

    private String businessDomain;
    private String jsDomain;
    private String webDomain;

}
