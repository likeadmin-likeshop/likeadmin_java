package com.mdd.admin.validate.channel;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@ApiModel("小程序渠道参数")
public class ChannelMpValidate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Length(max = 100, message = "小程序名称不能超100个字符")
    private String name = "";

    @Length(max = 100, message = "原始ID不能超100个字符")
    private String primaryId = "";

    @Length(max = 100, message = "appId不能超100个字符")
    private String appId = "";

    @Length(max = 200, message = "appSecret不能超100个字符")
    private String appSecret = "";

    @Length(max = 300, message = "小程序码不能超300个字符")
    private String qrCode = "";

}
