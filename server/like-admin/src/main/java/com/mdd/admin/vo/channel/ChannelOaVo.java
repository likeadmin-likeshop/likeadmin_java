package com.mdd.admin.vo.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("公众号渠道Vo")
public class ChannelOaVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "公众号名称")
    private String name;
    @ApiModelProperty(value = "原始ID")
    private String primaryId;
    @ApiModelProperty(value = "appId")
    private String appId;
    @ApiModelProperty(value = "appSecret")
    private String appSecret;
    @ApiModelProperty(value = "二维码")
    private String qrCode;
    @ApiModelProperty(value = "URL")
    private String url;
    @ApiModelProperty(value = "Token")
    private String token;
    @ApiModelProperty(value = "EncodingAESKey")
    private String encodingAesKey ;
    @ApiModelProperty(value = "消息加密方式")
    private Integer encryptionType;

    @ApiModelProperty(value = "业务域名")
    private String businessDomain;
    @ApiModelProperty(value = "JS接口安全域名")
    private String jsDomain;
    @ApiModelProperty(value = "网页授权域名")
    private String webDomain;

}
