package com.mdd.admin.validate.channel;

import com.mdd.common.validator.annotation.IntegerContains;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@ApiModel("公众号渠道参数")
public class ChannelOaValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(max = 100, message = "小程序名称不能超100个字符")
    @ApiModelProperty(value = "公众号名称")
    private String name = "";

    @Length(max = 100, message = "原始ID不能超100个字符")
    @ApiModelProperty(value = "原始ID")
    private String primaryId = "";

    @Length(max = 100, message = "appId不能超100个字符")
    @ApiModelProperty(value = "appId")
    private String appId = "";

    @Length(max = 200, message = "appSecret不能超100个字符")
    @ApiModelProperty(value = "appSecret")
    private String appSecret = "";

    @Length(max = 300, message = "小程序码不能超300个字符")
    @ApiModelProperty(value = "二维码")
    private String qrCode = "";

    @Length(max = 300, message = "URL不能超300个字符")
    @ApiModelProperty(value = "URL")
    private String url = "";

    @Length(max = 200, message = "Token不能超200个字符")
    @ApiModelProperty(value = "Token")
    private String token = "";

    @Length(max = 43, message = "EncodingAESKey不能超43个字符")
    @ApiModelProperty(value = "EncodingAESKey")
    private String encodingAesKey = "";

    @IntegerContains(values = {1, 2, 3}, message = "encryptionType不是合法值")
    @ApiModelProperty(value = "消息加密方式")
    private Integer encryptionType = 1;

}
