package com.mdd.admin.vo.channel;

import lombok.Data;

import java.io.Serializable;

/**
 * 开发平台Vo
 */
@Data
public class ChannelOpVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String appId;
    private String appSecret;

}
