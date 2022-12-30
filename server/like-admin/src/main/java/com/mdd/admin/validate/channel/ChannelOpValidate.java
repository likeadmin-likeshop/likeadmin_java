package com.mdd.admin.validate.channel;

import lombok.Data;

import java.io.Serializable;

/**
 * 开发平台渠道参数
 */
@Data
public class ChannelOpValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;

    private String appSecret;

}
