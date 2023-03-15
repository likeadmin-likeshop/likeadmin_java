package com.mdd.admin.vo.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("小程序渠道Vo")
public class ChannelMpVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "小程序名称")
    private String name;
    @ApiModelProperty(value = "原始ID")
    private String primaryId;
    @ApiModelProperty(value = "appId")
    private String appId;
    @ApiModelProperty(value = "appSecret")
    private String appSecret;
    @ApiModelProperty(value = "二维码")
    private String qrCode;

    @ApiModelProperty(value = "request合法域名")
    private String requestDomain;
    @ApiModelProperty(value = "socket合法域名")
    private String socketDomain;
    @ApiModelProperty(value = "uploadFile合法域名")
    private String uploadFileDomain;
    @ApiModelProperty(value = "downloadFile合法域名")
    private String downloadFileDomain;
    @ApiModelProperty(value = "udp合法域名")
    private String udpDomain;
    @ApiModelProperty(value = "tcp合法域名")
    private String tcpDomain;
    @ApiModelProperty(value = "业务域名")
    private String businessDomain;

}
